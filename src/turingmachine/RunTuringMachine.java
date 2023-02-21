package turingmachine;

import turingmachine.highlevel.CommandList;

public class RunTuringMachine extends CommandList {
    public static void main(String[] args) {
        var machine = new TuringMachine(0, new int[]{2, 1, 1, 1});


        machine.addCommand(0, NONE, (m -> {
            m.move(1);
            m.setAwareness(m.getTape());
            m.goToPage(1);
        }));

        machine.run();
        machine.printTape();
    }
}
