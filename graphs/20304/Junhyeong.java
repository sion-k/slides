import java.io.*;
import java.util.*;

public class Junhyeong {
    static class Cord {
        int y;
        int x;
        int h;

        public Cord(int y, int x, int h) {
            super();
            this.y = y;
            this.x = x;
            this.h = h;
        }
    }

    static int n, m, ans;
    static int[][] mountains;
    static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static Queue<Cord> que = new ArrayDeque<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mountains = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                mountains[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j])
                    continue;

                boolean success = true;
                que.offer(new Cord(i, j, mountains[i][j]));
                visited[i][j] = true;

                while (!que.isEmpty()) {
                    Cord cur = que.poll();
                    int curH = cur.h;

                    for (int r = 0; r < 8; r++) {
                        int nextY = cur.y + dy[r], nextX = cur.x + dx[r];
                        if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m ||
                                (visited[nextY][nextX] && mountains[nextY][nextX] == curH)) {
                            continue;
                        }
                        int nextH = mountains[nextY][nextX];
                        if (curH < nextH) {
                            success = false;
                        } else if (curH == nextH) {
                            que.offer(new Cord(nextY, nextX, nextH));
                            visited[nextY][nextX] = true;
                        }

                    }
                }

                if (success) {
                    ans++;
                }
            }
        }
        System.out.print(ans);
    }
}
