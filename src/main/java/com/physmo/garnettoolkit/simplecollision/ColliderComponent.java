package com.physmo.garnettoolkit.simplecollision;

import com.physmo.garnettoolkit.Component;
import com.physmo.garnettoolkit.GameObject;
import com.physmo.garnettoolkit.Rect;

import java.util.ArrayList;
import java.util.List;

public class ColliderComponent extends Component implements Collidable {

    int ox, oy, width, height;
    Rect collisionRegion = new Rect();
    CollisionCallback callbackEnter = null;
    CollisionCallback callbackLeave = null;
    CollisionCallback callbackContinue = null;
    List<GameObject> newCollisions = new ArrayList<>();
    List<GameObject> existingCollisions = new ArrayList<>();

    public ColliderComponent() {
        setCallbackEnter(a -> {
        });
        setCallbackLeave(a -> {
        });
        setCallbackContinue(a -> {
        });
    }

    public void setCallbackEnter(CollisionCallback callback) {
        this.callbackEnter = callback;
    }

    public void setCallbackLeave(CollisionCallback callback) {
        this.callbackLeave = callback;
    }

    public void setCallbackContinue(CollisionCallback callback) {
        this.callbackContinue = callback;
    }

    @Override
    public void init() {
        ox = -6;
        oy = -6;
        width = 12;
        height = 12;

    }

    @Override
    public void tick(double t) {
        List<GameObject> keepList = new ArrayList<>();

        // First handle collisions leaving
        for (GameObject other : existingCollisions) {
            if (!newCollisions.contains(other)) {
                // call leaving handler
                callbackLeave.go(other);
            } else {
                //  keep
                keepList.add(other);
            }
        }
        existingCollisions.clear();
        existingCollisions.addAll(keepList);
        keepList.clear();


        for (GameObject other : newCollisions) {
            if (existingCollisions.contains(other)) {
                // call existing handler
                callbackContinue.go(other);
            } else {
                // call new handler
                callbackEnter.go(other);
                keepList.add(other);
            }
        }
        newCollisions.clear();
        existingCollisions.addAll(keepList);
    }

    @Override
    public void draw() {

    }

    @Override
    public Rect collisionGetRegion() {
        collisionRegion.set(parent.getTransform().x + ox, parent.getTransform().y + oy, width, height);
        return collisionRegion;
    }

    // Notified when the collision system detects an intersection
    // this class should handle whether this is new, maintaining or leaving collision
    // and call user supplied handlers
    @Override
    public void collisionCallback(CollisionPacket collisionPacket) {
        newCollisions.add(collisionPacket.targetEntity.collisionGetGameObject());
    }

    @Override
    public GameObject collisionGetGameObject() {
        return parent;
    }
}
