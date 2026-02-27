import java.util.*;
import java.io.*;

public class Sion {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        Queue<Integer> q = new LinkedList<>();
        int[] d = new int[n + 1];
        Arrays.fill(d, -1);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            int u = Integer.parseInt(st.nextToken());
            q.add(u);
            d[u] = 0;
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int i = 0; i <= 20; i++) {
                int v = u ^ (1 << i);
                if (0 <= v && v <= n && d[v] == -1) {
                    q.add(v);
                    d[v] = d[u] + 1;
                }
            }
        }

        int max = 0;
        for (int u = 0; u <= n; u++) {
            max = Math.max(max, d[u]);
        }
        System.out.println(max);
    }

}
