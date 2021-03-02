import java.util.Scanner;

public class TicTacToe {
	public static char[] board;
	public static String PlayerLetter;
	public static String ComputerLetter;
	static Scanner sc = new Scanner(System.in);
	static int count;
	static int chance;

	static ArrayList<int[]> checkCondition = new ArrayList<>();

	public static void conditionsForWin()
	{
		int[] row1 = {1, 2, 3};
		int[] row2 = {4, 5, 6};
		int[] row3 = {7, 8, 9};
		int[] col1 = {1, 4, 7};
		int[] col2 = {2, 5, 8};
		int[] col3 = {3, 6, 9};
		int[] diag1 = {1, 5, 9};
		int[] diag2 = {3, 5, 7};

		checkCondition.add(row1);
		checkCondition.add(row2);
		checkCondition.add(row3);
		checkCondition.add(col1);
		checkCondition.add(col2);
		checkCondition.add(col3);
		checkCondition.add(diag1);
		checkCondition.add(diag2);
	}


	
	static char[] createBoard() {

		char[] board = new char[10];
		
		for(int i = 1; i < 10; i++) {
			board[i] = ' ';
		}
		return board;
         }
	
	public static void showBoard() {
		System.out.println (board[1] + " | " + board[2] + " | " + board[3]);
		System.out.println("----------");
		System.out.println (board[4] + " | " + board[5] + " | " + board[6]);
		System.out.println("----------");
		System.out.println (board[7] + " | " + board[8] + " | " + board[9]);
	}
	
	public static String choose(String option) {
		
		if(option.equals("X")) { 
			PlayerLetter = "X";
			ComputerLetter = "O";
		}
		else if(option.equals("O")) {
			ComputerLetter = "X";
			PlayerLetter = "O";
		}
		else {
			System.out.println("Incorrect Input");
		}
		return ComputerLetter;
	}
	

	
	public static void makePlayerMove(){

	System.out.println("Enter the position you want to move to : "
        					+ "\nPosition must be betwween 1 to 9");
        int position = sc.nextInt();

        if(position >= 1 && position <= 9 && board[position] == 0) {
        	board[position] = PlayerLetter.charAt(0);
        }
        else if (position >= 1 && position <= 9 && board[position] != 0){
        	System.out.println("Position is taken. \n Enter again");
        	position = sc.nextInt();
        	if(position >= 1 && position <= 9 && board[position] == 0)
        		board[position] = PlayerLetter.charAt(0);
        }
        else {
        	System.out.println("Invalid Position.");
        }
        	
        showBoard();
	}
	
	public static void makeComputerMove() {
		int cposition = (int)Math.floor(Math.random() * (10-1))+1;
		
		if(board[cposition] == 0 ) {
			System.out.println("Computer will put " + ComputerLetter.charAt(0) + " at position : " + cposition);
			board[cposition] = ComputerLetter.charAt(0);
		}
		else if(board[cposition] != 0){
			cposition = (int)Math.floor(Math.random() * (10-1))+1;
			if(board[cposition] == 0 ) {
				System.out.println("Computer will put " + ComputerLetter.charAt(0) + " at position : " + cposition);
				board[cposition] = ComputerLetter.charAt(0);
			}
		}
		
		showBoard();
	}
	
	public static void Toss() {
		System.out.println("Lets Toss! \nEnter 1 for Heads and 2 for Tails");
		int opt = sc.nextInt();
		int toss = (int)Math.floor(Math.random() * 10) %2;
		
		if(opt == toss) {
			System.out.println("Player won the toss! So player starts the game.");
			count = 0;
		}
		else {
			System.out.println("Computer won the toss! So computer starts the game.");
			count = 1;
		}
		
	}
public static char winCheck()
	{
		final int WIN_CONDITION = 3;

		for (int index = 0; index < checkCondition.size(); index++)
		{
			int addX = 0;
			int addO = 0;
			for (int i = 0; i < checkCondition.get(index).length; i++)
			{
				if (board[checkCondition.get(index)[i]] == 'X')
				{
					addX += 1;
					if (addX == WIN_CONDITION)
					{
						return 'X';
					}
				}
				else if (board[checkCondition.get(index)[i]] == 'O')
				{
					addO += 1;
					if (addO == WIN_CONDITION)
					{
						return 'O';
					}
				}
			}
		}
		return ' ';
	}

	public static boolean tie()
	{
		for (int k = 1; k < 10; k++)
		{
			if (board[k] == ' ')
			{
				return false;
			}
		}
		return true;
	}

	public static void turn()
	{
		while (true)
		{
			if (winCheck() == 'X' || winCheck() == 'O')
			{
				System.out.println((userLetter == winCheck()) ? "You Win" : "Computer Wins");
				break;
			}
			else if (tie())
			{
				System.out.println("It's a tie");
				break;
			}
			else
			{
				if (count%2 == chance)
				{
					System.out.println("Your Turn");
					makeMove();
					showBoard();
				}
				else
				{
					System.out.println("Computer Turn");
					computerTurn();
					showBoard();
				}
				count++;
			}
		}
	}

        public static void PlayAgain(){
        
        System.out.println("Nice game ! you wnt play again....y/n");
        String option = sc.next().toUpperCase();
        if (option.equals("y")){
            Toss();
        }
        else 
            System.out.println("Thank you");
        }

	public static void main(String[] args) {
		System.out.println("Welocme to Tic-Tac-Toe program");
		board = new char[10];
		System.out.println("Please Choose X or O");
		String option = sc.next().toUpperCase();		
		
		choose(option);
		System.out.println("Player is : " + PlayerLetter);
		System.out.println("Computer is : " + ComputerLetter);
		showBoard();
		Toss();
		turn();
		
	}
}