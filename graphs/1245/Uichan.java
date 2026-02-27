import java.io.*;
import java.util.*;

class Uichan {
    static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static int[][] map;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        Set<Integer> checked = new HashSet<>();
        Set<Integer> temp;

        for (int i = 0; i < n; i++) {
            Loop: for (int j = 0; j < m; j++) {
                if (checked.contains(i * 100 + j))
                    continue;

                temp = new HashSet<>();
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[] { i, j });
                temp.add(i * 100 + j);

                while (!q.isEmpty()) {
                    int[] current = q.poll();
                    for (int k = 0; k < 8; k++) {
                        int nY = current[0] + dy[k];
                        int nX = current[1] + dx[k];
                        if (nY >= 0 && nY < n
                                && nX >= 0 && nX < m) {
                            // 본인보다 낮은지검사
                            if (map[current[0]][current[1]] < map[nY][nX])
                                continue Loop;
                            // 같은높이라면 큐 추가
                            if (map[current[0]][current[1]] == map[nY][nX] && !temp.contains(nY * 100 + nX)) {
                                q.add(new int[] { nY, nX });
                                temp.add(nY * 100 + nX);
                            }
                        }
                    }
                }

                checked.addAll(temp);
                answer++;
            }
        }

        System.out.println(answer);
    }
}
