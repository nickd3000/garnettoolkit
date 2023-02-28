package com.physmo.garnettoolkit.simplecollision;

public interface Collider {
    boolean testCollision(Collider other);

    void render();
}
