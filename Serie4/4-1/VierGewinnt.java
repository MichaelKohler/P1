/*
 Programmierung 1 HS 2011
 Aufgabe 4-1
 
 Michael Kohler - 11-108-289
 Lukas Diener - 11-123-213
*/

import java.util.Arrays;
import java.util.Scanner;

public class VierGewinnt {
	
	public static final int COLS = 7;
	public static final int ROWS = 6;
	
	// Enum for the different tokens (empty, O, X)
	public enum Token { 
		empty(" "),
		player1("O"),
		player2("X");
		private String representation; // string representation of value
		Token(String s) { this.representation = s; }
		public String toString() { return this.representation; }
	}
	
	private Token[][] board = new Token[COLS][ROWS]; // 7 columns with 6 fields each
	private IPlayer[] players = new IPlayer[2]; // two players
	
	private Token correctToken;
	
	/** initialize board and players and start the game */
	public void play() {
		// initialize the board
		for (Token[] column : this.board) {
			Arrays.fill(column, Token.empty);
		}
		// initialize players
		players[0] = new HumanPlayer();
		System.out.print("Play against a human opponent? (y / n) ");
		String opponent = new Scanner(System.in).nextLine();
		if(opponent.toLowerCase().equals("y")){
			players[1] = new HumanPlayer();
		}else{
			players[1] = new ComputerPlayer();
		}
		players[0].setToken(Token.player1);
		players[1].setToken(Token.player2);
		// play...
		boolean solved = false;
		int currentPlayer = (new java.util.Random()).nextInt(2);  //choose randomly who begins
		System.out.println("current player: "+currentPlayer);
		int insertCol, insertRow; // starting from 0
		while (!solved && !this.isBoardFull()){
			// get player's next "move"
			insertCol = players[currentPlayer].getNextColumn(this.board);
			// insert the token and get the row where it landed
			insertRow = this.insertToken(insertCol, players[currentPlayer].getToken());
			// check if the game is over
			solved = this.checkVierGewinnt(insertCol, insertRow);
			//switch to other player
			if (!solved) currentPlayer = (currentPlayer+1) % 2;
		}
		System.out.println(displayBoard(this.board));
		if (solved) System.out.println("Player "+players[currentPlayer].getToken() + " wins!");
		else System.out.println("Draw! Game over.");
	}
	
	
	/** Inserts the token at the specified column (if possible)
	    and returns the row where the token landed */
	    
	/** !!!!! Achtung: in der Aufgabenstellung steht, dass das Programm bei ungültiger
	    Eingabe mit Fehlermeldung terminieren soll. Hier ist das aber gar nicht
	    noetig, da eine ungültige Spaltenangabe abgefangen wird und neu gefragt
	    wird. !!!!! */
	private int insertToken(int column, Token tok){
		int row = -1;
		for (int i = 0; i < board[column].length; i++) {
		  if (board[column][i].equals(Token.empty)) {
		    row = i;
		    break;
		  }
		}
		board[column][row] = tok;
		return row;
	}
	
	
	/** Checks if every position is occupied */
	private boolean isBoardFull() {
		boolean full = false;
		for (int i = 0; i < this.COLS; i++) {
		  full = !board[i][this.ROWS - 1].equals(Token.empty);
		}
		return full;
	}
	
	
	/** Checks for at least four equal tokens in a row in either direction,
	    starting from the given position. */
	private boolean checkVierGewinnt(int col, int row){
	  this.correctToken = board[col][row];
	
		boolean horizontal = checkHorizontal(col, row);
		boolean vertical = checkVertical(col, row);
		boolean diagonal1 = checkDiagonal1(col, row);
		boolean diagonal2 = checkDiagonal2(col, row);

    return (horizontal || vertical || diagonal1 || diagonal2);
	}

  private boolean checkHorizontal(int col, int row) {
    int counter = 1;
    for (int i = col + 1; i < this.COLS; i++) {
      if (board[i][row].equals(this.correctToken))
        counter++;
      else
        break;
    }
    
    for (int i = col - 1; i >= 0; i--) {
      if (board[i][row].equals(this.correctToken))
        counter++;
      else
        break;
    }
    
    return (counter == 4);
  }
	
	private boolean checkVertical(int col, int row) {
    int counter = 1;
    for (int i = row + 1; i < this.ROWS; i++) {
      if (board[col][i].equals(this.correctToken))
        counter++;
      else
        break;
    }
    
    for (int i = row - 1; i >= 0; i--) {
      if (board[col][i].equals(this.correctToken))
        counter++;
      else
        break;
    }
    
    return (counter == 4);
  }
  
  private boolean checkDiagonal1(int col, int row) {
    int counter = 1;
    int i, j;
    for (i = row + 1, j = col + 1; i < this.ROWS && j < this.COLS;
         i++, j++) {
      if (board[j][i].equals(this.correctToken))
        counter++;
      else
        break;
    }
    
    int k, l;
    for (k = row - 1, l = col - 1; k >= 0 && l >= 0; k--, l--) {
      if (board[l][k].equals(this.correctToken))
        counter++;
      else
        break;
    }
    
    return (counter == 4);
  }
  
  private boolean checkDiagonal2(int col, int row) {
    int counter = 1;
    int i, j;
    for (i = row + 1, j = col - 1; i < this.ROWS && j >= 0;
         i++, j--) {
      if (board[j][i].equals(this.correctToken))
        counter++;
      else
        break;
    }
    
    int k, l;
    for (k = row - 1, l = col + 1; k >= 0 && l < this.COLS; k--, l++) {
      if (board[l][k].equals(this.correctToken))
        counter++;
      else
        break;
    }
    
    return (counter == 4);
  }
	
	/** returns a graphical representation of the board */
	public static String displayBoard(Token[][] myBoard){
		String rowDelimiter = "+";
		String rowNumbering = " ";
		for(int col=0; col<myBoard.length; col++){
			rowDelimiter += "---+";
			rowNumbering += " "+(col+1)+"  ";
		}
		rowDelimiter += "\n";
		
		String rowStr;
		String presentation = rowDelimiter;
		for(int row=myBoard[0].length-1; row >= 0; row--){
			rowStr = "| ";
			for(int col=0; col < myBoard.length; col++){
				rowStr += myBoard[col][row].toString() + " | ";
			}
			presentation += rowStr + "\n" + rowDelimiter;
		}
		presentation += rowNumbering;
		return presentation;
	}
	
	
	
	/** main method, starts the program */
	public static void main(String args[]){
		VierGewinnt game = new VierGewinnt();
		game.play();
	}
}
