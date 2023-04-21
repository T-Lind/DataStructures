package markov_kt

import markov.MarkovChainGen3
import markov_kt.MarkovChain.Companion.areImproperChars
import markov_kt.MarkovChain.Companion.cleanup
import markov_kt.MarkovChain.Companion.count

class MarkovChainSonnet(filepath: String?) : MarkovChainGen3(filepath, START_SIGNAL, ",") {
    /**
     * Only difference is that is uses the isEndWordSonnet method to generate things that look more like sonnets.
     * @return sonnet line
     */
    override fun generateSentence(): String {
        lastWord = null
        val ret = StringBuilder()
        do {
            val word = nextWord
            if (!areImproperChars(word)) {
                ret.append(word)
                ret.append(" ")
            }
        } while (!wg.isEndWordSonnet(lastWord))
        val trimmed = cleanup(ret.toString())
        return if (count(trimmed, ' ') < 3 || areImproperChars(trimmed)) generateSentence() else trimmed
    }

    fun generateSonnet(): String {
        val builder = StringBuilder()
        for (i in 0..11) {
            if (i >= 10) builder.append("  ")
            builder.append(generateSentence()).append("\n")
            if (i == 3 || i == 7) builder.append("\n")
        }
        return builder.toString()
    }
}