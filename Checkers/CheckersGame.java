import java.util.*;

public class CheckersGame {
   public static void main(String[] args) {
      description();
      
      Scanner console = new Scanner(System.in);
      String player1 = whoIsWho(console, 1);
      String player2 = whoIsWho(console, 2);
      int firstTurn = whoGoesFirst(player1, player2);
      CheckerBoard board = new CheckerBoard();
      while (!board.gameOver()) {
         
         
         

      }
      String winner = player1;
      if (board.winner() == 1) {
         winner = player2;
      }
      System.out.println("The game was won by " + winner);
   }
   
   public static String whoIsWho(Scanner console, int playerNum) {
      System.out.print("Who is player #" + playerNum + "? ");
      String name = console.next();
      return name;
   }
   
   public static int whoGoesFirst(String player1, String player2) {
      System.out.println();
      Random r = new Random();
      int num = r.nextInt(2);
      System.out.println("Randomly choosing who gets to go first...");
      if (num == 0) {
         System.out.println(player1 + " goes first.");
      } else {
         System.out.println(player2 + " goes first.");
      }
      return num;
   }
   
   public static void description() {
      System.out.println("This program is a digital rendition");
      System.out.println("of the game of checkers. It is");
      System.out.println("designed for two human players.");
      System.out.println();
   }
   
   // method to prompt user for what to move and where to move
}