package com.physmo.garnettoolkit.simplecollision;

import com.physmo.garnettoolkit.GameObject;

@FunctionalInterface
public interface CollisionCallback {
    void go(GameObject target);
}
