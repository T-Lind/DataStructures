package extendedturingmachine.components;

import extendedturingmachine.highlevel.ExtendedTuringMachine;

import java.util.ArrayList;

public class Observer {
    private final int start;

    private int position;

    private Tape tape;

    public Observer(int initPos, Tape tape) {
        start = initPos;
        position = initPos;
        this.tape = tape;
    }

    public void runCommand(ExtendedTuringMachine machine)  {
        tape.getItem(position).runCommand(machine);
    }

    public void setCommands(ArrayList<ExtendedCommand> commands) {
        tape.getItem(position).setCommands(commands);
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void moveRelative(int delta) {
        if (position + delta >= 0 && position + delta < tape.size())
            position += delta;
    }

    public boolean atTail() {
        return position == tape.size() - 1;
    }

    public boolean atStart() {
        return position == start;
    }

    public boolean atHead() {
        return position == 0;
    }

    public void addCommand(ExtendedCommand command) {
        tape.getItem(position).addCommand(command);
    }

    public Item getItem() {
        return tape.getItem(position);
    }

    public int get() {
        return tape.get(position).getInt();
    }

    public void set(Digit digit) {
        tape.set(position, digit);
    }

    public void set(Boolean state) {
        tape.set(position, new Digit(state));
    }

    public String toString() {
        return "Position: " + position + ", Tape: " + tape.toString();
    }
}
