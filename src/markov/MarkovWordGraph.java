package markov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Creates graphs of words designed for Markov chains.
 */
class MarkovWordGraph extends WordGraph {
    private String START;
    private String END;


    private Queue<String> lastWords;

    public MarkovWordGraph(String START, String END) {
        this.START = START;
        this.END = END;
        graph.add(START);
        graph.add(END);

        lastWords = new LinkedList<>();
    }

    public boolean isEndWord(String word) {
        return word.matches(".*[.?!'\"]$");
    }

    public boolean isEndWordSonnet(String word) {
        return word.matches(".*[.?!';,\"]$");
    }

    @Override
    public void addWord(String newWord) {
        super.addWord(newWord);

        if (lastWord == null)
            lastWord = START;

        if (isEndWord(newWord.trim())) {
            super.addWord(END);
            lastWord = null;
        }
    }


    private void addWordGen(String newWord, int order) {
        newWord = newWord.trim();

        if (lastWords.isEmpty())
            lastWords.add(START);

        if (lastWords.size() < order) {
            if (!newWord.equals("")) {
                lastWords.add(newWord);
            }
        } else {
            lastWord = null;
            super.addWord(lastWords.toString());
            super.addWord(newWord);
            if (!newWord.equals("")) {
                lastWords.remove();
                lastWords.add(newWord);
            }
            if (isEndWord(newWord)) {
                lastWord = lastWords.toString();
                super.addWord(END);
                lastWords.clear();
                lastWord = null;
            }
        }
    }

    public void processStringGen(String str, int order) {
        for (String word : str.split(" "))
            addWordGen(word, order);
    }

    public void processFileGen(String filename, int order) {
        try {
            for (String line : new BufferedReader(new FileReader((filename))).lines().toList())
                processStringGen(line, order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
