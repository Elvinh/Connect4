/* Elton Vinh
 * Homework 3
 * CS 151
 * SJSU
 * Vidya Rangasayee 
 */

public class ConnectGameBoard {
	private Integer[][] board;
	private int size;
	private int winCondition;
	private static final int UNOCCUPIED = 0;
	private static final int RED_OCCUPIED = 1;
	private static final int BLACK_OCCUPIED = 2;
	
	public ConnectGameBoard(int size, int connectionLength) {
		this.size = size;
		this.winCondition = connectionLength;
		board = new Integer[size][size];
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				board[i][j] = 0;
		
	}
	public int drop(int player, int column) {
		int row = size-1;
		if(board[0][column] != UNOCCUPIED)  // Is column full?
			return -1;
		else {
			while(board[row][column] != UNOCCUPIED) {
				row = row - 1;
			}
			board[row][column] = (player == 1) ? RED_OCCUPIED : BLACK_OCCUPIED;
			return row;
		}
	}
	public boolean isWinner(int player, int row, int column) {
		int currCol = column;
		int currRow = row;
		int connectionLength = 0;
		int connected = (player == 1) ? RED_OCCUPIED : BLACK_OCCUPIED;
		boolean isWinner = false;
	
//		for(int i=0;i<size;i++) {
//			for(int j=0;j<size;j++)
//				System.out.print(board[i][j]);
//			System.out.println();
//		}
//		System.out.println();
		
		/* Check Up-Right Diagonal Connection */
		while(board[currRow][currCol] == connected || isWinner) {
			connectionLength++;
			if( connectionLength == winCondition )
				isWinner = true;
			currRow = currRow - 1;  // check upper right of cell
			currCol = currCol + 1;
			if( currRow < 0 || currCol > size-1)  // check out of bounds
				break;
		}
		
		if(!isWinner) {
			currRow = row + 1;
			currCol = column - 1;
			if(currRow < size && currCol > -1) {
				while(board[currRow][currCol] == connected || isWinner) {
					connectionLength++;
					if( connectionLength == winCondition )
						isWinner = true;
					currRow = currRow + 1;  // check lower left of cell
					currCol = currCol - 1;
					if(currRow >= size || currCol < 0)
						break;
				}
			}
		}
		/*									*/
		
		/* Check Up-Left Diagonal Connection */
		if(!isWinner) {
			connectionLength = 0;
			currRow = row;
			currCol = column;
			while(board[currRow][currCol] == connected || isWinner) {
				connectionLength++;
				if( connectionLength == winCondition )
					isWinner = true;
				currRow = currRow - 1;  // check upper left of cell
				currCol = currCol - 1;
				if( currRow < 0 || currCol < 0)  // check out of bounds
					break;
			}
			
			if(!isWinner) {
				currRow = row + 1;
				currCol = column + 1;
				if(currRow < size && currCol < size) {
					while(board[currRow][currCol] == connected || isWinner) {
						connectionLength++;
						if( connectionLength == winCondition )
							isWinner = true;
						currRow = currRow + 1;  // check lower right of cell
						currCol = currCol + 1;
						if(currRow >= size || currCol >= size)
							break;
					}
				}
			}
		}
		/*									*/
		
		/* Check Vertical Connection */
		connectionLength = 0;
		currRow = row;
		currCol = column;
		if(!isWinner) {
			while(board[currRow][currCol] == connected || isWinner) {
				connectionLength++;
				if( connectionLength == winCondition )
					isWinner = true;
				currRow = currRow - 1;  // check up of cell
				if( currRow < 0)  // check out of bounds
					break;
			}
			
			if(!isWinner) {
				currRow = row + 1;
				if(currRow < size) {
					while(board[currRow][currCol] == connected || isWinner) {
						connectionLength++;
						if( connectionLength == winCondition )
							isWinner = true;
						currRow = currRow + 1;  // check below of cell
						if(currRow >= size)
							break;
					}
				}
			}
		}
		/*									*/
		
		/* Check Horizontal Connection */
		connectionLength = 0;
		currRow = row;
		currCol = column;
		if(!isWinner) {
			while(board[currRow][currCol] == connected || isWinner) {
				connectionLength++;
				if( connectionLength == winCondition )
					isWinner = true;
				currCol = currCol + 1;  // check right of cell
				if( currCol >= size)  // check out of bounds
					break;
			}
			
			if(!isWinner) {
				currCol = column - 1;
				if(currCol >= 0) {
					while(board[currRow][currCol] == connected || isWinner) {
						connectionLength++;
						if( connectionLength == winCondition )
							isWinner = true;
						currCol = currCol - 1;  // check below of cell
						if(currCol < 0)
							break;
					}
				}
			}
		}
		/*									*/
		
		return isWinner;
	}
}
