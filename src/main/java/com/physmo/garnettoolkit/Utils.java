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


    public static double remapRange(double value, double inMin, double inMax, double outMin, double outMax) {
        if (outMax - outMin == 0) return 0;
        value = (value - inMin) / ((inMax - inMin) / (outMax - outMin));
        return value + outMin;
    }

    public static float remapRange(float value, float inMin, float inMax, float outMin, float outMax) {
        if (outMax - outMin == 0) return 0;
        value = (value - inMin) / ((inMax - inMin) / (outMax - outMin));
        return value + outMin;
    }
}
