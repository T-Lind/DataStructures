package markov_kt

import markov.Graph

class AdjacencyListGraph<E> : Graph<E> {
    //INSTANCE VARIABLES
    /**
     * An Adjacency List that maps Node -> List of neighbor Nodes
     */
    private val adjacencyList: MutableMap<E, MutableList<E>?>

    //CONSTRUCTORS
    init {
        //initialize instance variables
        adjacencyList = HashMap()
    }
    //METHODS
    /**
     * Add a node to the graph
     *
     * @param node the data to add to the graph. Duplicate data will not be added.
     * @return true if the data was successfully added to graph
     */
    override fun add(node: E): Boolean {
        //TODO: check if this graph already contains *node*. If so, return false.
        //TODO: add *node* to the Adjacency List, mapping it to an empty ArrayList then return true
        return adjacencyList.putIfAbsent(node, ArrayList()) == null
    }

    /**
     * Add an edge between two nodes in the graph
     *
     * Only add an edge if both nodes exist AND if an edge does not already exist
     *
     * @param from the node to add the edge from
     * @param to the node to add the edge to
     * @return true if the edge was successfully added to the graph
     */
    override fun addEdge(from: E, to: E): Boolean {
        //TODO: check if this graph contains *from* and *to*. If not, return false.
        //TODO: Check if *to* is a neighbor of *from*. If so, return false.
        return if (!this.contains(from) || !this.contains(to) || getNeighbors(from)
                .contains(to)
        ) false else adjacencyList[from]!!
            .add(to)
    }

    /**
     * Check if an edge exists between from and to
     *
     * @param from the node to check from
     * @param to the node to check to
     * @return true if an edge exists between from and to
     */
    override fun hasEdge(from: E, to: E): Boolean {
        //TODO: check if this graph contains both *from* and *to*. If not, return false.
        //TODO: check if *to* is a neighbor of *from*. If so, return true. Otherwise, return false.
        return this.contains(from) &&
                this.contains(to) &&
                getNeighbors(from).contains(to)
    }

    /**
     * Check if graph contains specified node
     *
     * @param node the node to check for
     * @return true if node exists in graph
     */
    override fun contains(node: E): Boolean {
        //if the graph contains *node*, then it will have been put in the adjacencyList as a key.
        return null != adjacencyList[node]
    }

    /**
     * Return a list of all nodes in the graph
     *
     * @return a list of all nodes in the graph
     */
    override fun getNodes(): List<E> {
        val ret: MutableList<E> = ArrayList()
        for (node in adjacencyList.keys) ret.add(node)
        return ret
    }

    /**
     * Return a list of nodes that node is connected to by an edge
     *
     * @param node the node to check for neighbors
     * @return the list of neighbors
     */
    override fun getNeighbors(node: E): List<E> {
        //create a List object to return
        val ret: MutableList<E> = ArrayList()

        //get list of neighbor nodes
        val neighbors: List<E>? = adjacencyList[node]

        //if node has neighbors, add them all to the List ret
        if (null != neighbors) ret.addAll(neighbors)
        return ret
    }

    /**
     * Return the number of nodes in the graph
     *
     * @return the number of nodes in the graph
     */
    override fun size(): Int {
        //the size of the graph is the number of nodes that have been added
        return adjacencyList.size
    }

    /**
     * Return a string representation of the graph data
     *
     * @return a string representation of the graph
     */
    override fun toString(): String {
        val ret: MutableList<String> = ArrayList()
        for (node in nodes) ret.add(node.toString() + "->" + getNeighbors(node).toString())
        return ret.toString()
    }
}