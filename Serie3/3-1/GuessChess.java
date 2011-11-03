/*

*/


public class GuessChess{
	private int secretRow;	   // secret row, between 1 and 8
	private char secretColumn; // secret column, between 'a' and 'h'
	
	public final int MAX_GUESSES = 3;
	
	public static void main (String[] args) {
		GuessChess game = new GuessChess();
		game.start();
	}


	// your code goes here...




	// ==================================================================
	// Helper methods:

	/** prints a chess board with an 'X' on the field defined by column and row	*/
	private void printBoard(char column, int row){
		int column_num = getColumnAsInt(column);
		String board = "  _ _ _ _ _ _ _ _\n";
		for(int i=8; i>0; i--){
			board += i;
			if(row==i){
				for(int j=1; j<=8; j++){
					if(column_num==j){
						board += "|X";
					}else{
						board += "|_";
					}
				}
				board += "|\n";
			}else{
				board += "|_|_|_|_|_|_|_|_|\n";
			}
		}
		board += "  a b c d e f g h ";
		System.out.println(board);
	}
	/** print the board without an 'X' */
	private void printEmptyBoard(){
		printBoard('z',-1);
	}
	
	private void showWelcomeScreen(){
		String s="";
		s += "           ()\n";
		s += "         <~~~~>\n";
		s += "          \\__/                     ___/\"\"\"\n";
		s += "         (____)                   |___ 0 }\n";
		s += "          |  |    *************     /    }\n";
		s += "          |__|    *GUESS CHESS*    /     }\n";
		s += "         /____\\   *************    \\____/\n";
		s += "        (______)                   /____\\\n";
		s += "       (________)                 (______)\n";
		System.out.println(s);
	}
	
	private int getColumnAsInt(char column){
		switch(column){
			case 'a': return 1;
			case 'b': return 2;
			case 'c': return 3;
			case 'd': return 4;
			case 'e': return 5;
			case 'f': return 6;
			case 'g': return 7;
			case 'h': return 8;
			default: return 0;
		}
	}
	private char getColumnAsChar(int column){
		switch(column){
			case 1: return 'a';
			case 2: return 'b';
			case 3: return 'c';
			case 4: return 'd';
			case 5: return 'e';
			case 6: return 'f';
			case 7: return 'g';
			case 8: return 'h';
			default: return 'z';
		}
	}
}
