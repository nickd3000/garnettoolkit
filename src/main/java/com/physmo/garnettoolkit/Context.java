package com.physmo.garnettoolkit;

import java.util.ArrayList;
import java.util.List;

/*
    Managing class for objects with special handling for GameObjects.
 */
public class Context {
    // TODO: we need to handle lists of objects of the same type.

    List<Object> objects = new ArrayList<>();

    public void add(Object object) {
        if (object instanceof GameObject) {
            ((GameObject) object).injectContext(this);
            //((GameObject) object)._init();
        }
        objects.add(object);
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
     * @param clazz
     * @param <T>
     * @return
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
        for (Object object : objects) {
            if (object instanceof GameObject) {
                ((GameObject) object)._init();
            }
        }
    }

    public void tick(double t) {
        for (Object object : objects) {
            if (object instanceof GameObject) {
                if (!((GameObject) object).isActive()) continue;
                ((GameObject) object)._tick(t);
            }
        }
    }

    public void draw() {
        for (Object object : objects) {
            if (object instanceof GameObject) {
                ((GameObject) object)._draw();
            }
        }
    }
}
