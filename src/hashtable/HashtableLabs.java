package hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HashtableLabs {
    public static void main(String[] args) throws FileNotFoundException {
        Func<Integer> f1 = (input) -> input % 10;
        var table1 = new Hashtable<>(10, f1);

        var scanner = new Scanner( new File("C:\\Users\\lindauer_927142\\IdeaProjects\\JavaCS3\\JavaCS3\\src\\hashtable\\numbers.dat"));
        while(scanner.hasNextLine()){
            var data = scanner.nextLine();
            table1.add(Integer.parseInt(data.strip()));
        }
        scanner.close();

        System.out.println(table1);

        var VOWELS = "AEIOUaeiou";
        Func<String> f2 = (input -> {
            int vowelCount = 0;
            for(Character c: input.toCharArray()) {
                if (VOWELS.contains(c.toString())) {
                    vowelCount++;
                }
            }
            return (vowelCount * input.length()) % 10;
        });
        var table2 = new Hashtable<>(10, f2);

        scanner = new Scanner( new File("C:\\Users\\lindauer_927142\\IdeaProjects\\JavaCS3\\JavaCS3\\src\\hashtable\\words.dat"));
        while(scanner.hasNextLine()){
            var data = scanner.nextLine();
            table2.add(data.strip());
        }
        scanner.close();
        System.out.println(table2);
    }
}
