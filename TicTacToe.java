import java.util.Scanner;
import java.util.Random;

public class TicTacToe {
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static char currentPlayer = 'X';
    private static final char HUMAN_PLAYER = 'X';
    private static final char COMPUTER_PLAYER = 'O';

    public static void main(String[] args) {
        initializeBoard();
        while (true) {
            printBoard();
            if (currentPlayer == HUMAN_PLAYER) {
                playerMove();
            } else {
                computerMove();
            }
            if (isWinner()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw!");
                break;
            }
            switchPlayer();
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    private static void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        while (true) {
            System.out.println("Player " + HUMAN_PLAYER + ", enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '-') {
                board[row][col] = HUMAN_PLAYER;
                break;
            } else {
                System.out.println("This move is not valid");
            }
        }
    }

    private static void computerMove() {
        if (makeWinningMove(COMPUTER_PLAYER))
            return;
        if (makeWinningMove(HUMAN_PLAYER))
            return;
        makeRandomMove();
    }

    private static boolean makeWinningMove(char player) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = player;
                    if (isWinner()) {
                        if (player == COMPUTER_PLAYER) {
                            return true;
                        } else {
                            board[i][j] = COMPUTER_PLAYER;
                            return true;
                        }
                    }
                    board[i][j] = '-';
                }
            }
        }
        return false;
    }

    private static void makeRandomMove() {
        Random rand = new Random();
        int row, col;
        while (true) {
            row = rand.nextInt(SIZE);
            col = rand.nextInt(SIZE);
            if (board[row][col] == '-') {
                board[row][col] = COMPUTER_PLAYER;
                break;
            }
        }
        System.out.println("Computer played at position: (" + row + ", " + col + ")");
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == HUMAN_PLAYER) ? COMPUTER_PLAYER : HUMAN_PLAYER;
    }

    private static boolean isWinner() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    private static boolean checkRows() {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumns() {
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        return ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer));
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}
