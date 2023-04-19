package graphs.markov;

import graphs.WordGraph;

import java.io.IOException;

public class MarkovChainGen2 extends MarkovChain
{
	//INSTANCE VARIABLES
	/**
	 *	This holds relationships between pairs-of-words -> next-word
	 */
	private WordGraph wgPairs;

	//CONSTRUCTOR
    public MarkovChainGen2() 
    {
    	//Initialize Instance Variables
    }
    
    //METHODS
    
    @Override
    public void train(String filename) throws IOException {
    	super.train(filename); //tell the MarkovChain super class to train() like it normally does
    	
    	//TODO: do some magic to store 2nd order markov chain data
    	
    }
    
    
    //TODO: override any other other methods needed to generate 2nd Degree Markov Chains
    
}