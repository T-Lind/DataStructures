package turingmachine;

import turingmachine.components.ValueError;

public class RunTuringMachine {
    public static void main(String[] args) throws ValueError {
        var machine = new TuringMachine(0, new int[]{0, 1, 1, 0, 0, 1, 1});
        var observer = machine.getObserver();
        while(!observer.atTail()){
            observer.setCommand(currentState -> {
                currentState.set(!currentState.getBoolean());
                observer.moveRelative(1);
                observer.runCommand();
            });
            observer.moveRelative(1);
        }
        System.out.println(observer);

        observer.setPosition(0);
        observer.runCommand();
        System.out.println(machine);
    }
}
