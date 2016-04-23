/* Elton Vinh
 * Homework 3
 * CS 151
 * SJSU
 * Vidya Rangasayee 
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class ConnectGameGUI extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static int RED_TURN = 1;
    private final static int BLACK_TURN = 2;
    private final static int GAMEOVER = 0;
    private final ImageIcon redIcon = new ImageIcon(getClass().getResource("/red.png"));
    private final ImageIcon blackIcon = new ImageIcon(getClass().getResource("/black.png"));
   
    private ConnectGameController game;
    private int state;
    private int gridSize;
    private int winner;
    
    private JButton[][] cellGrid;
    private JPanel panel;
    private JTextField myText;

    
    private class CellListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
    		String currentState = (state == 1) ? "Black's Turn" : "Red's Turn";
        	myText.setText(currentState);
        	if(state != GAMEOVER) {
	        	int row=0; int col=0;
	        	outerloop:  // find index of selected cell
	        	for(row=0; row<gridSize; row++)
	        		for(col=0; col<gridSize; col++)
	        			if(((JButton)e.getSource()) == cellGrid[row][col])
	        				break outerloop;
	        	if(state == RED_TURN){
	        		row = game.drop(RED_TURN, row,col);
	        		if(row == -1) {
	            		Toolkit.getDefaultToolkit().beep();
	        			myText.setText("Invalid column choice. Try Again");
	        		}
	        		else {
		        		cellGrid[row][col].setIcon(redIcon);
		        		if(state != GAMEOVER) {
		        			state = BLACK_TURN;
		                	panel.setBackground(Color.BLACK);
		        		}
	        		}
	        	}
	        	else {
	        		row = game.drop(BLACK_TURN, row,col);
	        		if(row == -1) {
	        			Toolkit.getDefaultToolkit().beep();
	            		myText.setText("Invalid column choice. Try Again");
	        		}
	        		else {
	        			cellGrid[row][col].setIcon(blackIcon);
		        		if(state != GAMEOVER) {
		        			state = RED_TURN;
		                	panel.setBackground(Color.RED);
		        		}
	        		}
	        	}
        	}
        	if(state == GAMEOVER) {
        		ImageIcon winnerIcon = new ImageIcon();
        		String winnerMessage = (winner == RED_TURN) ? "Red Wins!" : "Black Wins!";
        		if( winner == 0)
        			winnerMessage = "Tie.";
        		else
        			winnerIcon = (winner == RED_TURN) ? redIcon : blackIcon;
        		myText.setText("GAMEOVER. " + winnerMessage);
        		int dialogResult = JOptionPane.showConfirmDialog(panel, winnerMessage+ "\nPlay Again?", "Connect Four", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, winnerIcon);
        		if(dialogResult  == JOptionPane.YES_OPTION)
        			game.reset();
        		else if(dialogResult  == JOptionPane.NO_OPTION)
        			game.terminate();
        	}
        }
    }
    
    public ConnectGameGUI(ConnectGameController game, int size) {
    	super("Connect Four"); 
    	
    	gridSize = size;
		this.game = game;
		state = 1;  // red always starts first
		
        GridLayout boardGrid = new GridLayout(size, size);
        panel = new JPanel();
        panel.setLayout(boardGrid);
        panel.setBackground(Color.RED);

       
        myText = new JTextField("Welcome! Red starts first.");
        this.add(myText, BorderLayout.SOUTH);
        
        cellGrid = new JButton[size][size];
        ActionListener al = new CellListener();
        
        for(int i=0; i<size; i++) {
        	for(int j=0; j<size; j++) {
        		cellGrid[i][j] = new JButton("");
        		cellGrid[i][j].setBackground(Color.WHITE);
        		cellGrid[i][j].setPreferredSize(new Dimension(80,80));
        		cellGrid[i][j].addActionListener(al);
        		panel.add(cellGrid[i][j]);
        	}
        }   
        add(panel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void gameOver(int type) {  // if type = -1 BOARD FULL
    	this.winner = state;
    	if( type == -1 )
    		this.winner = 0;
    	this.state = 0;
    }
    
}


