import java.io.*;
import java.util.*;

public class Dijkstra {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj.get(u).add(new int[] { v, w });
            adj.get(v).add(new int[] { u, w });
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        int[] d = new int[n + 1];

        q.offer(new int[] { (int) 1e9, s });
        d[s] = (int) 1e9;

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int c = t[0], u = t[1];

            if (d[u] != c) {
                continue;
            }

            for (int[] edge : adj.get(u)) {
                int v = edge[0], w = edge[1];

                int nw = Math.min(c, w);
                if (d[v] < nw) {
                    d[v] = nw;
                    q.add(new int[] { nw, v });
                }
            }
        }

        System.out.println(d[e]);
    }
}