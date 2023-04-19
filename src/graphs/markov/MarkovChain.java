package graphs.markov;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

public class MarkovChain {
    //INSTANCE varIABLES
    /**
     * This holds all of the word relationships
     */
    private WordGraph wg;

    /**
     * This remembers the lastWord that this Markov Chain generated
     */
    private String lastWord;

    //CONSTRUCTOR
    public MarkovChain() {
        //initialize instance variables
        wg = new WordGraph();
        lastWord = null;
        wg.addWord("[START]");
        wg.addWord("[END]");
    }

    //METHODS

    /**
     * Add word relationships from the specified file
     */
    public void train(String filename) throws IOException {
        String content = null;

        var f = new File(filename);
        try (var reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "Cp1252"))) {
            content = reader.lines().collect(Collectors.joining());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert content != null : "File must not be null";
        var words = content.split(" ");
        for (String word : words) {
            wg.addWord(word);
        }
//        System.out.println(wg.getGraph().toString());
    }

    /**
     * Get a list of words that follow lastWord.
     * words with more *weight* will appear more times in the list.
     * if lastWord is null, then return the words that are neighbors of [START]
     */
    public List<String> getNextWords() {
        //TODO: return List<String> of words that are neighbors of lastWord, weighted appropriately
        var list = new ArrayList<String>();
        System.out.println(wg.getGraph().getNeighbors("to"));
        for (String neighbor : wg.getGraph().getNeighbors(lastWord)) {
            for (int i = 0; i < wg.getGraph().getWeight(lastWord, neighbor); i++)
                list.add(neighbor);
        }
        return list;
    }

    /**
     * Get a word that follows lastWord
     * Use a weighted random number to pick the next word from the list of all of lastWord's neighbors in wordGraph
     * If lastWord is null or [END], this should generate a word that *starts* a sentence
     * Remember the word that is about to be returned in the appropriate instance variable
     */
    public String getNextWord() {
        //TODO: return random word with an edge from lastWord
        var choices = getNextWords();
        return choices.get((int) (Math.random() * choices.size()));
    }

    /**
     * Generate a sentence using the data in the wordGraph.
     */
    public String generateSentence() {
        //TODO: generate a sentenec from [START] to [END]
        var retStr = new StringBuilder();
        var stopped = false;
        while (!stopped) {
            var word = getNextWord();
            retStr.append(word);
            if (word.equals("[END]"))
                stopped = true;
        }
        return retStr.toString();
    }
}