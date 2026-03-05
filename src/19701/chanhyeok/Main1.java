import java.util.*;
import java.io.*;

public class Main1 {
    static int n, m;
    static long res[][];
    static boolean visited[][];
    static List<Edge> e[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        int u, v, w, k;
        res = new long[n + 1][2];
        visited = new boolean[n + 1][2];
        m = Integer.parseInt(st.nextToken());
        e = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            e[i] = new ArrayList<>();
            res[i][0] = Long.MAX_VALUE;
            res[i][1] = Long.MAX_VALUE;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            e[u].add(new Edge(v, w, k));
            e[v].add(new Edge(u, w, k));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0, 0, 0));
        res[1][0] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.v][node.v2])
                continue;
            visited[node.v][node.v2] = true;
            for (Edge edge : e[node.v]) {
                if (res[edge.v][node.v2] > node.w + edge.w - node.k) {
                    res[edge.v][node.v2] = node.w + edge.w - node.k;
                    pq.offer(new Node(edge.v, node.v2, node.w + edge.w, node.k));
                }
                if (node.v2 == 0) {
                    if (res[edge.v][1] > node.w + edge.w - edge.k) {
                        res[edge.v][1] = node.w + edge.w - edge.k;
                        pq.offer(new Node(edge.v, 1, node.w + edge.w, edge.k));
                    }
                }
            }
        }
        for (int i = 2; i <= n; i++)
            sb.append(res[i][1]).append("\n");
        System.out.println(sb);
    }
}

class Edge {
    int v;
    long w;
    int k;

    public Edge(int v, long w, int k) {
        this.v = v;
        this.w = w;
        this.k = k;
    }
}

class Node implements Comparable<Node> {
    int v;
    int v2;
    long w;
    int k;

    public Node(int v, int v2, long w, int k) {
        this.v = v;
        this.v2 = v2;
        this.w = w;
        this.k = k;
    }

    @Override
    public int compareTo(Node o) {
        int ret = Integer.compare(this.v2, o.v2);
        return (ret != 0) ? ret : Long.compare(this.w - this.k, o.w - o.k);
    }
}