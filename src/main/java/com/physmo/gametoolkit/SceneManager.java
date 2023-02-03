package com.physmo.gametoolkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SceneManager {

    private Scene activeScene;
    private Scene targetScene;
    private List<Scene> activeSubScene;

    private List<String> subScenePushRequests;
    private List<String> subScenePopRequests;

    private Map<String, Scene> scenes;

    public SceneManager() {
        scenes = new HashMap<>();
        activeSubScene = new ArrayList<>();
        subScenePushRequests = new ArrayList<>();
        subScenePopRequests = new ArrayList<>();
    }

    // called after gamestate ticks so safe to add/remove/change states here.
    public void update() {

        handleStateChange();
        handleSubscenePop();
        handleSubstatePush();
    }

    public void handleStateChange() {
        if (targetScene != null) {
            targetScene._init();
            activeScene = targetScene;
            targetScene = null;
        }
    }

    public void handleSubstatePush() {
        for (String substateName : subScenePushRequests) {

            Scene state = scenes.get(substateName);
            state._init();
            if (state == null) {
                System.out.println("Substate not found: " + substateName);
                return;
            }
            activeSubScene.add(state);
        }

        subScenePushRequests.clear();
    }

    public void handleSubscenePop() {
        List<Scene> newActiveSubStates = new ArrayList<>();
        boolean substateRemoved = false;
        for (Scene activeSubState : activeSubScene) {
            boolean skip = false;
            for (String subStatePopRequest : subScenePopRequests) {
                if (activeSubState.getName().equalsIgnoreCase(subStatePopRequest)) {
                    skip = true;
                    substateRemoved = true;
                    System.out.println("remove");
                }
            }

            if (!skip) {
                newActiveSubStates.add(activeSubState);
            }
        }
        subScenePopRequests.clear();
        activeSubScene = newActiveSubStates;
        if (substateRemoved) {
            //garnet.getInput().postStateChangeTask();
        }
    }

    public void tick(double delta) {
        // Only tick main state if there are no active sub states.
        if (activeSubScene.size() == 0) activeScene._tick(delta);

        // Find the last active substate and tick it.
        if (activeSubScene.size() > 0)
            activeSubScene.get(activeSubScene.size() - 1)._tick(delta);
    }

    public void draw() {
        if (activeScene != null) activeScene._draw();
        for (Scene activeSubState : activeSubScene) {
            activeSubState._draw();
        }
    }

    public Optional<Scene> getActiveScene() {
        return Optional.ofNullable(activeScene);
    }

    public void addScene(Scene scene) {
        if (targetScene == null) targetScene = scene;
        scenes.put(scene.getName(), scene);
        scene.setSceneManager(this);
        if (activeScene == null) activeScene = scene;
    }

    public void switchActiveState(String name) {
        for (String state : scenes.keySet()) {
            if (state.equalsIgnoreCase(name)) {
                targetScene = scenes.get(name);
            }
        }
        // TODO: exception if not found?
    }

    public void pushSubScene(String name) {
        subScenePushRequests.add(name);


    }

    public void popSubScene(String name) {
        subScenePopRequests.add(name);
    }
}
