package extendedturingmachine;

import org.jetbrains.annotations.NotNull;
import extendedturingmachine.components.SyntaxException;
import extendedturingmachine.highlevel.InterpreterTMI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RunInterpreterTMI {
    public static void main(@NotNull String[] args) throws FileNotFoundException, SyntaxException {
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
        var interpreter = new InterpreterTMI(readString.toString());
    }
}
