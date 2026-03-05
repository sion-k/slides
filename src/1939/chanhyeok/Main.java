import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int p[];

    static int find(int v) {
        if (p[v] == 0)
            return v;
        p[v] = find(p[v]);
        return p[v];
    }

    static void merge(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb)
            return;
        p[pb] = pa;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Node> q = new PriorityQueue<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = new int[n + 1];
        int a, b, c, res = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            q.offer(new Node(c, a, b));
        }
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        while (find(a) != find(b)) {
            Node node = q.poll();
            res = node.w;
            merge(node.a, node.b);
        }
        System.out.println(res);
    }
}

class Node implements Comparable<Node> {
    int w;
    int a;
    int b;

    public Node(int w, int a, int b) {
        this.w = w;
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(o.w, this.w);
    }
}
