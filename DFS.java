import java.util.LinkedList;
import java.util.Stack;

public class DFS {

    public static void dfs(int k, int visited[], LinkedList<Integer> graph[]) {
        if (visited[k] == 0) {
            System.out.print(k + " ");
            visited[k] = 1;
        } else {
            return;
        }
        for (int i : graph[k]) {
            if (visited[i] == 0) {
                dfs(i, visited, graph);
            }
        }
    }

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
        int visited[] = new int[9];
        Stack<Integer> s = new Stack<Integer>();
        int k = 0;
        s.push(0);
        while (!s.empty()) {
            k = s.pop();
            if (visited[k] == 0) {
                visited[k] = 1;
                System.out.print(k + " ");
            } else {
                continue;
            }
            for (int i : graph[k]) {
                if (visited[i] == 0) {
                    s.push(i);
                }
            }
        }
        java.util.Arrays.fill(visited, 0);
        System.out.println();
        dfs(0, visited, graph);
    }
}
