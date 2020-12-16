package Q1;

public class FourInRowModel {

	private final int NUM_OF_ROWS = 5;
	private final int NUM_OF_COLUMNS = 7;
    private final int INDEX_OF_LAST_ROW = NUM_OF_ROWS - 1;
    private final int THREE_MORE_TO_WIN = 3;
    private final int FIRST_INDEX = 0;
    private final int SECOND_INDEX = 1;
    private final int THIRD_INDEX = 2;
    private final int FOURTH_INDEX = 3;

    private PlayerColor[][] gameBoard;
    private PlayerColor playerTurn;
	
    public FourInRowModel() {
	   this.gameBoard = new PlayerColor[NUM_OF_ROWS][NUM_OF_COLUMNS];
       this.playerTurn = PlayerColor.RED;			// Player 1 is Red
    }

    public PlayerColor[][] getBoard() {
    	return this.gameBoard;
    }
	
    public PlayerColor getTurn() {
    	return playerTurn;
    }
   
    public boolean checkWin(PlayerColor playerColor) {
      
    	//check if there are 4 cells the same in a row
    	boolean winInROW = checkRowWin(playerColor);

    	//check if there are 4 cells the same in a row
    	boolean winInColumn = checkColumnWin(playerColor);

    	//check if there are 4 cells the same in a row
    	boolean winInDiagonal = checkDiagonalWin(playerColor);

    	//return the boolean value for each case. in case there is one "true"- there is a winner, game over
    	return winInROW || winInColumn || winInDiagonal;
    }

    //Check win in a row
    private boolean checkRowWin(PlayerColor playerColor) {
       for (int i = FIRST_INDEX; i < gameBoard.length; i++) {
           for (int j = FIRST_INDEX; j < gameBoard[i].length - THREE_MORE_TO_WIN; j++) {
               if (gameBoard[i][j] == playerColor && gameBoard[i][j + SECOND_INDEX] == playerColor &&
   				gameBoard[i][j + THIRD_INDEX] == playerColor && gameBoard[i][j + FOURTH_INDEX] == playerColor) {
                   return true;
               }
           }
       }
       return false;
    }
       
    //Check win in a column
    private boolean checkColumnWin(PlayerColor playerColor) {
    	for (int i = FIRST_INDEX; i < gameBoard.length - THREE_MORE_TO_WIN; i++) {
    		for (int j = FIRST_INDEX; j < gameBoard[i].length; j++) {
    			if (gameBoard[i][j] == playerColor && gameBoard[i + SECOND_INDEX][j] == playerColor &&
					gameBoard[i + THIRD_INDEX][j] == playerColor && gameBoard[i + FOURTH_INDEX][j] == playerColor) {
                   return true;
                }
            }
    	}
    	return false;
    }

    //Check win in diagonal
    private boolean checkDiagonalWin(PlayerColor playerColor) {
  
    	//Diagonal to the right
    	for (int i = FIRST_INDEX; i < gameBoard.length - THREE_MORE_TO_WIN; i++) {
           for (int j = FIRST_INDEX; j < gameBoard[i].length - THREE_MORE_TO_WIN; j++) {
               if (gameBoard[i][j] == playerColor && gameBoard[i + SECOND_INDEX][j + SECOND_INDEX] == playerColor &&
   				gameBoard[i + THIRD_INDEX][j + THIRD_INDEX] == playerColor && gameBoard[i + FOURTH_INDEX][j + FOURTH_INDEX] == playerColor) {
                   return true;
               }
           }
    	}
       
   		//Diagonal to the left
       	for (int i = INDEX_OF_LAST_ROW; i >= THREE_MORE_TO_WIN; i--) {
           for (int j = FIRST_INDEX; j < gameBoard[i].length - THREE_MORE_TO_WIN; j++) {
               if (gameBoard[i][j] == playerColor && gameBoard[i - SECOND_INDEX][j + SECOND_INDEX] == playerColor &&
               	gameBoard[i - THIRD_INDEX][j + THIRD_INDEX] == playerColor && gameBoard[i - FOURTH_INDEX][j + FOURTH_INDEX] == playerColor) {
                   return true;
               }
           }
       	}
       	return false;
    }

    //change the player who plays
    public void changeTurns() {
    	if (this.playerTurn == PlayerColor.RED) {
    		this.playerTurn = PlayerColor.BLUE;
    	} else {
    		if (this.playerTurn == PlayerColor.BLUE) {
    			this.playerTurn = PlayerColor.RED;
    		}
    	}
    }

    //resetting the board values
    public void reset() {
    	this.gameBoard = new PlayerColor[NUM_OF_ROWS][NUM_OF_COLUMNS];
    	this.playerTurn = PlayerColor.RED;
    }

    //put a cell in the column choosed by a player
    public void putCellInGameBoard(PlayerColor playerColor, int column) {
    	int i = 0;
    	if (column < NUM_OF_COLUMNS) {
    		while (i < gameBoard.length && gameBoard[i][column] != null) {
    			i++;
    		}
    	if (i < gameBoard.length) 
    		gameBoard[i][column] = playerColor;
    	}
    }
    	
    //check if a column is full
    public boolean isColumnFull(int columnIndex) {
    	return gameBoard[INDEX_OF_LAST_ROW][columnIndex] != null;
    }
}
    
