package turingmachine;

import org.jetbrains.annotations.NotNull;
import turingmachine.highlevel.TuringInterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Deprecated
public class RunInterpreter {
    public static void main(@NotNull String[] args) throws FileNotFoundException {
        StringBuilder summedArgs = new StringBuilder();
        for(String arg: args)
            summedArgs.append(arg).append(" ");

        File program = new File(summedArgs.toString());
        Scanner reader = new Scanner(program);
        StringBuilder readString = new StringBuilder();
        while(reader.hasNextLine()){
            readString.append(reader.nextLine()).append("\n");
        }
        reader.close();
        new TuringInterpreter(readString.toString());
    }
}
