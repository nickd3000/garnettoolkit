package com.physmo.garnettoolkit.simplecollision;


import com.physmo.garnettoolkit.GameObject;
import com.physmo.garnettoolkit.Rect;

import java.util.ArrayList;
import java.util.List;

public class CollisionSystem extends GameObject {

    List<Collidable> collidables = new ArrayList<>();
    List<Collidable> collidablesPendingRemoval = new ArrayList<>();
    CollisionDrawingCallback collisionDrawingCallback = null;

    public CollisionSystem(String name) {
        super(name);
    }

    public void setCollisionDrawingCallback(CollisionDrawingCallback collisionDrawingCallback) {
        this.collisionDrawingCallback = collisionDrawingCallback;
    }

    @Override
    public void init() {
        collidables = new ArrayList<>();
    }

    @Override
    public void tick(double t) {
        removePendingCollidables();

        List<CollisionPacket> collisions = new ArrayList<>();

        List<Collidable> activeCollidables = getListOfActiveCollidables();


        for (Collidable c1 : activeCollidables) {
            for (Collidable c2 : activeCollidables) {
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

    private List<Collidable> getListOfActiveCollidables() {
        List<Collidable> activeCollidables = new ArrayList<>();
        for (Collidable collidable : collidables) {
            if (!collidable.collisionGetGameObject().isActive()) continue;
            activeCollidables.add(collidable);
        }
        return activeCollidables;
    }

    public boolean testCollision(Collidable c1, Collidable c2) {
        Rect rect1 = c1.collisionGetRegion();
        Rect rect2 = c2.collisionGetRegion();
        return rect1.intersect(rect2);
    }

    private void removePendingCollidables() {
        if (collidablesPendingRemoval.size() == 0) return;

        List<Collidable> keepList = new ArrayList<>();

        for (Collidable collidable : collidables) {
            if (collidablesPendingRemoval.contains(collidable)) continue;
            keepList.add(collidable);
        }

        collidablesPendingRemoval.clear();
        collidables = keepList;
    }

    @Override
    public void draw() {

        if (collisionDrawingCallback == null) return;
        List<Collidable> activeCollidables = getListOfActiveCollidables();
        for (Collidable collidable : activeCollidables) {
            collisionDrawingCallback.draw(collidable);
        }

    }

    public void addCollidable(Collidable collidable) {
        collidables.add(collidable);
    }

    public int getSize() {
        return collidables.size();
    }

    public void removeCollidable(Collidable collidable) {
        collidablesPendingRemoval.add(collidable);
    }
}
