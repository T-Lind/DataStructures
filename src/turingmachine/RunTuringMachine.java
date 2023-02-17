package turingmachine;

import turingmachine.components.ValueException;

public class RunTuringMachine {
    public static void main(String[] args) throws ValueException {
        var machine = new TuringMachine(0, new int[]{0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1});
        var observer = machine.getObserver();
        while (!observer.atTail()) {
            observer.addCommand((state, m) -> {
                state.set(!state.getBoolean());
                observer.moveRelative(1);
                observer.runCommand(m);
            });
            observer.moveRelative(1);
        }
        observer.addCommand((state, m) -> state.set(!state.getBoolean()));

        observer.setPosition(0);
        System.out.println(observer);

        observer.runCommand(machine);
        System.out.println(observer);

        System.out.println(machine);

    }
}
