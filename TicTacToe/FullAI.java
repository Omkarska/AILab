public class FullAI {
    char board[][];

    FullAI() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
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

    public boolean checkTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public void drawBoard() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println();
    }

    public int minMax(boolean isMax) {
        if (checkWin('X')) {
            return 1;
        }
        if (checkWin('O')) {
            return -1;
        }
        if (checkTie()) {
            return 0;
        }
        if (isMax) {
            int bestScore = Integer.MIN_VALUE;
            int score;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'X';
                        score = minMax(false);
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
                        score = minMax(true);
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

    public void makeBestMoveX() {
        int bestScore = Integer.MIN_VALUE;
        int BestI = 0, BestJ = 0;
        int Score;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = 'X';
                    Score = minMax(false);
                    board[i][j] = '-';
                    if (Score > bestScore) {
                        bestScore = Score;
                        BestI = i;
                        BestJ = j;
                    }
                }
            }
        }
        board[BestI][BestJ] = 'X';
    }

    public void makeBestMoveO() {
        int bestScore = Integer.MAX_VALUE;
        int BestI = 0, BestJ = 0;
        int Score;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = 'O';
                    Score = minMax(true);
                    board[i][j] = '-';
                    if (Score < bestScore) {
                        bestScore = Score;
                        BestI = i;
                        BestJ = j;
                    }
                }
            }
        }
        board[BestI][BestJ] = 'O';
    }

    public void playGame() {
        int count = 0;
        char curr;
        drawBoard();
        while (true) {
            if (count % 2 == 0) {
                curr = 'X';
                makeBestMoveX();
                drawBoard();
                count++;
            } else {
                curr = 'O';
                makeBestMoveO();
                drawBoard();
                count++;
            }
            if (checkWin(curr)) {
                System.out.println(curr + " won");
                drawBoard();
                break;
            } else if (checkTie()) {
                System.out.println("Tie");
                drawBoard();
                break;
            }
        }
    }

    public static void main(String args[]) {
        FullAI a = new FullAI();
        a.playGame();
    }
}
