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
    private static int toWin = 4;      //4 gewinnt
    public static int lastX = 0;
    public static int lastY = 0;

    // Enum for the different tokens (empty, O, X)
    public enum Token {

        empty(" "),
        player1("O"),
        player2("X");
        private String representation; // string representation of value

        Token(String s) {
            this.representation = s;
        }

        public String toString() {
            return this.representation;
        }
    }
    private static Token[][] board = new Token[COLS][ROWS]; // 7 columns with 6 fields each
    private static IPlayer[] players = new IPlayer[2]; // two players
    private Token correctToken;

    /** initialize board and players and start the game */
    public void play() {
        // initialize the board
        for (Token[] column : VierGewinnt.board) {
            Arrays.fill(column, Token.empty);
        }


        // initialize players
        players[0] = new HumanPlayer();
        System.out.print("Play against a human opponent? (y / n) ");
        String opponent = new Scanner(System.in).nextLine();
        if (opponent.toLowerCase().equals("y")) {
            players[1] = new HumanPlayer();
        } else {
            players[1] = new ComputerPlayer();
        }
        players[0].setToken(Token.player1);
        players[1].setToken(Token.player2);
        // play...
        boolean solved = false;
        int currentPlayer = (new java.util.Random()).nextInt(2);  //choose randomly who begins
        System.out.println("current player: " + currentPlayer);
        int insertCol, insertRow; // starting from 0
        while (true) {
            // get player's next "move"
            lastX = insertCol = players[currentPlayer].getNextColumn(VierGewinnt.board);
            // insert the token and get the row where it landed
            lastY = insertRow = this.insertToken(insertCol, players[currentPlayer].getToken());
            // check if the game is over
            if(VierGewinnt.checkWin(insertCol, insertRow) != ' ' || isBoardFull())
                break;
            
            //char bla = VierGewinnt.checkWin(insertCol, insertRow);
            //switch to other player
            currentPlayer = (currentPlayer + 1) % 2;
        }
        char winner = VierGewinnt.checkWin(lastX, lastY);
        System.out.println(displayBoard(VierGewinnt.board));
        if (winner != ' ') {
            System.out.println("Player " + winner + " wins!");
        } else{
            System.out.println("Draw! Game over.");
        }
    }

    /** Inserts the token at the specified column (if possible)
    and returns the row where the token landed */
    /** !!!!! Achtung: in der Aufgabenstellung steht, dass das Programm bei ungültiger
    Eingabe mit Fehlermeldung terminieren soll. Hier ist das aber gar nicht
    noetig, da eine ungültige Spaltenangabe abgefangen wird und neu gefragt
    wird. !!!!! */
    private int insertToken(int column, Token tok) {
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
        boolean full = true;
        for (int i = 0; i < COLS; i++) {
            if (board[i][ROWS - 1].equals(Token.empty)) {
                full = false;
            }
        }
        return full;
    }

    /** Checks for at least four equal tokens in a row in either direction,
    starting from the given position. */
    private boolean checkVierGewinnt(int col, int row) {
        this.correctToken = board[col][row];

        boolean horizontal = checkHorizontal(col, row);
        boolean vertical = checkVertical(col, row);
        boolean diagonal1 = checkDiagonal1(col, row);
        boolean diagonal2 = checkDiagonal2(col, row);

        return (horizontal || vertical || diagonal1 || diagonal2);
    }

    private boolean checkHorizontal(int col, int row) {
        int counter = 1;
        for (int i = col + 1; i < VierGewinnt.COLS; i++) {
            if (board[i][row].equals(this.correctToken)) {
                counter++;
            } else {
                break;
            }
        }

        for (int i = col - 1; i >= 0; i--) {
            if (board[i][row].equals(this.correctToken)) {
                counter++;
            } else {
                break;
            }
        }

        return (counter == 4);
    }

    private boolean checkVertical(int col, int row) {
        int counter = 1;
        for (int i = row + 1; i < VierGewinnt.ROWS; i++) {
            if (board[col][i].equals(this.correctToken)) {
                counter++;
            } else {
                break;
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            if (board[col][i].equals(this.correctToken)) {
                counter++;
            } else {
                break;
            }
        }

        return (counter == 4);
    }

    private boolean checkDiagonal1(int col, int row) {
        int counter = 1;
        int i, j;
        for (i = row + 1, j = col + 1; i < VierGewinnt.ROWS && j < VierGewinnt.COLS;
                i++, j++) {
            if (board[j][i].equals(this.correctToken)) {
                counter++;
            } else {
                break;
            }
        }

        int k, l;
        for (k = row - 1, l = col - 1; k >= 0 && l >= 0; k--, l--) {
            if (board[l][k].equals(this.correctToken)) {
                counter++;
            } else {
                break;
            }
        }

        return (counter == 4);
    }

    private boolean checkDiagonal2(int col, int row) {
        int counter = 1;
        int i, j;
        for (i = row + 1, j = col - 1; i < VierGewinnt.ROWS && j >= 0;
                i++, j--) {
            if (board[j][i].equals(this.correctToken)) {
                counter++;
            } else {
                break;
            }
        }

        int k, l;
        for (k = row - 1, l = col + 1; k >= 0 && l < VierGewinnt.COLS; k--, l++) {
            if (board[l][k].equals(this.correctToken)) {
                counter++;
            } else {
                break;
            }
        }

        return (counter == 4);
    }

    public static char checkWin(int lastX, int lastY) {
        //We only have to check around the last placed
        //piece.
        int x1, y1, x2, y2;
        int width = COLS;
        int height = ROWS;
        char player = at(lastX, lastY);
        //
        //Test if there's a horizontal win
        //
        x1 = x2 = lastX;
        //Go right
        while (x1 < width && at(x1, lastY) == player) {
            ++x1;
        }
        //Go left
        while (x2 >= 0 && at(x2, lastY) == player) {
            --x2;
        }
        //Do we have a win here?
        if (x1 - x2 > toWin) {
            return player;
        }

        //
        //Test if there's a vertical win
        //
        y1 = y2 = lastY;
        //Go up
        while (y1 < height && at(lastX, y1) == player) {
            ++y1;
        }
        //Go down
        while (y2 >= 0 && at(lastX, y2) == player) {
            --y2;
        }
        //Do we have a win here?
        if (y1 - y2 > toWin) {
            return player;
        }

        //                              X
        // Do we have a win like this?    X
        //                                  X
        x1 = x2 = lastX;
        y1 = y2 = lastY;
        //Go down and right
        while (y1 >= 0 && x1 < width && at(x1, y1) == player) {
            ++x1;
            --y1;
        }
        //Go up and left
        while (y2 < height && x2 >= 0 && at(x2, y2) == player) {
            --x2;
            ++y2;
        }
        //Do we have a win here?
        if (x1 - x2 > toWin) {
            return player;
        }

        //                                  X
        // Do we have a win like this?    X
        //                              X
        x1 = x2 = lastX;
        y1 = y2 = lastY;
        //Go down and left
        while (y1 >= 0 && x1 >= 0 && at(x1, y1) == player) {
            --x1;
            --y1;
        }
        //Go up and right
        while (y2 < height && x2 < width && at(x2, y2) == player) {
            ++x2;
            ++y2;
        }
        //Do we have a win here?
        if (x2 - x1 > toWin) {
            return player;
        }

        //No one is the winner
        return ' ';
    }

    public static int getScore() {
        int score = 0;
        int width = COLS;
        int height = ROWS;

        //Pieces in the middle score higher than
        //pieces at the edge

        for (int x = 0; x < width; ++x) {
            int columnscore = (width / 2) - x;
            if (columnscore < 0) {
                columnscore = -columnscore;
            }
            columnscore = (width / 2) - columnscore;

            //Count the number of pieces in each column
            //and score accordingly
            for (int y = 0; y < height; ++y) {
                int rowscore = (height / 2) - y;
                if (rowscore < 0) {
                    rowscore = -rowscore;
                }
                rowscore = (height / 2) - rowscore;

                if (at(x, y) == 'X') {
                    score += columnscore + rowscore;
                } else if (at(x, y) == 'O') {
                    score -= columnscore + rowscore;
                }
            }
        }

        return score;
    }

    public static char at(int x, int y) {
        if (board[x][y].equals(Token.player1)) {
            return 'O';
        } else if (board[x][y].equals(Token.player2)) {
            return 'X';
        } else {
            return ' ';
        }
    }

    /** returns a graphical representation of the board */
    public static String displayBoard(Token[][] myBoard) {
        String rowDelimiter = "+";
        String rowNumbering = " ";
        for (int col = 0; col < myBoard.length; col++) {
            rowDelimiter += "---+";
            rowNumbering += " " + (col + 1) + "  ";
        }
        rowDelimiter += "\n";

        String rowStr;
        String presentation = rowDelimiter;
        for (int row = myBoard[0].length - 1; row >= 0; row--) {
            rowStr = "| ";
            for (int col = 0; col < myBoard.length; col++) {
                rowStr += myBoard[col][row].toString() + " | ";
            }
            presentation += rowStr + "\n" + rowDelimiter;
        }
        presentation += rowNumbering;
        return presentation;
    }

    public static Token getTokenFromChar(char player) {
        Token token = Token.empty;
        if (player == 'O') {
            token = Token.player1;
        }
        if (player == 'X') {
            token = Token.player2;
        }
        return token;
    }

    public static void move(int x, char player) {
        int height = ROWS;
        int width = COLS;
        //Find the first free place and place a
        //brick there
        for (int y = 0; y < height; ++y) {
            if (at(x, y) == ' ') {
                board[x][y] = getTokenFromChar(player);
                lastX = x;
                lastY = y;
                return;
            }
        }
    }

    public static boolean canMove(int x) {
        return board[x][ROWS - 1].equals(Token.empty);
    }

    public static void undo(int x, char player) {
        //Find the last non-free place and place a
        int y = ROWS - 1;
        while (y >= 0 && at(x, y) == ' ') {
            --y;
        }
        if (at(x, y) == player) {
            board[x][y] = Token.empty;
        }
    }

    /** main method, starts the program */
    public static void main(String args[]) {
        VierGewinnt game = new VierGewinnt();
        game.play();
    }
}
