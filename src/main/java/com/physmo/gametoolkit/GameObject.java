package com.physmo.gametoolkit;

import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {

    protected final List<Component> components = new ArrayList<>();
    private final PointInt position = new PointInt();
    protected Context context;
    protected GameProperties properties = new GameProperties();

    public <T> T getComponent(Class<T> clazz) {
        for (Object component : components) {
            if (component.getClass() == clazz) return (T) component;
        }
        return null;
    }

    public PointInt getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    public GameProperties getProperties() {
        return properties;
    }

    public void injectContext(Context context) {
        this.context = context;
    }

    public void addComponent(Component component) {
        component.setParent(this);
        components.add(component);
    }

    public void _init() {
        this.init();
        for (Component c : components) {
            c.init();
        }
    }

    abstract public void init();
    // coords

    // tags

    public void _tick(double t) {
        this.tick(t);
        for (Component c : components) {
            c.tick(1.0f);
        }
    }

    abstract public void tick(double t);


}
