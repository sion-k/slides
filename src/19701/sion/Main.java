import java.util.*;
import java.io.*;

public class Main {
    static final long inf = (long) 1e18;
    static final long base = 1_000_000_000L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<int[]>> g = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            g.get(u).add(new int[] { v, t, k });
            g.get(v).add(new int[] { u, t, k });
        }

        long[][] d = new long[n + 1][2];
        for (int i = 1; i <= n; i++) {
            d[i][0] = inf;
            d[i][1] = inf;
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>(
                (a, b) -> Long.compare(a[0], b[0]));

        d[1][0] = 0;
        pq.offer(new long[] { 0, 1, 0 });

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();

            long dist = cur[0];
            int u = (int) cur[1];
            int b = (int) cur[2];

            if (d[u][b] < dist) {
                continue;
            }

            for (int[] e : g.get(u)) {
                int v = e[0], t = e[1], k = e[2];

                if (d[v][b] > dist + t) {
                    d[v][b] = dist + t;
                    pq.offer(new long[] { dist + t, v, b });
                }

                if (b == 0) {
                    long nd = dist + t - k + base;
                    if (d[v][1] > nd) {
                        d[v][1] = nd;
                        pq.offer(new long[] { nd, v, 1 });
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(d[i][1] - base).append('\n');
        }
        System.out.print(sb);
    }
}