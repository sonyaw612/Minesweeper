import java.util.Random;

// https://youtu.be/wIsRNLTMjvY

public class Grid {
	
	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	public Grid() {
		numBombs = 20;
		numColumns = 10;
		numRows = 10;
		bombGrid = new boolean [10][10];
		countGrid = new int [10][10];
		createBombGrid();
		createCountGrid();

	}
	
	public Grid(int rows, int columns) {
		numBombs = rows;
		numRows = rows;
		numColumns = columns;
		countGrid = new int[rows][columns];
		bombGrid = new boolean[rows][columns];
		createBombGrid();
		createCountGrid();
	}
	
	public Grid(int rows, int columns, int numBombs) {
		numColumns = columns;
		numRows = rows;
		if(numBombs < (numRows*numColumns)) {
			this.numBombs = numBombs;
		}
		else {
			this.numBombs = 20;
		}
		countGrid = new int[rows][columns];
		bombGrid = new boolean[rows][columns];
		createBombGrid();
		createCountGrid();
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public int getNumBombs() {
		return numBombs;
	}
	
	public boolean[][] getBombGrid(){
		boolean[][] copiedBomb = new boolean[numRows][numColumns];
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				copiedBomb[i][j] = bombGrid[i][j];
			}
		}
		return copiedBomb;
	}
	
	public int[][] getCountGrid(){
		int[][] copiedArr = new int[numRows][numColumns];
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				copiedArr[i][j] = countGrid[i][j];
			}
		}
		return copiedArr;
	}
	
	public boolean isBombAtLocation(int row, int column) {
		if(bombGrid[row][column] == true) {return true;}
		else {return false;}
	}
	
	public int getCountAtLocation(int row, int column) {
		int boxVal = countGrid[row][column];
		return boxVal;
	}
	
	private void createBombGrid() {
		Random rand = new Random();
		int row = 0;
		int col = 0;
		int implementedBombs = 0;
		while(implementedBombs < numBombs) {
			row = Math.abs(rand.nextInt()%numRows);
			col = Math.abs(rand.nextInt()%numColumns);
			if(bombGrid[row][col] != true) {
				bombGrid[row][col] = true;
				implementedBombs++;
			}
			else {
				continue;
			}
		}
	}
	
	
	
	private void createCountGrid() {
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				int count = 0;
				if(i == 0) {
					if(j == 0) {
						if(bombGrid[i][j] == true) {
							count++;
						}
						if(bombGrid[i][j+1] == true) {
							count++;
						}
						if(bombGrid[i+1][j] == true) {
							count ++;
						}
						if(bombGrid[i+1][j+1] == true){
							count++;
						}
					}
					else if(j == bombGrid[i].length - 1) {
						if(bombGrid[i][j-1] == true) {
							count++;
						}
						if(bombGrid[i][j] == true) {
							count++;
						}
						if(bombGrid[i+1][j-1] == true) {
							count++;
						}
						if(bombGrid[i+1][j] == true) {
							count++;
						}
					}
					else {
						if(bombGrid[i][j-1] == true) {
							count++;
						}
						if(bombGrid[i][j] == true) {
							count++;
						}
						if(bombGrid[i+1][j-1] == true) {
							count++;
						}
						if(bombGrid[i+1][j] == true) {
							count++;
						}
						if(bombGrid[i+1][j+1] == true){
							count++;
						}
						if(bombGrid[i][j+1] == true) {
							count++;
						}
					}
					
				}
				//END OF UPPER BORDER

				else if(j == 0) {
					if(i == bombGrid.length-1) {
						if(bombGrid[i-1][j] == true) {
							count++;
						}
						if(bombGrid[i-1][j+1] == true) {
							count++;
						}
						if(bombGrid[i][j] == true) {
							count++;
						}
						if(bombGrid[i][j+1] == true) {
							count++;
						}
					}
					else {
						if(bombGrid[i-1][j] == true) {
							count++;
						}
						if(bombGrid[i-1][j+1] == true) {
							count++;
						}
						if(bombGrid[i][j] == true) {
							count++;
						}
						if(bombGrid[i][j+1] == true) {
							count++;
						}
						if(bombGrid[i+1][j] == true) {
							count++;
						}
						if(bombGrid[i+1][j+1] == true) {
							count++;
						}
					}
				}
				//END OF LEFT BORDER
				
				else if(j == bombGrid[i].length-1) {
					if(i == bombGrid.length-1) {
						if(bombGrid[i-1][j-1] == true) {
							count++;
						}
						if(bombGrid[i-1][j] == true) {
							count++;
						}
						if(bombGrid[i][j-1] == true) {
							count++;
						}
						if(bombGrid[i][j] == true) {
							count++;
						}
					}
					else {
						if(bombGrid[i-1][j-1] == true) {
							count++;
						}
						if(bombGrid[i-1][j] == true) {
							count++;
						}
						if(bombGrid[i][j-1] == true) {
							count++;
						}
						if(bombGrid[i][j] == true) {
							count++;
						}
						if(bombGrid[i+1][j-1] == true) {
							count++;
						}
						if(bombGrid[i+1][j]==true) {
							count++;
						}
					}
				}
				//END OF RIGHT BORDER
				
				else if(i == bombGrid.length-1) {
					if(bombGrid[i-1][j-1] == true) {
						count++;
					}
					if(bombGrid[i-1][j] == true) {
						count++;
					}
					if(bombGrid[i-1][j+1] == true) {
						count++;
					}
					if(bombGrid[i][j-1] == true) {
						count++;
					}
					if(bombGrid[i][j] == true) {
						count++;
					}
					if(bombGrid[i][j+1] == true) {
						count++;
					}
				}
				//END OF BOTTOM BORDER
				
				else {
					if(bombGrid[i-1][j-1] == true) {
						count++;
					}
					if(bombGrid[i-1][j] == true) {
						count++;
					}
					if(bombGrid[i-1][j+1] == true) {
						count++;
					}
					if(bombGrid[i][j-1] == true) {
						count++;
					}
					if(bombGrid[i][j] == true) {
						count++;
					}
					if(bombGrid[i][j+1] == true) {
						count++;
					}
					if(bombGrid[i+1][j-1] == true) {
						count++;
					}
					if(bombGrid[i+1][j] == true) {
						count++;
					}
					if(bombGrid[i+1][j+1] == true) {
						count++;
					}
				}
				//EVERYTHING ELSE
				
				countGrid[i][j] = count;
			}
		}
	}
	
	
	

}
