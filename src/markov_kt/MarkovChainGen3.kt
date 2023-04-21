package markov_kt

import markov.MarkovChainGen2
import markov_kt.MarkovChainGen2.Companion.getStrings

class MarkovChainGen3 : MarkovChainGen2 {
    var wgTriples: MarkovWordGraph
    protected var thirdLastWord: String?

    constructor() : super() {
        wgTriples = MarkovWordGraph(START_SIGNAL, END_SIGNAL)
        thirdLastWord = null
    }

    constructor(start: String, end: String?) : super() {
        wgTriples = MarkovWordGraph(start, end)
        thirdLastWord = null
    }

    constructor(filename: String, start: String, end: String?) : super() {
        wgTriples = MarkovWordGraph(start, end)
        thirdLastWord = null
        train(filename)
    }

    constructor(filename: String) : this() {
        train(filename)
    }

    override fun train(filename: String) {
        super.train(filename)
        wgTriples.processFileGen(FILEPATH + filename, 3)
    }

    override fun getNextWords(): List<String> {
        val previousOrder = super.getNextWords()
        if (thirdLastWord == null) return previousOrder
        val strToFind = "[" + thirdLastWord + ", " + secondLastWord + ", " + getLastWord() + "]"
        return getStrings(previousOrder, strToFind, wgTriples)
    }

    override fun updateMemory() {
        thirdLastWord = secondLastWord
        super.updateMemory()
    }

    override fun generateSentence(): String {
        thirdLastWord = null
        return super.generateSentence()
    }
}