package turingmachine.components;

import turingmachine.TuringMachine;

public interface Command {
    void invoke(Digit currentState, TuringMachine machine) throws ValueError;
}