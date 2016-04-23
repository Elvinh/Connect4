/* Elton Vinh
 * Homework 3
 * CS 151
 * SJSU
 * Vidya Rangasayee 
 */

import javax.swing.UIManager;
import javax.swing.UIManager.*;
public class ConnectGameController {
	
	private ConnectGameBoard gameBoard;
	private ConnectGameGUI GameGUI;
	private int boardSize;
	private int winCondition;
	private int turns;
	
	public ConnectGameController(int boardSize, int winCondition) {
		this.turns = 0;
		this.boardSize = boardSize;
		this.winCondition = winCondition;
		gameBoard = new ConnectGameBoard(boardSize, winCondition);
		GameGUI = new ConnectGameGUI(this, boardSize);
	}
	public int drop(int player, int row, int col){
		int rowPlaced = 0;
		rowPlaced = gameBoard.drop(player, col);

		if(rowPlaced != -1) {  // If piece dropped successfully 
			turns++;
			if(gameBoard.isWinner(player, rowPlaced, col)) {
				GameGUI.gameOver(0);
			}
			if(turns >= boardSize*boardSize) {
				GameGUI.gameOver(-1);
			}
		}
		return rowPlaced;
	}
	public void reset() {
		turns = 0;
		GameGUI.dispose();
		gameBoard = new ConnectGameBoard(boardSize, winCondition);
		GameGUI = new ConnectGameGUI(this, boardSize);
	}
	public void terminate() {
		GameGUI.dispose();
	}
	public static void main(String[] args) {
		
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		@SuppressWarnings("unused")
		ConnectGameController game = new ConnectGameController(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	}
}
