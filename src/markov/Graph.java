package markov;

import java.util.List;

public interface Graph<E> {
    boolean add(E node);


    boolean addEdge(E from, E to);

    boolean hasEdge(E from, E to);

    List<E> getNodes();

    List<E> getNeighbors(E node);

    boolean contains(E node);

    int size();

}