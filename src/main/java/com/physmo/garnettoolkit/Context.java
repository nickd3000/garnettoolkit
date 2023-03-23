package com.physmo.garnettoolkit;

import java.util.ArrayList;
import java.util.List;

/**
 * Managing class for objects with special handling for GameObjects.
 * <p>
 * Newly added GameObjects will be initialised just before they are ticked for the first time.
 * GameObjects added during the tick cycle will be available on the next tick cycle.
 */
public class Context {

    private final List<Object> newObjects = new ArrayList<>();
    private final List<Object> uninitialisedObjects = new ArrayList<>();
    private List<Object> objects = new ArrayList<>();
    private boolean duringTick = false;

    public void add(Object object) {
        if (object instanceof GameObject) {
            ((GameObject) object).injectContext(this);
            //((GameObject) object)._init();
        }
        if (duringTick) {
            newObjects.add(object);
        } else {
            objects.add(object);
        }
        uninitialisedObjects.add(object);
    }

    public <T> T getObjectByType(Class<T> clazz) {
        for (Object object : objects) {
            if (object.getClass() == clazz) return (T) object;
        }

        throw new RuntimeException("Context: object not found: " + clazz.getCanonicalName());

    }

    /**
     * Search all game objects for a specific component.
     * NOTE: Only use this if you are sure there is only one game object with a given component.
     * (Or happy to get the first instance found)
     *
     * @param clazz class type of components to return
     * @param <T>
     * @return the component matching the passed in class type
     */
    public <T> T getComponent(Class<T> clazz) {
        for (Object object : objects) {
            if (!(object instanceof GameObject)) continue;
            T component = ((GameObject) object).getComponent(clazz);
            if (component != null) return component;
        }
        return null;
    }

    public List<GameObject> getObjectsByTag(String tag) {
        List<GameObject> list = new ArrayList<>();

        for (Object object : objects) {
            if (object instanceof GameObject) {
                if (((GameObject) object).getTags().contains(tag)) {
                    list.add((GameObject) object);
                }
            }
        }
        return list;
    }

    public void init() {
        addNewObjects();
        initialiseNewObjects();
    }

    private void addNewObjects() {
        if (newObjects.size() == 0) return;
        for (Object object : newObjects) {
            objects.add(object);
            if (object instanceof GameObject) {
                ((GameObject) object)._init();
            }
        }
        newObjects.clear();
    }

    private void initialiseNewObjects() {
        if (uninitialisedObjects.size() == 0) return;
        for (Object object : uninitialisedObjects) {
            if (object instanceof GameObject) {
                ((GameObject) object)._init();
            }
        }
        uninitialisedObjects.clear();
    }

    public void tick(double t) {
        addNewObjects();
        initialiseNewObjects();

        duringTick = true;
        for (Object object : objects) {
            if (object instanceof GameObject) {
                if (!((GameObject) object).isActive()) continue;
                ((GameObject) object)._tick(t);
            }
        }
        duringTick = false;
        processDestruction();
    }

    private void processDestruction() {
        int destructionCount = 0;
        for (Object object : objects) {
            if (object instanceof GameObject) {
                if (((GameObject) object).isDestroy()) destructionCount++;
            }
        }
        if (destructionCount == 0) return;

        List<Object> keep = new ArrayList<>();

        for (Object object : objects) {
            if (object instanceof GameObject) {
                if (!((GameObject) object).isDestroy()) {
                    keep.add(object);
                }
            } else {
                keep.add(object);
            }
        }

        objects = keep;
    }

    public void draw() {
        for (Object object : objects) {
            if (object instanceof GameObject) {
                ((GameObject) object)._draw();
            }
        }
    }

    /**
     * Erase all objects contained within this context.
     */
    public void reset() {
        objects.clear();
    }

    public int getObjectCount() {
        return objects.size();
    }
}
