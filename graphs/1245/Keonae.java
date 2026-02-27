import java.util.*;
import java.io.*;

public class Keonae {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] dy = { 1, 0, -1, 1, -1, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && BFS(i, j)) {
                    count++;
                }
            }
        }

        System.out.println(count);

    }

    static boolean BFS(int x, int y) {
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { x, y });

        boolean flag = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int k = map[cur[0]][cur[1]];
            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] == k && !visited[nx][ny]) {
                        q.add(new int[] { nx, ny });
                        visited[nx][ny] = true;
                    } else if (map[nx][ny] > k) {
                        flag = false;
                    }
                }
            }
        }

        return flag;
    }

}