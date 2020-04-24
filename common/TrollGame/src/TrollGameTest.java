import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;
import java.util.Random;

@SuppressWarnings("unchecked")
public class TrollGameTest {

   private TrollGame seededGame;
   private TrollGame seededGame2;
    private TrollGame seededGame3;
   private TrollGame randGame;
   private static final int INIT_PLAYER_POINTS = 160;
   private static final int PLAYER_POINTS_DEC = -10;
   private static final int TREASURE_POINTS = 500;
   private static final int ROWS = 8;
   private static final int COLS = 10;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
       seededGame = new TrollGame(1239);//troll is at 6,2.
       seededGame2 = new TrollGame(123);//player can win with troll row moves.
       seededGame3 = new TrollGame(991123344);//player can win with troll col moves.
       randGame = new TrollGame();
   }
   
   /** Test that the instance variables have been initialized to 
       the correct values in both constructors. **/
       
      @Test 
     public void initCurPlayerRowValTest1()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = seededGame.getClass().getDeclaredField("curPlayerRow");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      int curPlayerRow = (int)targetObj;
      assertEquals("Test 1: curPlayerRow is initialized to 0.", 0, curPlayerRow);
   }
   
      @Test 
   public void initCurPlayerRowValTest2()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = randGame.getClass().getDeclaredField("curPlayerRow");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(randGame);
      int curPlayerRow = (int)targetObj;
      assertEquals("Test 2: curPlayerRow is initialized to 0- unseeded constructor.", 0, curPlayerRow);
   }
   
      @Test 
   public void initCurPlayerColValTest1()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = seededGame.getClass().getDeclaredField("curPlayerCol");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      int curPlayerRow = (int)targetObj;
      assertEquals("Test 3: curPlayerCol is initialized to 0.", 0, curPlayerRow);
   }
   
      @Test 
   public void initCurPlayerColValTest2()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = randGame.getClass().getDeclaredField("curPlayerCol");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(randGame);
      int curPlayerRow = (int)targetObj;
      assertEquals("Test 4: curPlayerCol is initialized to 0- unseeded constructor.", 0, curPlayerRow);
   }
   
         @Test 
   public void initPlayerWinsValTest1()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = seededGame.getClass().getDeclaredField("playerWins");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      boolean playerWins = (boolean)targetObj;
      assertEquals("Test 5: playerWins is initialized to false.", false, playerWins);
   }
   
         @Test 
   public void initPlayerWinsValTest2()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = randGame.getClass().getDeclaredField("playerWins");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(randGame);
      boolean playerWins = (boolean)targetObj;
      assertEquals("Test 6: playerWins is initialized to false- unseeded constructor.", false, playerWins);
   }
   
         @Test 
   public void initPlayerLoosesValTest1()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = seededGame.getClass().getDeclaredField("playerLoses");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      boolean playerLoses = (boolean)targetObj;
      assertEquals("Test 7: playerLoses is initialized to false.", false, playerLoses);
   }
   
         @Test 
   public void initPlayerLoosesValTest2()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = randGame.getClass().getDeclaredField("playerLoses");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(randGame);
      boolean playerLoses = (boolean)targetObj;
      assertEquals("Test 8: playerLoses is initialized to false- unseeded constructor.", false, playerLoses);
   }
   
         @Test 
   public void initRandomTest1()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = seededGame.getClass().getDeclaredField("rand");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      Random randObj = (Random)targetObj;
      assertNotEquals("Test 9: rand should be initialized to an instance of Random.", null, randObj);
   }  
   
         @Test 
   public void initRandomTest2()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = randGame.getClass().getDeclaredField("rand");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(randGame);
      Random randObj = (Random)targetObj;
      assertNotEquals("Test 10: rand should be initialized to an instance of Random- unseeded constructor.", null, randObj);
   } 
   
   @Test 
   public void initGameBoardRowsTest1()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = seededGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] board = (GamePiece[][])targetObj;
      int actualRows = board.length; 
      assertEquals("Test 11: The gameBoard should be initialized to "+ROWS+"rows.", ROWS, actualRows);
   }
   
   @Test 
   public void initGameBoardRowsTest2()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = randGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(randGame);
      GamePiece[][] board = (GamePiece[][])targetObj;
      int actualRows = board.length; 
      assertEquals("Test 12: The gameBoard should be initialized to "+ROWS+"rows- unseeded constructor.", ROWS, actualRows);
   }
   
   @Test 
   public void initGameBoardColsTest1()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = seededGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] board = (GamePiece[][])targetObj;
      int actualCols = board[0].length; 
      assertEquals("Test 13: The gameBoard should be initialized to "+COLS+"columns.", COLS, actualCols);
   }
   
   @Test 
   public void initGameBoardColsTest2()throws NoSuchFieldException, 
		SecurityException, IllegalArgumentException, IllegalAccessException { 
      int actualLen = 0;
      Field field = randGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(randGame);
      GamePiece[][] board = (GamePiece[][])targetObj;
      int actualCols = board[0].length; 
      assertEquals("Test 14: The gameBoard should be initialized to "+COLS+"columns- unseeded constructor.", COLS, actualCols);
   }
   
   @Test 
   public void initBoardTestRows1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("initBoard", new Class[]{Integer.TYPE,Integer.TYPE,Random.class});
         methodToExecute.setAccessible(true);
         Object gameBoardObj = methodToExecute.invoke(seededGame, new Object[]{8,10,new Random(12398)});
	      GamePiece[][] gameBoard = (GamePiece[][])gameBoardObj; 
         int result = gameBoard.length;
      assertEquals("Test 15: initBoard test- correct rows.", ROWS, result);
   }  
   
   @Test 
   public void initBoardTestCols1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("initBoard", new Class[]{Integer.TYPE,Integer.TYPE,Random.class});
         methodToExecute.setAccessible(true);
         Object gameBoardObj = methodToExecute.invoke(seededGame, new Object[]{ROWS,COLS,new Random(12398)});
	      GamePiece[][] gameBoard = (GamePiece[][])gameBoardObj; 
         int result = gameBoard[0].length;
      assertEquals("Test 16: initBoard test- correct cols.", COLS, result);
   }
   
      @Test 
   public void initBoardTestEmptyPiece1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("initBoard", new Class[]{Integer.TYPE,Integer.TYPE,Random.class});
         methodToExecute.setAccessible(true);
         Object gameBoardObj = methodToExecute.invoke(seededGame, new Object[]{ROWS,COLS,new Random(12398)});
	      GamePiece[][] gameBoard = (GamePiece[][])gameBoardObj; 
         GamePiece emptyPiece = gameBoard[3][6];// a "random" location that isn't troll, player, treasure.
         boolean result = (emptyPiece instanceof EmptyPiece);
      assertEquals("Test 17: initBoard test- instance of EmptyPiece present.", true, result);
   }  
   
   @Test 
   public void initBoardTestInitPlayerPos1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("initBoard", new Class[]{Integer.TYPE,Integer.TYPE,Random.class});
         methodToExecute.setAccessible(true);
         Object gameBoardObj = methodToExecute.invoke(seededGame, new Object[]{ROWS,COLS,new Random(12398)});
	      GamePiece[][] gameBoard = (GamePiece[][])gameBoardObj; 
         GamePiece playerPiece = gameBoard[0][0];
         boolean result = (playerPiece instanceof Player);
      assertEquals("Test 18: initBoard test- correct player position.", true, result);
   } 
   
   @Test 
   public void initBoardTestInitPlayerPoints1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("initBoard", new Class[]{Integer.TYPE,Integer.TYPE,Random.class});
         methodToExecute.setAccessible(true);
         Object gameBoardObj = methodToExecute.invoke(seededGame, new Object[]{ROWS,COLS,new Random(12398)});
	      GamePiece[][] gameBoard = (GamePiece[][])gameBoardObj; 
         GamePiece playerPiece = gameBoard[0][0];
         int result = playerPiece.getLifePoints();
      assertEquals("Test 19: initBoard test- correct player points.", INIT_PLAYER_POINTS, result);
   }
   
   @Test 
   public void initBoardTestInitTreasurePos1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("initBoard", new Class[]{Integer.TYPE,Integer.TYPE,Random.class});
         methodToExecute.setAccessible(true);
         Object gameBoardObj = methodToExecute.invoke(seededGame, new Object[]{ROWS,COLS,new Random(12398)});
	      GamePiece[][] gameBoard = (GamePiece[][])gameBoardObj; 
         GamePiece treasurePiece = gameBoard[7][9];
         boolean result = (treasurePiece instanceof Treasure);
      assertEquals("Test 20: initBoard test- correct treasure position.", true, result);
   } 
   
   @Test 
   public void initBoardTestInitTreasurePoints1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("initBoard", new Class[]{Integer.TYPE,Integer.TYPE,Random.class});
         methodToExecute.setAccessible(true);
         Object gameBoardObj = methodToExecute.invoke(seededGame, new Object[]{ROWS,COLS,new Random(12398)});
	      GamePiece[][] gameBoard = (GamePiece[][])gameBoardObj; 
         GamePiece treasurePiece = gameBoard[7][9];
         int result = treasurePiece.getLifePoints();
      assertEquals("Test 21: initBoard test- correct treasure points.", 500, result);
   }
   
   @Test 
   public void initBoardTestInitTrollExists1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("initBoard", new Class[]{Integer.TYPE,Integer.TYPE,Random.class});
         methodToExecute.setAccessible(true);
         Object gameBoardObj = methodToExecute.invoke(seededGame, new Object[]{ROWS,COLS,new Random(12398)});
	      GamePiece[][] gameBoard = (GamePiece[][])gameBoardObj;
         int found =0;
         for(int i=0;i<8;i++) {
            for(int j=0;j<10;j++){
               if(gameBoard[i][j] instanceof Troll)
                  found++;
            }
         }
      assertEquals("Test 22: initBoard test- one troll exists.", 1, found);
   }
   
   @Test 
   public void initBoardTestInitRandomTroll1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("initBoard", new Class[]{Integer.TYPE,Integer.TYPE,Random.class});
         methodToExecute.setAccessible(true);
         Random rand = new Random(396051);
         int[] correctRows = {2,1,4,3,1};
         int[] correctCols = {6,3,4,8,7};
         int[] rows = new int[5];
         int[] cols = new int[5];
         for(int k=0; k<5; k++){
            Object gameBoardObj = methodToExecute.invoke(seededGame, new Object[]{ROWS,COLS,rand});
	         GamePiece[][] gameBoard = (GamePiece[][])gameBoardObj;
            for(int i=0;i<8;i++) {
               for(int j=0;j<10;j++){
                  if(gameBoard[i][j] instanceof Troll){
                     rows[k]=i;
                     cols[k]=j;
                  }
               }
            }
         }
         boolean allCorrect = true;
         for(int i=0;i<5;i++){
           if(rows[i]!=correctRows[i] || cols[i]!=correctCols[i])
              allCorrect = false;
         }
      assertEquals("Test 23: initBoard test- troll randomly distributed.", true, allCorrect);
   }
   
   @Test 
   public void getRandTrollRowTest1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("getRandTrollRow", new Class[]{Random.class,Integer.TYPE});
         methodToExecute.setAccessible(true);
         Random rand = new Random(6782315);
         int[] correctRows = {3,2,6,6,1};
         int[] rows = new int[5];
         for(int k=0; k<5; k++){
            Object randRow = methodToExecute.invoke(seededGame, new Object[]{rand,ROWS}); 
            rows[k]=(int)randRow;
         }
         boolean allCorrect = true;
         for(int i=0;i<5;i++){
           if(rows[i]!=correctRows[i])
              allCorrect = false; 
         }
      assertEquals("Test 24: Test getRandTrollRow.", true, allCorrect);
   }
   
   @Test 
   public void getRandTrollColTest1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("getRandTrollCol", new Class[]{Random.class,Integer.TYPE});
         methodToExecute.setAccessible(true);
         Random rand = new Random(9784195);
         int[] correctCols = {9,7,6,1,5};
         int[] cols = new int[5];
         for(int k=0; k<5; k++){
            Object randRow = methodToExecute.invoke(seededGame, new Object[]{rand,COLS}); 
            cols[k]=(int)randRow;
         }
         boolean allCorrect = true;
         for(int i=0;i<5;i++){
           if(cols[i]!=correctCols[i])
              allCorrect = false; 
         }  
      assertEquals("Test 25: Test getRandTrollCol.", true, allCorrect);
   }
   
   @Test 
   public void testPlayerWinsMethod1()throws Exception {
      boolean result = seededGame.playerWins();
      assertEquals("Test 26: test public method playerWins on init value.", false, result);
   }
   
   @Test 
   public void testPlayerLoosesMethod1()throws Exception {
      boolean result = seededGame.playerLoses();
      assertEquals("Test 27: test public method playerLoses on init value.", false, result);
   }
   
   @Test 
   public void testGetTreasurePoints1()throws Exception {
      int result = seededGame.getTreasurePoints();
      assertEquals("Test 28: test public method getTreasurePoints.", TREASURE_POINTS, result);
   }
   
   @Test 
   public void testPlayerAlive1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("playerAlive", new Class[]{Integer.TYPE,Integer.TYPE});
         methodToExecute.setAccessible(true);
         Object returnObj = methodToExecute.invoke(seededGame, new Object[]{0,0});
	      boolean result = (boolean)returnObj; 
      assertEquals("Test 29: test playerAlive method.", true, result);
   } 
   
   @Test 
   public void testPlayerAlive2()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("playerAlive", new Class[]{Integer.TYPE,Integer.TYPE});
         methodToExecute.setAccessible(true);
         Object returnObj = methodToExecute.invoke(seededGame, new Object[]{3,6});
	      boolean result = (boolean)returnObj; 
      assertEquals("Test 30: test playerAlive method on EmptyPiece.", false, result);
   }  
   
   @Test 
   public void testAdjustPlayerLifeLevel()throws Exception {
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("adjustPlayerLifeLevel", new Class[]{Integer.TYPE,Integer.TYPE});
      methodToExecute.setAccessible(true);
      methodToExecute.invoke(seededGame, new Object[]{0,0});
      //now get the player to test if the decrement happened.
      Field field = randGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;     
      int result = gameBoard[0][0].getLifePoints();   
      int correct = INIT_PLAYER_POINTS + PLAYER_POINTS_DEC;
      assertEquals("Test 31: test adjustPlayerLifeLevel method.", correct, result);
   }   

   @Test 
   public void testPlayerFoundTreasureMethod1()throws Exception {
         Class classToCall = Class.forName("TrollGame");            
	      Method methodToExecute = classToCall.getDeclaredMethod("playerFoundTreasure", new Class[]{Integer.TYPE,Integer.TYPE});
         methodToExecute.setAccessible(true);
         Object returnObj = methodToExecute.invoke(seededGame, new Object[]{0,0});
	      boolean result = (boolean)returnObj; 
      assertEquals("Test 32: test playerFoundTreasure method- false.", false, result);
   }
   @Test 
   public void testPlayerFoundTreasureMethod2()throws Exception {
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("playerFoundTreasure", new Class[]{Integer.TYPE,Integer.TYPE});
      methodToExecute.setAccessible(true);
      Object returnObj = methodToExecute.invoke(seededGame, new Object[]{ROWS-1,COLS-1});
	   boolean result = (boolean)returnObj; 
      assertEquals("Test 33: test playerFoundTreasure method- true.", true, result);
   }   
   
   @Test 
   public void testOverwritePositionWithEmptyMethod()throws Exception {
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("overwritePositionWithEmpty", new Class[]{Integer.TYPE,Integer.TYPE});
      methodToExecute.setAccessible(true);
      methodToExecute.invoke(seededGame, new Object[]{0,0});
      //now get the player to test if the overwite happened.
      Field field = randGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;          
      boolean result = (gameBoard[0][0] instanceof EmptyPiece);   
      assertEquals("Test 34: test overwritePositionWithEmpty method.", true, result);
   }   
    
   @Test 
   public void testOverwritePositionMethod()throws Exception {
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("overwritePosition", 
                                                        new Class[]{Integer.TYPE,Integer.TYPE,Integer.TYPE,Integer.TYPE});
      methodToExecute.setAccessible(true);
      methodToExecute.invoke(seededGame, new Object[]{6,2,0,0});
      //the player becomes the troll, then the troll's old position is empty.
      Field field = randGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;          
      boolean result = (gameBoard[0][0] instanceof Troll) && (gameBoard[6][2] instanceof EmptyPiece);   
      assertEquals("Test 35: test overwritePosition method.", true, result);
   }       
   
   @Test 
   public void testSwapPositionMethod()throws Exception {
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("swapPosition", 
                                                        new Class[]{Integer.TYPE,Integer.TYPE,Integer.TYPE,Integer.TYPE});
      methodToExecute.setAccessible(true);
      methodToExecute.invoke(seededGame, new Object[]{6,2,0,0});
      //the player becomes the troll, then the troll's old position is empty.
      Field field = randGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;          
      boolean result = (gameBoard[0][0] instanceof Troll) && (gameBoard[6][2] instanceof Player);   
      assertEquals("Test 36: test swapPosition method.", true, result);
   }   
 
   @Test 
   public void testcalcNewTrollCoordinatesMethod1()throws Exception {
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("calcNewTrollCoordinates", 
                                                        new Class[]{Integer.TYPE,Integer.TYPE,Integer.TYPE,Integer.TYPE});
      methodToExecute.setAccessible(true);
      boolean result = true;
      //Case 1: P (0,0) T (6,2) move should be 5,2 
      Object trollMoveCoordsObj = methodToExecute.invoke(seededGame, new Object[]{0,0,6,2});
      int[] trollMoveCoords = (int[])trollMoveCoordsObj;
      if(trollMoveCoords[0]!=5 || trollMoveCoords[1]!=2)
         result = false;  
      //Case 2: P (0,0) T (1,4) move should be 1,3 
      trollMoveCoordsObj = methodToExecute.invoke(seededGame, new Object[]{0,0,1,4});
      trollMoveCoords = (int[])trollMoveCoordsObj;
      if(trollMoveCoords[0]!=1 || trollMoveCoords[1]!=3)
         result = false;
      //Case 3: P (0,0) T (4,4) move should be 3,4 or 4,3 
      trollMoveCoordsObj = methodToExecute.invoke(seededGame, new Object[]{0,0,4,4});
      trollMoveCoords = (int[])trollMoveCoordsObj;
      if(!((trollMoveCoords[0]==4 && trollMoveCoords[1]==3) || (trollMoveCoords[0]==3 && trollMoveCoords[1]==4)))
         result = false; 
      assertEquals("Test 37: test calcNewTrollCoordinates method.", true, result);
   }  
   
   
   @Test 
   public void testMovePlayerMethod1()throws Exception {
      boolean result = true;
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      methodToExecute.setAccessible(true);
      Field field = seededGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;
      //Case 1- move up from (0,0) should not move.
      methodToExecute.invoke(seededGame, new Object[]{"u"});
      if(!(gameBoard[0][0] instanceof Player))
         result = false;  
      //Case 2- move left from (0,0) should not move.
      methodToExecute.invoke(seededGame, new Object[]{"l"});
      if(!(gameBoard[0][0] instanceof Player))
         result = false;  
      assertEquals("Test 38: test movePlayer method- player stays in bounds.", true, result);
   }
   
   @Test 
   public void testMovePlayerMethod2()throws Exception {
      boolean result = true;
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      methodToExecute.setAccessible(true);
      Field field = seededGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;
      //move player to the right-.
      methodToExecute.invoke(seededGame, new Object[]{"r"});
      if(!(gameBoard[0][1] instanceof Player))
         result = false; 
      //next move player down.
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      if(!(gameBoard[1][1] instanceof Player))
         result = false; 
  
      assertEquals("Test 39: test movePlayer method- player moves to the right and down correctly.", true, result);
   }  
   
   @Test 
   public void testMovePlayerMethod3()throws Exception {
      boolean result = true;
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      methodToExecute.setAccessible(true);
      Field field = seededGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;
      //move player to the right- also test that prev position is empty.
      methodToExecute.invoke(seededGame, new Object[]{"r"});
      if(!(gameBoard[0][1] instanceof Player) && !(gameBoard[0][0] instanceof EmptyPiece))
         result = false; 
      //next move player down- also test that prev position is empty.
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      if(!(gameBoard[1][1] instanceof Player) && !(gameBoard[0][1] instanceof EmptyPiece))
         result = false; 
  
      assertEquals("Test 40: test movePlayer method- player moves to the right and down correctly and previous position is empty.", true, result);
   } 
   
   @Test 
   public void testMovePlayerMethod4()throws Exception {
      boolean result = true;
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      methodToExecute.setAccessible(true);
      Field field = seededGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;
      //move player to the right- the troll should have moved- test that troll's prev position is empty.
      methodToExecute.invoke(seededGame, new Object[]{"r"});
      if(!(gameBoard[5][2] instanceof Troll) && !(gameBoard[6][2] instanceof EmptyPiece))
         result = false; 
      assertEquals("Test 41: test movePlayer method- test that the troll's old position is empty.", true, result);
   }
   
   @Test 
   public void testMovePlayerMethod5()throws Exception {
      boolean result = true;
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      methodToExecute.setAccessible(true);
      Field field = seededGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;
      //move player down 4 times- the troll will intercept at 4,0 and wins.
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      if(!(gameBoard[4][0] instanceof Troll))
         result = false; 
      assertEquals("Test 42: test movePlayer method- test that the player loses and troll occupys the intersect cell.", true, result);
   }
   
   @Test 
   public void testMovePlayerMethod6()throws Exception {
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      //move player down 4 times- the troll will intercept at 4,0 and wins.
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      boolean result = seededGame.playerLoses(); 
      assertEquals("Test 43: test movePlayer method- test that the player loses.", true, result);
   }
   
    @Test 
   public void testMovePlayerMethod7()throws Exception {
      boolean result1 = true;
      boolean result2 = true;
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      methodToExecute.setAccessible(true);
      
      Field field1 = seededGame2.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field1.getModifiers())) 
        	field1.setAccessible(true);
      Object targetObj1 = field1.get(seededGame2);
      GamePiece[][] gameBoard1 = (GamePiece[][])targetObj1;
       //for row-first troll moves: move player down, then right until treasure cell.
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});      
      methodToExecute.invoke(seededGame2, new Object[]{"r"}); 
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});             
      if(!(gameBoard1[7][9] instanceof Player))
         result1 = false;  
      
      Field field2 = seededGame3.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field2.getModifiers())) 
        	field2.setAccessible(true);
      Object targetObj2 = field2.get(seededGame3);
      GamePiece[][] gameBoard2 = (GamePiece[][])targetObj2;
      
      //for col-first troll moves: move player right, then down until treasure cell.
      methodToExecute.invoke(seededGame3, new Object[]{"r"});      
      methodToExecute.invoke(seededGame3, new Object[]{"r"}); 
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      if(!(gameBoard2[7][9] instanceof Player))
         result2 = false;
      assertEquals("Test 44: test movePlayer method- test that the player wins and occupys the treasure cell.", true, (result1 ^ result2));
   }
   
   @Test 
   public void testMovePlayerMethod8()throws Exception {
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      //for row-first troll moves: move player down, then right until treasure cell.
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});      
      methodToExecute.invoke(seededGame2, new Object[]{"r"}); 
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      methodToExecute.invoke(seededGame2, new Object[]{"r"});
      boolean result1 = seededGame2.playerWins(); 
      
      //for col-first troll moves: move player right, then down until treasure cell.
      methodToExecute.invoke(seededGame3, new Object[]{"r"});      
      methodToExecute.invoke(seededGame3, new Object[]{"r"}); 
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"r"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      methodToExecute.invoke(seededGame3, new Object[]{"d"});
      boolean result2 = seededGame3.playerWins(); 
      assertEquals("Test 45: test movePlayer method- test the playerWins method.", true, (result1 ^ result2));
   }

   
   @Test 
   public void testMovePlayerMethod9()throws Exception {
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      methodToExecute.setAccessible(true);
      Field field = seededGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame2);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;
      //move player down, then right until treasure cell.
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});
      methodToExecute.invoke(seededGame2, new Object[]{"d"});             
      int result = gameBoard[7][0].getLifePoints();
      assertEquals("Test 46: test movePlayer method- test that the player life level is decremented.", 90, result);
   }

   @Test 
   public void testResetGameMethod()throws Exception {
      boolean result = true;
      Class classToCall = Class.forName("TrollGame");            
	   Method methodToExecute = classToCall.getDeclaredMethod("movePlayer", new Class[]{String.class});
      methodToExecute.setAccessible(true);
      //move player down 4 times- the troll will intercept at 4,0 and wins.
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      methodToExecute.invoke(seededGame, new Object[]{"d"});
      
      Method methodToExecute2 = classToCall.getDeclaredMethod("resetGame");
      methodToExecute2.invoke(seededGame);
      Field field = seededGame.getClass().getDeclaredField("gameBoard");
      if (Modifier.isPrivate(field.getModifiers())) 
        	field.setAccessible(true);
      Object targetObj = field.get(seededGame);
      GamePiece[][] gameBoard = (GamePiece[][])targetObj;

      if(!(gameBoard[0][0] instanceof Player))
         result = false; 
      if(!(gameBoard[7][9] instanceof Treasure))
         result = false;
      if(!(gameBoard[3][4] instanceof Troll))
         result = false; 
      assertEquals("Test 47: test the resetGame method.", true, result);
   }
  
}