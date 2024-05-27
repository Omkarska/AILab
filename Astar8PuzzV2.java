import java.util.*;

public class Astar8PuzzV2 {
    // public static int[][] start = { { 2, 8, 3 }, { 1, 6, 4 }, { 7, 0, 5 } };
    // public static int[][] goal = { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
    // static int[][] start = { { 0, 2, 6 }, { 7, 5, 3 }, { 4, 8, 1 } };
    // static int[][] goal = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } }; not valid
    // input
    public static int[][] start = { { 4, 2, 6 }, { 1, 3, 5 }, { 8, 7, 0 } };
    public static int[][] goal = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
    public static int goalPos[][] = Goalpoints();

    public static void main(String[] args) {
        HashSet<String> h = new HashSet<String>();
        PuzzleNode initial = new PuzzleNode(copyArray(start), 0, calculateHuristic(start), 2, 2, null);
        PriorityQueue<PuzzleNode> pq = new PriorityQueue<PuzzleNode>();
        pq.add(initial);
        h.add(initial.ArrayToStr());
        PuzzleNode temp1 = null, temp2 = null;
        while (!pq.isEmpty()) {
            temp1 = pq.poll();
            if (temp1.h == 0) {
                break;
            }
            if (temp1.ex + 1 < 3) {
                temp2 = new PuzzleNode(copyArray(temp1.state), temp1.g + 1, temp1.h, temp1.ex, temp1.ey, temp1);
                temp2.state[temp1.ex][temp1.ey] = temp2.state[temp1.ex + 1][temp1.ey];
                temp2.state[temp1.ex + 1][temp1.ey] = 0;
                temp2.ex = temp2.ex + 1;
                temp2.h = calculateHuristic(temp2.state);
                temp2.f = temp2.h + temp2.g;
                if (!h.contains(temp2.ArrayToStr())) {
                    pq.add(temp2);
                    h.add(temp2.ArrayToStr());
                }
            }
            if (temp1.ex - 1 >= 0) {
                temp2 = new PuzzleNode(copyArray(temp1.state), temp1.g + 1, temp1.h, temp1.ex, temp1.ey, temp1);
                temp2.state[temp1.ex][temp1.ey] = temp2.state[temp1.ex - 1][temp1.ey];
                temp2.state[temp1.ex - 1][temp1.ey] = 0;
                temp2.ex = temp2.ex - 1;
                temp2.h = calculateHuristic(temp2.state);
                temp2.f = temp2.h + temp2.g;
                if (!h.contains(temp2.ArrayToStr())) {
                    pq.add(temp2);
                    h.add(temp2.ArrayToStr());
                }
            }
            if (temp1.ey + 1 < 3) {
                temp2 = new PuzzleNode(copyArray(temp1.state), temp1.g + 1, temp1.h, temp1.ex, temp1.ey, temp1);
                temp2.state[temp1.ex][temp1.ey] = temp2.state[temp1.ex][temp1.ey + 1];
                temp2.state[temp1.ex][temp1.ey + 1] = 0;
                temp2.ey = temp2.ey + 1;
                temp2.h = calculateHuristic(temp2.state);
                temp2.f = temp2.h + temp2.g;
                if (!h.contains(temp2.ArrayToStr())) {
                    pq.add(temp2);
                    h.add(temp2.ArrayToStr());
                }
            }
            if (temp1.ey - 1 >= 0) {
                temp2 = new PuzzleNode(copyArray(temp1.state), temp1.g + 1, temp1.h, temp1.ex, temp1.ey, temp1);
                temp2.state[temp1.ex][temp1.ey] = temp2.state[temp1.ex][temp1.ey - 1];
                temp2.state[temp1.ex][temp1.ey - 1] = 0;
                temp2.ey = temp2.ey - 1;
                temp2.h = calculateHuristic(temp2.state);
                temp2.f = temp2.h + temp2.g;
                if (!h.contains(temp2.ArrayToStr())) {
                    pq.add(temp2);
                    h.add(temp2.ArrayToStr());
                }
            }
        }
        Stack<PuzzleNode> st = new Stack<PuzzleNode>();
        while (temp1 != null) {
            st.push(temp1);
            temp1 = temp1.parent;
        }
        while (!st.isEmpty()) {
            st.pop().printBoard();
        }
    }

    public static int calculateHuristic(int board[][]) {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                distance += Math.abs(i - (goalPos[(board[i][j])][0])) + Math.abs(j - (goalPos[(board[i][j])][1]));
            }
        }
        return distance;
    }

    public static int[][] Goalpoints() {
        int arr[][] = new int[9][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[(goal[i][j])][0] = i;
                arr[(goal[i][j])][1] = j;
            }
        }
        return arr;
    }

    public static int[][] copyArray(int array[][]) {
        int newArr[][] = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                newArr[i][j] = array[i][j];
            }
        }
        return newArr;
    }
}

class PuzzleNode implements Comparable<PuzzleNode> {
    int ex, ey;
    int[][] state;
    PuzzleNode parent;
    int h, g, f;

    PuzzleNode(int[][] state, int g, int h, int ex, int ey, PuzzleNode parent) {
        this.ex = ex;
        this.ey = ey;
        this.state = state;
        this.parent = parent;
        this.g = g;
        this.h = h;
        this.f = this.g + this.h;
    }

    public int compareTo(PuzzleNode p) {
        return Integer.compare(this.f, p.f);
    }

    public String ArrayToStr() {
        String str = new String();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                str += this.state[i][j];
            }
        }
        return str;
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("|" + ((this.state[i][j] == 0) ? " " : this.state[i][j]));
            }
            System.out.println("|");
        }
        System.out.println();
    }
}
