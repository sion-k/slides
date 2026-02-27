import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hyeonjun {
    // 1 2 3
    // 8 x 4
    // 7 6 5
    static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
    static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };
    static int N, M;
    static int[][] map;
    static boolean[][] isPeek;
    static boolean[][] visited;
    static int peekCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 받기
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 산 봉우리 확인
        isPeek = new boolean[N][M];
        peekCnt = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (isPeek[r][c])
                    continue;

                int now = map[r][c];
                int state = -1; // -1: 봉우리, 0: 높이 같은거 있음, 1: 높이 큰거 있음
                // 8방향 확인
                for (int d = 0; d < 8; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                        continue;

                    if (now < map[nr][nc]) {
                        state = 1;
                        break;
                    } else if (now == map[nr][nc])
                        state = 0;
                }

                if (state == 1)
                    continue; // 더 높은 칸 있는 경우
                else if (state == -1) { // 내가 봉우리면
                    isPeek[r][c] = true;
                    peekCnt++;
                } else { // 높이 같은 칸이 존재하면 bfs 탐색
                    if (checkPeek(r, c, now)) {
                        peekCnt++;

                        // 기록된 봉우리 복사
                        for (int ir = 0; ir < N; ir++) {
                            for (int ic = 0; ic < M; ic++) {
                                if (visited[ir][ic])
                                    isPeek[ir][ic] = true;
                            }
                        }
                    }
                }

            }
        }
        System.out.println(peekCnt);
    }

    static boolean checkPeek(int sr, int sc, int h) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { sr, sc });

        visited = new boolean[N][M];

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int d = 0; d < 8; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M)
                    continue;
                if (map[nr][nc] > h)
                    return false;
                if (map[nr][nc] != h)
                    continue;
                if (visited[nr][nc])
                    continue;

                q.offer(new int[] { nr, nc });
                visited[nr][nc] = true;
            }
        }
        return true;
    }
    /*
     * 산봉우리 : 주변 8칸이 현재 높이와 같거나 작다
     * 
     * [배열 모든 칸에 대해 반복]
     * - 주변 8칸 확인
     * if 자신보다 높은 칸 있으면 continue
     * else if 자신과 높이가 같은 칸 있으면
     * else 그 칸은 봉우리
     * 
     * 1) 자신과 높이가 같은 칸이 있을 때 탐색 방법
     * (탐색 된 봉우리가 체크된 boolean 배열 확인하고 이미 방문한 봉우리면 continue)
     * 방문 체크 하며 주변에 높이가 같은 칸으로 번지면서 확인
     * ㄴ 주변에 더 높은 칸이 있으면 봉우리가 아님
     * 봉우리 확인에 성공했다면 방문 체크된 칸을 '탐색 된 봉우리'를 저장하는 배열에 복사
     */
}
