package markov;

public class MarkovChainSonnet extends MarkovChainGen3 {
    public MarkovChainSonnet(String filepath) {
        super(filepath, START_SIGNAL, ",");
    }

    /**
     * Only difference is that is uses the isEndWordSonnet method to generate things that look more like sonnets.
     * @return sonnet line
     */
    @Override
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
        while (!wg.isEndWordSonnet(lastWord));

        var trimmed = cleanup(ret.toString());
        if (count(trimmed, ' ') < 3 || areImproperChars(trimmed))
            return generateSentence();
        return trimmed;
    }

    public String generateSonnet(){
        var builder = new StringBuilder();
        for (var i = 0; i < 12; i++) {
            if(i >= 10)
                builder.append("  ");
            builder.append(generateSentence()).append("\n");
            if(i == 3 || i == 7)
                builder.append("\n");
        }
        return builder.toString();
    }
}
