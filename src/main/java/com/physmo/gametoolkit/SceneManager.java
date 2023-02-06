package com.physmo.gametoolkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SceneManager {

    private Scene activeScene;
    private Scene targetScene;
    private List<Scene> activeSubScenes;

    private List<String> subScenePushRequests;
    private List<String> subScenePopRequests;

    private Map<String, Scene> scenes;
    private Context sharedContext;

    public SceneManager() {
        scenes = new HashMap<>();
        activeSubScenes = new ArrayList<>();
        subScenePushRequests = new ArrayList<>();
        subScenePopRequests = new ArrayList<>();
        sharedContext = new Context();
    }

    public Context getSharedContext() {
        return sharedContext;
    }

    public void tick(double delta) {
        update();

        sharedContext.tick(delta);

        // Only tick main scene if there are no active sub scenes.
        if (activeSubScenes.size() == 0 && activeScene.isInitCalled()) activeScene._tick(delta);

        // Find the last active subscene and tick it.
        if (activeSubScenes.size() > 0)
            activeSubScenes.get(activeSubScenes.size() - 1)._tick(delta);


    }

    // called after gamestate ticks so safe to add/remove/change states here.
    public void update() {

        handleSceneChange();
        handleSubscenePop();
        handleSubscenePush();
    }

    public void handleSceneChange() {
        if (targetScene != null) {
            //targetScene._init();

            if (activeScene != targetScene) {
                targetScene.onMakeActive();
                if (activeScene != null) activeScene.onMakeInactive();
            }

            activeScene = targetScene;
            targetScene = null;
        }
    }

    public void handleSubscenePush() {
        if (subScenePushRequests.isEmpty()) return;

        for (String subsceneName : subScenePushRequests) {

            Scene scene = scenes.get(subsceneName);
            //scene._init();
            if (scene == null) {
                System.out.println("Subscene not found: " + subsceneName);
                return;
            }
            scene.onMakeActive();
            activeSubScenes.add(scene);
        }

        subScenePushRequests.clear();
    }

    public void handleSubscenePop() {
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

    public void draw() {
        if (activeScene != null && activeScene.isInitCalled()) activeScene._draw();
        for (Scene activeSubScene : activeSubScenes) {
            activeSubScene._draw();
        }
    }

    public Optional<Scene> getActiveScene() {
        return Optional.ofNullable(activeScene);
    }

    /**
     * Request active scene change.
     *
     * @param name
     */
    public void setActiveScene(String name) {
        exceptionIfSceneNameNotFound(name);

        for (String scene : scenes.keySet()) {
            if (scene.equalsIgnoreCase(name)) {
                targetScene = scenes.get(name);
            }
        }

    }

    public void exceptionIfSceneNameNotFound(String name) {
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
    public void addScene(Scene scene) {
        if (activeScene == null) targetScene = scene;
        scenes.put(scene.getName(), scene);
        scene.setSceneManager(this);
    }

    /**
     * Push an already added scene to the active subscene list.
     *
     * @param name
     */
    public void pushSubScene(String name) {
        subScenePushRequests.add(name);
    }

    public void popSubScene(String name) {
        subScenePopRequests.add(name);

        exceptionIfSceneNameNotFound(name);
    }
}
