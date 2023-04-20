package turingmachine.components;

import turingmachine.highlevel.TuringMachine;

public interface Command {
    void invoke(TuringMachine m);
}
