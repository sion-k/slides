import java.util.*;
import java.io.*;

public class Sion {
    static int b[];
    static List<List<Integer>> g = new ArrayList<>();

    static int dfs(int u, int p) {
        int s = 1;
        for (int v : g.get(u)) {
            if (v != p) {
                s += dfs(v, u);
                b[v] = u;
            }
        }
        return s;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            g.get(u).add(v);
            g.get(v).add(u);
        }

        b = new int[n + 1];
        dfs(1, 1);

        Set<Integer> s = new HashSet<>();
        for (int u = n; u != 0; u = b[u]) {
            s.add(u);
        }

        List<Integer> a = new ArrayList<>();
        for (int u : s) {
            for (int v : g.get(u)) {
                if (!s.contains(v)) {
                    a.add(dfs(v, u));
                }
            }
        }

        int t = a.stream().mapToInt(Integer::intValue).sum();
        long r = 0;
        for (int v : a) {
            r += (long) v * (t - v);
        }

        System.out.println(r / 2);
    }

}
