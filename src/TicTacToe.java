import java.util.Scanner;2
public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String[][] board = new String[ROWS][COLS];
    private static String currentPlayer = "X";
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            // Clear board and start with X
            clearBoard();
            currentPlayer = "X";
            boolean gameOver = false;
            while (!gameOver) {
                display();
                System.out.println("Player " + currentPlayer + "'s turn.");
                // Get row and column from player
                int row = SafeInput.getRangedInt(in,
                        "Enter row (1-3)", 1, 3) - 1;
                int col = SafeInput.getRangedInt(in,
                        "Enter column (1-3)", 1, 3) - 1;
                // Keep asking until the player enters an empty location
                while (!isValidMove(row, col)) {
                    System.out.println("That space is already taken. Try again.");
                    row = SafeInput.getRangedInt(in,
                            "Enter row (1-3)", 1, 3) - 1;
                    col = SafeInput.getRangedInt(in,
                            "Enter column (1-3)", 1, 3) - 1;
                }
                // Record the move
                board[row][col] = currentPlayer;
                // Check for win
                if (isWin(currentPlayer)) {
                    display();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameOver = true;
                }
                // Check for tie
                else if (isTie()) {
                    display();
                    System.out.println("The game is a tie!");
                    gameOver = true;
                }
                // Switch players
                else {
                    if (currentPlayer.equals("X")) {
                        currentPlayer = "O";
                    } else {
                        currentPlayer = "X";
                    }
                }
            }
            // Ask players if they want another game
            playAgain = SafeInput.getYNConfirm(in,
                    "Would you like to play again?");
        }
        System.out.println("Thanks for playing!");
    }
    // Clears all spaces on the board
    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }
    // Displays the Tic Tac Toe board
    private static void display() {
        System.out.println();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                System.out.print(board[row][col]);
                if (col < COLS - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println("-----");
            }
        }
        System.out.println();
    }
    // Checks whether the selected location is empty
    private static boolean isValidMove(int row, int col) {
        if (board[row][col].equals(" ")) {
            return true;
        }
        return false;
    }
    // Checks all possible types of wins
    private static boolean isWin(String player) {
        if (isColWin(player) ||
                isRowWin(player) ||
                isDiagnalWin(player)) {
            return true;
        }
        return false;
    }
    // Checks for a column win
    private static boolean isColWin(String player) {
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player)) {
                return true;
            }
        }
        return false;
    }
    // Checks for a row win
    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player)) {
                return true;
            }
        }
        return false;
    }
    // Checks for a diagonal win
    private static boolean isDiagnalWin(String player) {
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player)) {
            return true;
        }
        return false;
    }
    // Checks whether all board spaces are filled
    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}