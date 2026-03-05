import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static List<ArrayList<int[]>> graph;
    static int[] water;
    static long[] dist, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        water = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            water[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[] { v, w });
            graph.get(v).add(new int[] { u, w });
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        sum = new long[N + 1];
        dijkstra(S);

        StringBuilder sb = new StringBuilder();
        if (dist[T] == Long.MAX_VALUE) {
            sb.append(-1);
        } else {
            sb.append(dist[T]).append(" ").append(sum[T]);
        }

        System.out.println(sb);

    }

    static void dijkstra(int start) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Long.compare(o2[2], o1[2]); // dist가 같다면 물 양 내림차순
            }
            return Long.compare(o1[1], o2[1]);
        });
        pq.add(new long[] { start, 0, water[start] });
        dist[start] = 0;
        sum[start] = water[start];

        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            int cur = (int) current[0];
            long weight = current[1], wtr = current[2];

            if (dist[cur] < weight || (dist[cur] == weight && sum[cur] > wtr))
                continue;

            for (int[] next : graph.get(cur)) {
                int n = next[0], nw = next[1];

                long cost = dist[cur] + nw;
                long sumWater = wtr + water[n];

                // dist가 더 크거나, 같을 때 sumWater가 더 작다면
                if (dist[n] > cost || (dist[n] == cost && sum[n] < sumWater)) {
                    dist[n] = cost;
                    sum[n] = sumWater;
                    pq.add(new long[] { n, cost, sumWater });
                }

            }

        }

    }

}
