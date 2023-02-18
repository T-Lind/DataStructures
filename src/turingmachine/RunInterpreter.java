package turingmachine;

import turingmachine.components.ValueException;

public class RunInterpreter {
    public static void main(String[] args) throws ValueException {
        new TuringInterpreter("""
                BEGIN INFO
                SIZE 3
                START POS 0
                EXECUTE AUTO
                END INFO
                
                BEGIN COMMANDS
                CMD SET NOT GET MOVE 1 DONE
                MOVE 1
                CMD SET NOT GET MOVE 1 DONE
                MOVE 1
                CMD SET NOT GET DONE
                END COMMANDS
                RUN
                """);
    }
}
