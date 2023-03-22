package com.physmo.garnettoolkit;

/**
 *
 */
public abstract class Component {
    protected GameObject parent;

    abstract public void tick(double t);

    abstract public void init();

    abstract public void draw();

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
