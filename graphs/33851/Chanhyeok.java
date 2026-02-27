import java.util.*;
import java.io.*;

class Chanhyeok {
    static int n, m, q, res, a, b;
    static List<Integer>[] e;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        int u, v;
        e = new List[n];
        dp = new int[n][n];
        Queue<Integer> qq = new ArrayDeque<>();
        for (int i = 0; i < n; i++)
            e[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken()) - 1;
            v = Integer.parseInt(st.nextToken()) - 1;
            e[v].add(u);
        }
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], -1);
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
            qq.offer(i);
            while (!qq.isEmpty()) {
                int t = qq.poll();
                for (int it : e[t]) {
                    if (dp[i][it] < 0) {
                        dp[i][it] = dp[i][t] + 1;
                        qq.offer(it);
                    }
                }
            }
        }
        for (int k = 0; k < q; k++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            res = -1;
            for (int i = 0; i < n; i++) {
                if (dp[a][i] >= 0 && dp[b][i] >= 0) {
                    if (res < 0)
                        res = Math.max(dp[a][i], dp[b][i]);
                    res = Math.min(res, Math.max(dp[a][i], dp[b][i]));
                }
            }
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }
}