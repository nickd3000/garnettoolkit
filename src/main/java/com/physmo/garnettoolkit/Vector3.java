package com.physmo.garnettoolkit;

/**
 * NOTE: Typically methods will not return a new vector.
 */
public class Vector3 {

    static final Vector3 ZERO_VECTOR = new Vector3(0, 0, 0);
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

    public Vector3 set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3 set(Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        return this;
    }


    public Vector3 getDirectionTo(Vector3 other) {
        Vector3 v = new Vector3(this);
        v.sub(other);
        v.normalise();
        return v;
    }


    public Vector3 translate(Vector3 other) {
        x += other.x;
        y += other.y;
        z += other.z;
        return this;
    }

    /**
     * Add a vector multiplied by scale
     * @param other The vector you want to scale and add to the subject
     * @param scale The scale value
     * @return self
     */
    public Vector3 addScaled(Vector3 other, double scale) {
        x += other.x * scale;
        y += other.y * scale;
        z += other.z * scale;
        return this;
    }

    public Vector3 scale(double v) {
        x *= v;
        y *= v;
        z *= v;
        return this;
    }

    public Vector3 sub(Vector3 other) {
        x -= other.x;
        y -= other.y;
        z -= other.z;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[x:%.2f, y:%.2f, z:%.2f]", x, y, z);
    }

    public Vector3 normalise() {
        double l = Math.sqrt((x * x) + (y * y) + (z * z));
        this.x /= l;
        this.y /= l;
        this.z /= l;
        return this;
    }

    /**
     * Return the distance from this point vector to another point vector
     *
     * @param other The other position
     * @return Distance between two vectors.
     */
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

    public double length() {
        return Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public void setFromAngle(double angle, double magnitude) {
        this.x = Math.cos(angle) * magnitude;
        this.y = Math.sin(angle) * magnitude;
    }
}
