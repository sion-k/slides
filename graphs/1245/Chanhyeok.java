import java.util.*;
import java.io.*;

class Chanhyeok {
    static int n, m;
    static int ary[][];
    static int dir[][] = { { 1, 0 }, { 1, 1 }, { 1, -1 }, { 0, 1 }, { 0, -1 }, { -1, 1 }, { -1, 0 }, { -1, -1 } };
    static boolean visited[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ary = new int[n][m];
        visited = new boolean[n][m];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                ary[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    if (dfs(i, j))
                        cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    static boolean dfs(int i, int j) {
        int ii, jj;
        boolean ret = true;
        for (int d = 0; d < 8; d++) {
            ii = i + dir[d][0];
            jj = j + dir[d][1];
            if (ii >= 0 && ii < n && jj >= 0 && jj < m) {
                if (ary[ii][jj] > ary[i][j])
                    ret = false;
                if (!visited[ii][jj]) {
                    if (ary[ii][jj] == ary[i][j]) {
                        visited[ii][jj] = true;
                        ret = dfs(ii, jj) && ret;
                    }
                }
            }
        }
        return ret;
    }
}