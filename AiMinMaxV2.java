import java.util.Scanner;

public class AiMinMaxV2 {
    char b[][];

    AiMinMaxV2() {
        b = new char[3][3];
        for (int i = 0; i < this.b.length; i++) {
            for (int j = 0; j < this.b.length; j++) {
                this.b[i][j] = '-';
            }
        }
        this.playGame();
    }

    public void drawBoard() {
        for (int i = 0; i < this.b.length; i++) {
            for (int j = 0; j < this.b.length; j++) {
                System.out.print("|" + this.b[i][j]);
            }
            System.out.println('|');
        }
    }

    public boolean checkWin(char c) {
        if (this.b[0][0] == c && this.b[1][1] == c && this.b[2][2] == c)
            return true;
        if (this.b[0][2] == c && this.b[1][1] == c && this.b[2][0] == c)
            return true;
        for (int i = 0; i < this.b.length; i++) {
            if (this.b[i][0] == c && this.b[i][1] == c && this.b[i][2] == c)
                return true;
            if (this.b[0][i] == c && this.b[1][i] == c && this.b[2][i] == c)
                return true;
        }
        return false;
    }

    public boolean checkDraw() {
        for (int i = 0; i < this.b.length; i++) {
            for (int j = 0; j < this.b.length; j++) {
                if (this.b[i][j] == '-')
                    return false;
            }
        }
        return true;
    }

    public int minmax(boolean isMax) {
        if (this.checkWin('X'))
            return -1;
        if (this.checkWin('O'))
            return 1;
        if (checkDraw())
            return 0;
        if (isMax) {
            int bestScore = Integer.MIN_VALUE, score = 0;
            for (int i = 0; i < this.b.length; i++) {
                for (int j = 0; j < this.b.length; j++) {
                    if (this.b[i][j] == '-') {
                        this.b[i][j] = 'O';
                        score = this.minmax(false);
                        this.b[i][j] = '-';
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE, score = 0;
            for (int i = 0; i < this.b.length; i++) {
                for (int j = 0; j < this.b.length; j++) {
                    if (this.b[i][j] == '-') {
                        this.b[i][j] = 'X';
                        score = this.minmax(true);
                        this.b[i][j] = '-';
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public void playBestMoveO() {
        int bestI = 0, bestJ = 0;
        int bestScore = Integer.MIN_VALUE, score = 0;
        for (int i = 0; i < this.b.length; i++) {
            for (int j = 0; j < this.b.length; j++) {
                if (this.b[i][j] == '-') {
                    this.b[i][j] = 'O';
                    score = this.minmax(false);
                    this.b[i][j] = '-';
                    if (score > bestScore) {
                        bestScore = score;
                        bestI = i;
                        bestJ = j;
                    }
                }
            }
        }
        this.b[bestI][bestJ] = 'O';
    }

    public void playBestMoveX() {
        int bestI = 0, bestJ = 0;
        int bestScore = Integer.MAX_VALUE, score = 0;
        for (int i = 0; i < this.b.length; i++) {
            for (int j = 0; j < this.b.length; j++) {
                if (this.b[i][j] == '-') {
                    this.b[i][j] = 'X';
                    score = this.minmax(true);
                    this.b[i][j] = '-';
                    if (score < bestScore) {
                        bestScore = score;
                        bestI = i;
                        bestJ = j;
                    }
                }
            }
        }
        this.b[bestI][bestJ] = 'X';
    }

    public void playGame() {
        Scanner s = new Scanner(System.in);
        int i, j;
        int count = 0;
        char curr;
        drawBoard();
        while (true) {
            if (count % 2 == 0) {
                curr = 'X';
                System.out.println("X chance(Enter cordinates)");
                playBestMoveX();
                // i = s.nextInt();
                // j = s.nextInt();
                // this.b[i][j] = curr;
                drawBoard();
                count++;
            } else {
                curr = 'O';
                System.out.println("O chance");
                this.playBestMoveO();
                drawBoard();
                count++;
            }
            if (this.checkWin(curr)) {
                System.out.println(curr + " Win");
                this.drawBoard();
                break;
            }
            if (this.checkDraw()) {
                System.out.println("Draw");
                this.drawBoard();
                break;
            }
        }
    }

    public static void main(String args[]) {
        AiMinMaxV2 a = new AiMinMaxV2();
    }
}