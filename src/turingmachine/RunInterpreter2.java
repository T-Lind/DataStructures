package turingmachine;

import org.jetbrains.annotations.NotNull;
import turingmachine.components.ValueException;
import turingmachine.highlevel.TuringInterpreter;
import turingmachine.highlevel.TuringInterpreter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RunInterpreter2 {
    public static void main(@NotNull String[] args) throws FileNotFoundException {
        String summedArgs = "";
        for(String arg: args)
            summedArgs += arg+" ";

        File program = new File(summedArgs);
        Scanner reader = new Scanner(program);
        StringBuilder readString = new StringBuilder();
        while(reader.hasNextLine()){
            readString.append(reader.nextLine()).append("\n");
        }
        reader.close();
        var interpreter = new TuringInterpreter2(readString.toString());
    }
}
