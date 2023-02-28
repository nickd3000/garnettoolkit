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

/*
    List<CollisionPacket> collisions;

    public void tick(List<GameObject> gameObjectList) {
        collisions = new ArrayList<>();
        //List<Entity> entityList = entityGroup.getAll();

        for (GameObject entityA : gameObjectList) {
            if (entityA.isActive() == false) continue;
            List<Collider> collidersA = entityA.getColliders();
            if (collidersA == null || collidersA.size() == 0) continue;
            for (GameObject entityB : gameObjectList) {
                if (entityB.isActive() == false) continue;
                if (entityA == entityB) continue;
                List<Collider> collidersB = entityB.getColliders();
                if (collidersB == null || collidersB.size() == 0) continue;
                processColliders(entityA, entityB);
            }
        }

        for (CollisionPacket collision : collisions) {
            sendCollisionNotifications(collision);
        }

    }

    public void processColliders(Entity entityA, Entity entityB) {
        List<Collider> collidersA = entityA.getColliders();
        List<Collider> collidersB = entityB.getColliders();
        for (Collider colA : collidersA) {
            for (Collider colB : collidersB) {
                boolean colliding = colA.testCollision(colB);
                if (colliding) {
                    collisions.add(new CollisionPacket(entityA, entityB));
                }
            }
        }

    }

    public void sendCollisionNotifications(CollisionPacket collisionPacket) {

        for (Component component : collisionPacket.sourceEntity.getComponents()) {
            component.onCollisionStart(collisionPacket);
        }
        for (Component component : collisionPacket.targetEntity.getComponents()) {
            component.onCollisionStart(collisionPacket);
        }
    }

    public void debugRender(EntityGroup entityGroup) {
//        glColor3f(0.2f, 1.0f, 0.2f);
//        glDisable(GL_TEXTURE_2D);
//
//        for (Entity entity : entityGroup.getAll()) {
//            for (Collider collider : entity.getColliders()) {
//                collider.render();
//            }
//        }
    }
    */

}
