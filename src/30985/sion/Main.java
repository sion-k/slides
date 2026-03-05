import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static ArrayList<ArrayList<int[]>> g = new ArrayList<>();

    static long[] dijkstra(int s) {
        PriorityQueue<long[]> q = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        long[] d = new long[n + 1];
        Arrays.fill(d, (long) 1e18);

        q.add(new long[] { 0, s });
        d[s] = 0;

        while (!q.isEmpty()) {
            long[] t = q.poll();
            long c = t[0];
            int u = (int) t[1];

            if (d[u] != c) {
                continue;
            }

            for (int[] edge : g.get(u)) {
                int v = edge[0], w = edge[1];

                if (d[v] > c + w) {
                    d[v] = c + w;
                    q.add(new long[] { c + w, v });
                }
            }
        }

        return d;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            g.get(u).add(new int[] { v, w });
            g.get(v).add(new int[] { u, w });
        }

        long[] ds = dijkstra(1), de = dijkstra(n);

        int[] e = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            e[i] = Integer.parseInt(st.nextToken());
        }

        long min = (long) 1e18;
        for (int i = 1; i <= n; i++) {
            if (e[i] != -1) {
                long cand = ds[i] + de[i] + (long) (k - 1) * e[i];
                min = Math.min(min, cand);
            }
        }

        System.out.println(min == (long) 1e18 ? -1 : min);
    }
}