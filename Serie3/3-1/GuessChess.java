/*
 * Programmierung 1 HS 2011
 * Aufgabe 3-1
 *
 * Lukas Diener - 11-123-213
 * Michael Kohler - 11-108-289
*/

import java.util.Random;
import java.util.Scanner;

public class GuessChess{
  private int secretRow;     // secret row, between 1 and 8
  private char secretColumn; // secret column, between 'a' and 'h'
  private String secretField = "";

  public final int MAX_GUESSES = 3;
  
  private String userGuess = "";
  private char userColumn;
  private int userRow;

  private static boolean again = true;
  private static int totalGuesses = 0; // only counted when field found
  private static int totalGames = 0;
  private static int lostGames = 0;
  private static int wonGames = 0;

  public static void main (String[] args) {
    GuessChess game = new GuessChess();
    while (again) {
      totalGames++;
      game.start();
    }
    statOutput();
  }


  public void start() {
    showWelcomeScreen();
    printEmptyBoard();
    System.out.println("");
    rememberSecretField();

    for (int i = 0; i < MAX_GUESSES; i++) {
      System.out.print("You have " + (MAX_GUESSES - i) + " attempts left. " + 
                       "Guess field (e.g. b3): ");
      getUserInput();
      processUserInput();
      boolean same = compareFields();
      if (same) {
        showSuccessMessage();
        wonGames++;
        totalGuesses += i + 1;
        askAgain();
        return;
      }
      else if (i < MAX_GUESSES - 1) {
        showHints();
        printBoard(userColumn, userRow);
      }
    }

    // if user didn't get it right
    showFailureMessage();
    lostGames++;
    askAgain();
  }

  private void rememberSecretField() {
    Random randRow = new Random();
    secretRow = randRow.nextInt(7) + 1;

    Random randCol = new Random();
    int randintCol = randCol.nextInt(7) + 1;
    secretColumn = getColumnAsChar(randintCol);

    secretField = "" + secretColumn + secretRow;
    System.out.println(secretField);
  }

  private void getUserInput() {
    Scanner scan = new Scanner(System.in);
    userGuess = scan.nextLine();
  }

  private void processUserInput() {
    userColumn = userGuess.charAt(0);
    userRow = Integer.parseInt(userGuess.substring(1, 2));    
  }

  private boolean compareFields() {
    return (userGuess.equals(secretField));
  }

  private void showHints() {
    String horizontalHint = (userColumn < secretColumn) ?
                             " GO RIGHT! " : " GO LEFT!";
    if (userColumn == secretColumn) { horizontalHint = ""; }
    
    String verticalHint = (userRow < secretRow) ?
                           " GO UP! " : " GO DOWN!";
    if (userRow == secretRow) { verticalHint = ""; }

    String hintMessage = "" + horizontalHint + verticalHint;
    System.out.println("\n" + userGuess + " was wrong. " +
                       "Hints:" + hintMessage);
  }

  private void showSuccessMessage() {
    System.out.println("You win!");
  }

  private void showFailureMessage() {
    System.out.println("You lose! Secret field: " + secretField + ".");
  }

  private void askAgain() {
    System.out.print("Do you want to play again? (y/n)\t");
    Scanner askScan = new Scanner(System.in);
    String tmpAnswer = askScan.nextLine();
    if (!tmpAnswer.equals("y")) {
      again = false;
    }
  }

  private static void statOutput() {
    System.out.println("\n\nTotal games:\t" + totalGames);
    System.out.println("Lost games:\t" + lostGames);
    System.out.println("Won games:\t" + wonGames);

    double percentage = (float)wonGames / totalGames * 100;
    System.out.println("\nYou won " + percentage + "% of " + totalGames +
                       " games.");

    int avgGuesses = Math.round((int) totalGuesses / totalGames);
    String out = "In average you needed " +
                 ((avgGuesses == 1) ? "one guess" : avgGuesses + " guesses") +
                 " to find the secret field.";
    System.out.println(out);
  }
  

  // ==================================================================
  // Helper methods:

  /** prints a chess board with an 'X' on the field defined by column
      and row  */
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
