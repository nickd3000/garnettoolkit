import com.physmo.garnettoolkit.stateMachine.StateMachine;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class StateMachineTest {

    int stateCounter = 0;

    @Test
    public void t1() {
        List<String> debugStrings = new ArrayList<>();

        StateMachine stateMachine = new StateMachine();


        stateMachine.addState("state1", t -> {
            stateCounter++;

            debugStrings.add("a");
            if (stateCounter > 2) stateMachine.changeState("state2");
        });
        stateMachine.addState("state2", t -> {
            debugStrings.add("b");
        });

        for (int i = 0; i < 5; i++) {
            stateMachine.tick(1);
        }

        debugStrings.forEach(s -> {
            System.out.println(s);
        });

        Assertions.assertEquals(debugStrings.get(0), "a");
        Assertions.assertEquals(debugStrings.get(1), "a");
        Assertions.assertEquals(debugStrings.get(2), "a");
        Assertions.assertEquals(debugStrings.get(3), "b");
        Assertions.assertEquals(debugStrings.get(4), "b");

    }
}
