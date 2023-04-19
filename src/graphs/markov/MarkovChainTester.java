package graphs.markov;

import java.io.IOException;

public class MarkovChainTester
{
	public static void main(String[] args) throws IOException {
		//https://www.gutenberg.org/catalog/
		
		//Create a MarkovChain object
		var chain = new MarkovChain();
		
		//train the MarkovChain with some data
		chain.train("C:\\Users\\lindauer_927142\\IdeaProjects\\DataStructures\\src\\graphs\\markov\\grimm.txt");


		System.out.println(chain.getNextWords());

		//Generate some random sentences
//		int num = 10;
//		for(int i = 0; i < num; i++)
//		{
//			System.out.println(chain.generateSentence());
//			System.out.println();
//		}
	}
	
	/**
	 *TODO: add features to WordGraph
	 *
	 *	Constructor:
	 *		- Add [START] and [END] nodes to graph
	 *
	 *	Add the method 	public boolean isEndWord(String word)
	 *	
	 *	update addWord() method
	 *		- don't add empty string words to graph
	 *		- if lastWord is null, add an edge between [START] and newWord (or increment weight by one)
	 *		- if newWord is an endWord, add an edge to [END] and set lastWord to null
	 */	
}
