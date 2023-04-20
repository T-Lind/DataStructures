package graphs.markov;

import java.io.IOException;

public class MarkovChainTester
{
	public static void main(String[] args) throws IOException {
		//https://www.gutenberg.org/catalog/

		//Create a MarkovChain object
		var chain = new MarkovChain();

		//train the MarkovChain with some data
		chain.train("grimm.txt");


		//Generate some random sentences
		int num = 10;
		for (int i = 0; i < num; i++) {
			System.out.println(chain.generateSentence());
			System.out.println();
		}
	}
}
