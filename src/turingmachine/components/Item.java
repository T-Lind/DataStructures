package turingmachine.components;

import turingmachine.TuringMachine;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Item {
    private Digit state;
    private ArrayList<Command> commands;

    public Item(boolean state){
        this.state = new Digit(state);
    }

    public Item(int state) throws ValueError {
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

    public void runCommand(TuringMachine machine) throws ValueError {
        System.out.println("Size of commands run: "+commands.size());
        if(commands != null)
            for(Command cmd: commands)
                cmd.invoke(state, machine);
    }

    public Digit get(){
        return state;
    }

    public Digit set(Digit state){
        System.out.println("setting");
        var old = this.state;
        this.state = state;
        return old;
    }

    public String toString(){
        return state.toString();
    }
}
