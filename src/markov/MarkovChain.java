package markov;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class MarkovChain {
    public final static String START_SIGNAL = "[START]";
    public final static String END_SIGNAL = "[END]";
    public final static String FILEPATH = "src\\markov\\";

    protected final MarkovWordGraph wg;

    protected String lastWord;

    protected String start;

    public MarkovChain() {
        wg = new MarkovWordGraph(START_SIGNAL, END_SIGNAL);
        start = START_SIGNAL;
    }

    public MarkovChain(String filename) {
        this();
        train(filename);
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
//        if(wordsToPick.size() == 0)
        lastWord = wordsToPick.get((int) (Math.random() * wordsToPick.size()));

        return lastWord;
    }


    public String generateSentence() {
        lastWord = null;

        var ret = new StringBuilder();

        do {
            var word = getNextWord();
            if(!areImproperChars(word)) {
                ret.append(word);
                ret.append(" ");
            }
        }
        while (!wg.isEndWord(lastWord));

        var trimmed = cleanup(ret.toString());
        if (count(trimmed, ' ') < 3 || areImproperChars(trimmed))
            return generateSentence();
        return trimmed;
    }

    static String cleanup(String input){
        input = input.replace('�', '\'');
        return input;
    }

    static int count(String input, char c){
        int cnt = 0;
        for(char character: input.toCharArray())
            if(character == c)
                cnt++;
        return cnt;
    }

    static boolean areImproperChars(String input){
        int cnt = 0;
        for(char character: input.toCharArray())
            if(Character.isUpperCase(character) || Character.isDigit(character) || character == '*')
                cnt++;
        return (float) cnt / input.length() > 0.5;
    }
}
