package graphs;

public class WeightedGraphTester {

    public static void main(String[] args) 
    {
    	//create a graph object to test
    	WeightedGraph<String> graph = new WeightedAdjacencyListGraph<String>();
    	
    	//add nodes to the graph
    	graph.add("A");
    	graph.add("B");
    	graph.add("C");
    	
    	//add edges to the graph
    	graph.addEdge("A", "B");
    	graph.addEdge("B", "A");
    	graph.addEdge("A", "C");
    	graph.addEdge("B", "C");
    	
    	//add weights to the graph
    	graph.setWeight("A", "B", 7);

    	//print the grapha
    	System.out.println(graph);
    }
}