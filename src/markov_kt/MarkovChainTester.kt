package markov_kt

import markov.MarkovChain
import markov.MarkovChainGen2
import markov.MarkovChainGen3

object MarkovChainTester {
    private const val N_SENTENCES = 2
    @JvmStatic
    fun main(args: Array<String>) {
        //https://www.gutenberg.org/catalog/
        val testFile = "text\\jane-austen.txt"

        // Test 1st order markov chain
        val chain = MarkovChain()
        chain.train(testFile)
        for (i in 0 until N_SENTENCES) println(chain.generateSentence())
        println()

        // Test 2nd order markov chain
        val chain2 = MarkovChainGen2()
        chain2.train(testFile)
        for (i in 0 until N_SENTENCES) println(chain2.generateSentence())
        println()

        // Test 3rd order markov chain
        val chain3 = MarkovChainGen3()
        chain3.train(testFile)
        for (i in 0 until N_SENTENCES) println(chain3.generateSentence())
    }
}