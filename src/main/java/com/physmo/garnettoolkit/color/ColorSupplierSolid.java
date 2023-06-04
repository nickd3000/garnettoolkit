package com.physmo.garnettoolkit.color;

public class ColorSupplierSolid implements ColorSupplier {
    int color;

    public ColorSupplierSolid(int color) {
        this.color = color;
    }

    @Override
    public int getColor(double t) {
        return color;
    }
}
