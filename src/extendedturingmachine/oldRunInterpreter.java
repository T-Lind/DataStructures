package extendedturingmachine;

import org.jetbrains.annotations.NotNull;
import extendedturingmachine.highlevel.OldTuringInterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Deprecated
public class oldRunInterpreter {
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
        new OldTuringInterpreter(readString.toString());
    }
}
