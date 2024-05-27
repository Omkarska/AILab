import java.util.*;

public class AStarEightPuzzle {

    public static int goalState[][] = { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };

    public static void main(String[] args) {
        int initialState[][] = { { 2, 8, 3 }, { 1, 6, 4 }, { 7, 0, 5 } };
        PuzzleState p = new PuzzleState(copyArray(initialState), 0, calH(initialState), 2, 1, null);
        PriorityQueue<PuzzleState> pq = new PriorityQueue<PuzzleState>();
        pq.add(p);
        PuzzleState t, s = null;
        while (!pq.isEmpty()) {
            s = pq.poll();
            if (s.h == 0) {
                // s.printBoardState();
                break;
            }
            if (s.eX + 1 < 3) {
                t = new PuzzleState(copyArray(s.state), s.g, s.h, s.eX, s.eY, s);
                t.state[t.eX][t.eY] = t.state[t.eX + 1][t.eY];
                t.state[t.eX + 1][t.eY] = 0;
                t.eX = t.eX + 1;
                t.g = t.g + 1;
                t.h = calH(t.state);
                t.f = t.h + t.g;
                if (t.f <= s.f) {
                    pq.add(t);
                }
            }
            if (s.eX - 1 >= 0) {
                t = new PuzzleState(copyArray(s.state), s.g, s.h, s.eX, s.eY, s);
                t.state[t.eX][t.eY] = t.state[t.eX - 1][t.eY];
                t.state[t.eX - 1][t.eY] = 0;
                t.eX = t.eX - 1;
                t.g = t.g - 1;
                t.h = calH(t.state);
                t.f = t.h + t.g;
                if (t.f <= s.f) {
                    pq.add(t);
                }
            }
            if (s.eY + 1 < 3) {
                t = new PuzzleState(copyArray(s.state), s.g, s.h, s.eX, s.eY, s);
                t.state[t.eX][t.eY] = t.state[t.eX][t.eY + 1];
                t.state[t.eX][t.eY + 1] = 0;
                t.eY = t.eY + 1;
                t.g = t.g + 1;
                t.h = calH(t.state);
                t.f = t.h + t.g;
                if (t.f <= s.f) {
                    pq.add(t);
                }
            }
            if (s.eY - 1 >= 0) {
                t = new PuzzleState(copyArray(s.state), s.g, s.h, s.eX, s.eY, s);
                t.state[t.eX][t.eY] = t.state[t.eX][t.eY - 1];
                t.state[t.eX][t.eY - 1] = 0;
                t.eY = t.eY - 1;
                t.g = t.g + 1;
                t.h = calH(t.state);
                t.f = t.h + t.g;
                if (t.f <= s.f) {
                    pq.add(t);
                }
            }

        }
        PuzzleState j = s;
        Stack<PuzzleState> st = new Stack<PuzzleState>();
        while (j != null) {
            st.push(j);
            j = j.parent;
        }
        while (!st.empty()) {
            st.pop().printBoardState();
        }
    }

    static public int calH(int[][] a) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] != goalState[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int[][] copyArray(int array[][]) {
        int cpy[][] = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cpy[i][j] = array[i][j];
            }
        }
        return cpy;
    }
}

class PuzzleState implements Comparable<PuzzleState> {
    int[][] state;
    int g;
    int h;
    int f;
    int eX, eY;
    PuzzleState parent;

    PuzzleState(int[][] state, int g, int h, int eX, int eY, PuzzleState parent) {
        this.state = state;
        this.g = g;
        this.h = h;
        this.eX = eX;
        this.eY = eY;
        this.f = this.h + this.g;
        this.parent = parent;
    }

    public int compareTo(PuzzleState p) {
        return Integer.compare(this.f, p.f);
    }

    public void printBoardState() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("|" + ((this.state[i][j] == 0) ? " " : this.state[i][j]));
            }
            System.out.println("|");
        }
        System.out.println();
    }
}
