import java.util.Scanner;

public class AIMinMax {
    char board[][];

    AIMinMax() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void drawBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.print('|');
            System.out.println();
        }
        System.out.println();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(char state) {
        if (board[0][0] == state && board[1][1] == state && board[2][2] == state) {
            return true;
        }
        if (board[0][2] == state && board[1][1] == state && board[2][0] == state) {
            return true;
        }
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == state && board[i][1] == state && board[i][2] == state) {
                return true;
            }
            if (board[0][i] == state && board[1][i] == state && board[2][i] == state) {
                return true;
            }
        }
        return false;
    }

    public int minimax(int depth, boolean isMax) {
        if (checkWin('X')) {
            return 1;
        }
        if (checkWin('O')) {
            return -1;
        }
        if (isBoardFull()) {
            return 0;
        }
        if (isMax) {
            int bestScore = Integer.MIN_VALUE;
            int score;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'X';
                        score = minimax(depth + 1, false);
                        board[i][j] = '-';
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            int score;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'O';
                        score = minimax(depth + 1, true);
                        board[i][j] = '-';
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public void makeBestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestI = 0, bestJ = 0;
        int score;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = 'X';
                    score = minimax(0, false);
                    board[i][j] = '-';
                    if (score > bestScore) {
                        bestScore = score;
                        bestI = i;
                        bestJ = j;
                    }
                }
            }
        }
        board[bestI][bestJ] = 'X';
    }

    public void playGame() {
        int count = 0;
        Scanner s = new Scanner(System.in);
        int i, j;
        char currentState;
        drawBoard();
        while (true) {
            if (count % 2 == 0) {
                System.out.println("X move");
                makeBestMove();
                currentState = 'X';
                drawBoard();
                count++;
            } else {
                System.out.println("O move (i,j)");
                i = s.nextInt();
                j = s.nextInt();
                board[i][j] = 'O';
                currentState = 'O';
                drawBoard();
                count++;
            }

            if (count > 4) {
                if (checkWin(currentState)) {
                    System.out.println(currentState + " Wins");
                    drawBoard();
                    break;
                } else if (isBoardFull()) {
                    System.out.println("Draw");
                    break;
                }
            }
        }
        s.close();
    }

    public static void main(String[] args) {
        AIMinMax game = new AIMinMax();
        game.playGame();
    }
}
