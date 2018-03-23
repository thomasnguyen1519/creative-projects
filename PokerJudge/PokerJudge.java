
/*
    PokerJudge is a program that computes the better of two poker hands, using standard
    poker rules. The behavior of this program assumes that one 52 card deck is used and
    therefore, no duplicate cards may be in a hand.

    @Author: ThomasNguyen
*/



import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.*;


public class PokerJudge {

    public static final int HAND_SIZE = 5;
    public static final int NUM_DENOMS = 13;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        intro();
        String handOne = getHand(input, "first");
        String handTwo = getHand(input, "second");
        Card[] firstHand = parseCards(handOne);
        Card[] secondHand = parseCards(handTwo);
        int winner = compareHands(firstHand, secondHand);
        if (winner == 1) {
            System.out.println("Hand one wins");
        } else if (winner == -1) {
            System.out.println("Hand two wins");
        } else {
            System.out.println("Hands tie");
        }
    }

    /*
        Converts a String representation of a poker hand into an array of Cards.

        Params
            hand: String representation of a poker hand

        Returns
            Card[]: stores the poker hand

    */
    private static Card[] parseCards(String hand) {
        Card[] cardHand = new Card[HAND_SIZE];
        hand = hand.toUpperCase();
        int index = 0;
        for (int i = 0; i < hand.length(); i = i + 2) {
            String str = "";
            if (hand.charAt(i) == '1') {
                str += hand.charAt(i);
                i++;
            }
            cardHand[index++] = new Card(str + hand.charAt(i) + hand.charAt(i + 1));
        }
        return cardHand;
    }

    /*
        Computes the better of two poker hands, using the standard poker hand heirarchy.

        Params
            firstHand: Card[] representation of the first poker hand
            secondHand: Card[] representation of the second poker hand

        Returns
            int: 1 if firstHand wins, -1 if secondHand wins, 0 if tie.
    */
    public static int compareHands(Card[] firstHand, Card[] secondHand) {
        int[] firstHandAsInt = new int[HAND_SIZE];
        int[] secondHandAsInt = new int[HAND_SIZE];
        int[] firstCounts = new int[NUM_DENOMS];
        int[] secondCounts = new int[NUM_DENOMS];
        int[] firstSuits = new int[HAND_SIZE];
        int[] secondSuits = new int[HAND_SIZE];
        for (int i = 0; i < HAND_SIZE; i++) {
            firstHandAsInt[i] = firstHand[i].getValueAsInt();
            secondHandAsInt[i] = secondHand[i].getValueAsInt();
            firstSuits[i] = firstHand[i].getSuitAsInt();
            secondSuits[i] = secondHand[i].getSuitAsInt();
            firstCounts[firstHand[i].getValueAsInt()]++;
            secondCounts[secondHand[i].getValueAsInt()]++;
        }
        Arrays.sort(firstHandAsInt);
        Arrays.sort(secondHandAsInt);
        int maxMulti1 = 1;
        int maxMulti2 = 1;
        for (int i = 0; i < NUM_DENOMS; i++) {
            maxMulti1 = Math.max(maxMulti1, firstCounts[i]);
            maxMulti2 = Math.max(maxMulti2, secondCounts[i]);
        }
        int handOneRank = findBestRank(firstCounts, firstSuits, firstHandAsInt, maxMulti1);
        int handTwoRank = findBestRank(secondCounts, secondSuits, secondHandAsInt, maxMulti2);
        return (handOneRank < handTwoRank) ? 1 : (handOneRank > handTwoRank) ? -1 :
               tieBreakerSameRank(firstHandAsInt, secondHandAsInt, firstCounts,
                                  secondCounts, handOneRank);
    }

    /*
        Computes the tiebreaker between two hands of the same rank.

        Params
            hand1: int[] representation of the first poker hand
            hand2: int[] representation of the second poker hand
            hand1Counts: int[] of the counts of denominations in hand1
            hand2Counts: int[] of the counts of denominations in hand2
            rank: int of the index that the hands tied at

        Returns
            int: 1 if first hand wins, -1 if second hand wins, 0 if tie
    */
    private static int tieBreakerSameRank(int[] hand1, int[] hand2, int[] hand1Counts,
                                          int[] hand2Counts, int rank) {
        if (rank == 0 || rank == 1 || rank == 4 || rank == 5 || rank == 9) {
            return highCard(hand1, hand2);
        } else {
            List<Integer> l1 = IntStream.of(hand1Counts).boxed().collect(Collectors.toList());
            List<Integer> l2 = IntStream.of(hand2Counts).boxed().collect(Collectors.toList());
            if (rank == 2) {
;               return compareHighMultiples(l1, l2, 4, 1);
            } else if (rank == 3) {
                return compareHighMultiples(l1, l2, 3, 2);
            } else if (rank == 6) {
                return compareHighMultiples(l1, l2, 3, 1);
            } else if (rank == 8) {
                return compareHighMultiples(l1, l2, 2, 1);
            } else { // compare two pair hands
                return compareHighMultiples(l1, l2, 2, 2);
            }
        }
    }
    
    /*
        Compares two poker hands that both had high multiples of a denomination.

        Params
            l1: int[] representation of the first poker hand
            l2: int[] representation of the second poker hand
            numHigh: int of the higher mutliple
            numLow: int of the multiple of the other card(s) in the hand

        Returns
            int: 1 if first hand wins, -1 if second hand wins, 0 if tie
    */
    private static int compareHighMultiples(List<Integer> l1, List<Integer> l2,
                                            int numHigh, int numLow) {
        int highCard1 = l1.lastIndexOf(numHigh);
        int highCard2 = l2.lastIndexOf(numHigh);
        int kicker1 = l1.indexOf(numLow);
        int kicker2 = l2.indexOf(numLow);
        if (numLow == 2) { // compares 2 pair hands (full house falls into this case too)
            int lastCard1 = l1.indexOf(1);
            int lastCard2 = l2.indexOf(1);
            return (highCard1 > highCard2) ? 1 : (highCard1 < highCard2) ? -1 :
                   (kicker1 > kicker2) ? 1 : (kicker1 < kicker2) ? -1 :
                   (lastCard1 > lastCard2) ? 1 : (lastCard1 < lastCard2) ? -1 : 0;
        } else if (numLow == 1) { // compare the kickers of 3 of a kind and pair
            if (numHigh == 3) {
                int lastCard1 = l1.lastIndexOf(1);
                int lastCard2 = l2.lastIndexOf(1);
                return (highCard1 > highCard2) ? 1 : (highCard1 < highCard2) ? -1 :
                       (lastCard1 > lastCard2) ? 1 : (lastCard1 < lastCard2) ? -1 :
                       (kicker1 > kicker2) ? 1 : (kicker1 < kicker2) ? -1 : 0;
            } else {
                int lastCard1 = l1.lastIndexOf(1);
                int lastCard2 = l2.lastIndexOf(1);
                l1.remove(lastCard1);
                l2.remove(lastCard2);
                int nextCard1 = l1.lastIndexOf(1);
                int nextCard2 = l2.lastIndexOf(1);
                return (highCard1 > highCard2) ? 1 : (highCard1 < highCard2) ? -1 :
                       (lastCard1 > lastCard2) ? 1 : (lastCard1 < lastCard2) ? -1 :
                       (nextCard1 > nextCard2) ? 1 : (nextCard1 < nextCard2) ? -1 :
                       (kicker1 > kicker2) ? 1 : (kicker1 < kicker2) ? -1 : 0;
            }
        }
        return (highCard1 > highCard2) ? 1 : (highCard1 < highCard2) ? -1 :
               (kicker1 > kicker2) ? 1 : (kicker1 < kicker2) ? -1 : 0;
    }

    /*
        Computes the best possible ranking of a poker hand.

        Params
            firstHand: int[] representation of the first poker hand
            secondHand: int[] representation of the second poker hand

        Returns
            int: first truth index of the ranking array i.e. best ranking of the hand
    */
    private static int findBestRank(int[] handCounts, int[] suitCounts,
                                    int[] intHand, int maxMulti) {
        boolean[] ranks = new boolean[9];
        // [royal flush, straight flush, four of a kind, full house, flush, straight, three of a kind, two pair, pair]

        if (maxMulti > 1) {
            if (maxMulti >= 4) {
                ranks[2] = true;
            } else if (maxMulti == 3) {
                ranks[6] = true;
                ranks[3] = checkFullHouse(handCounts);
            } else {
                ranks[8] = true;
                ranks[7] = checkTwoPair(handCounts);
            }
        } else {
            ranks[5] = checkStraight(intHand);
            if (ranks[5] && intHand[0] == 0 && intHand[4] == 12) { // if ace was used as low card
                for (int i = 0; i < HAND_SIZE - 1; i++) {
                    intHand[i + 1] = intHand[i];
                }
                intHand[0] = -1;
            }
            List<Integer> list = IntStream.of(suitCounts).boxed().collect(Collectors.toList());
            Set<Integer> uniqSuits = new HashSet<Integer>(list);
            if (uniqSuits.size() == 1) { // check if flush
                ranks[4] = true;
                if (ranks[5]) { // if hand is a straight
                    ranks[1] = true;
                    ranks[0] = intHand[4] == 12; // check high card for ace (royal flush)
                }
            }
        }
        int index = 0;
        while (index < ranks.length && ranks[index] == false) { // find the first rank
            index++;
        }
        return index;
    }

    /*
        Checks if a poker hand contains a straight.

        Params
            intHand: int[] of the poker hand

        Returns
            boolean: true if the hand is a straight, false otherwise
    */
    private static boolean checkStraight(int[] intHand) {
        List<Integer> hand = IntStream.of(intHand).boxed().collect(Collectors.toList());
        if (intHand[0] == 0 && intHand[4] == 12) {
            hand.remove(4);
            hand.add(0, -1);
        }
        int prev = hand.get(0);
        for (int i = 1; i < HAND_SIZE; i++) {
            int curr = hand.get(i);
            if (prev + 1 != curr) {
                return false;
            }
            prev = curr;
        }
        return true;
    }

    /*
        Checks if a three of a kind hand also had a pair.

        Params
            handCounts: int[] of the counts of each denomination

        Returns
            boolean: true if there was a pair, false otherwise
    */
    private static boolean checkFullHouse(int[] handCounts) {
        for (int i = 0; i < handCounts.length; i++) {
            if (handCounts[i] == 2) {
                return true;
            }
        }
        return false;
    }

    /*
        Checks if a pair hand had an additional pair.

        Params
            handCounts: int[] of the counts of each denomination

        Returns
            boolean: true if there was another pair, false otherwise
    */
    private static boolean checkTwoPair(int[] handCounts) {
        int pairCount = 0;
        for (int i = 0; i < handCounts.length; i++) {
            if (handCounts[i] == 2) {
                pairCount++;
                if (pairCount == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
        Helper method to compute the tiebreaker by means of the high Card of a poker hand.

        Params
            firstHand: int[] representation of the first poker hand
            secondHand: int[] representation of the second poker hand

        Returns
            int: 1 if firstHand wins, -1 if secondHand wins, 0 if tie
    */
    private static int highCard(int[] firstHand, int[] secondHand) {
        for (int i = HAND_SIZE - 1; i >= 0; i--) {
            if (firstHand[i] > secondHand[i]) {
                return 1;
            } else if (firstHand[i] < secondHand[i]) {
                return -1;
            }
        }
        return 0;
    }

    /*
        Outputs description of the program.

        Params
            N/A

        Returns
            N/A
    */
    private static void intro() {
        System.out.println("Welcome! PokerJudge is a program that computes the better of");
        System.out.println("two provided poker hands in terms of standard poker rules.");
        System.out.println("Each hand should be represented by a String of valid format");
        System.out.println("and size of a standard poker hand i.e. each card will be");
        System.out.println("represented as such, \"2S\" for 2 of Spades or \"JC\" for");
        System.out.println("Jack of Clubs, and there should be five of these in each");
        System.out.println("of the Strings.");
        System.out.println();
    }

    /*
        Retrieves the poker hand from client input.

        Params
            input: Scanner to System.in
            order: String of the hand order

        Returns
            String: representation of the provided poker hand
    */
    private static String getHand(Scanner input, String order) {
        System.out.print("What is the " + order + " hand? ");
        String hand = input.nextLine();
        return hand;
    }
}
