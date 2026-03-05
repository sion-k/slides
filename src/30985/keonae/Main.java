import java.util.*;
import java.io.*;

public class Main {

    static long INF = Long.MAX_VALUE;
    static int N, M, K;
    static List<ArrayList<int[]>> graph;
    static long[] dist1, distN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[] { b, w });
            graph.get(b).add(new int[] { a, w });
        }

        dist1 = new long[N + 1];
        Arrays.fill(dist1, INF);
        dijkstra(1, dist1);

        distN = new long[N + 1];
        Arrays.fill(distN, INF);
        dijkstra(N, distN);

        // 엘베있는 방, 시간
        List<int[]> has = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int take = Integer.parseInt(st.nextToken());
            if (take != -1) {
                has.add(new int[] { i, take });
            }
        }

        long min = INF;
        for (int[] n : has) {
            int x = n[0], w = n[1];

            if (dist1[x] != INF && distN[x] != INF) {
                min = Math.min(min, dist1[x] + distN[x] + (w * (K - 1L)));
            }
        }

        System.out.println(min == INF ? -1 : min);
    }

    static void dijkstra(int start, long[] dist) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
        pq.add(new long[] { start, 0 });
        dist[start] = 0;

        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            int cur = (int) current[0];
            long weight = current[1];

            if (dist[cur] < weight)
                continue;

            for (int[] next : graph.get(cur)) {
                int n = next[0], nw = next[1];

                long cost = dist[cur] + nw;
                if (dist[n] > cost) {
                    dist[n] = cost;
                    pq.add(new long[] { n, cost });
                }

            }

        }
    }

}