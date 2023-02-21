package turingmachine;

import java.util.ArrayList;

public class Entry {
    private ArrayList<Command> commands;

    public Entry(){
        commands = new ArrayList<>();
    }

    public void addCommand(Command c){
        commands.add(c);
    }

    public void setCommands(ArrayList<Command> commands){
        this.commands = commands;
    }

    public void runCommands(TuringMachine machine){
        for(Command c: commands){
            c.invoke(machine);
        }
    }
}
