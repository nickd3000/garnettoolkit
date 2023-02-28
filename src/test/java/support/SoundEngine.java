package support;

import com.physmo.garnettoolkit.GameObject;

public class SoundEngine extends GameObject {

    int tickCount = 0;

    public SoundEngine(String name) {
        super(name);
    }

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
