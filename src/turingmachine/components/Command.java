package turingmachine.components;

import turingmachine.highlevel.TuringMachine;

public interface Command {
    void invoke(Digit currentState, TuringMachine machine) throws ValueException;
}
