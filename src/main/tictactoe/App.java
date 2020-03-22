package tictactoe;

public class App {

    private Board board;

    private App(int n) {
        board = new Board(n);
    }

    private void init () {
        System.out.println("Welcome to Tic-Tac-Toe.");
        board.getNumOfGames();
        board.initGames();
        board.displayWinners();
    }

    public static void main(String[] args) {
        App ticTacToe = new App(3);
        ticTacToe.init();
    }
}
