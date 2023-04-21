package markov_kt

import markov.WeightedAdjacencyListGraph
import markov.WeightedGraph
import java.io.BufferedReader
import java.io.FileReader

internal class WordGraph {
    var graph: WeightedGraph<String>
        protected set
    protected var lastWord: String?

    init {
        graph = WeightedAdjacencyListGraph()
        lastWord = null
    }

    fun addWord(newWord: String) {
        //TODO: add a word to the graph instance variable
        val trimmedWord = newWord.trim { it <= ' ' }
        if (trimmedWord != "") {
            graph.add(trimmedWord)
            if (lastWord != null) {
                graph.addEdge(lastWord, trimmedWord)
                graph.setWeight(lastWord, trimmedWord, graph.getWeight(lastWord, trimmedWord) + 1)
            }
            lastWord = trimmedWord
        }
    }

    fun addWordsFromString(str: String) {
        for (word in str.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) addWord(word)
    }

    fun processFile(filename: String?) {
        try {
            for (line in BufferedReader(FileReader(filename)).lines().toList()) {
                addWordsFromString(line)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}