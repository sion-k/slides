import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static List<Pair> room[];
    static int[] e;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Node> q = new PriorityQueue<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        room = new List[n + 1];
        e = new int[n + 1];
        visited = new boolean[n + 1][2];
        int u, v, c;
        long res = -1;
        for (int i = 1; i <= n; i++)
            room[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            room[v].add(new Pair(u, c));
            room[u].add(new Pair(v, c));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            e[i] = Integer.parseInt(st.nextToken());

        q.offer(new Node(0, 1, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.n == n && node.k == 1) {
                res = node.w;
                break;
            }
            if (visited[node.n][node.k])
                continue;

            visited[node.n][node.k] = true;
            if (node.k == 0 && e[node.n] > 0) {
                if (!visited[node.n][1])
                    q.offer(new Node(node.w + ((long) e[node.n] * (k - 1)), node.n, 1));
            }
            for (Pair p : room[node.n]) {
                if (!visited[p.first][node.k])
                    q.offer(new Node(node.w + p.second, p.first, node.k));
            }
        }
        System.out.println(res);
    }
}

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Node implements Comparable<Node> {
    long w;
    int n;
    int k;

    public Node(long w, int n, int k) {
        this.w = w;
        this.n = n;
        this.k = k;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.w, o.w);
    }
}