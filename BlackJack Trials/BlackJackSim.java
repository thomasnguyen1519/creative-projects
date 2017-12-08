


import java.util.*;
import java.io.*;


/*
    This is a simple program that will simulate trials of Blackjack games
    based on a decision rule that the client provides. Statistics will be
    outputted every 10% interval. If the client does not provide the
    preferences, the program will assume the defaults.
*/

public class BlackJackSim {

    public static final int NUM_DECKS  = 6;
    public static final boolean SPAN_21 = false;
    public static final int NUM_TRIALS = 1000;


    public static void main(String[] args) {
        Map<String, List<INTEGER>> key = new HashMap<String, List<INTEGER>>();
        String settings[6] = {SPAN_21, NUM_DECKS, };
        welcomeMessage();
        setPrefs(settings);
        initializeValMap(key);
        performTrials(settings);
    }


    public static void welcomeMessage() {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello and welome, guest. This is program simulates");
        System.out.println("the statistical outcomes of 21 Blackjack games based");
        System.out.println("on a provided decision rule by you.");
        System.out.println();
        System.out.println("As of now, there is not much functionality... But");
        System.out.println("more and more features will come.");
        System.out.println();
        System.out.println("Now we will set some values for the program.");
    }

    public static void setPrefs(String[] defaults) {
        Scanner input = new Scanner(System.in);
        System.out.println("Are we playing Spanish 21? (\"true\" or \"false\") ");
        String s21flag = input.next();
        System.out.println("How many trials do you want to perform? ");
        int amount = input.nextInt();
        System.out.println("How many decks are there? ");
        int decks = input.nextInt();
        System.out.println("What upcard do you stand on? ");
        String upcard = input.next();
        System.out.println("When will you stop hitting? ");
        String stop = input.next();
        System.out.println("Great!");
        System.out.println("Beginning trials now...");
        System.out.println();
    }

    public static void initializeValMap(Map<<CHARACTER>, <INTEGER>> vals) {

    }

    public static void peformTrials(String[] settings, values, ) {
        Map<<CHARACTER>, <INTEGER>> deckCount = new HashMap<<CHARACTER>, <INTEGER>>();
        int wins = 0;
        int losses = 0
        int pushed = 0;
        for(int i = 0; i < settings[0]; i++) {
            int tenth = ((args[0] / 10) != 0 ) ? args[0] / 10 : i;
            if (i % tenth == 0) {
                printStats(i, wins, losses);
            }
        }
    }


    public static void printStats(int wins, ) {
        int dummy = 0;
        System.out.println("Number of hands played: " + dummy);
        System.out.println("Wins: " + dummy);
        System.out.println("Losses: " + dummy);
        System.out.println("Pushed: " + dummy);
        System.out.println("Percent Won: " + dummy);
    }
}
