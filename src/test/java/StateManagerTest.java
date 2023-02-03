import com.physmo.gametoolkit.SceneManager;
import org.junit.jupiter.api.Test;
import support.MainGameState;

import java.util.ArrayList;
import java.util.List;

public class StateManagerTest {

    @Test
    public void testSimpleSetup() {
        List<String> messageList = new ArrayList<>();
        SceneManager stateManager = new SceneManager();

        MainGameState mainGameState = new MainGameState("Main Game");
        mainGameState.setMessageList(messageList);
        stateManager.addScene(mainGameState);
        mainGameState._init();
        stateManager.switchActiveState("Main Game");
        stateManager.tick(1);

        messageList.forEach(str -> System.out.println(str));
    }
}
