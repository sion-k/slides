import java.io.*;
import java.util.*;

public class BinarySearch {
    static int n, m;
    static ArrayList<ArrayList<int[]>> g = new ArrayList<>();
    static int s, e;

    static boolean f(int x) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] b = new boolean[n + 1];

        q.add(s);
        b[s] = true;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int[] t : g.get(u)) {
                int v = t[0], w = t[1];

                if (w >= x && !b[v]) {
                    q.add(v);
                    b[v] = true;
                }
            }
        }

        return b[e];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

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

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int l = 1, r = (int) 1e9 + 1;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (f(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }

        System.out.println(l);
    }
}