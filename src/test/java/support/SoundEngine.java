package support;

import com.physmo.gametoolkit.GameObject;

public class SoundEngine extends GameObject {

    int tickCount = 0;

    public int getTickCount() {
        return tickCount;
    }

    @Override
    public void init() {
        System.out.println("SoundEngine init");
    }

    @Override
    public void tick(double t) {
        System.out.println("SoundEngine tick");
        tickCount++;
    }
}
