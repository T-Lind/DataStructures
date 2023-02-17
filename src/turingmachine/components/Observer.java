package turingmachine.components;

public class Observer {
    private final int start;

    private int position;

    private Tape tape;

    public Observer(int initPos, Tape tape){
        start = initPos;
        position = initPos;
        this.tape = tape;
    }

    public void runCommand() throws ValueError {
            tape.getItem(position).runCommand();
    }

    public void setPosition(int position){
        this.position = position;
    }
    public int getPosition(){
        return position;
    }

    public void moveRelative(int delta){
        if(position + delta >= 0 && position + delta < tape.size())
            position += delta;
        else{
            System.out.println();
        }
    }

    public boolean atTail(){
        return position == tape.size()-1;
    }

    public boolean atStart(){
        return position == start;
    }

    public boolean atHead(){
        return position == 0;
    }

    public Command setCommand(Command command){
        return tape.getItem(position).setCommand(command);
    }

    public Item getItem(){
        return tape.getItem(position);
    }
    public int get(){
        return tape.get(position).getInt();
    }
    public void set(Digit digit){
        tape.set(position, digit);
    }
    public void set(Boolean state){
        tape.set(position, new Digit(state));
    }

    public String toString(){
        return "Position: "+position+", Tape: "+tape.toString();
    }
}
