import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/* 농장 관리 */
public class Jihoon {

    static int N, M, res;
    static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
    static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {

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

        res = 0;
        visited = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!visited[r][c])
                    checkPeak(r, c);
            }
        }

        System.out.println(res);
    }

    private static void checkPeak(int r, int c) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(r, c));

        int height = map[r][c];
        boolean isPeak = true;
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int d = 0; d < 8; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;

                if (map[nr][nc] > height) {
                    isPeak = false;
                } else if (map[nr][nc] == height && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.offer(new Node(nr, nc));
                }
            }
        }

        if (isPeak)
            res++;
    }

    private static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}