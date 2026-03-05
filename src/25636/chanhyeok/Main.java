import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int a[];
    static boolean visited[];
    static List<Pair> e[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int u, v, w;
        a = new int[n + 1];
        visited = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        e = new List[n + 1];
        for (int i = 1; i <= n; i++)
            e[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            e[u].add(new Pair(v, w));
            e[v].add(new Pair(u, w));
        }
        st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(u, 0, a[u]));
        long level = 0, res = -1;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.v == v) {
                level = node.w;
                res = node.a;
                break;
            }
            if (visited[node.v])
                continue;
            visited[node.v] = true;
            for (Pair p : e[node.v]) {
                if (!visited[p.v])
                    pq.offer(new Node(p.v, p.w + node.w, a[p.v] + node.a));
            }
        }
        if (res > 0)
            System.out.println(level + " " + res);
        else
            System.out.println(-1);
    }
}

class Pair {
    int v;
    long w;

    public Pair(int v, long w) {
        this.v = v;
        this.w = w;
    }
}

class Node implements Comparable<Node> {
    int v;
    long w;
    long a;

    public Node(int v, long w, long a) {
        this.v = v;
        this.w = w;
        this.a = a;
    }

    @Override
    public int compareTo(Node o) {
        int ret = Long.compare(this.w, o.w);
        return (ret != 0) ? ret : Long.compare(o.a, this.a);
    }
}