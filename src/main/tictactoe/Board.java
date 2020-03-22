package tictactoe;

import java.util.Scanner;

public class Board {

        private char [][] board;
        private int size;

        private Player player1, player2;
        private char winner;
        private char[] numOfWins;
        private int turns, numOfGames;

        // Construct board size and turns.
        public Board(int n) {
            size = n;
            numOfGames = 0;
        }

        // Initialize board.
        private void initBoard() {
            board = new char[size][size];
            turns = size * size;
            getPlayers();
            printCurrentBoard();
        }

        // Ask for user input to determine X or O.
        private void getPlayers() {
            Scanner sc = new Scanner(System.in);
            char input = 0;
            while(input != 'X' && input != 'O') {
                System.out.println("Will X or O play first?");
                input = sc.next().charAt(0);
            }
            player1 = new Player(input);
            char input2 = input == 'X' ? 'O' : 'X';
            player2 = new Player(input2);
        }

        // Ask for user input of how many games to play.
        public void getNumOfGames() {
            if (numOfGames == 0) {
                Scanner sc = new Scanner(System.in);
                int num = 0;
                while (num <= 0) {
                    System.out.println("How many games would you like to play?");
                    while (!sc.hasNextInt()) {
                        System.out.println("That's not a number!");
                        sc.next(); // this is important!
                    }
                    num = sc.nextInt();
                }
                numOfGames = num;
                numOfWins = new char[num];
            }
        }

        // Initialize games.
        public void initGames() {
            initBoard();
            while(turns > 0) {
                if(turns == 0 || move(player1)) break;
                if(turns == 0 || move(player2)) break;
            }
            numOfWins[numOfWins.length - numOfGames] = winner;
            displayWinner();
            if (--numOfGames > 0) {
                initGames();
            }
        }

        // Display winner or draw of each game.
        private void displayWinner() {
            if (winner != 0) System.out.println("Player " + winner + " wins!");
            else System.out.println("It was a draw!");
            System.out.println();
        }

        // Display winner if they have won all games.
        public void displayWinners() {
            int winnersInRow = 0;
            for (int i = 0; i < numOfWins.length; i++) {
                if (numOfWins[i] == winner) winnersInRow++;
            }
            if (winner != 0 && winnersInRow == numOfWins.length) {
                System.out.println("Player " + winner + " has won " + winnersInRow + " times in a row!");
            }
        }

        // Takes player input, validates and sets X or O depending on who's turn it is.
        private boolean move(Player player) {
            player.getInput(board);
            int row = player.getCurrentRow(), col = player.getCurrentCol();
            char symbol = player.getSymbol();

            setMove(symbol, row, col);
            --turns;
            printCurrentBoard();

            if (checkDiagonally(symbol, row, col) || checkVertical(symbol, col) || checkHorizontal(symbol, row)) {
                winner = symbol;
                return true;
            }

            return false;
        }

        // Checks specified column to see if there is a winner.
        private boolean checkVertical(char player, int col) {
            for(int i = 0; i < board.length; i++) {
                if(board[i][col] != player) return false;
            }
            return true;
        }

        // Checks specified row to see if there is a winner.
        private boolean checkHorizontal(char player, int row) {
            for(int i = 0; i < board[0].length; i++) {
                if(board[row][i] != player) return false;
            }
            return true;
        }

        // Checks both cross sections to see if there is winner.
        private boolean checkDiagonally(char player, int row, int col) {
            if (row != col && row + col != board.length) return false;
            boolean upperLeftToBottomRight = true;
            boolean upperRightToBottomLeft = true;
            for (int i = 0; i < board.length; i++) {
                if (board[i][i] != player) upperLeftToBottomRight = false;
            }
            for (int i = 0; i < board.length; i++) {
                if (board[i][board.length - 1 - i] != player) upperRightToBottomLeft = false;
            }
            return upperRightToBottomLeft || upperLeftToBottomRight;
        }

        // Print current board.
        private void printCurrentBoard() {
            System.out.println("Board:");
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    System.out.print("|" + board[i][j]);
                }
                System.out.print("|");
                System.out.println();
            }
            System.out.println();
            printBoardMovementOptions();
        }

        // Print board indexes for player to choose.
        private void printBoardMovementOptions() {
            System.out.println("Movement Options:");
            int count = 0;
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    System.out.print("|" + ++count);
                }
                System.out.print("|");
                System.out.println();
            }
            System.out.println();
        }

        // Sets X or O in the board by row and col position.
        private void setMove(char symbol, int row, int col) {
            board[row][col] = symbol;
        }
}
