package com.physmo.gametoolkit;

import java.util.ArrayList;
import java.util.List;

public class Context {

    List<GameObject> gameObjects = new ArrayList<>();

    public void add(GameObject gameObject) {
        gameObject.injectContext(this);
        gameObjects.add(gameObject);
    }

    public <T> T getObjectByType(Class<T> clazz) {
        for (Object gameObject : gameObjects) {
            if (gameObject.getClass() == clazz) return (T) gameObject;
        }
        return null;
    }

    public void init() {
        for (GameObject gameObject : gameObjects) {
            gameObject._init();
        }
    }

    public void tick(double t) {
        for (GameObject gameObject : gameObjects) {
            gameObject._tick(t);
        }
    }
}
