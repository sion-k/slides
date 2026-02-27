import java.util.*;
import java.io.*;

public class Sion {
    static int n, m;
    static int[][] a;
    static boolean[][] b;
    static final int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static final int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static boolean bfs(int sx, int sy) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { sx, sy });
        b[sx][sy] = true;
        boolean f = true;
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                // 8방향으로 인접한 칸 중에서 높이가 같은 칸으로 간선이 이어져 있다.
                if (inRange(nx, ny) && a[x][y] == a[nx][ny] && !b[nx][ny]) {
                    q.add(new int[] { nx, ny });
                    b[nx][ny] = true;
                }
            }

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                // 8방향으로 인접한 칸 중에서 높이가 더 높은 칸이 있는지 확인.
                if (inRange(nx, ny) && a[x][y] < a[nx][ny]) {
                    f = false;
                }
            }
        }
        return f;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Sion.n = Integer.parseInt(st.nextToken());
        Sion.m = Integer.parseInt(st.nextToken());
        Sion.a = new int[n][m];
        for (int x = 0; x < n; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < m; y++) {
                a[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        Sion.b = new boolean[n][m];

        int c = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (!b[x][y]) {
                    if (bfs(x, y)) {
                        c++;
                    }
                }
            }
        }
        System.out.println(c);
    }

}
