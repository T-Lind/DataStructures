package graphs;

public class WordGraphTester
{

    public static void main(String[] args) 
    {
    	//Create a word graph object
    	WordGraph wg = new WordGraph();
    	
    	//Process a string
    	String str = "One ring to rule them all, one ring to find them. One ring to bring them all and in the darkness bind them.";
    	wg.processString(str);
    	
    	//Output the graph so you can see the word associations and weights
    	System.out.println(wg.getGraph());
    }
    
    
}