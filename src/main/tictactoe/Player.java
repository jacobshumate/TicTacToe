package tictactoe;

public class Player {

    /*This could would be extended by a remote user or ai class.
      This could also contain a copy of the existing board for ai implementation. */

    private char symbol;
    private int currentRow;
    private int currentCol;

    public Player(char symbol) {
        this.symbol = symbol;
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
