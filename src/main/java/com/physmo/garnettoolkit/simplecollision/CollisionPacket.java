package com.physmo.garnettoolkit.simplecollision;


public class CollisionPacket {

    public Collidable sourceEntity;
    public Collidable targetEntity;

    public CollisionPacket(Collidable sourceEntity, Collidable targetEntity) {
        this.sourceEntity = sourceEntity;
        this.targetEntity = targetEntity;
    }
}
