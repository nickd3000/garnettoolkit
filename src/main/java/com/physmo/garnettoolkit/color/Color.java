package com.physmo.garnettoolkit.color;


public class Color {
    public static Color BLACK, WHITE, RED, GREEN, BLUE, YELLOW;
    public static Color SUNSET_BLUE, SUNSET_GREEN, SUNSET_YELLOW, SUNSET_ORANGE, SUNSET_RED;

    static {
        BLACK = new Color(0, 0, 0, 1);
        WHITE = new Color(1, 1, 1, 1);
        RED = new Color(1, 0, 0, 1);
        GREEN = new Color(0, 1, 0, 1);
        BLUE = new Color(0, 0, 1, 1);
        YELLOW = new Color(1, 1, 0, 1);
        //
        SUNSET_BLUE = new Color(0x264653ff);
        SUNSET_GREEN = new Color(0x2A9D8Fff);
        SUNSET_YELLOW = new Color(0xE9C46Aff);
        SUNSET_ORANGE = new Color(0xF4A261ff);
        SUNSET_RED = new Color(0xE76F51ff);
    }

    public float r, g, b, a;

    public Color(int rgba) {
        float[] f = rgbToFloat(rgba);
        setValues(f[0], f[1], f[2], f[3]);
    }

    public Color() {
        setValues(1, 1, 1, 1);
    }

    public Color(float r, float g, float b) {
        setValues(r, g, b, 1);
    }

    public Color(float r, float g, float b, float a) {
        setValues(r, g, b, a);
    }

    private static float clampFloat(float f) {
        if (f < 0) return 0;
        if (f > 1) return 1;
        return f;
    }

    public static float[] rgbToFloat(int rgba) {
        float[] f = new float[4];
        f[0] = ((rgba >> 24) & 0xff) / 255f;
        f[1] = ((rgba >> 16) & 0xff) / 255f;
        f[2] = ((rgba >> 8) & 0xff) / 255f;
        f[3] = ((rgba) & 0xff) / 255f;
        return f;
    }

    public void setValues(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float[] toArray() {
        float[] arr = new float[]{r, g, b, a};
        return arr;
    }

    public int toInt() {
        int rgb = 0;

        rgb += ((int) (clampFloat(r) * 255f)) << 24;
        rgb += ((int) (clampFloat(g) * 255f)) << 16;
        rgb += ((int) (clampFloat(b) * 255f)) << 8;
        rgb += ((int) (clampFloat(a) * 255f));

        return rgb;
    }
}
