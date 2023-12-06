package GameProject;



import java.util.Random;
import java.util.Scanner;

class TicTacToe {
	static char[][] board;

	TicTacToe() {
		board = new char[3][3];
		initBoard();
	}

	static void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = ' ';
			}
		}
	}

	static void displayBoard() {
		System.out.println("-------------");
		for (int i = 0; i < board.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");

		}
	}

	static void placeMark(int row, int column, char mark) {
		if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
			board[row][column] = mark;
		} else {
			System.out.println("Invalid Position");
		}
	}

	static boolean checkColumnWin() {
		for (int j = 0; j <= 2; j++) {
			if (board[j][0] != ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkRowWin() {
		for (int i = 0; i <= 2; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkDiagonalWin() {
		if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]
				|| board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}
		return false;
	}
	static boolean toChackDraw() {
		for (int i = 0; i <=2; i++) {
			for (int j = 0; j <=2; j++) {
				if(board[i][j] ==' ') {
					return false ;
				}
			}
			
		}
		return true;
	}
}

abstract class Player {
	static String name;
	static char mark;

	abstract void makeMove();

	boolean isValidMove(int row, int column) {
		if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
			if (TicTacToe.board[row][column] == ' ') {
				return true;
			} else {
				System.out.println("Inavalid position already have ");
			}
		}
		return false;
	}

}

class AI extends Player {

	AI(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		int row;
		int column;
		do {
			System.out.println("Enter the row and column");
			Random r = new Random();
			row = r.nextInt(3);
			column = r.nextInt(3);
		} while (!isValidMove(row, column));
		TicTacToe.placeMark(row, column, mark);
	}

}

class HumanPlayer extends Player {

	HumanPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		int row;
		int column;
		do {
			System.out.println("Enter the row and column");
			Scanner scan = new Scanner(System.in);
			row = scan.nextInt();
			column = scan.nextInt();
		} while (!isValidMove(row, column));
		TicTacToe.placeMark(row, column, mark);
	}

}

public class LunchGame {
	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();

		HumanPlayer p1 = new HumanPlayer("Suresh", 'X');
		AI p2 = new AI("Vasu", 'O');

		Player cp;
		cp = p1;

		while (true) {
			System.out.println(cp.name + " turn");
			cp.makeMove();
			t.displayBoard();
			if (TicTacToe.checkColumnWin() || TicTacToe.checkRowWin() || TicTacToe.checkDiagonalWin()) {
				System.out.println(cp.name + " Win the game");
				break;
			} else if(TicTacToe.toChackDraw()) {
				System.out.println("Game is a Draw");
			}
			else {
				if (cp == p1) {
					cp = p2;
				} else if (cp == p2) {
					cp = p1;
				}
			}
		}

	}

}

