import java.util.Scanner;
/*
 * This class manages the interaction between the user (player) and the TrollGame object.
 * It also keeps the player's toal games played, number of games won, and point total.
 */
public class TrollGameMain {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    String inputStr = " ";
    boolean keepPlaying = true;
    boolean gameOver = false;
    int numGamesPlayed = 0;
    int playerWinCount = 0;
    int playerTotalScore = 0;
   // troll wins in this configuration  
   // TrollGame game = new TrollGame(1239);
    // player can win  
    //TrollGame game = new TrollGame(123);
    // another seed for testing
    TrollGame game = new TrollGame(123892);
   // random version- to be used when delivering the game.  
   // TrollGame game = new TrollGame();
    System.out.println("Welcome to the Troll Game.");
    System.out.println(game.getGameStr());
	 while(keepPlaying){
      if(gameOver) {
         System.out.println("Enter Y to play again, X to quit.");
         inputStr = scan.nextLine();
         if(inputStr.equalsIgnoreCase("X"))
	         break;
         else if(inputStr.equalsIgnoreCase("Y")){
            game.resetGame();
            gameOver = false;
            System.out.println(game.getGameStr());
         }
      }
      else if(!gameOver) {
          System.out.println("Enter your move: u, d, r, l.");
          inputStr = scan.nextLine();
          game.movePlayer(inputStr);
          System.out.println(game.getGameStr());
          if(game.playerLoses()){
             System.out.println("Player loses!");
             numGamesPlayed++;
             gameOver = true;
             showGameSummary(numGamesPlayed, playerWinCount, playerTotalScore);
          }
          else if(game.playerWins()){
             System.out.println("Player wins!");
             playerWinCount++;
             numGamesPlayed++;
             playerTotalScore+=game.getTreasurePoints();
             gameOver = true;
             showGameSummary(numGamesPlayed, playerWinCount, playerTotalScore);
          }
          else {
             System.out.println("continue...");
          }
      } 
    }// end keep playing loop
    System.out.println("Thanks for playing!");
   }
  
   /* display the current user stats */
   public static void showGameSummary(int numGamesPlayed, int playerWinCount, int playerTotalScore){
      System.out.println("Total games played: "+numGamesPlayed + ", player wins: "+playerWinCount+ " player points: "+playerTotalScore);
   }
}