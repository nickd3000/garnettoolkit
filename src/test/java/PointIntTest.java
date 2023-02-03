import com.physmo.gametoolkit.PointInt;
import org.junit.jupiter.api.Test;

public class PointIntTest {

    @Test
    public void testCreation() {
        PointInt p1 = new PointInt();
        PointInt p2 = new PointInt(1, 2);
        PointInt p3 = new PointInt(1, 2, 3);
        PointInt p4 = new PointInt(p3);


    }

}
