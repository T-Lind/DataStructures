package graphs;

import junit.framework.TestCase;

import java.util.List;
import java.util.Map;

public class Lab25Tester extends TestCase
{
	public void testAddAndSize()
	{
		//add some nodes to graph and compare to toString
		//add duplicate nodes
		Graph<String> g = new AdjacencyListGraph<String>();
		
		assertEquals("", "[]", g.toString());
		
		g.add("A");
		assertEquals("", "[A->[]]", g.toString());
		
		g.add("B");
		assertEquals("", "[A->[], B->[]]", g.toString());
		
		g.add("B");//duplicate should be ignored
		assertEquals("", "[A->[], B->[]]", g.toString());
		
		g.add("C");
		assertEquals("", "[A->[], B->[], C->[]]", g.toString());
	}
	
	public void testAddEdge()
	{
		//add some nodes and edges to graph and compare to toString
		Graph<String> g = new AdjacencyListGraph<String>();
		g.add("A");
		g.add("B");
		g.add("C");
		
		boolean ret = g.addEdge("A", "B");
		assertEquals("", "[A->[B], B->[], C->[]]", g.toString());
		
		ret = g.addEdge("A", "B");//ignore duplicate edge
		assertEquals("", "[A->[B], B->[], C->[]]", g.toString());
		
		ret = g.addEdge("B", "A");
		assertEquals("", "[A->[B], B->[A], C->[]]", g.toString());
		
		ret = g.addEdge("A", "C");
		assertEquals("", "[A->[B, C], B->[A], C->[]]", g.toString());
		
		//add edges to self
		ret = g.addEdge("C", "C");
		assertEquals("", "[A->[B, C], B->[A], C->[C]]", g.toString());
		
		//test if one node does not exist
		ret = g.addEdge("C", "D");
		assertEquals("", false, ret);
		assertEquals("", "[A->[B, C], B->[A], C->[C]]", g.toString());
		
		//test if neither nodes exists		
		ret = g.addEdge("E", "F");
		assertEquals("", false, ret);
		assertEquals("", "[A->[B, C], B->[A], C->[C]]", g.toString());
	}
	
	public void testSize()
	{
		Graph<String> g = new AdjacencyListGraph<String>();
		
		//test size with no nodes
		assertEquals("", 0, g.size());
		
		g.add("A");
		g.add("B");
		g.add("C");
		
		//test size with some nodes, no edges
		assertEquals("", 3, g.size());
		
		//test size after adding duplicate
		g.add("A");
		assertEquals("", 3, g.size());
		
		//test size with nodes and edges
		g.addEdge("A", "B");
		assertEquals("", 3, g.size());
	}

	public void testHasEdge()
	{
		//add nodes and edges
		Graph<String> g = new AdjacencyListGraph<String>();
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		
		g.addEdge("A", "B");
		g.addEdge("A", "C");
		g.addEdge("B", "A");
		g.addEdge("C", "C");
		
		//test hasEdge (when edge exists)
		assertEquals("", true, g.hasEdge("A", "B"));
		
		//test hasEdge (when edge does not exist)
		assertEquals("", false, g.hasEdge("C", "A"));
		
		//test hasEdge (when node has no edges at all)
		assertEquals("", false, g.hasEdge("A", "D"));
		assertEquals("", false, g.hasEdge("D", "A"));
		
		//test hasEdge when one node doesn't exist
		assertEquals("", false, g.hasEdge("A", "E"));
		
		//test hasEdge when both nodes don't exist
		assertEquals("", false, g.hasEdge("E", "F"));
	}
	
	public void testGetNodes()
	{
		Graph<String> g = new AdjacencyListGraph<String>();
			
		//test getNodes with no nodes added to graph
		assertEquals("", "[]", g.getNodes().toString());
		
		//add some nodes to graph
		g.add("A");
		g.add("B");
		g.add("C");
		
		assertEquals("", "[A, B, C]", g.getNodes().toString());	
			
		g.addEdge("A", "B");
		g.addEdge("C", "C");
		assertEquals("", "[A, B, C]", g.getNodes().toString());	
	}
	
	public void testGetNeighbors()
	{
		Graph<String> g = new AdjacencyListGraph<String>();
		
		//test getNeighbors with no nodes added to graph
		assertEquals("", "[]", g.getNeighbors("A").toString());
		
		//test getNeighbors with some nodes added, no edges
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		
		assertEquals("", "[]", g.getNeighbors("A").toString());
		
		//test getNeighbors with nodes and edges
		g.addEdge("A", "B");
		g.addEdge("A", "C");
		g.addEdge("B", "A");
		g.addEdge("B", "C");
		g.addEdge("C", "C");
		assertEquals("", "[A, C]", g.getNeighbors("B").toString());
		
		//test getNeighbors with self-edge
		assertEquals("", "[C]", g.getNeighbors("C").toString());
		
		//test if modifying list modifies neighbors
		List<String> neighbors = g.getNeighbors("A");
		neighbors.add("D");
		assertEquals("getNeighbors should return a COPY of the list of neighbors! Otherwise nefarious computer science teachers can modify the neighbor list without called the addEdge method!", "[B, C]", g.getNeighbors("A").toString());
	}
	
	public void testContains()
	{
		Graph<String> g = new AdjacencyListGraph<String>();
		
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		
		g.addEdge("A", "B");
		g.addEdge("A", "C");
		g.addEdge("B", "A");
		g.addEdge("B", "C");
		g.addEdge("C", "C");
		
		//test if node is not in graph
		assertEquals("", true, g.contains("A"));
		assertEquals("", true, g.contains("B"));
		assertEquals("", true, g.contains("C"));
		assertEquals("", true, g.contains("D"));
		
		//test if node is in graph
		assertEquals("", false, g.contains("E"));
	}
	
	////////////////////////
	//Weighted Graph Tests//
	////////////////////////
	public void testWeightedGraph()
	{
		WeightedGraph<String> g = new WeightedAdjacencyListGraph<String>();
		
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		
		g.addEdge("A", "B");
		g.addEdge("A", "C");
		g.addEdge("B", "A");
		g.addEdge("B", "C");
		g.addEdge("C", "C");
		
		//set some weights
		g.setWeight("A", "B", 1);
		g.setWeight("C", "C", 2);
		
		assertEquals("", "[A->[B(1), C(0)], B->[A(0), C(0)], C->[C(2)], D->[]]", g.toString());

		//get weights that were set
		assertEquals("", 1, g.getWeight("A", "B"));
		assertEquals("", 2, g.getWeight("C", "C"));
		assertEquals("", 0, g.getWeight("A", "C"));
		
		//test setting weight to non-existing nodes
		//test setting weight to non-existing edges
		g.setWeight("C", "A", 3);
		g.setWeight("E", "F", 4);
		
		//test getting weight from non-existing nodes
		//test getting weight from non-existing edges
		assertEquals("", 0, g.getWeight("C", "A"));
		assertEquals("", 0, g.getWeight("E", "F"));
	}
	
	public void testGetNeighborWeights()
	{
		WeightedGraph<String> g = new WeightedAdjacencyListGraph<String>();
		
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		
		g.addEdge("A", "B");
		g.addEdge("A", "C");
		g.addEdge("B", "A");
		g.addEdge("B", "C");
		g.addEdge("C", "C");
		
		//set some weights
		g.setWeight("A", "B", 1);
		g.setWeight("C", "C", 2);
		
		Map<String, Integer> weights = g.getNeighborWeights("A");
		
		assertEquals("getNeighborWeights should never return null", true, null!=weights);
		assertEquals("", "1", ""+weights.get("B"));
		assertEquals("", ""+null, ""+weights.get("A"));
		
		weights = g.getNeighborWeights("D");
		assertEquals("getNeighborWeights should never return null", true, null!=weights);
	}
}