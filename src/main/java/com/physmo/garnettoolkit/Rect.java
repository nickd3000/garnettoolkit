package com.physmo.garnettoolkit;

public class Rect {
    public double x;
    public double y;
    public double w;
    public double h;

    public Rect() {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
    }

    public Rect(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void set(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean intersect(Rect other) {
        if (x + w < other.x) return false;
        if (y + h < other.y) return false;
        if (x > other.x + other.w) return false;
        return !(y > other.y + other.h);
    }
}
