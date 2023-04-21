package markov;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class MarkovChainGen2 extends MarkovChain {
    private MarkovWordGraph wgPairs;

    private String secondLastWord;

    public MarkovChainGen2() {
        super();
        wgPairs = new MarkovWordGraph(START_SIGNAL, END_SIGNAL);

        secondLastWord = null;
    }
    public MarkovChainGen2(String filename){
        this();
        train(filename);
    }

    public String getSecondLastWord() {
        return secondLastWord;
    }

    @Override
    public void train(String filename) {
        super.train(filename);

        wgPairs.processFileGen(FILEPATH + filename, 2);
    }

    @Override
    public List<String> getNextWords() {
        var firstOrder = super.getNextWords();

        if (secondLastWord == null) return firstOrder;

        var findInText = "[" + secondLastWord + ", " + getLastWord() + "]";

        return getStrings(firstOrder, findInText, wgPairs);
    }

    static List<String> getStrings(List<String> firstOrder, String findMe, MarkovWordGraph wgPairs) {
        var data = wgPairs.getGraph().getNeighborWeights(findMe);

        List<String> retList = new LinkedList<>();
        if (!data.isEmpty()) {
            for (String word : data.keySet())
                for (int i = 0; i < data.get(word); i++)
                    retList.add(word);
        } else retList = firstOrder;
        return retList;
    }

    @Override
    public void updateMemory() {
        secondLastWord = getLastWord();
    }

    @Override
    public String generateSentence() {
        secondLastWord = null;

        return super.generateSentence();
    }
}