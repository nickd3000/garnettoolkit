package com.physmo.gametoolkit;

import java.util.ArrayList;
import java.util.List;

/*
    Managing class for objects with special handling for GameObjects.
 */
public class Context {
    // TODO: we need to handle lists of objects of the same type.
    // TODO: add tag handling

    List<Object> objects = new ArrayList<>();

    public void add(Object object) {
        if (object instanceof GameObject) {
            ((GameObject)object).injectContext(this);
        }
        objects.add(object);
    }

    public <T> T getObjectByType(Class<T> clazz) {
        for (Object object : objects) {
            if (object.getClass() == clazz) return (T) object;
        }
        return null;
    }

    public void init() {
        for (Object object : objects) {
            if (object instanceof GameObject) {
                ((GameObject)object)._init();
            }
        }
    }

    public void tick(double t) {
        for (Object object : objects) {
            if (object instanceof GameObject) {
                ((GameObject)object)._tick(t);
            }
        }
    }
}
