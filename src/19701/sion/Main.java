import java.util.*;
import java.io.*;

public class Main {
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

        int[][] d = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            d[i][0] = 2_000_000_000;
            d[i][1] = 2_000_000_000;
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        d[1][0] = 0;
        q.offer(new int[] { 0, 1, 0 });

        while (!q.isEmpty()) {
            int[] p = q.poll();

            int c = p[0], u = p[1], b = p[2];
            if (d[u][b] < c) {
                continue;
            }

            for (int[] e : g.get(u)) {
                int v = e[0], t = e[1], k = e[2];

                if (d[v][b] > c + t) {
                    d[v][b] = c + t;
                    q.offer(new int[] { c + t, v, b });
                }

                if (b == 0) {
                    if (d[v][1] > c + t - k) {
                        d[v][1] = c + t - k;
                        q.offer(new int[] { c + t - k, v, 1 });
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(d[i][1]).append('\n');
        }
        System.out.print(sb);
    }
}