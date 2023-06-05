package com.physmo.garnettoolkit.particle;


import com.physmo.garnettoolkit.Vector3;
import com.physmo.garnettoolkit.color.ColorSupplier;
import com.physmo.garnettoolkit.color.ColorSupplierLinear;
import com.physmo.garnettoolkit.color.ColorUtils;
import com.physmo.garnettoolkit.curve.Curve;

public class Particle {
    public Vector3 position = new Vector3();
    public double lifeTime;
    public double age;
    public ColorSupplier colorSupplier = new ColorSupplierLinear(ColorUtils.YELLOW, ColorUtils.asRGBA(1, 0, 0, 0));
    Vector3 direction = new Vector3();
    double speed = 5;
    Curve speedCurve;
    Vector3 force = new Vector3();
    boolean active = false;

    public void tick(double delta) {
        double pAge = age / lifeTime;
        double _speed = speed * delta * speedCurve.value(pAge);

        position.x += direction.x * _speed;
        position.y += direction.y * _speed;
        position.z += direction.z * _speed;

        age += delta;
        if (age > lifeTime) active = false;
    }

}
