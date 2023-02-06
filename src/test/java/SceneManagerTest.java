import com.physmo.gametoolkit.SceneManager;
import org.junit.jupiter.api.Test;
import support.InventorySubScene;
import support.MainGameScene;

import java.util.ArrayList;
import java.util.List;

public class SceneManagerTest {

    @Test
    public void testSimpleSetup() {
        List<String> messageList = new ArrayList<>();
        SceneManager sceneManager = new SceneManager();

        MainGameScene mainGameState = new MainGameScene("Main Game");
        mainGameState.setMessageList(messageList);
        sceneManager.addScene(mainGameState);
        mainGameState._init();
        sceneManager.setActiveScene("Main Game");
        sceneManager.tick(1);

        messageList.forEach(str -> System.out.println(str));
    }

    @Test
    public void testSubScene() {
        List<String> messageList = new ArrayList<>();
        SceneManager sceneManager = new SceneManager();

        MainGameScene mainGameState = new MainGameScene("Main Game");
        mainGameState.setMessageList(messageList);
        mainGameState._init();


        InventorySubScene inventorySubScene = new InventorySubScene("Inventory");
        inventorySubScene.setMessageList(messageList);
        inventorySubScene._init();


        sceneManager.addScene(mainGameState);
        sceneManager.addScene(inventorySubScene);

        sceneManager.setActiveScene("Main Game");

        for (int i=0;i<3;i++) {
            sceneManager.tick(1);
        }

        sceneManager.pushSubScene("Inventory");
        for (int i=0;i<5;i++) {
            sceneManager.tick(1);
        }

        messageList.forEach(str -> System.out.println(str));
    }
}
