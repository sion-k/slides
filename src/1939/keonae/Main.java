import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static List<ArrayList<int[]>> graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        dijkstra(start);

        System.out.println(dist[end]);
    }

    static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        pq.offer(new int[] { start, Integer.MAX_VALUE });

        dist[start] = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int cur = current[0], weight = current[1];

            if (dist[cur] > weight)
                continue;

            for (int[] next : graph.get(cur)) {
                int n = next[0], nw = next[1];

                int limit = Math.min(nw, dist[cur]);
                if (dist[n] < limit) {
                    dist[n] = limit;
                    pq.offer(new int[] { n, limit });
                }

            }

        }

    }

}