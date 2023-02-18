package turingmachine;

import turingmachine.components.ValueException;
import turingmachine.highlevel.TuringInterpreter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RunInterpreter {
    public static void main(String[] args) throws ValueException, FileNotFoundException {
        String path = "XXX";
        String name = path+"main.tmi";
        File program = new File(name);
        Scanner reader = new Scanner(program);
        StringBuilder readString = new StringBuilder();
        while(reader.hasNextLine()){
            readString.append(reader.nextLine()).append("\n");
        }
        reader.close();
        var interpreter = new TuringInterpreter(readString.toString());
        System.out.println(interpreter);
    }
}
