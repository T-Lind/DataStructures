package markov_kt

import markov.Graph

/**
 * Creates a weighted graph for use in markov chains
 * @param <E> The type of data to store in the graph
</E> */
interface WeightedGraph<E> : Graph<E> {
    fun setWeight(from: E, to: E, weight: Int)
    fun getWeight(from: E, to: E): Int
    fun getNeighborWeights(node: E): Map<E, Int?>?
}