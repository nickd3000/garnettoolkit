package com.physmo.garnettoolkit.color;

import static com.physmo.garnettoolkit.Utils.lerp;

/**
 * A color supplier used to blend two colors.
 */
public class ColorSupplierLinear implements ColorSupplier {
    Color c1;
    Color c2;

    public ColorSupplierLinear(Color c1, Color c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public Color getColor(double t) {
        return new Color(lerp(c1.r, c2.r, (float) t),
                lerp(c1.g, c2.g, (float) t),
                lerp(c1.b, c2.b, (float) t),
                lerp(c1.a, c2.a, (float) t));
    }


}
