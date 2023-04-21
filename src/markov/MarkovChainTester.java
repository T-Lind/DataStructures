package markov;

public class MarkovChainTester {
    private static final int N_SENTENCES = 2;

    public static void main(String[] args) {
        //https://www.gutenberg.org/catalog/
        var testFile = "text\\jane-austen.txt";

        // Test 1st order markov chain
        var chain = new MarkovChain();
        chain.train(testFile);
        for (var i = 0; i < N_SENTENCES; i++)
            System.out.println(chain.generateSentence());
        System.out.println();

        // Test 2nd order markov chain
        var chain2 = new MarkovChainGen2();
        chain2.train(testFile);
        for (var i = 0; i < N_SENTENCES; i++)
            System.out.println(chain2.generateSentence());
        System.out.println();

        // Test 3rd order markov chain
        var chain3 = new MarkovChainGen3();
        chain3.train(testFile);
        for (var i = 0; i < N_SENTENCES; i++)
            System.out.println(chain3.generateSentence());

    }
}
