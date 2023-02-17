package turingmachine;

import turingmachine.components.*;

public class TuringMachine {
    private Tape tape;
    private Observer observer;

    public TuringMachine(int len){
        this(len, 0);
    }

    public TuringMachine(int len, int startPos){
        tape = new Tape(len);
        observer = new Observer(startPos, tape);
    }

    public TuringMachine(int startPos, int... ints) throws ValueError {
        tape = new Tape(ints);
        observer = new Observer(startPos, tape);
    }

    public TuringMachine(int... ints) throws ValueError {
        this(0, ints);
    }

    public Observer getObserver(){
        return observer;
    }

    public Digit set(int idx, Digit value){
        return tape.set(idx, value);
    }
    public Digit set(int idx, int value) throws ValueError {
        return tape.set(idx, new Digit(value));
    }

    public String toString(){
        return "Tape: "+tape.toString();
    }
}
