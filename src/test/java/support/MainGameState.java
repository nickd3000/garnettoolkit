package support;

import com.physmo.gametoolkit.Scene;

import java.util.List;

public class MainGameState extends Scene {

    List<String> messageList;

    public MainGameState(String name) {
        super(name);
    }

    public void setMessageList(List<String> messageList) {
        this.messageList = messageList;
    }

    @Override
    public void init() {
        messageList.add("MainGameState init");
    }

    @Override
    public void tick(double delta) {
        messageList.add("MainGameState tick");
    }

    @Override
    public void draw() {
        messageList.add("MainGameState draw");
    }
}
