package com.physmo.garnettoolkit;

public class Vector3 {

    static Vector3 ZERO_VECTOR = new Vector3(0, 0, 0);
    public double x, y, z;

    public Vector3() {
        this(ZERO_VECTOR);
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public static Vector3 generateRandomRadial2D(double magnitude) {
        double angle = (Math.random() * Math.PI * 2);
        return new Vector3(Math.sin(angle) * magnitude, Math.cos(angle) * magnitude, 0);
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public Vector3 add(Vector3 other) {
        Vector3 newVec = new Vector3(this);
        newVec.x += other.x;
        newVec.y += other.y;
        newVec.z += other.z;
        return newVec;
    }

    public Vector3 getDirectionTo(Vector3 other) {
        Vector3 v = this.sub(other);
        v.normalise();
        return v;
    }

    // 'i' suffix means do the operation "in-place", i.e. update the object.
    public void addi(Vector3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
    }

    public Vector3 scale(double v) {
        Vector3 newVec = new Vector3(this);
        newVec.x *= v;
        newVec.y *= v;
        newVec.z *= v;
        return newVec;
    }

    public Vector3 sub(Vector3 other) {
        Vector3 newVec = new Vector3(this);
        newVec.x -= other.x;
        newVec.y -= other.y;
        newVec.z -= other.z;
        return newVec;
    }

    @Override
    public String toString() {
        return String.format("Vector3{x=%.2f, y=%.2f, z=%.2f}", x, y, z);
    }

    public void normalise() {
        double l = Math.sqrt((x * x) + (y * y) + (z * z));
        this.x /= l;
        this.y /= l;
        this.z /= l;
    }

    public double distance(Vector3 other) {
        double x = this.x - other.x;
        double y = this.y - other.y;
        double z = this.z - other.z;
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public double distance(double x, double y) {
        double xx = this.x - x;
        double yy = this.y - y;
        return Math.sqrt((xx * xx) + (yy * yy));
    }
}
