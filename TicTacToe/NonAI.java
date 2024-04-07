import java.util.Scanner;

public class NonAI {
    char board[][];

    NonAI() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void DrawBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.print('|');
            System.out.println();
        }
        System.out.println();
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
            if (board[0][i] == state && board[1][i] == state && board[2][0] == state) {
                return true;
            }
        }
        return false;
    }

    public void playGame() {
        int count = 0;
        Scanner s = new Scanner(System.in);
        int i, j;
        char currantState;
        DrawBoard();
        while (true) {
            if (count % 2 == 0) {
                System.out.println("X move (i,j)");
                i = s.nextInt();
                j = s.nextInt();
                board[i][j] = 'X';
                currantState = 'X';
                DrawBoard();
                count++;
            } else {
                System.out.println("O move (i,j)");
                i = s.nextInt();
                j = s.nextInt();
                board[i][j] = 'O';
                currantState = 'O';
                DrawBoard();
                count++;
            }
            if (count > 8) {
                System.out.println("Draw");
                break;
            }
            if (count > 3) {
                if (checkWin(currantState)) {
                    System.out.println(currantState + " Wins");
                    DrawBoard();
                    break;
                }
            }
        }
        s.close();
    }

    public static void main(String[] args) {
        NonAI n = new NonAI();
        n.playGame();
    }
}