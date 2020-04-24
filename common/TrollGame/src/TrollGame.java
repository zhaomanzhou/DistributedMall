import java.util.Random;

/* The game is played in a series of rounds. The game board is a 2D array with
 * a set number of rows and columns. The array contains Objects of type GamePiece.
 * There are four types of GamePieces: Player- the user, Troll- the villain, Treasure- the
 * goal, and EmptyPiece- represents cells that are not one of the previous types.
 * The player's goal is to reach the treasure without encountering the Troll.
 * The Troll's goal is to intercept the player before the player reaches the treasure.
 * The player starts in the upper left-hand corner- position 0,0.
 * The treasure is located at the lower right-hand corner- position 7, 9.
 * The Troll starts at a random position in the board that is not
 * the player's starting position or the treasure's position.
 * The player makes a move one step in one of four directions: up, down, left, right.
 * If the player is on a border and the requested move would take the player off the
 * board, the move is ignored- i.e. the player does not move.
 * The Troll knows the player's position and after the player moves, the Troll will
 * move towards the player. If the Troll's next move reaches the player, the Troll
 * wins. If the player reaches the treasure and the Troll also reaches the treasure
 * in the same round, the player wins.
 * The player starts with 160 life points. Each move the player makes costs 10 life points.
 * This deduction occurs for all player moves- even if the player attempts to move off the board.
 * If a player's life level drops to 0 or less, the player is not alive an cannot move.
 */

public class TrollGame
{
    //constants for this version of the game
    private static final int INIT_PLAYER_POINTS = 160;
    private static final int PLAYER_POINTS_DEC = -10;
    private static final int TREASURE_POINTS = 500;
    private static final int ROWS = 8;
    private static final int COLS = 10;
    // random number generator
    private Random rand;
    // the game board, a 2D array of GamePieces
    private GamePiece[][] gameBoard;
    // variables to keep track of the locations of player, troll
    private int curPlayerRow, curPlayerCol;
    private int curTrollRow, curTrollCol;
    // the player's status
    private boolean playerWins;
    private boolean playerLoses;

    /* Constructor that uses an unseeded instance of the random number generator.
     * Calls initBoard.
     */
    public TrollGame()
    {
        curPlayerRow = curPlayerCol = curTrollRow = curPlayerCol = 0;
        playerWins = false;
        playerLoses = false;
        rand = new Random();
        initBoard(ROWS, COLS, rand);

    }

    /* Constructor that uses a seeded instance of the random number generator.
     * Calls initBoard.
     */
    public TrollGame(int seed)
    {
        curPlayerRow = curPlayerCol = curTrollRow = curPlayerCol = 0;
        playerWins = false;
        playerLoses = false;
        rand = new Random(seed);
        initBoard(ROWS, COLS, rand);

    }

    /*
     * Manages a player move. This is a multi-step process that involves:
     * 1- if the player is alive:
     *    - determine the direction of the user's input.
     *    - check that the user move is valid. If not, player does not change position,
     *      if valid, adjust player position accordingly.
     *    - adjust player life level- regardless of validity of move.
     * 2- calculate troll's move given the player's new position.
     * 3- check if troll has same position as player.if so, player loses. Adjust the GamePieces
     *    so that the board is displayed properly, i.e. the player is gone and is replaced by the troll.
     * 4- check if the player reached the treasure. If so, the player wins. Adjust the GamePieces
     *    so that the board is displayed properly, i.e. the player now appears in the treasure position.
     *    Also, update the troll's last position.
     * 5- If neither 3 nor 4 above apply, then update the player and trollpositions.
     *    Adjust the GamePieces so that the board is displayed properly, i.e. the player and troll appear
     *    in their new positions.
     */
    public void movePlayer(String direction)
    {
//        if (!playerAlive(curPlayerRow, curPlayerCol))
//            return;

        int vertical = 0, horizontal = 0;
        switch (direction)
        {
            case "u":
            {
                if (curPlayerRow != 0)
                    vertical = -1;
                break;
            }
            case "d":
            {
                if (curPlayerRow != ROWS-1)
                    vertical = 1;
                break;
            }
            case "l":
            {
                if (curPlayerCol != 0)
                    horizontal = -1;
                break;
            }
            case "r":
            {
                if (curPlayerCol != COLS-1)
                    horizontal = 1;
                break;
            }
        }
        int oldPlayerRow = curPlayerRow;
        int oldPlayerCol = curPlayerCol;
        adjustPlayerLifeLevel(curPlayerRow, curPlayerCol);
        curPlayerRow += vertical;
        curPlayerCol += horizontal;
        //System.out.println("ROW: " + curPlayerRow + "   COL: " + curPlayerCol);
        if(playerFoundTreasure(curPlayerRow, curPlayerCol))
        {
            //System.out.println("--------------");
            playerWins = true;
            overwritePosition(oldPlayerRow, oldPlayerCol, curPlayerRow, curPlayerCol);
        }else if(curPlayerRow == curTrollRow && curPlayerCol == curTrollCol){
            overwritePositionWithEmpty(oldPlayerRow, oldPlayerCol);
            return;
        }else{
            swapPosition(oldPlayerRow, oldPlayerCol, curPlayerRow, curPlayerCol);
        }
        int[] newPosition = calcNewTrollCoordinates(curPlayerRow, curPlayerCol, curTrollRow, curTrollCol);
        if(newPosition[0] == curPlayerRow && newPosition[1] == curPlayerCol)
            overwritePosition(curTrollRow, curTrollCol, newPosition[0], newPosition[1]);
        else
            swapPosition(curTrollRow, curTrollCol, newPosition[0], newPosition[1]);
        curTrollRow = newPosition[0];
        curTrollCol = newPosition[1];
        playerLoses();


    }

    /* Returns true if the player wins, false otherwise. */
    public boolean playerWins()
    {
        if(playerLoses)
            return false;
        return playerWins;
    }

    /* Returns true if the player loses, false otherwise. */
    public boolean playerLoses()
    {
        if((curPlayerRow == curTrollRow && curPlayerCol == curTrollCol))
        {
            playerLoses = true;
        }
        return playerLoses;
    }

    /* Returns the number of treasure points. */
    public int getTreasurePoints()
    {
        return TREASURE_POINTS;
    }

    /* Resets the game variables and game board (call initBoard).
     * Does NOT change the random number generator instance.
     */
    public void resetGame()
    {
        initBoard(ROWS, COLS, rand);
    }

    /* Returns a String version of the game. */
    public String getGameStr()
    {
        StringBuilder outStr = new StringBuilder();
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
                outStr.append("|").append(gameBoard[i][j].show());
            outStr.append("|").append(System.getProperty("line.separator"));
        }
        return outStr.toString();
    }

    /**
     * private methods below
     **/

    /* Creates the game board array with rows and cols. The player starts in the upper left-hand corner.
     * The treasure is at the lower right-hand corner. The rest of the cells are empty pieces.
     * The player starts with INIT_PLAYER_POINTS life points. The treasure is initialized to
     * TREASURE_POINTS. The Troll position is determined at random by calling the getRandTrollRow
     * and getRandTrollCol methods. This method returns the initialized array.
     */
    private GamePiece[][] initBoard(int rows, int cols, Random rand)
    {
        gameBoard = new GamePiece[ROWS][COLS];

            curTrollRow = getRandTrollRow(rand, ROWS);
            curTrollCol = getRandTrollCol(rand, COLS);

        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                gameBoard[i][j] = new EmptyPiece();
            }
        }
        gameBoard[0][0] = new Player(INIT_PLAYER_POINTS);
        gameBoard[7][9] = new Treasure(TREASURE_POINTS);
        gameBoard[curTrollRow][curTrollCol] = new Troll();

        return gameBoard;
    }

    /* Returns true if the player is alive, false otherwise.*/
    private boolean playerAlive(int curPlayerRow, int curPlayerCol)
    {
        return gameBoard[curPlayerRow][curPlayerCol].isAlive();
    }

    /* Adjusts the player's life level by the amount PLAYER_POINTS_DEC. */
    private void adjustPlayerLifeLevel(int curPlayerRow, int curPlayerCol)
    {
        GamePiece player = gameBoard[curPlayerRow][curPlayerCol];
        player.updateLifePoints(PLAYER_POINTS_DEC);
    }

    /* Returns true if the player row and column passed in equals
       the treasure row and column. */
    private boolean playerFoundTreasure(int playerRow, int playerCol)
    {
        if(playerRow == ROWS-1 && playerCol == COLS-1)
            return true;
        return false;
    }

    /* Returns a random number in [1,numRows-1] */
    private int getRandTrollRow(Random rand, int numRows)
    {
        return rand.nextInt(ROWS-1)+1;
    }

    /* Returns a random number in [1,numCols-1] */
    private int getRandTrollCol(Random rand, int numCols)
    {
        return rand.nextInt(COLS-1)+1;
    }

    /*
     * This method calculates the direction the troll will move to get as close to the player as possible.
     * The player and current troll positions are passed in. The method chooses a move in one direction
     * that will bring it closer to the player. The method returns an int array [row, col], where row is the
     * index of the verical position and col is the index of the horizontal position in the game board.
     * The strategy of calculating the new troll position is to minimize the distance between the troll and player.
     * This can be done by using the Manhattan, or city block, distance between the two pieces.
     * Determine the horizontal distance and then the vertical distance. The troll will want to move to
     * decrease the greater of the two distances. Note that the direction is also important. The sign of
     * the difference can indicate the direction to move in. Asume the player is never out of bounds.
     * Note: the player should loose if they move onto the troll's position. The troll should detect this
     * situation and not make a move.
     */
    private int[] calcNewTrollCoordinates(int playerRow, int playerCol, int trollRow, int trollCol)
    {
        if (playerRow == trollRow && playerCol == trollCol)
            return new int[]{trollRow, trollCol};
        if (Math.abs(playerRow - trollRow) < Math.abs(playerCol - trollCol))
        {
            if (playerCol > trollCol)
            {
                return new int[]{trollRow, trollCol + 1};
            } else
            {
                return new int[]{trollRow, trollCol - 1};
            }
        } else
        {
            if (playerRow > trollRow)
            {
                return new int[]{trollRow + 1, trollCol};
            } else
            {
                return new int[]{trollRow - 1, trollCol};
            }
        }

    }

    // The following three methods may be helpful when adjusting the GamePieces in the movePlayer method. */

    /* Overwrite the GamePiece at the coordinates passed in with an empty GamePiece. */
    private void overwritePositionWithEmpty(int row, int col)
    {
        gameBoard[row][col] = new EmptyPiece();
    }

    /* Overwrite the GamePiece at the new coordinates with the GamePiece at the
       old coordinates. Place a new EmptyPiece at the old coordinates. */
    private void overwritePosition(int oldRow, int oldCol, int newRow, int newCol)
    {
        gameBoard[newRow][newCol] = gameBoard[oldRow][oldCol];
        overwritePositionWithEmpty(oldRow, oldCol);
    }

    /* Swap the position of the GamePiece at the current position with the
     * GamePiece at the new position. */
    private void swapPosition(int curRow, int curCol, int newRow, int newCol)
    {
        GamePiece temp = gameBoard[curRow][curCol];
        gameBoard[curRow][curCol] = gameBoard[newRow][newCol];
        gameBoard[newRow][newCol] = temp;
    }

}