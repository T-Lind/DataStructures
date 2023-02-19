package turingmachine.components;

import turingmachine.highlevel.TuringMachine;

import java.util.ArrayList;

public class Item {
    private Digit state;
    private ArrayList<Command> commands;
    private boolean hasRun = false;

    public Item(boolean state){
        this.state = new Digit(state);
    }

    public Item(int state)  {
        this.state = new Digit(state);
        commands = new ArrayList<>();
    }


//    public Item(boolean state, Command command) {
//        this.state = new Digit(state);
//        this.command = command;
//    }

//    public Item(int state, Command command) throws ValueError {
//        this.state = new Digit(state);
//        this.command = command;
//    }

    public void addCommand(Command command){
        commands.add(command);
    }

    public void setCommands(ArrayList<Command> commands){
        this.commands = commands;
    }

    public void runCommand(TuringMachine machine)  {
        if(commands != null && !hasRun) {
            hasRun = true;
            for (Command command : commands) {
                command.invoke(state, machine);
            }
        }
    }

    public Digit get(){
        return state;
    }

    public Digit set(Digit state){
        var old = this.state;
        this.state = state;
        return old;
    }

    public String toString(){
        return state.toString();
    }
}
