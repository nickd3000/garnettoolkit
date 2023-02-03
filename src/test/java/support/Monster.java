package support;

import com.physmo.gametoolkit.GameObject;

public class Monster extends GameObject {
    @Override
    public void init() {
        System.out.println("Monster init");
    }

    @Override
    public void tick(double t) {
        System.out.println("Monster ticked");
    }
}
