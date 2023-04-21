package markov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class PrintFile {
    public static void main(String[] args) {
//        processFileGen("Z:\\My Drive\\ComputerScience\\IdeaProjects\\DataStructures\\src\\markov\\text\\grimm.txt");
        processFileGen("C:\\Users\\lindauer_927142\\IdeaProjects\\DataStructures\\src\\markov\\text\\shakespeare.txt");
    }
    public static void processFileGen(String filename) {
        try {
            //open the specified file
            var file = new File(filename);

            System.out.println(new BufferedReader(new FileReader((filename))).lines().toList());

//            var in = new Scanner(file);
//
//            //loop through each line of the file and process it
//            while (in.hasNextLine()) {
//                System.out.println(in.nextLine());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
