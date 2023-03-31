package com.physmo.garnettoolkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SceneManager {

    private static final List<String> subScenePushRequests;
    private static final List<String> subScenePopRequests;
    private static final Map<String, Scene> scenes;
    private static Context sharedContext = null;
    private static Scene activeScene;
    private static Scene targetScene;
    private static List<Scene> activeSubScenes;

    static {
        scenes = new HashMap<>();
        activeSubScenes = new ArrayList<>();
        subScenePushRequests = new ArrayList<>();
        subScenePopRequests = new ArrayList<>();
        sharedContext = new Context();
    }

    private SceneManager() {

    }

    public static Context getSharedContext() {
        if (sharedContext == null) {
            sharedContext = new Context();
        }

        return sharedContext;
    }

    public static void tick(double delta) {
        update();

        sharedContext.tick(delta);

        // Only tick main scene if there are no active sub scenes.
        if (activeSubScenes.size() == 0 && activeScene.isInitCalled()) activeScene._tick(delta);

        // Find the last active subscene and tick it.
        if (activeSubScenes.size() > 0)
            activeSubScenes.get(activeSubScenes.size() - 1)._tick(delta);

    }

    // called after gamestate ticks so safe to add/remove/change scenes here.
    public static void update() {

        handleSceneChange();
        handleSubscenePop();
        handleSubscenePush();
    }

    public static void handleSceneChange() {
        if (targetScene != null) {
            targetScene._init();

            if (activeScene != targetScene) {
                targetScene.onMakeActive();
                if (activeScene != null) activeScene.onMakeInactive();
            }

            activeScene = targetScene;
            targetScene = null;
        }
    }

    public static void handleSubscenePush() {
        if (subScenePushRequests.isEmpty()) return;

        for (String subsceneName : subScenePushRequests) {

            Scene scene = scenes.get(subsceneName);
            //scene._init();
            if (scene == null) {
                System.out.println("Subscene not found: " + subsceneName);
                return;
            }
            scene._init();
            scene.onMakeActive();
            activeSubScenes.add(scene);
        }

        subScenePushRequests.clear();
    }

    public static void handleSubscenePop() {
        if (subScenePopRequests.isEmpty()) return;

        List<Scene> newActiveSubScenes = new ArrayList<>();

        for (Scene activeSubScene : activeSubScenes) {
            boolean skip = false;
            for (String subScenePopRequest : subScenePopRequests) {
                if (activeSubScene.getName().equalsIgnoreCase(subScenePopRequest)) {
                    skip = true;
                    System.out.println("remove");
                    activeSubScene.onMakeInactive();
                }
            }

            if (!skip) {
                newActiveSubScenes.add(activeSubScene);
            }
        }
        subScenePopRequests.clear();
        activeSubScenes = newActiveSubScenes;

    }

    public static void draw() {
        sharedContext.draw();

        if (activeScene != null && activeScene.isInitCalled()) {
            activeScene._draw();
        }

        for (Scene activeSubScene : activeSubScenes) {
            activeSubScene._draw();
        }
    }

    public static Optional<Scene> getActiveScene() {
        return Optional.ofNullable(activeScene);
    }

    /**
     * Request active scene change.
     *
     * @param name
     */
    public static void setActiveScene(String name) {
        throwExceptionIfSceneNameNotFound(name);

        for (String scene : scenes.keySet()) {
            if (scene.equalsIgnoreCase(name)) {
                targetScene = scenes.get(name);
                System.out.println("Switching scene to " + name);
                return;
            }
        }

        System.out.println("Scene name not found: " + name);
    }

    public static void throwExceptionIfSceneNameNotFound(String name) {
        for (String str : scenes.keySet()) {
            if (str.equalsIgnoreCase(name)) return;
        }
        throw new RuntimeException("Unknown Scene name " + name + ".");
    }

    /**
     * Add a scene but do not make active.
     *
     * @param scene
     */
    public static void addScene(Scene scene) {
        System.out.println("Adding scene");
        if (activeScene == null) targetScene = scene;
        scenes.put(scene.getName(), scene);
        //scene.setSceneManager(this);
    }

    /**
     * Push an already added scene to the active subscene list.
     *
     * @param name
     */
    public static void pushSubScene(String name) {
        subScenePushRequests.add(name);
    }

    public static void popSubScene(String name) {
        subScenePopRequests.add(name);

        throwExceptionIfSceneNameNotFound(name);
    }
}
