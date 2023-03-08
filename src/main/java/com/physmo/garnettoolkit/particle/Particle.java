package com.physmo.garnettoolkit.particle;


import com.physmo.garnettoolkit.Vector3;
import com.physmo.garnettoolkit.color.Color;
import com.physmo.garnettoolkit.color.ColorSupplier;
import com.physmo.garnettoolkit.color.ColorSupplierLinear;
import com.physmo.garnettoolkit.curve.Curve;

public class Particle {
    public Vector3 position = new Vector3();
    public double lifeTime;
    public double age;
    public ColorSupplier colorSupplier = new ColorSupplierLinear(Color.YELLOW, new Color(1, 0, 0, 0));
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

//        direction.x += force.x * delta;
//        direction.y += force.y * delta;
//        direction.z += force.z * delta;

//        direction.x -= direction.x * friction * delta;
//        direction.y -= direction.y * friction * delta;
//        direction.z -= direction.z * friction * delta;

        age += delta;
        if (age > lifeTime) active = false;
    }

//    public void draw(SpriteBatch sb) {
//        Sprite2D spr = Sprite2D.build(
//                (int) (position.x) - 8,
//                (int) (position.y) - 8,
//                16, 16, 16 * 3, 0, 16, 16);
//
//        float pAge = (float) (age / lifeTime);
//
//        spr.addColor(colorSupplier.getColor(pAge));
//
//        sb.add(spr);
//    }
}
