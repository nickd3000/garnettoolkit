import com.physmo.garnettoolkit.GameProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GamePropertiesTest {

    @Test
    public void testBasicPropertyUse() {
        GameProperties gameProperties = new GameProperties();

        gameProperties.addProperty("property1", Integer.valueOf(123));

        Integer property1 = (Integer) gameProperties.getProperty("property1");

        Assertions.assertTrue(gameProperties.hasProperty("property1"));
        Assertions.assertFalse(gameProperties.hasProperty("xxxxx"));

        Assertions.assertEquals(123, property1);
    }
}
