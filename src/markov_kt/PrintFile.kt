package markov_kt

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

object PrintFile {
    @JvmStatic
    fun main(args: Array<String>) {
//        processFileGen("Z:\\My Drive\\ComputerScience\\IdeaProjects\\DataStructures\\src\\markov\\text\\grimm.txt");
        processFileGen("C:\\Users\\lindauer_927142\\IdeaProjects\\DataStructures\\src\\markov\\text\\shakespeare.txt")
    }

    fun processFileGen(filename: String?) {
        try {
            //open the specified file
            val file = File(filename)
            println(BufferedReader(FileReader(filename)).lines().toList())

//            var in = new Scanner(file);
//
//            //loop through each line of the file and process it
//            while (in.hasNextLine()) {
//                System.out.println(in.nextLine());
//            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}