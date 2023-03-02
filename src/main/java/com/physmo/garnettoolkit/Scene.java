package com.physmo.garnettoolkit;

/**
 * A scene can be thought of as a game state
 * Each scene has a Context that contains game objects
 * All game objects in the context get ticked and drawn.
 */
public abstract class Scene {

    protected Context context = new Context();
    private String name;
    private boolean initCalled = false;

    public Scene(String name) {
        this.name = name;
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
