



public class BlackJackDeck {

    public final String[] key = {"2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K", "A", "10"};

    private Map<Character, Integer> counts;
    private int numCards;

    public BlackJackDeck() {
        this(52);
    }

    public BlackJackDeck(int num) {
        counts = initDeckCounts(num);
    }


    private Map<Character, Integer> initDeckCounts(int n) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();


    }
}
