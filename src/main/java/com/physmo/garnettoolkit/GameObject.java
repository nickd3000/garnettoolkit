package com.physmo.garnettoolkit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    An object that can be added to a Context and ticked.
    Contains a list of components and properties.
 */
public class GameObject {

    protected final List<Component> components = new ArrayList<>();
    private final PointInt position = new PointInt();
    protected Context context;
    protected GameProperties properties = new GameProperties();
    protected Vector3 transform = new Vector3(0, 0, 0);
    String name;
    Set<String> tags = new HashSet<>();
    boolean active = true;
    boolean visible = true;
    boolean destroy = false;

    public GameObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDestroy() {
        return destroy;
    }

    /**
     * Call to request this object is destroyed by the context update loop.
     */
    public void destroy() {
        this.destroy = true;
    }

    public Context getContext() {
        return context;
    }

    public Vector3 getTransform() {
        return transform;
    }

    public void setTransform(Vector3 transform) {
        this.transform = transform;
    }

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

    public GameObject addComponent(Component component) {
        component.setParent(this);
        components.add(component);
        return this;
    }

    public <T> T getComponentByType(Class<T> clazz) {
        for (Object object : components) {
            if (object.getClass() == clazz) return (T) object;
        }
        return null;
    }

    public void _init() {
        this.init();
        for (Component c : components) {
            c.init();
        }
    }

    public void init() {
    }

    public void _tick(double t) {
        this.tick(t);
        for (Component c : components) {
            c.tick(t);
        }
    }

    public void tick(double t) {
    }

    public void _draw() {
        this.draw();
        for (Component c : components) {
            c.draw();
        }
    }

    public void draw() {
    }

    public void setVisible(boolean b) {
        visible = b;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public Set<String> getTags() {
        return tags;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean b) {
        active = b;
    }
}
