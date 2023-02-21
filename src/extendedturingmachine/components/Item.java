package extendedturingmachine.components;

import extendedturingmachine.highlevel.ExtendedTuringMachine;

import java.util.ArrayList;

public class Item {
    private Digit state;
    private ArrayList<ExtendedCommand> commands;
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

    public void addCommand(ExtendedCommand command){
        commands.add(command);
    }

    public void setCommands(ArrayList<ExtendedCommand> commands){
        this.commands = commands;
    }

    public void runCommand(ExtendedTuringMachine machine)  {
        if(commands != null && !hasRun) {
            hasRun = true;
            for (ExtendedCommand command : commands) {
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
