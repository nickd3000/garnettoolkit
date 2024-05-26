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

    // Return array containing overlap amounts for each side.
    // 0 == top, 1 = right etc


    public void overlap(Rect other, double[] overlap) {

        double hh = this.h / 2 + other.h / 2;
        double ww = this.w / 2 + other.w / 2;

        double dy = (other.y + (other.h / 2)) - (this.y + (this.h / 2));
        double dx = (other.x + (other.w / 2)) - (this.x + (this.w / 2));

        if (Math.abs(dx) >= ww) return;
        if (Math.abs(dy) >= hh) return;

        double up = 0, down = 0, left = 0, right = 0;

        if (this.y + (this.h / 2) > other.y + (other.h / 2)) {
            up = hh + dy;
        } else {
            down = hh - dy;
        }

        if (this.x + (this.w / 2) < other.x + (other.w / 2)) {
            right = ww - dx;
        } else {
            left = ww + dx;
        }

        overlap[0] = up;
        overlap[1] = right;
        overlap[2] = down;
        overlap[3] = left;

    }
}
