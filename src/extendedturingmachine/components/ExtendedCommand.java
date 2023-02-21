package extendedturingmachine.components;

import extendedturingmachine.highlevel.ExtendedTuringMachine;

public interface ExtendedCommand {
    void invoke(Digit state, ExtendedTuringMachine m);
}
