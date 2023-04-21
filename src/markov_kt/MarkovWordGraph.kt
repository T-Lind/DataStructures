package markov_kt

import markov.WordGraph
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.*

/**
 * Creates graphs of words designed for Markov chains.
 */
class MarkovWordGraph(private val START: String, private val END: String?) : WordGraph() {
    private val lastWords: Queue<String>

    init {
        graph.add(START)
        graph.add(END)
        lastWords = LinkedList()
    }

    fun isEndWord(word: String?): Boolean {
        return word!!.matches(Regex(".*[.?!'\"]$"))
    }

    fun isEndWordSonnet(word: String): Boolean {
        return word.matches(Regex(".*[.?!';,\"]$"))
    }

    override fun addWord(newWord: String) {
        super.addWord(newWord)
        if (lastWord == null) lastWord = START
        if (isEndWord(newWord.trim { it <= ' ' })) {
            super.addWord(END)
            lastWord = null
        }
    }

    private fun addWordGen(newWord: String, order: Int) {
        var newWord = newWord
        newWord = newWord.trim { it <= ' ' }
        if (lastWords.isEmpty()) lastWords.add(START)
        if (lastWords.size < order) {
            if (newWord != "") {
                lastWords.add(newWord)
            }
        } else {
            lastWord = null
            super.addWord(lastWords.toString())
            super.addWord(newWord)
            if (newWord != "") {
                lastWords.remove()
                lastWords.add(newWord)
            }
            if (isEndWord(newWord)) {
                lastWord = lastWords.toString()
                super.addWord(END)
                lastWords.clear()
                lastWord = null
            }
        }
    }

    fun processStringGen(str: String, order: Int) {
        for (word in str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) addWordGen(word, order)
    }

    fun processFileGen(filename: String, order: Int) =
        File(filename).useLines {
            it.forEach { line ->  processStringGen(line, order ) }
        }
}