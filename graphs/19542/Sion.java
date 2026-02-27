import java.util.*;
import java.io.*;

public class Sion {
    static List<List<Integer>> g = new ArrayList<>();
    static int[] h;

    static void dfs(int u, int p) {
        for (int v : g.get(u)) {
            if (v != p) {
                dfs(v, u);
                h[u] = Math.max(h[u], h[v] + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            g.get(u).add(v);
            g.get(v).add(u);
        }

        h = new int[n + 1];
        dfs(s, s);

        int c = 0;
        for (int u = 1; u <= n; u++) {
            if (h[u] >= d) {
                c++;
            }
        }
        System.out.println(2 * Math.max(c - 1, 0));
    }

}
