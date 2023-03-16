package com.physmo.garnettoolkit.stateMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateMachine {

    public static final String ANY_STATE = "ANY_STATE";
    Map<String, StateMachineState> stateMap = new HashMap<>();
    String targetStateName = null;
    StateMachineState currentState;
    String currentStateName;
    List<Transition> transitions = new ArrayList<>();

    public String getCurrentStateName() {
        return (currentStateName == null ? "" : currentStateName);
    }

    public void addState(String stateName, StateMachineState state) {
        stateMap.put(stateName, state);
        if (currentState == null) {
            currentState = state;
            currentStateName = stateName;
        }
    }

    /**
     * Add a state that will be called when moving between two specified states.
     *
     * @param fromState
     * @param toState
     * @param state
     */
    public void addTransition(String fromState, String toState, StateMachineState state) {
        Transition transition = new Transition();
        transition.fromState = fromState;
        transition.toState = toState;
        transition.transitionState = state;
        transitions.add(transition);
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

        // First, run any state transition that matches our source and target state.
        Transition transition = findTransition(currentStateName, targetStateName);
        if (transition != null) {
            transition.transitionState.tick(0);
        }

        if (stateMap.containsKey(targetStateName)) {
            currentState = stateMap.get(targetStateName);
            currentStateName = targetStateName;
            targetStateName = null;
        } else {
            // todo: exception if name not found
        }
    }

    private Transition findTransition(String fromState, String toState) {
        for (Transition transition : transitions) {
            if (transition.fromState.compareTo(fromState) != 0 && transition.fromState.compareTo(ANY_STATE) != 0)
                continue;
            if (transition.toState.compareTo(toState) != 0 && transition.toState.compareTo(ANY_STATE) != 0) continue;
            return transition;
        }
        return null;
    }
}
