import java.util.*;

class graphNode implements Comparable<graphNode> {
    int edgeName;
    int dist;

    graphNode(int edgeName, int dist) {
        this.edgeName = edgeName;
        this.dist = dist;
    }

    public int compareTo(graphNode g) {
        if (this.dist > g.dist) {
            return 1;
        } else if (this.dist < g.dist) {
            return -1;
        } else {
            return 0;
        }
    }
}

public class Dijkstra {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        LinkedList[] graph = new LinkedList[5];
        for (int i = 0; i < 5; i++)
            graph[i] = new LinkedList<graphNode>();
        graph[0].add(new graphNode(1, 4));
        graph[0].add(new graphNode(2, 1));
        graph[1].add(new graphNode(3, 1));
        graph[2].add(new graphNode(1, 2));
        graph[2].add(new graphNode(3, 5));
        graph[3].add(new graphNode(4, 3));
        boolean visited[] = new boolean[5];
        Arrays.fill(visited, false);
        int distance[] = new int[5];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<graphNode> pq = new PriorityQueue<graphNode>();
        pq.add(new graphNode(0, 0));
        graphNode[] parent = new graphNode[5];
        parent[0] = new graphNode(0, 0);
        graphNode p;
        LinkedList<graphNode> g;
        while (!pq.isEmpty()) {
            p = pq.poll();
            if (distance[p.edgeName] > p.dist) {
                distance[p.edgeName] = p.dist;
            }
            visited[p.edgeName] = true;
            g = graph[p.edgeName];
            for (graphNode k : g) {
                if (visited[k.edgeName] != true || distance[k.edgeName] > k.dist + p.dist) {
                    parent[k.edgeName] = p;
                    pq.add(new graphNode(k.edgeName, k.dist + p.dist));
                }
            }
        }
        for (int i : distance) {
            System.out.print(i + " ");
        }
    }

}
