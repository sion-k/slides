import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<ArrayList<int[]>> g = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            g.get(u).add(new int[] { v, w });
            g.get(v).add(new int[] { u, w });
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        long[][] d = new long[n + 1][2];
        for (int i = 1; i <= n; i++) {
            d[i][0] = (long) 1e18;
            d[i][1] = (long) 1e18;
        }

        PriorityQueue<long[]> q = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) {
                return Long.compare(x[0], y[0]);
            } else {
                return Long.compare(x[1], y[1]);
            }
        });

        d[s][0] = 0;
        d[s][1] = -a[s];

        q.add(new long[] { 0, -a[s], s });

        while (!q.isEmpty()) {
            long[] p = q.poll();

            long c1 = p[0], c2 = p[1];
            int u = (int) p[2];

            if (d[u][0] != c1 || d[u][1] != c2) {
                continue;
            }

            for (int[] e : g.get(u)) {
                int v = e[0], w = e[1];

                long nc1 = c1 + w, nc2 = c2 - a[v];
                if (d[v][0] > nc1) {
                    d[v][0] = nc1;
                    d[v][1] = nc2;
                    q.add(new long[] { nc1, nc2, v });
                } else if (d[v][0] == nc1 && d[v][1] > nc2) {
                    d[v][0] = nc1;
                    d[v][1] = nc2;
                    q.add(new long[] { nc1, nc2, v });
                }
            }
        }

        if (d[t][0] == (long) 1e18) {
            System.out.println(-1);
        } else {
            System.out.println(d[t][0] + " " + (-d[t][1]));
        }
    }
}