import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class tester {
	public static void main (String[] args) {
		Scanner scnr = new Scanner(System.in);
				
		boolean[][] gridArr = new boolean[5][5];
		int[][] arr = new int[5][5];

		Random rand = new Random();
		for(int i = 0; i < gridArr.length; i++) {
			for(int j = 0; j < gridArr[i].length; j++) {
				int num = rand.nextInt();
				if(num%2 == 0) {
					gridArr[i][j] = true;
				}
				else {
					gridArr[i][j] = false;
				}
			}
		}
		
		
		for(int i = 0; i < gridArr.length; i++) {
			for(int j = 0; j < gridArr[i].length; j++) {
				if(gridArr[i][j] == true) {
					System.out.print("T ");
				}
				else {
					System.out.print("F ");
				}
			}
			System.out.println();
		}
		System.out.println();
		
		for(int i = 0; i < gridArr.length; i++) {
			for(int j = 0; j < gridArr[i].length; j++) {
				int count = 0;
				if(i == 0) {
					if(j == 0) {
						if(gridArr[i][j] == true) {
							count++;
						}
						if(gridArr[i][j+1] == true) {
							count++;
						}
						if(gridArr[i+1][j] == true) {
							count ++;
						}
						if(gridArr[i+1][j+1] == true){
							count++;
						}
					}
					else if(j == gridArr[i].length - 1) {
						if(gridArr[i][j-1] == true) {
							count++;
						}
						if(gridArr[i][j] == true) {
							count++;
						}
						if(gridArr[i+1][j-1] == true) {
							count++;
						}
						if(gridArr[i+1][j] == true) {
							count++;
						}
					}
					else {
						if(gridArr[i][j-1] == true) {
							count++;
						}
						if(gridArr[i][j] == true) {
							count++;
						}
						if(gridArr[i+1][j-1] == true) {
							count++;
						}
						if(gridArr[i+1][j] == true) {
							count++;
						}
						if(gridArr[i+1][j+1] == true){
							count++;
						}
						if(gridArr[i][j+1] == true) {
							count++;
						}
					}
					
				}
				else if(j == 0) {
					if(i == gridArr.length-1) {
						if(gridArr[i-1][j] == true) {
							count++;
						}
						if(gridArr[i-1][j+1] == true) {
							count++;
						}
						if(gridArr[i][j] == true) {
							count++;
						}
						if(gridArr[i][j+1] == true) {
							count++;
						}
					}
					else {
						if(gridArr[i-1][j] == true) {
							count++;
						}
						if(gridArr[i-1][j+1] == true) {
							count++;
						}
						if(gridArr[i][j] == true) {
							count++;
						}
						if(gridArr[i][j+1] == true) {
							count++;
						}
						if(gridArr[i+1][j] == true) {
							count++;
						}
						if(gridArr[i+1][j+1] == true) {
							count++;
						}
					}
				}
				
				else if(j == gridArr[i].length-1) {
					if(i == gridArr.length-1) {
						if(gridArr[i-1][j-1] == true) {
							count++;
						}
						if(gridArr[i-1][j] == true) {
							count++;
						}
						if(gridArr[i][j-1] == true) {
							count++;
						}
						if(gridArr[i][j] == true) {
							count++;
						}
					}
					else {
						if(gridArr[i-1][j-1] == true) {
							count++;
						}
						if(gridArr[i-1][j] == true) {
							count++;
						}
						if(gridArr[i][j-1] == true) {
							count++;
						}
						if(gridArr[i][j] == true) {
							count++;
						}
						if(gridArr[i+1][j-1] == true) {
							count++;
						}
						if(gridArr[i+1][j]==true) {
							count++;
						}
					}
				}
				else if(i == gridArr.length-1) {
					if(gridArr[i-1][j-1] == true) {
						count++;
					}
					if(gridArr[i-1][j] == true) {
						count++;
					}
					if(gridArr[i-1][j+1] == true) {
						count++;
					}
					if(gridArr[i][j-1] == true) {
						count++;
					}
					if(gridArr[i][j] == true) {
						count++;
					}
					if(gridArr[i][j+1] == true) {
						count++;
					}
				}
				else {
					if(gridArr[i-1][j-1] == true) {
						count++;
					}
					if(gridArr[i-1][j] == true) {
						count++;
					}
					if(gridArr[i-1][j+1] == true) {
						count++;
					}
					if(gridArr[i][j-1] == true) {
						count++;
					}
					if(gridArr[i][j] == true) {
						count++;
					}
					if(gridArr[i][j+1] == true) {
						count++;
					}
					if(gridArr[i+1][j-1] == true) {
						count++;
					}
					if(gridArr[i+1][j] == true) {
						count++;
					}
					if(gridArr[i+1][j+1] == true) {
						count++;
					}
				}
				
				arr[i][j] = count;
				
			}
		}
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
			System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		
		
		System.out.println("Enter row number and column numer:");
		int userNum = scnr.nextInt();
		int userNum2 = scnr.nextInt();
		
		for(int i = userNum; i < arr.length; i++) {
			for(int j = userNum2; j < arr[i].length; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 10;
				}
			}
		}
		for(int i = userNum; i < arr.length; i--) {
			for(int j = userNum2; j < arr[i].length; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 10;
				}
			}
		}
		for(int i = userNum; i < arr.length; i--) {
			for(int j = userNum2; j < arr[i].length; j--) {
				if(arr[i][j] == 0) {
					arr[i][j] = 10;
				}
			}
		}
		for(int i = userNum; i < arr.length; i++) {
			for(int j = userNum2; j < arr[i].length; j--) {
				if(arr[i][j] == 0) {
					arr[i][j] = 10;
				}
			}
		}
		
		
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
			System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}

}
