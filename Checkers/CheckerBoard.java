import java.awt.*;

public class CheckerBoard {
   
   public static final int COLUMNS_AND_ROWS = 8;
   
   public static final int X_DIMENSION = 400;
   
   public static final int Y_DIMENSION = 400;
   
   // fields
   private DrawingPanel board;
   private Graphics g;
   private int[][] checkersGrid; // 0 = empty, 1 and 2 = regulars, 3 and 4 = kings
   private int whiteLeft;       // only kings can move backwards
   private int blackLeft;
   
   // constuctors
   //public CheckerBoard() {
   //   this(400, 400);
   //}
   
   public CheckerBoard() {
      board = new DrawingPanel(X_DIMENSION, Y_DIMENSION);
      board.setBackground(Color.BLACK);
      g = board.getGraphics();
      checkersGrid = new int[COLUMNS_AND_ROWS][COLUMNS_AND_ROWS];
      for (int i = 0; i < COLUMNS_AND_ROWS; i++) {// create board
         int offset = 0;
         int offsetArray = 0;
         if (i % 2 == 1) {
            offset = 50;
            offsetArray = 1;
         }
         for (int j = 0; j < 8; j = j + 2) {
            g.setColor(Color.RED);
            g.fillRect(50 * j + offset, 50 * i, 50, 50);
            if (i < 3) {
               placeChecker(g, 50 * j + offset, 50 * i, false, Color.WHITE);
               if (j != 8) {
                  checkersGrid[i][j + offsetArray] = 1;
               } else {
                  checkersGrid[i][j - 1 + offsetArray] = 1;
               }
               whiteLeft++;
            } else if (i > 4) {
               placeChecker(g, 50 * j + offset, 50 * i, false, Color.BLACK);
               if (j != 8) {
                  checkersGrid[i][j + offsetArray] = 2;
               } else {
                  checkersGrid[i][j - 1 + offsetArray] = 2;
               }
               blackLeft++;
            }
         }
      }
   }
   
   // instance methods
   public void placeChecker(Graphics g, int x, int y, boolean kingMe, Color color) {
      g.setColor(color);
      g.fillOval(x + 5, y + 5, 40, 40);
      if (kingMe) {
        // draw a crown
      }
   }
   
   public void makeEmptyPosition(Graphics g, int x, int y) {
      
   }
   
   public void createBoard() {
   }
   // method to check if game is over
   public boolean gameOver() {
      return whiteLeft == 0 || blackLeft == 0;
   }
   
   public int winner() {
      if (whiteLeft == 0) {
         return 1;
      }
      return 2;
   }
   
   public void editBoard() {
   
   }
}