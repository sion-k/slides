import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1939 {
    static int N, M, res, START, END;
    static int[] weight;
    static ArrayList<ArrayList<Edge>> adjList;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점
        M = Integer.parseInt(st.nextToken()); // 간선
        res = 0;

        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new Edge(b, w));
            adjList.get(b).add(new Edge(a, w));
        }

        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());

        weight = new int[N + 1];
        Arrays.fill(weight, -1);
        solve();

        System.out.println(weight[END]);
    }

    private static void solve() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(START, Integer.MAX_VALUE));
        weight[START] = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int c_v = cur.vertex;
            int c_w = cur.value;

            if (c_w < weight[c_v])
                continue;

            for (Edge e : adjList.get(c_v)) {
                int next = e.vertex;
                int value = e.value;

                int temp = Math.min(weight[c_v], value);

                if (temp > weight[next]) {
                    weight[next] = temp;
                    pq.add(new Edge(next, weight[next]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int vertex, value;

        Edge(int vertex, int value) {
            this.vertex = vertex;
            this.value = value;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(e.value, this.value);
        }
    }
}