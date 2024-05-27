import java.util.*;

class PuzzleState implements Comparable<PuzzleState> {
    int[][] state;
    PuzzleState parent;
    int h;
    int ex, ey;

    PuzzleState(int[][] state, int h, int ex, int ey, PuzzleState p) {
        this.state = state;
        this.h = h;
        this.ex = ex;
        this.ey = ey;
        this.parent = p;
    }

    public int compareTo(PuzzleState p) {
        return Integer.compare(this.h, p.h);
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

    public int deepHashCode() {
        return Arrays.deepHashCode(this.state);
    }

}

public class BestFirstSearch8Puzzle {
    // static int[][] initial = { { 2, 8, 3 }, { 1, 6, 4 }, { 7, 0, 5 } };
    // static int[][] goal = { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } }; working
    static int[][] initial = { { 1, 2, 6 }, { 7, 5, 3 }, { 4, 8, 0 } };
    static int[][] goal = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

    // static int[][] initial = { { 0, 2, 6 }, { 7, 5, 3 }, { 4, 8, 1 } };
    // static int[][] goal = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } }; Not working
    // for some reason
    public static int[][] goalPos = goalPosCal();

    public static int[][] goalPosCal() {
        int pos[][] = new int[9][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pos[(goal[i][j])][0] = i;
                pos[(goal[i][j])][1] = j;
            }
        }
        return pos;
    }

    public static int calHueS(int[][] board) {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                distance += Math.abs(i - (goalPos[(board[i][j])][0])) + Math.abs(j - (goalPos[(board[i][j])][1]));
            }
        }
        return distance;
    }

    public static int[][] copyArray(int[][] array) {
        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = array[i][j];
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        HashSet<Integer> hs = new HashSet<Integer>();
        PuzzleState start = new PuzzleState(copyArray(initial), calHueS(initial), 2, 2, null);
        PriorityQueue<PuzzleState> pq = new PriorityQueue<PuzzleState>();
        pq.add(start);
        hs.add(start.deepHashCode());
        PuzzleState s = null, t = null;
        while (!pq.isEmpty()) {
            s = pq.poll();
            if (s.h == 0) {
                break;
            }
            if (s.ex + 1 < 3) {
                t = new PuzzleState(copyArray(s.state), s.h, s.ex, s.ey, s);
                t.state[s.ex][s.ey] = t.state[s.ex + 1][s.ey];
                t.state[s.ex + 1][s.ey] = 0;
                t.ex = t.ex + 1;
                t.h = calHueS(t.state);
                if (!hs.contains(t.deepHashCode())) {
                    pq.add(t);
                    hs.add(t.deepHashCode());
                }
            }
            if (s.ex - 1 >= 0) {
                t = new PuzzleState(copyArray(s.state), s.h, s.ex, s.ey, s);
                t.state[s.ex][s.ey] = t.state[s.ex - 1][s.ey];
                t.state[s.ex - 1][s.ey] = 0;
                t.ex = t.ex - 1;
                t.h = calHueS(t.state);
                if (!hs.contains(t.deepHashCode())) {
                    pq.add(t);
                    hs.add(t.deepHashCode());
                }
            }
            if (s.ey + 1 < 3) {
                t = new PuzzleState(copyArray(s.state), s.h, s.ex, s.ey, s);
                t.state[s.ex][s.ey] = t.state[s.ex][s.ey + 1];
                t.state[s.ex][s.ey + 1] = 0;
                t.ey = t.ey + 1;
                t.h = calHueS(t.state);
                if (!hs.contains(t.deepHashCode())) {
                    pq.add(t);
                    hs.add(t.deepHashCode());
                }
            }
            if (s.ey - 1 >= 0) {
                t = new PuzzleState(copyArray(s.state), s.h, s.ex, s.ey, s);
                t.state[s.ex][s.ey] = t.state[s.ex][s.ey - 1];
                t.state[s.ex][s.ey - 1] = 0;
                t.ey = t.ey - 1;
                t.h = calHueS(t.state);
                if (!hs.contains(t.deepHashCode())) {
                    pq.add(t);
                    hs.add(t.deepHashCode());
                }
            }
        }
        Stack<PuzzleState> st = new Stack<PuzzleState>();
        t = s;
        while (t != null) {
            st.push(t);
            t = t.parent;
        }
        while (!st.isEmpty()) {
            st.pop().printBoard();
        }
    }
}