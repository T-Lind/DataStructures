package markov;

/**
 * @author TLInd
 * @version 1.00 2023/4/21
 */


public class FinalOutputs {
    private static final int N_SENTENCES = 4;

    public static void main(String[] args) {
        printParagraphFromSource(3, N_SENTENCES, "aristotle.txt");
        printParagraphFromSource(3, N_SENTENCES, "garfield.txt");
        printParagraphFromSource(3, N_SENTENCES, "jane-austen.txt");
        printParagraphFromSource(3, N_SENTENCES, "grimm.txt");
        printParagraphFromSource(3, N_SENTENCES, "shakespeare.txt");
        printParagraphFromSource(3, N_SENTENCES, "my-writing.txt");
        printParagraphFromSource(3, N_SENTENCES, "shakespeare-sonnets.txt");

        printSonnet( "shakespeare-sonnets.txt");
    }

    private static void printParagraphFromSource(int gen, int nSentences, String filename) {
        System.out.println("Source: " + filename);
        System.out.println("--------------------------");
        MarkovChain chain = switch (gen) {
            case 1 -> new MarkovChain("text\\" + filename);
            case 2 -> new MarkovChainGen2("text\\" + filename);
            default -> new MarkovChainGen3("text\\" + filename);
        };
        for (var i = 0; i < nSentences; i++)
            System.out.println(chain.generateSentence());
        System.out.println("--------------------------");
    }

    private static void printSonnet(String filename) {
        System.out.println("Source: " + filename);
        System.out.println("--------------------------");
        var chain = new MarkovChainSonnet("text\\" + filename);
        System.out.println(chain.generateSonnet());
        System.out.println("--------------------------");
    }
}