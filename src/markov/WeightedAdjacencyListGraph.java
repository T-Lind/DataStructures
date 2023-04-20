package markov;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class WeightedAdjacencyListGraph<E> extends AdjacencyListGraph<E> implements WeightedGraph<E> {
    private Map<E, Map<E, Integer>> weights;

    public WeightedAdjacencyListGraph() {
        weights = new HashMap<>();
    }

    @Override
    public boolean add(E node) {
        return (super.add(node) && (weights.putIfAbsent(node, new HashMap<>()) == null));
    }

    @Override
    public void setWeight(E from, E to, int weight) {
        if (super.hasEdge(from, to)) weights.get(from).put(to, weight);
    }

    @Override
    public int getWeight(E from, E to) {
        return (super.hasEdge(from, to) ? weights.get(from).getOrDefault(to, 0) : 0);
    }

    @Override
    public Map<E, Integer> getNeighborWeights(E node) {
        var weights = new HashMap<E, Integer>();
        var neighborWeights = this.weights.get(node);
        if (null != neighborWeights) weights.putAll(neighborWeights);
        return weights;
    }


    @Override
    public String toString() {
        var ret = new ArrayList<String>();

        for (E node : getNodes()) {
            var neighborStrings = new ArrayList<String>();
            for (E neighbor : getNeighbors(node))
                neighborStrings.add(neighbor + "(" + getWeight(node, neighbor) + ")");

            ret.add(node + "->" + neighborStrings);
        }

        return ret.toString();
    }
}