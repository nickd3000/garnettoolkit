package com.physmo.garnettoolkit.simplecollision;

import com.physmo.garnettoolkit.GameObject;
import com.physmo.garnettoolkit.Rect;

public interface Collidable {
    Rect collisionGetRegion();

    void collisionCallback(CollisionPacket collisionPacket);

    GameObject collisionGetGameObject();
}
