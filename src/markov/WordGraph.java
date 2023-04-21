package markov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class WordGraph {
    protected WeightedGraph<String> graph;

    protected String lastWord;

    public WordGraph() {
        graph = new WeightedAdjacencyListGraph<>();
        lastWord = null;
    }

    public void addWord(String newWord) {
        //TODO: add a word to the graph instance variable
        var trimmedWord = newWord.trim();
        if (!trimmedWord.equals("")) {
            graph.add(trimmedWord);
            if (lastWord != null) {
                graph.addEdge(lastWord, trimmedWord);
                graph.setWeight(lastWord, trimmedWord, graph.getWeight(lastWord, trimmedWord) + 1);
            }
            lastWord = trimmedWord;
        }
    }
    
    public void addWordsFromString(String str) {
        for (String word : str.split(" "))
            addWord(word);
    }


    public void processFile(String filename) {
        try {
            for(String line: new BufferedReader(new FileReader((filename))).lines().toList()) {
                addWordsFromString(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WeightedGraph<String> getGraph() {
        return graph;
    }

}
