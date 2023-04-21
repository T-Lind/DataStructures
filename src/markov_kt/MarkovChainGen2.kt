package markov_kt

import markov.MarkovChain
import java.util.*

class MarkovChainGen2() : MarkovChain() {
    private val wgPairs: MarkovWordGraph
    var secondLastWord: String?
        private set

    init {
        wgPairs = MarkovWordGraph(START_SIGNAL, END_SIGNAL)
        secondLastWord = null
    }

    constructor(filename: String) : this() {
        train(filename)
    }

    override fun train(filename: String) {
        super.train(filename)
        wgPairs.processFileGen(FILEPATH + filename, 2)
    }

    override fun getNextWords(): List<String> {
        val firstOrder = super.getNextWords()
        if (secondLastWord == null) return firstOrder
        val findInText = "[" + secondLastWord + ", " + getLastWord() + "]"
        return getStrings(firstOrder, findInText, wgPairs)
    }

    override fun updateMemory() {
        secondLastWord = getLastWord()
    }

    override fun generateSentence(): String {
        secondLastWord = null
        return super.generateSentence()
    }

    companion object {
        fun getStrings(firstOrder: MutableList<String>, findMe: String?, wgPairs: MarkovWordGraph): List<String> {
            val data = wgPairs.getGraph().getNeighborWeights(findMe)
            var retList: MutableList<String> = LinkedList()
            if (!data.isEmpty()) {
                for (word in data.keys) for (i in 0 until data[word]!!) retList.add(word)
            } else retList = firstOrder
            return retList
        }
    }
}