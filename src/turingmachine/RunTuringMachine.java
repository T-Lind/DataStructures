package turingmachine;

import turingmachine.highlevel.CommandList;

public class RunTuringMachine extends CommandList {
    public static void main(String[] args) {
        var machine = new TuringMachine(0, new int[]{2, 1, 1, 1});


        machine.addCommand(0, 0, (m -> {
            m.setTape(3);
            m.move(1);
            m.goToPage(1);

        }));
        machine.addCommand(0, 1, (m -> {
            m.setTape(8);
            m.stop();

        }));
        machine.addCommand(1, 2, (m -> {
            m.setTape(0);
            m.move(4);
            m.goToPage(0);
        }));

        machine.run();
        machine.printTape();
    }
}
