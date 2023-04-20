package markov;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class MarkovChain {
    public final static String START_SIGNAL = "[START]";
    public final static String END_SIGNAL = "[END]";

    public final static String FILEPATH = "Z:\\My Drive\\ComputerScience\\IdeaProjects\\DataStructures\\src\\markov\\";

    private MarkovWordGraph wg;

    private String lastWord;

    protected String start;

    public MarkovChain() {
        wg = new MarkovWordGraph(START_SIGNAL, END_SIGNAL);

        start = START_SIGNAL;

        lastWord = null;
    }

    public String getLastWord() {
        return lastWord;
    }


    public void train(String filename) {
        wg.processFile(FILEPATH + filename);
    }


    public List<String> getNextWords() {
        lastWord = lastWord == null ? START_SIGNAL : lastWord;

        List<String> ret = new LinkedList<>();
        Map<String, Integer> data = wg.getGraph().getNeighborWeights(lastWord);
        for (String word : data.keySet()) {
            for (int i = 0; i < data.get(word); i++) {
                ret.add(word);
            }
        }
        return ret;
    }


    /**
     * Does not do anything for first-order, only 2nd+
     */
    public void updateMemory() {
    }

    public String getNextWord() {

        var wordsToPick = this.getNextWords();

        this.updateMemory();
        lastWord = wordsToPick.get((int) (Math.random() * wordsToPick.size()));

        return lastWord;
    }


    public String generateSentence() {
        lastWord = null;

        var ret = new StringBuilder();

        do {
            ret.append(getNextWord());
            ret.append(" ");
        }
        while (!wg.isEndWord(lastWord));

        return ret.toString().trim();
    }


}