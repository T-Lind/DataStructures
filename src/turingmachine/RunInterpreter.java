package turingmachine;

import turingmachine.components.ValueError;

public class RunInterpreter {
    public static void main(String[] args) throws ValueError {
        new TuringInterpreter("""
                BEGIN INFO
                SIZE 2
                START POS 0
                EXECUTE AUTO
                END INFO
                
                BEGIN COMMANDS
                CMD SET TRUE MOVE 1 DONE
                MOVE 1
                CMD SET TRUE DONE
                END COMMANDS
                RUN
                """);
    }
}
