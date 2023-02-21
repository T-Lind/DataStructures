package turingmachine.components;

import turingmachine.TuringMachine;

public interface Command {
    void invoke(TuringMachine m);
}
