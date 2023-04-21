package markov_kt

import markov.AdjacencyListGraph
import markov.WeightedGraph

class WeightedAdjacencyListGraph<E> : AdjacencyListGraph<E>(), WeightedGraph<E> {
    private val weights: MutableMap<E, MutableMap<E, Int>?>

    init {
        weights = HashMap()
    }

    override fun add(node: E): Boolean {
        return super.add(node) && weights.putIfAbsent(node, HashMap()) == null
    }

    override fun setWeight(from: E, to: E, weight: Int) {
        if (super.hasEdge(from, to)) weights[from]!![to] = weight
    }

    override fun getWeight(from: E, to: E): Int {
        return if (super.hasEdge(from, to)) weights[from]!!.getOrDefault(to, 0) else 0
    }

    override fun getNeighborWeights(node: E): Map<E, Int> {
        val weights = HashMap<E, Int>()
        val neighborWeights: Map<E, Int>? = this.weights[node]
        if (null != neighborWeights) weights.putAll(neighborWeights)
        return weights
    }

    override fun toString(): String {
        val ret = ArrayList<String>()
        for (node in nodes) {
            val neighborStrings = ArrayList<String>()
            for (neighbor in getNeighbors(node)) neighborStrings.add(
                neighbor.toString() + "(" + getWeight(
                    node,
                    neighbor
                ) + ")"
            )
            ret.add(node.toString() + "->" + neighborStrings)
        }
        return ret.toString()
    }
}