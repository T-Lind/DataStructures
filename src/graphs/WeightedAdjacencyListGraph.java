package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedAdjacencyListGraph<E> extends AdjacencyListGraph<E> implements WeightedGraph<E> {
    //INSTANCE VARIABLES
    /**
     * A mapping between a Node and a map of Neighbor->Weight
     */
    private Map<E, Map<E, Integer>> weights;

    //CONSTRUCTORS
    public WeightedAdjacencyListGraph() {
        //initialize instance variables
        weights = new HashMap<>();
    }

    //METHODS

    /**
     * Add a node to the graph
     * <p>
     * Add a node to the graph exactly like an AdjacencyGraph,
     * then create a Map to keep track of weight associations between neighbors
     *
     * @param node the data to add to the graph. Duplicate data will not be added.
     * @return true if the data was successfully added to graph
     */
    @Override
    public boolean add(E node) {
        //TODO: call add() on the parent class to add this node to the graph
        var result = super.add(node);
        //TODO: if the node was successfully added, create a mapping between the node and a new HashMap<E, Integer>
        if (result && !weights.containsKey(node)) {
            weights.put(node, new HashMap<>());
            return true;
        }
        //TODO: return whether the node was successfully added to the graph
        return false;
    }

    /**
     * Set the weight between two nodes
     *
     * @param from   the node to set the weight from
     * @param to     the node to set the weight to
     * @param weight the weight to set
     */
    public void setWeight(E from, E to, int weight) {
        //TODO: if there is an edge between them, set the weight between from and to
        if (hasEdge(from, to)) {
            getNeighborWeights(from).put(to, weight);
        }
    }

    /**
     * Set the weight between two nodes
     * <p>
     * If there is no edge between the nodes, this will return 0.
     * If no weight has been assigned to these nodes, then this will return 0.
     *
     * @param from the node to get the weight from
     * @param to   the node to get the weight to
     * @return the weight between from and to or 0 if no edge or weight exists.
     */
    public int getWeight(E from, E to) {
        //TODO: check if there is an edge between from and to. If not, return 0
        //TODO: check if a weight has been set between from and to, If not, return 0
        //		HINT: Integer objects can be null...
        if(!hasEdge(from, to) || getNeighborWeights(from).get(to) == null)
            return 0;


        //TODO: return the weight between from and to
        return getNeighborWeights(from).get(to);
    }

    /**
     * Return a Mapping of Node -> Weight for all neighbors of node
     *
     * @param node the node to get weight mappings from
     * @return mappings from node to weight
     */
    public Map<E, Integer> getNeighborWeights(E node) {
        //TODO: return a map of Neighbor->Weight for all neighbors of node
        return weights.get(node);
    }

    /**
     * Return a string representation of the weighted graph data
     *
     * @return a string representation of the weighted graph
     */
    @Override
    public String toString() {
        List<String> ret = new ArrayList<String>();

        for (E node : getNodes()) {
            List<String> neighborStrings = new ArrayList<String>();
            for (E neighbor : getNeighbors(node))
                neighborStrings.add(neighbor + "(" + getWeight(node, neighbor) + ")");

            ret.add(node + "->" + neighborStrings.toString());
        }

        return ret.toString();
    }
}