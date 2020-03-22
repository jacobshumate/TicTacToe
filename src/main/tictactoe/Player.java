package tictactoe;

import java.util.Scanner;

public class Player {

    /*This could would be extended by a remote user or ai class.
      This could also use  the existing board passed in through methods for ai implementation. */

    private char symbol;
    private int currentRow;
    private int currentCol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    // Get player input from console. If not valid then recurse for input again.
    public void getInput(char[][] board) {
        int input = -1;
        Scanner sc = new Scanner(System.in);
        while(input < 0 || input > (board.length * board[0].length)) {
            System.out.println("Player " + getSymbol() + ", where to? ");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!");
                sc.next(); // this is important!
            }
            input = sc.nextInt();
        }
        System.out.println();
        if (!validateInput(board, input)) getInput(board);
    }

    // Checks if choice of board index already contains an X or O,
    // otherwise set the row and col for that player for reference.
    private boolean validateInput(char[][] board, int input) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if (--input == 0) {
                    if (board[i][j] == 0) {
                        setCurrentRow(i);
                        setCurrentCol(j);
                        return true;
                    } else {
                        System.out.println("This choice has already been taken.");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentRow(int row) {
        currentRow = row;
    }

    public void setCurrentCol(int col) {
        currentCol = col;
    }
}
