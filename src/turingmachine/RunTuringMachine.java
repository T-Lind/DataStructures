package turingmachine;

import turingmachine.components.ValueError;

public class RunTuringMachine {
    public static void main(String[] args) throws ValueError {
        var machine = new TuringMachine(0, new int[]{0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1});
        var observer = machine.getObserver();
        while(!observer.atTail()){
            observer.setCommand(state -> {
                state.set(!state.getBoolean());
                observer.moveRelative(1);
                observer.runCommand();
            });
            observer.moveRelative(1);
        }
        observer.setCommand(state -> state.set(!state.getBoolean()));

        observer.setPosition(0);
        System.out.println(observer);

        observer.runCommand();
        System.out.println(observer);

        System.out.println(machine);
        var interpreter =new TuringInterpreter("""
                BEGIN INFO
                SIZE 100
                START POS 4
                END INFO
                BEGIN COMMANDS
                CMD SET NOT GET STORED GET MOVE 1 DONE
                MOVE 1
                CMD SET NOT STORED MOVE -2 DONE
                MOVE 1
                END COMMANDS
                RUN
                """);
    }
}
