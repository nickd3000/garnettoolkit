package com.physmo.garnettoolkit;

/**
 * A scene can be thought of as a game state
 */
public abstract class Scene {

    protected Context context = new Context();
    private SceneManager sceneManager;
    private String name;
    private boolean initCalled = false;

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
        initCalled = true;
    }

    public abstract void init();

    public void _tick(double delta) {
        context.tick(delta);
        tick(delta);
    }

    public abstract void tick(double delta);

    public void _draw() {
        context.draw();
        draw();
    }

    public abstract void draw();

    public abstract void onMakeActive();

    public abstract void onMakeInactive();

    public boolean isInitCalled() {
        return initCalled;
    }
}
