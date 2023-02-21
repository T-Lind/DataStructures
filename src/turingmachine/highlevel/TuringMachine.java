package turingmachine.highlevel;

import turingmachine.components.*;

public class TuringMachine {
    private final Tape tape;
    private final Observer observer;

    private Digit stored;

    public TuringMachine(int len){
        this(len, 0);
        stored = new Digit(0);
    }

    public TuringMachine(int len, int startPos){
        tape = new Tape(len);
        observer = new Observer(startPos, tape);
        stored = new Digit(0);
    }


    public TuringMachine(int startPos, int... ints)  {
        tape = new Tape(ints);
        observer = new Observer(startPos, tape);
        stored = new Digit(0);
    }

    public TuringMachine(int... ints)  {
        this(0, ints);
        stored = new Digit(0);
    }
    public TuringMachine(int startPos, boolean... bools)  {
        tape = new Tape(bools);
        observer = new Observer(startPos, tape);
        stored = new Digit(0);
    }


    public Observer getObserver(){
        return observer;
    }
    public Tape getTape(){
        return tape;
    }

    public void setStored(boolean state) {
        stored = new Digit(state);
    }
    public boolean getBool(){
        return tape.get(getObserver().getPosition()).getBoolean();
    }
    public boolean getStoredBool(){
        return stored.getBoolean();
    }
    public int getStoredInt(){
        return stored.getInt();
    }

    public Digit set(int idx, Digit value){
        return tape.set(idx, value);
    }
    public Digit set(int idx, int value)  {
        return tape.set(idx, new Digit(value));
    }

    public String toString(){
        return "Tape: "+tape.toString();
    }
}
