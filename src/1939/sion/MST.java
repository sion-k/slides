import java.io.*;
import java.util.*;

public class MST {
    static int[] p;

    static int find(int u) {
        if (p[u] == 0) {
            return u;
        }
        return p[u] = find(p[u]);
    }

    static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            p[u] = v;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] edge = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edge[i][0] = Integer.parseInt(st.nextToken());
            edge[i][1] = Integer.parseInt(st.nextToken());
            edge[i][2] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        Arrays.sort(edge, (a, b) -> Integer.compare(b[2], a[2]));

        p = new int[n + 1];
        for (int[] t : edge) {
            int u = t[0], v = t[1], w = t[2];

            union(u, v);
            if (find(s) == find(e)) {
                System.out.println(w);
                return;
            }
        }
    }
}