package graphs;

import junit.framework.TestCase;

public class Lab26Tester extends TestCase
{
	public void testWordGraph()
	{
		WordGraph wg = new WordGraph();
    	
    	//Process a string
    	//String str = "One ring to rule them all, one ring to find them. One ring to bring them all and in the darkness bind them.";
    	String str = "How much wood would a woodchuck chuck if a woodchuck could chuck wood";
    	wg.processString(str);
    	
    	//Output the graph so you can see the word associations and weights
    	assertEquals("", "[How->[much(1)], a->[woodchuck(2)], would->[a(1)], woodchuck->[chuck(1), could(1)], could->[chuck(1)], wood->[would(1)], chuck->[if(1), wood(1)], if->[a(1)], much->[wood(1)]]", wg.getGraph().toString());
	}
	
	public void testAnotherWordGraph()
	{
		WordGraph wg = new WordGraph();
    	
    	//Process a string
    	//String str = "One ring to rule them all, one ring to find them. One ring to bring them all and in the darkness bind them.";
    	String str = "In a hole in the ground there lived a hobbit. It was a hobbit hole and that means confort.";
    	wg.processString(str);
    	
    	//Output the graph so you can see the word associations and weights
    	assertEquals("", "[a->[hole(1), hobbit.(1), hobbit(1)], means->[confort.(1)], In->[a(1)], in->[the(1)], was->[a(1)], It->[was(1)], confort.->[], hole->[in(1), and(1)], the->[ground(1)], hobbit->[hole(1)], that->[means(1)], and->[that(1)], there->[lived(1)], hobbit.->[It(1)], ground->[there(1)], lived->[a(1)]]", wg.getGraph().toString());
	}
	
	public void testEndWords()
	{
		WordGraph wg = new WordGraph();
    	
    	//Process a string
    	//String str = "One ring to rule them all, one ring to find them. One ring to bring them all and in the darkness bind them.";
    	String str = "a a. a? a! a\" a'";
    	wg.processString(str);
    	
    	//Output the graph so you can see the word associations and weights
    	assertEquals("", "[a!->[a\"(1)], a->[a.(1)], a\"->[a'(1)], a'->[], a.->[a?(1)], a?->[a!(1)]]", wg.getGraph().toString());
	}
}