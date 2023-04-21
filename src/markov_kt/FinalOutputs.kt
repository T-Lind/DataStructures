package markov_kt

import markov.MarkovChain
import markov.MarkovChainGen2
import markov.MarkovChainGen3
import markov.MarkovChainSonnet

/**
 * @author TLInd
 * @version 1.00 2023/4/21
 */
object FinalOutputs {
    private const val N_SENTENCES = 4
    @JvmStatic
    fun main(args: Array<String>) {
        printParagraphFromSource(3, N_SENTENCES, "aristotle.txt")
        printParagraphFromSource(3, N_SENTENCES, "garfield.txt")
        printParagraphFromSource(3, N_SENTENCES, "jane-austen.txt")
        printParagraphFromSource(3, N_SENTENCES, "grimm.txt")
        printParagraphFromSource(3, N_SENTENCES, "shakespeare.txt")
        printParagraphFromSource(3, N_SENTENCES, "my-writing.txt")
        printParagraphFromSource(3, N_SENTENCES, "shakespeare-sonnets.txt")
        printSonnet("shakespeare-sonnets.txt")
    }

    private fun printParagraphFromSource(gen: Int, nSentences: Int, filename: String) {
        println("Source: $filename")
        println("--------------------------")
        val chain = when (gen) {
            1 -> MarkovChain("text\\$filename")
            2 -> MarkovChainGen2("text\\$filename")
            else -> MarkovChainGen3("text\\$filename")
        }
        for (i in 0 until nSentences) println(chain.generateSentence())
        println("--------------------------")
    }

    private fun printSonnet(filename: String) {
        println("Source: $filename")
        println("--------------------------")
        val chain = MarkovChainSonnet("text\\$filename")
        println(chain.generateSonnet())
        println("--------------------------")
    }
}