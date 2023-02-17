package turingmachine;

public class Item {
    private Digit state;
    private Command command;

    public Item(boolean state, Command command) {
        this.state = new Digit(state);
        this.command = command;
    }

    public Item(int state, Command command) throws ValueError {
        this.state = new Digit(state);
        this.command = command;
    }

    public Command setCommand(Command command){
        var old = this.command;
        this.command = command;
        return old;
    }

    public Digit get(){
        return state;
    }

    public Digit set(Digit state){
        var old = this.state;
        this.state = state;
        return old;
    }
}
