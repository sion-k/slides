import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_30985 {
    static int N, M, K;
    static long[][] dist;
    static ArrayList<ArrayList<Edge>> adjList;
    static long INF = Long.MAX_dist / 2;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점
        M = Integer.parseInt(st.nextToken()); // 간선
        K = Integer.parseInt(st.nextToken()); // 층수

        dist = new long[2][N + 1];
        for (long[] row : dist) {
            Arrays.fill(row, INF);
        }

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            adjList.get(u).add(new Edge(v, c));
            adjList.get(v).add(new Edge(u, c));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            long c = Integer.parseInt(st.nextToken());
            if (c != -1) {
                adjList.get(i).add(new Edge(i, c * (K - 1)));
            }
        }

        solve();

        if (dist[1][N] == INF)
            System.out.println(-1);
        else
            System.out.println(dist[1][N]);
    }

    private static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.flag][cur.vertex] < cur.dist)
                continue;

            for (Edge next : adjList.get(cur.vertex)) {
                // 같은 층 이동
                if (next.to != cur.vertex) {
                    long nextDist = cur.dist + next.weight;
                    if (dist[cur.flag][next.to] > nextDist) {
                        dist[cur.flag][next.to] = nextDist;
                        pq.add(new Node(next.to, nextDist, cur.flag));
                    }
                }
                // 엘리베이터 탑승
                else if (cur.flag == 0) {
                    long nextDist = cur.dist + next.weight;
                    if (dist[1][next.to] > nextDist) {
                        dist[1][next.to] = nextDist;
                        pq.add(new Node(next.to, nextDist, 1));
                    }
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int vertex, flag;
    long dist;

    public Node(int vertex, long dist, int flag) {
        this.vertex = vertex;
        this.dist = dist;
        this.flag = flag;
    }

    @Override
    public int compareTo(Node e) {
        return Long.compare(this.dist, e.dist);
    }
}

class Edge {
    int to;
    long weight;

    public Edge(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }
}