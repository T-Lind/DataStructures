package graphs;

public class GraphTester {

    public static void main(String[] args) 
    {
    	//create a graph object to test
    	Graph<String> graph = new AdjacencyListGraph<String>();
    	
    	//add nodes to the graph
    	graph.add("A");
    	graph.add("B");
    	graph.add("C");
    	
    	//add edges to the graph
    	graph.addEdge("A", "B");
    	graph.addEdge("B", "A");
    	graph.addEdge("A", "C");
    	graph.addEdge("B", "C");
    	
    	//print the grapha
    	System.out.println(graph);
    }
}