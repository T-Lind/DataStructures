package turingmachine;

public class Observer {
    private int position;

    private Tape tape;

    public Observer(int initPos, Tape tape){
        position = initPos;
        this.tape = tape;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public void moveRelative(int delta){
        position = clampAdd(position, delta, 0, tape.size());
    }

    public Command setCommand(Command command){
        return tape.getItem(position).setCommand(command);
    }

    public Digit getItem(){
        return tape.get(position);
    }
    public int get(){
        return tape.get(position).getInt();
    }

    private static int clampAdd(int value, int delta, int min, int max){
        if(value+delta < min)
            return min;
        if(value+delta > max)
            return max;
        return value;
    }
}
