import com.physmo.garnettoolkit.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import support.Monster;
import support.MoveRightComponent;
import support.SoundEngine;

public class ContextTest {

    @Test
    public void basicTest() {
        Context context = new Context();

        Monster monster = new Monster("");
        MoveRightComponent moveRight = new MoveRightComponent();
        monster.addComponent(moveRight);

        context.add(monster);

        context.init();

        context.tick(1);
    }

    @Test
    public void testGetObjectByType() {
        Context context = new Context();
        context.add(new SoundEngine("sound engine"));
        context.init();
        context.tick(1);

        SoundEngine soundEngine = context.getObjectByType(SoundEngine.class);

        Assertions.assertNotNull(soundEngine);
        Assertions.assertTrue(soundEngine instanceof SoundEngine);

        Assertions.assertEquals(1, soundEngine.getTickCount());
    }
}
