/*
Programmierung 1 HS 2011
Aufgabe 4-1

Michael Kohler - 11-108-289
Lukas Diener - 11-123-213
 */

/** A very stupid computer player */
public class ComputerPlayer implements IPlayer {

    public static boolean aboutToWin;
    private VierGewinnt.Token token;
    public static final int maxScore = 100;			//max score for field
    public static final int invalidScore = 1000000;         //invalid score
    private static boolean useEvaluationFunction = true;
    private static int maxDepth = 8;
    public static int[] moveScore = new int[VierGewinnt.COLS];

    public static void main(String args[]) {
        for (int x = 0; x < VierGewinnt.COLS; ++x) {
            moveScore[x] = 0;
        }
    }

    public int getNextColumn(VierGewinnt.Token[][] board) {
        /*java.util.Random generator = new java.util.Random();
        boolean columnOK = false;
        int col = 0;
        while(!columnOK){
        // random number between 0 and 6
        col = generator.nextInt(board.length);
        // check if this column is still "free"
        if(isColFull(col, board) == false) columnOK = true;
        // if not, we have to generate another random number
        else columnOK = false;
        }
        return col;*/
        return determineMove('X');

    }

    /** returns true if the column col is already full and false otherwise. */
    private boolean isColFull(int col, VierGewinnt.Token[][] board) {
        int topRow = board[0].length - 1;
        if (board[col][topRow] != VierGewinnt.Token.empty) {
            return true;
        } else {
            return false;
        }
    }

    public void setToken(VierGewinnt.Token token) {
        this.token = token;
    }

    public VierGewinnt.Token getToken() {
        return this.token;
    }

    public String getProgrammers() {
        return "Lukas Diener, Michael Kohler";
    }

    private static int alphabeta(char player) {
        return alphabeta(player, -invalidScore, invalidScore, 0);
    }

    private static int alphabeta(char player, int alpha, int beta, int depth) {
        int width = VierGewinnt.COLS;
        int height = VierGewinnt.ROWS;
        //Check if there's a current winner
        char winner = VierGewinnt.checkWin(VierGewinnt.lastX, VierGewinnt.lastY);
        if (winner == 'X') {
            return maxScore;
        } else if (winner == 'O') {
            return -maxScore;
        }

        if (depth >= maxDepth) {
            //We cannot recurse more, and we haven't found
            //an end to the game
            //Return the score of the current table
            if (useEvaluationFunction) {
                return VierGewinnt.getScore();
            } else {
                return 0;
            }
        }

        if (player == 'X') {
            //It is player X's turn, he will try to
            //maximize the score

            //Go through all possible moves and get the
            //scores
            int maxScore = -invalidScore;
            for (int move = 0; move < width; ++move) {
                if (VierGewinnt.canMove(move)) {
                    VierGewinnt.move(move, 'X');
                    int score = alphabeta('O', alpha, beta, depth + 1);
                    VierGewinnt.undo(move, 'X');
                    if (score > alpha) {
                        alpha = score;
                    }
                    if (alpha >= beta) {
                        return alpha;
                    }
                }
            }
            return alpha;
        } else if (player == 'O') {
            //It is player X's turn, he will try to
            //maximize the score

            //Go through all possible moves and get the
            //scores
            int minScore = invalidScore;
            for (int move = 0; move < VierGewinnt.COLS; ++move) {
                if (VierGewinnt.canMove(move)) {
                    VierGewinnt.move(move, 'O');
                    int score = alphabeta('X', alpha, beta, depth + 1);
                    VierGewinnt.undo(move, 'O');
                    if (score < beta) {
                        beta = score;
                    }
                    if (alpha >= beta) {
                        return beta;
                    }
                }
            }
            return beta;
        } else //This is just for safty if the function
        //is called with wron parameters
        {
            return 0;
        }
    }

    private static int determineMove(char player) {

        if (player == 'X') {
            //It is player X's turn, he will try to
            //maximize the score

            //Go through all possible moves and get the
            //scores
            int maxScore = -invalidScore;
            int maxMove = 0;
            for (int move = 0; move < VierGewinnt.COLS; ++move) {
                if (VierGewinnt.canMove(move)) {
                    VierGewinnt.move(move, 'X');
                    int score = alphabeta('O');
                    ComputerPlayer.moveScore[move] = score;


                    if (score >= maxScore) {
                        maxScore = score;
                        maxMove = move;
                    }
                    VierGewinnt.undo(move, 'X');
                } else //Just set the move score to an invalid score
                {
                    moveScore[move] = invalidScore;
                }
            }

            //Return the move with the highest score
            return maxMove;
            
        } else if (player == 'O') {
            //It is player O's turn, he will try to
            //minimize the score

            //Go through all possible moves and get the
            //scores
            int minScore = invalidScore;
            int minMove = 0;
            for (int move = 0; move < VierGewinnt.COLS; ++move) {
                if (VierGewinnt.canMove(move)) {
                    VierGewinnt.move(move, 'O');
                    int score = alphabeta('X');

                    moveScore[move] = score;

                    if (score < minScore) {
                        minScore = score;
                        minMove = move;
                    }
                    VierGewinnt.undo(move, 'O');
                } else //Just set the move score to an invalid score
                {
                    moveScore[move] = invalidScore;
                }
            }
            //Return the move with the least score
            return minMove;
        } else //This is just for safty if the function
        //is called with wrong parameters
        {
            return 0;
        }
    }
}
