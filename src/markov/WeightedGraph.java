package markov;

import java.util.Map;

/**
 * Creates a weighted graph for use in markov chains
 * @param <E> The type of data to store in the graph
 */
public interface WeightedGraph<E> extends Graph<E> {
    void setWeight(E from, E to, int weight);
    int getWeight(E from, E to);
    Map<E, Integer> getNeighborWeights(E node);
}