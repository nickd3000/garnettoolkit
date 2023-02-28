package com.physmo.garnettoolkit;

// Position needs to be able to handle locations and floors as well as inside/outside.
public class PointInt {

    public int x;
    public int y;
    public int z;

    public PointInt() {
        x = 0;
        y = 0;
        z = 0;
    }

    public PointInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PointInt(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PointInt(PointInt p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public String toString() {
        return "[" + x + "," + y + "," + z + "]";
    }
}
