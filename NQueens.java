import java.util.*;

public class NQueens {

    // queenArray=chessboard, where each array element=column
    // and the value at each element=row
    private int[] queenArray;
    private int N;                  //size of board & # of queens
    private boolean success;        //flags if a solution is found
    private int currCol, currRow;   //used internally

    public NQueens(int N) {
        // This constructor takes in a positive integer N and creates
        // an array of N elements (the columns) which hold values that
        // correspond to the row at that column.

        this.N = N;
        if (N <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        queenArray = new int[N];

        placeNQueens();
        printToConsole();
        queenArray = null;
    }

    private boolean TestPiece() {
        // This method returns false if the currRow and currCol can be captured
        // by another queen, otherwise it returns true.

        for (int i = currCol - 1; i >= 0; i--) {
            if ((queenArray[i] == currRow) ||
                    (queenArray[i] == (currRow + (currCol - i))) ||
                    (queenArray[i] == (currRow - (currCol - i)))) {
                return false;
            }
        }

        return true;
    }

    public void printToConsole() {
        // This method simply prints out the current state of the
        // chess board. Note that success==true when there is a valid
        // configuration present in the queenArray.

        System.out.println();
        if (success == true) {
            for (int col = 0; col < N; col++) {
                for (int row = 0; row < N; row++) {
                    if (queenArray[col] == row) {
                        System.out.print("Q");
                    } else {
                        System.out.print("-");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution possible.");
        }
    }

    public boolean placeNQueens(){
        // This method uses backtracking to place N queens on the
        // NxN chess board.

            currRow = queenArray[currCol];

            // Infinite loop, but we'll return from the function
            // if currCol gets to N (past the rightmost column)
            // (ie. a solution was found)
            // or falls below 0 (past the leftmost column)
            // (ie. no solutions were found).
            for (; ; ) {
                if (TestPiece() == true) {
                    System.out.print(".");
                    queenArray[currCol] = currRow;
                    currCol++;
                    if (currCol >= N) {
                        success = true;
                        return success;
                    }
                    currRow = queenArray[currCol];
                } else { // advance
                    currRow++;
                    while (currRow >= N) { //backtrack
                        System.out.print("\u0008");
                        queenArray[currCol] = 0;
                        currCol--;
                        if (currCol < 0) {
                            success = false;
                            return success;
                        }
                        currRow = queenArray[currCol] + 1;
                    }
                }
            }
        }
}
