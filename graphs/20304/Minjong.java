import java.util.*;
import java.io.*;

public class Minjong {
    static int N, M;
    static int[][] farm;
    static int[][] around = { { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 }, { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 } };
    static boolean[][] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        farm = new int[N][M];
        checked = new boolean[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                farm[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!checked[r][c]) {
                    if (BFS(r, c)) {
                        answer++;
                    }
                }
            }
        }
        bw.write(answer + "\n");
        bw.flush();
    }

    public static boolean BFS(int startR, int startC) {
        boolean[][] visited = new boolean[N][M];
        int location = startR * 100 + startC;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(location);
        visited[startR][startC] = true;
        checked[startR][startC] = true;
        while (!queue.isEmpty()) {
            location = queue.poll();
            int r = location / 100;
            int c = location % 100;
            for (int i = 0; i < 8; i++) {
                int nr = r + around[i][0];
                int nc = c + around[i][1];
                int nloc = nr * 100 + nc;
                if (checkRange(nr, nc) && !visited[nr][nc]) {
                    if (farm[nr][nc] == farm[startR][startC]) {
                        queue.offer(nloc);
                        visited[nr][nc] = true;
                        checked[nr][nc] = true;
                    } else if (farm[nr][nc] < farm[startR][startC]) {
                        visited[nr][nc] = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean checkRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
