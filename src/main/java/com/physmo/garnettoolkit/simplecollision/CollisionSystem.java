package com.physmo.garnettoolkit.simplecollision;


import com.physmo.garnettoolkit.GameObject;
import com.physmo.garnettoolkit.Rect;

import java.util.ArrayList;
import java.util.List;

public class CollisionSystem extends GameObject {

    List<Collidable> collidables = new ArrayList<>();

    public CollisionSystem(String name) {
        super(name);
    }

    @Override
    public void init() {
        collidables = new ArrayList<>();
    }

    @Override
    public void tick(double t) {
        List<CollisionPacket> collisions = new ArrayList<>();

        for (Collidable c1 : collidables) {
            if (!c1.collisionGetGameObject().isActive()) continue;
            for (Collidable c2 : collidables) {
                if (!c2.collisionGetGameObject().isActive()) continue;
                if (c1 == c2) continue;

                boolean collided = testCollision(c1, c2);

                if (collided) collisions.add(new CollisionPacket(c1, c2));
            }
        }

        // Notify collider of collision.
        for (CollisionPacket collision : collisions) {
            collision.sourceEntity.collisionCallback(collision);
        }

    }

    public boolean testCollision(Collidable c1, Collidable c2) {
        Rect rect1 = c1.collisionGetRegion();
        Rect rect2 = c2.collisionGetRegion();
        if (rect1.intersect(rect2)) return true;
        return false;
    }

    public void addCollidable(Collidable collidable) {
        System.out.println("adding collidable");
        collidables.add(collidable);
    }

}
