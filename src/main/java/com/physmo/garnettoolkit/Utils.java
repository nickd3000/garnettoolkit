package com.physmo.garnettoolkit;

public class Utils {

    public static float lerp(float v1, float v2, float pos) {
        float span = v2 - v1;
        return (v1 + span * pos);
    }

    public static double lerp(double v1, double v2, double pos) {
        double span = v2 - v1;
        return (v1 + span * pos);
    }

}
