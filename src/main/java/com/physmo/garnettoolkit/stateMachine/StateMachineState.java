package com.physmo.garnettoolkit.stateMachine;

@FunctionalInterface
public interface StateMachineState {
    void tick(double t);
}
