package markov_kt

import java.util.*

class MarkovChain() {
    protected val wg: MarkovWordGraph
    var lastWord: String? = null
        protected set
    protected var start: String

    init {
        wg = MarkovWordGraph(START_SIGNAL, END_SIGNAL)
        start = START_SIGNAL
    }

    constructor(filename: String) : this() {
        train(filename)
    }

    fun train(filename: String) {
        wg.processFile(FILEPATH + filename)
    }

    val nextWords: List<String>
        get() {
            lastWord = if (lastWord == null) START_SIGNAL else lastWord
            val ret: MutableList<String> = LinkedList()
            val data = wg.getGraph().getNeighborWeights(lastWord)
            for (word in data.keys) {
                for (i in 0 until data[word]!!) {
                    ret.add(word)
                }
            }
            return ret
        }

    /**
     * Does not do anything for first-order, only 2nd+
     */
    fun updateMemory() {}

    //        if(wordsToPick.size() == 0)
    val nextWord: String?
        get() {
            val wordsToPick = nextWords
            updateMemory()
            //        if(wordsToPick.size() == 0)
            lastWord = wordsToPick[(Math.random() * wordsToPick.size).toInt()]
            return lastWord
        }

    fun generateSentence(): String {
        lastWord = null
        val ret = StringBuilder()
        do {
            val word = nextWord
            if (!areImproperChars(word)) {
                ret.append(word)
                ret.append(" ")
            }
        } while (!wg.isEndWord(lastWord))
        val trimmed = cleanup(ret.toString())
        return if (count(trimmed, ' ') < 3 || areImproperChars(
                trimmed
            )
        ) generateSentence() else trimmed
    }

    companion object {
        const val START_SIGNAL = "[START]"
        const val END_SIGNAL = "[END]"
        const val FILEPATH = "src\\markov\\"
        fun cleanup(input: String): String {
            var input = input
            input = input.replace('�', '\'')
            return input
        }

        fun count(input: String, c: Char): Int {
            var cnt = 0
            for (character in input.toCharArray()) if (character == c) cnt++
            return cnt
        }

        fun areImproperChars(input: String?): Boolean {
            var cnt = 0
            for (character in input!!.toCharArray()) if (Character.isUpperCase(character) || Character.isDigit(character) || character == '*') cnt++
            return cnt.toFloat() / input.length > 0.5
        }
    }
}