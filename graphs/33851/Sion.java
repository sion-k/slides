import java.util.*;
import java.io.*;

public class Sion {
    static int n;
    static List<List<Integer>> g = new ArrayList<>();

    static int[] bfs(int x) {
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        int[] d = new int[n + 1];
        Arrays.fill(d, -1);
        d[x] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : g.get(u)) {
                if (d[v] == -1) {
                    q.add(v);
                    d[v] = d[u] + 1;
                }
            }
        }

        return d;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder bw = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        Sion.n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            g.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            g.get(v).add(u);
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int[] d1 = bfs(u), d2 = bfs(v);

            int min = (int) 1e9;
            for (int x = 1; x <= n; x++) {
                if (d1[x] == -1 || d2[x] == -1) {
                    continue;
                }
                min = Math.min(min, Math.max(d1[x], d2[x]));
            }
            bw.append(min == (int) 1e9 ? -1 : min).append('\n');
        }
        System.out.print(bw);
    }
}
