import java.util.Queue;
import java.util.LinkedList;

public class BFS {
    public static void main(String[] args) {
        LinkedList<Integer> graph[] = new LinkedList[9];
        for (int i = 0; i < 9; i++) {
            graph[i] = new LinkedList<Integer>();
        }
        graph[0].add(1);
        graph[1].add(0);
        graph[1].add(2);
        graph[1].add(3);
        graph[1].add(4);
        graph[2].add(1);
        graph[3].add(1);
        graph[3].add(8);
        graph[4].add(1);
        graph[4].add(5);
        graph[5].add(4);
        graph[5].add(6);
        graph[6].add(5);
        graph[6].add(7);
        graph[7].add(6);
        graph[7].add(8);
        graph[8].add(7);
        graph[8].add(3);
        Queue<Integer> q = new LinkedList<Integer>();
        int visited[] = new int[9];
        int k = 0;
        q.add(0);
        visited[0] = 1;
        System.out.print(0 + " ");
        while (!q.isEmpty()) {
            k = q.remove();
            for (int i : graph[k]) {
                if (visited[i] == 0) {
                    q.add(i);
                    System.out.print(i + " ");
                    visited[i] = 1;
                }
            }
        }
    }
}
