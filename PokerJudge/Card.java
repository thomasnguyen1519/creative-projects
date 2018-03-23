
/*
    This Card class is a helper class for PokerJudge. A Card is a representation of a
    card from a standard 52 playing card deck. Implements the Comparable interface and
    compares two Cards based on their denomination ranking.

    @Author: ThomasNguyen
*/



import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class Card implements Comparable<Card> {

    public static final List<String> denomList = new ArrayList<String>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"));
    public static final List<String> suitList = new ArrayList<String>(Arrays.asList("S", "C", "D", "H"));

    private int suit;
    private int denomination;

    /*
        Default constructor that creates an Ace of Hearts Card.
    */
    public Card() {
        this("AH");
    }

    /*
        Constructor that initializes the Card to the provided String. Strings must
        be of the form "2S" for 2 of Spades or "JC" for Jack of Clubs. Will throw
        IllegalArgumentException error if the String was not valid.

        Params
            str: String representation of a Card
    */
    public Card(String str) {
        if (str.substring(0, 1).equals("1")) {
            this.denomination = denomList.indexOf(str.substring(0, 2));
            this.suit = suitList.indexOf(str.substring(2, 3));
        } else {
            this.denomination = denomList.indexOf(str.substring(0, 1));
            this.suit = suitList.indexOf(str.substring(1, 2));
        }
        if (this.suit == -1 || this.denomination == -1) {
            throw new IllegalArgumentException();
        }
    }

    /*
        Returns the suit of the Card as a String.

        Returns
            String: suit of the Card
    */
    public String getSuit() {
        return suitList.get(suit);
    }

    /*
        Returns the suit of the Card as an int.

        Returns
            int: suit of the Card
    */
    public int getSuitAsInt() {
        return suit;
    }

    /*
        Returns the denomination of the Card as an int.

        Returns
            int: denomination of the Card
    */
    public int getValueAsInt() {
        return denomination;
    }

    /*
        Returns the demonination of the Card as a String.

        Returns
            String: denomination of the Card
    */
    public String getValue() {
        return denomList.get(denomination);
    }

    /*
        Compares the denomination of this Card and another Card.

        Params
            other: Card to compare against

        Returns
            int: 1 if this > other, -1 if this < other, 0 otherwise (equal)
    */
    public int compareTo(Card other) {
        return (this.denomination > other.denomination) ? 1 :
               (this.denomination < other.denomination) ? -1 : 0;
    }
}
