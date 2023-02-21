package turingmachine;

public class RunTuringMachine {
    public static void main(String[] args) {
        var machine = new TuringMachine(new int[]{2, 1, 1, 1});


        machine.addCommand(0, 0, (m-> {
            m.setAwareness(m.getTape());
            m.setTape(3);
            m.move(1);
            m.goToPage(1);
//            m.setAwareness(m.getTape());
        }), false);

        machine.addCommand(0, 1, (m -> {
            m.setTape(8);
            m.stop();

        }));
        machine.addCommand(1, 2, (m -> {
            m.setTape(0);
            m.move(-4);
            m.goToPage(0);
        }));

        machine.run();
        machine.printTape();
    }
}
