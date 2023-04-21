package markov_kt

interface Graph<E> {
    fun add(node: E): Boolean
    fun addEdge(from: E, to: E): Boolean
    fun hasEdge(from: E, to: E): Boolean
    val nodes: List<E>?
    fun getNeighbors(node: E): List<E>?
    operator fun contains(node: E): Boolean
    fun size(): Int
}