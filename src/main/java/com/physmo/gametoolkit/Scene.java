package com.physmo.gametoolkit;

public abstract class Scene {

    SceneManager sceneManager;
    String name;

    public Scene(String name) {
        this.name = name;
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public void setSceneManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    public String getName() {
        return name;
    }

    public void _init() {
        init();
    }

    public abstract void init();

    public void _tick(double delta) {
        tick(delta);
    }

    public abstract void tick(double delta);

    public void _draw() {
        draw();
    }

    public abstract void draw();


}
