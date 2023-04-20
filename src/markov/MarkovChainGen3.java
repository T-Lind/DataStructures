package markov;

import java.util.List;

public class MarkovChainGen3 extends MarkovChainGen2 {

    private MarkovWordGraph wgTriples;

    protected String thirdLastWord;

    public MarkovChainGen3() {
        super();
        wgTriples = new MarkovWordGraph(START_SIGNAL, END_SIGNAL);

        thirdLastWord = null;
    }


    @Override
    public void train(String filename) {
        super.train(filename);
        wgTriples.processFileGen(FILEPATH + filename, 3);
    }

    @Override
    public List<String> getNextWords() {
        var previousOrder = super.getNextWords();

        if (thirdLastWord == null)
            return previousOrder;

        var strToFind = "[" + thirdLastWord + ", " + getSecondLastWord() + ", " + getLastWord() + "]";

        return getStrings(previousOrder, strToFind, wgTriples);
    }

    @Override
    public void updateMemory() {
        thirdLastWord = getSecondLastWord();
        super.updateMemory();
    }

    @Override
    public String generateSentence() {
        // Fresh start
        thirdLastWord = null;

        return super.generateSentence();
    }
}