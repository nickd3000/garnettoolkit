package com.physmo.garnettoolkit.stateMachine;

import java.util.HashMap;
import java.util.Map;

public class StateMachine {

    Map<String, StateMachineState> stateMap = new HashMap<>();
    String targetStateName = null;
    StateMachineState currentState;
    String currentStateName;

    public String getCurrentStateName() {
        return (currentStateName == null ? "" : currentStateName);
    }

    public void addState(String stateName, StateMachineState state) {
        stateMap.put(stateName, state);
    }

    public void changeState(String stateName) {
        targetStateName = stateName;
    }

    public void tick(double delta) {
        handleStateChange();

        if (currentState != null) {
            currentState.tick(delta);
        }
    }

    private void handleStateChange() {
        if (targetStateName == null) return;

        if (stateMap.containsKey(targetStateName)) {
            currentState = stateMap.get(targetStateName);
            currentStateName = targetStateName;
            targetStateName = null;
        } else {
            // todo: exception if name not found
        }
    }
}
