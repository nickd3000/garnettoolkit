package com.physmo.gametoolkit;

public abstract class Component {
    protected GameObject parent;

    abstract public void tick(double t);

    abstract public void init();

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
