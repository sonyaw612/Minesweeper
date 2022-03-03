import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MineSweeperGUI extends JFrame {
	private JPanel mainJP;
	private Grid msGrid;
	private MineSweeperField msField;
	private greeting welcome; 
	//userValues:
	private int row = 0;
	private int col = 0;
	private int bomb = 0;
	private JButton changeSpecsBtn;
	private msUserValues newSpecs;
	
	
	public MineSweeperGUI(int row, int col, int bomb) {
		this.row = row;
		this.col = col;
		this.bomb = bomb;
		changeSpecsBtn = new JButton("Change Specs");
		
		mainJP = new JPanel();
		mainJP.setLayout(new BorderLayout());
		msGrid = new Grid(row, col, bomb);
		msField = new MineSweeperField();
		welcome = new greeting();
		
		mainJP.add(welcome, BorderLayout.NORTH);
		mainJP.add(msField, BorderLayout.CENTER);
		
		add(mainJP);
		setTitle("MineSweeper");
		setSize(600, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	
	
	
	public MineSweeperGUI() {
		mainJP = new JPanel();
		mainJP.setLayout(new BorderLayout());
		msGrid = new Grid();
		msField = new MineSweeperField();
		welcome = new greeting();
		
		mainJP.add(welcome, BorderLayout.NORTH);
		mainJP.add(msField, BorderLayout.CENTER);
		
		add(mainJP);
		setVisible(true);
		setTitle("MineSweeper");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setBackground(Color.GRAY);
		
	}
	
	private class greeting extends JPanel{
		private JLabel welcome = new JLabel("  Welcome to");
		private JLabel numBombs = new JLabel(String.format("\t%03d", msGrid.getNumBombs()));
		private JLabel mineswe = new JLabel("MineSweeper");
		private JLabel placeHolder = new JLabel("");
		
		public greeting() {
			Font wordFont = new Font(Font.MONOSPACED, Font.BOLD, 18);
			setLayout(new GridLayout(1, 4));
			setBorder(new EmptyBorder(10, 25, 10, 10));
			this.setBackground(Color.DARK_GRAY);
			welcome.setForeground(Color.WHITE);
			numBombs.setForeground(Color.WHITE);
			mineswe.setForeground(Color.WHITE);
			welcome.setFont(wordFont);
			numBombs.setFont(wordFont);
			mineswe.setFont(wordFont);
			add(numBombs);
			add(welcome);
			add(mineswe);
			add(placeHolder);
		}
	}
	
	private class MineSweeperField extends JPanel implements ActionListener {
		private JButton[][] gridButtons;
		private final int numRows = msGrid.getNumRows();
		private final int numCols = msGrid.getNumColumns();
		
		public MineSweeperField() {
			this.setLayout(new GridLayout(numRows, numCols));
			gridButtons = new JButton[numRows][numCols];
			setField();
		}
		
		public void setField() {
			for(int i = 0; i < gridButtons.length; i++) {
				for(int j = 0; j < gridButtons[i].length; j++) {
					gridButtons[i][j] = new JButton();
					gridButtons[i][j].setOpaque(true);
					gridButtons[i][j].setBackground(Color.GRAY);
					
					if(msGrid.getCountAtLocation(i, j) >= 5) {
						gridButtons[i][j].setForeground(Color.RED);
					}
					else if(msGrid.getCountAtLocation(i, j) >=3) {
						gridButtons[i][j].setForeground(Color.MAGENTA);
					}
					else {
						gridButtons[i][j].setForeground(Color.BLUE);
					}
					Font buttonFont = new Font(Font.MONOSPACED, Font.BOLD, 18);
					gridButtons[i][j].setFont(buttonFont);
					
					this.gridButtons[i][j].addActionListener(this);
					gridButtons[i][j].setEnabled(true);

//					gridButtons[i][j].setMargin(new Insets(10, 10, 10, 10)); //adjusts size of JButton
					add(gridButtons[i][j]);
				}
			}
		}
		
		public void recolorField() {
			for(int i = 0; i < gridButtons.length; i++) {
				for(int j = 0; j < gridButtons[i].length; j++) {
					if(msGrid.getCountAtLocation(i, j) >= 5) {
						gridButtons[i][j].setForeground(Color.RED);
					}
					else if(msGrid.getCountAtLocation(i, j) >=3) {
						gridButtons[i][j].setForeground(Color.MAGENTA);
					}
					else {
						gridButtons[i][j].setForeground(Color.BLUE);
					}
				}
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btnClicked = (JButton)e.getSource();
			int rowNum = 0;
			int colNum = 0;
			for(int i = 0; i < gridButtons.length; i++) {
				for(int j = 0 ; j < gridButtons[i].length; j++) {
					if(btnClicked.equals(gridButtons[i][j])) {
						rowNum = i;
						colNum = j;
					}
				}
			}
			if(msGrid.isBombAtLocation(rowNum, colNum)) {
				revealField();
				displayFail();
				promptPlayAgain();
			}
			else if(msGrid.getCountAtLocation(rowNum, colNum) == 0) {
				String value = Integer.toString(msGrid.getCountAtLocation(rowNum, colNum)); 
				gridButtons[rowNum][colNum].setText(value);
				revealNearbyZeroes();
				if(checkBoardStatus()) {
					revealField();
					displayWin();
					promptPlayAgain();
				}
			}
			else {
				String value = Integer.toString(msGrid.getCountAtLocation(rowNum, colNum)); 
				gridButtons[rowNum][colNum].setText(value);
				if(checkBoardStatus()) { 
					revealField();
					displayWin();
					promptPlayAgain();
				}
			}
			
		}
		
		private boolean checkBoardStatus() {
			int emptyCount = 0;
			for(int i = 0; i < gridButtons.length; i++) {
				for(int j = 0; j < gridButtons[i].length; j++) {
					if(gridButtons[i][j].getText().equals("") && (!msGrid.isBombAtLocation(i, j))) {
						emptyCount++;
					}
				}
			}
			if(emptyCount == 0) {
				return true;
			}
			else {
				return false;
			}
		}

		private void revealNearbyZeroes() {
			int count = 1;
			
			while(count > 0) {
				count = 0;
				int numRevealed = 0;
				for(int i = 0; i < gridButtons.length; i++) {
					for(int j = 0; j < gridButtons[i].length; j++) {
						if(gridButtons[i][j].getText().isEmpty()) {
							if(i == 0) {
								if(j == 0) {
									if(gridButtons[i][j+1].getText().contentEquals("0") || gridButtons[i+1][j].getText().contentEquals("0") || gridButtons[i+1][j+1].getText().contentEquals("0")) {			
										String ctVal = Integer.toString(msGrid.getCountAtLocation(i, j));
										gridButtons[i][j].setText(ctVal);
										if(msGrid.getCountAtLocation(i, j) >= 5) {
											gridButtons[i][j].setForeground(Color.RED);
										}
										else if(msGrid.getCountAtLocation(i, j) >=3) {
											gridButtons[i][j].setForeground(Color.MAGENTA);
										}
										else {
											gridButtons[i][j].setForeground(Color.BLUE);
										}
										gridButtons[i][j].setVisible(true);
										count++;
									}
								}
								else if(j == gridButtons[i].length - 1) {
									if(gridButtons[i][j-1].getText().contentEquals("0") || gridButtons[i+1][j-1].getText().contentEquals("0") || gridButtons[i+1][j].getText().contentEquals("0")) {			
										String ctVal = Integer.toString(msGrid.getCountAtLocation(i, j));
										gridButtons[i][j].setText(ctVal);
										if(msGrid.getCountAtLocation(i, j) >= 5) {
											gridButtons[i][j].setForeground(Color.RED);
										}
										else if(msGrid.getCountAtLocation(i, j) >=3) {
											gridButtons[i][j].setForeground(Color.MAGENTA);
										}
										else {
											gridButtons[i][j].setForeground(Color.BLUE);
										}
										gridButtons[i][j].setVisible(true);
										count++;
									}
								}
								else {
									if(gridButtons[i][j-1].getText().contentEquals("0") || gridButtons[i+1][j-1].getText().contentEquals("0") || gridButtons[i+1][j].getText().contentEquals("0") || gridButtons[i+1][j+1].getText().contentEquals("0") || gridButtons[i][j+1].getText().contentEquals("0")) {				
										String ctVal = Integer.toString(msGrid.getCountAtLocation(i, j));
										gridButtons[i][j].setText(ctVal);
										if(msGrid.getCountAtLocation(i, j) >= 5) {
											gridButtons[i][j].setForeground(Color.RED);
										}
										else if(msGrid.getCountAtLocation(i, j) >=3) {
											gridButtons[i][j].setForeground(Color.MAGENTA);
										}
										else {
											gridButtons[i][j].setForeground(Color.BLUE);
										}
										gridButtons[i][j].setVisible(true);
										count++;
									}
								}
								
							}
							//END OF UPPER BORDER
	
							else if(j == 0) {
								if(i == gridButtons.length-1) {
									if(gridButtons[i-1][j+1].getText().contentEquals("0") || gridButtons[i-1][j].getText().contentEquals("0") || gridButtons[i][j+1].getText().contentEquals("0")) {
										String ctVal = Integer.toString(msGrid.getCountAtLocation(i, j));
										gridButtons[i][j].setText(ctVal);
										if(msGrid.getCountAtLocation(i, j) >= 5) {
											gridButtons[i][j].setForeground(Color.RED);
										}
										else if(msGrid.getCountAtLocation(i, j) >=3) {
											gridButtons[i][j].setForeground(Color.MAGENTA);
										}
										else {
											gridButtons[i][j].setForeground(Color.BLUE);
										}
										gridButtons[i][j].setVisible(true);
										count++;
									}
								}
								else {
									if(gridButtons[i-1][j].getText().contentEquals("0") || gridButtons[i+1][j+1].getText().contentEquals("0") || gridButtons[i+1][j].getText().contentEquals("0") || gridButtons[i-1][j+1].getText().contentEquals("0") || gridButtons[i][j+1].getText().contentEquals("0")) {
										String ctVal = Integer.toString(msGrid.getCountAtLocation(i, j));
										gridButtons[i][j].setText(ctVal);
										if(msGrid.getCountAtLocation(i, j) >= 5) {
											gridButtons[i][j].setForeground(Color.RED);
										}
										else if(msGrid.getCountAtLocation(i, j) >=3) {
											gridButtons[i][j].setForeground(Color.MAGENTA);
										}
										else {
											gridButtons[i][j].setForeground(Color.BLUE);
										}
										gridButtons[i][j].setVisible(true);
										count++;
									}
								}
							}
						//END OF LEFT BORDER
						
							else if(j == gridButtons[i].length-1) {
								if(i == gridButtons.length-1) {
									if(gridButtons[i-1][j-1].getText().contentEquals("0") || gridButtons[i-1][j].getText().contentEquals("0") || gridButtons[i][j-1].getText().contentEquals("0")) {
										String ctVal = Integer.toString(msGrid.getCountAtLocation(i, j));
										gridButtons[i][j].setText(ctVal);
										if(msGrid.getCountAtLocation(i, j) >= 5) {
											gridButtons[i][j].setForeground(Color.RED);
										}
										else if(msGrid.getCountAtLocation(i, j) >=3) {
											gridButtons[i][j].setForeground(Color.MAGENTA);
										}
										else {
											gridButtons[i][j].setForeground(Color.BLUE);
										}
										gridButtons[i][j].setVisible(true);
										count++;
									}
								}
								else {
									if(gridButtons[i-1][j-1].getText().contentEquals("0") || gridButtons[i-1][j].getText().contentEquals("0") || gridButtons[i][j-1].getText().contentEquals("0") || gridButtons[i+1][j-1].getText().contentEquals("0") || gridButtons[i+1][j].getText().contentEquals("0")) {			
										String ctVal = Integer.toString(msGrid.getCountAtLocation(i, j));
										gridButtons[i][j].setText(ctVal);
										if(msGrid.getCountAtLocation(i, j) >= 5) {
											gridButtons[i][j].setForeground(Color.RED);
										}
										else if(msGrid.getCountAtLocation(i, j) >=3) {
											gridButtons[i][j].setForeground(Color.MAGENTA);
										}
										else {
											gridButtons[i][j].setForeground(Color.BLUE);
										}
										gridButtons[i][j].setVisible(true);
										count++;
									}
								}
							}
						//END OF RIGHT BORDER
						
							else if(i == gridButtons.length-1) {
								if(gridButtons[i-1][j-1].getText().contentEquals("0") || gridButtons[i-1][j].getText().contentEquals("0") || gridButtons[i-1][j+1].getText().contentEquals("0") || gridButtons[i][j-1].getText().contentEquals("0") || gridButtons[i][j+1].getText().contentEquals("0")) {
									String ctVal = Integer.toString(msGrid.getCountAtLocation(i, j));
									gridButtons[i][j].setText(ctVal);
									if(msGrid.getCountAtLocation(i, j) >= 5) {
										gridButtons[i][j].setForeground(Color.RED);
									}
									else if(msGrid.getCountAtLocation(i, j) >=3) {
										gridButtons[i][j].setForeground(Color.MAGENTA);
									}
									else {
										gridButtons[i][j].setForeground(Color.BLUE);
									}
									gridButtons[i][j].setVisible(true);
									count++;
								}
							}
						//END OF BOTTOM BORDER
						
							else {
								if(gridButtons[i-1][j-1].getText().contentEquals("0") || gridButtons[i-1][j].getText().contentEquals("0") || gridButtons[i-1][j+1].getText().contentEquals("0") || gridButtons[i][j-1].getText().contentEquals("0") || gridButtons[i][j+1].getText().contentEquals("0") || gridButtons[i+1][j-1].getText().contentEquals("0") || gridButtons[i+1][j].getText().contentEquals("0") || gridButtons[i+1][j+1].getText().contentEquals("0")) {				
									String ctVal = Integer.toString(msGrid.getCountAtLocation(i, j));
									gridButtons[i][j].setText(ctVal);
									if(msGrid.getCountAtLocation(i, j) >= 5) {
										gridButtons[i][j].setForeground(Color.RED);
									}
									else if(msGrid.getCountAtLocation(i, j) >=3) {
										gridButtons[i][j].setForeground(Color.MAGENTA);
									}
									else {
										gridButtons[i][j].setForeground(Color.BLUE);
									}
									gridButtons[i][j].setVisible(true);
									count++;
								}
							}
							repaint();
						}
					}
				}
			}
		}

		private void revealField() {
			ImageIcon bomb = new ImageIcon("Images/rsz_1rsz_2bomb.png");
			for(int i = 0; i < numRows; i ++) {
				for(int j = 0; j < numCols; j++) {
					if(msGrid.isBombAtLocation(i, j)) {
						gridButtons[i][j].setIcon(bomb);
						gridButtons[i][j].setEnabled(true);
						validate();
					}
					else {
						String gridNum = Integer.toString(msGrid.getCountAtLocation(i, j));
						gridButtons[i][j].setText(gridNum);
						gridButtons[i][j].setEnabled(true);
					}
				}
			}
		}

		private void promptPlayAgain() {
			int choice = JOptionPane.showConfirmDialog(null, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);	
			if(choice == JOptionPane.YES_OPTION) {
				clearField();
				int option = JOptionPane.showConfirmDialog(null, "Woudld you like to play with the same specifications?", "", JOptionPane.YES_NO_OPTION);
				if(option != JOptionPane.YES_OPTION) {
					setVisible(false);
					newSpecs = new msUserValues();
				}
			}
			else {
				System.exit(EXIT_ON_CLOSE);
			}
		}
		
		public void clearField() {
			for(int i = 0; i < numRows; i++) {
				for(int j = 0; j < numCols; j++) {
					gridButtons[i][j].setIcon(null);
					gridButtons[i][j].setText("");
					gridButtons[i][j].setEnabled(true);
				}
			}
			if( (row != 0) && (col != 0) && (bomb != 0) ){
				msGrid = new Grid(row, col, bomb);
			}
			else {
				msGrid = new Grid();
			}
			recolorField();
		}
		
		private void displayWin() {
			ImageIcon trophy = new ImageIcon("Images/trophy.png");
			JOptionPane.showMessageDialog(null, "     Wow!\nYou survived!\n", "Congratulations!\n - GRRRRR", JOptionPane.PLAIN_MESSAGE, trophy);
		}
		
		private void displayFail() {
			ImageIcon devil = new ImageIcon("Images/rsz_1rsz_devil-face-png-clipart.png");
			JOptionPane.showMessageDialog(null, "          BOOM!\nYou have been blown up!\n - MWAHAHA!", "You Lost!", JOptionPane.PLAIN_MESSAGE, devil);
		}
		
	}
	

}
