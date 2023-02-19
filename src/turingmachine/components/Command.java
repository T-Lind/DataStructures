package turingmachine.components;

import turingmachine.highlevel.TuringMachine;

public interface Command {
    void invoke(Digit state, TuringMachine m);
}
