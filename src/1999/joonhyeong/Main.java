import java.io.*;
import java.util.*;

public class Main {
    static int n, b, k;
    static int[][] map;
    static int[][] minList;
    static int[][] maxList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        minList = new int[n][n - b + 1];
        maxList = new int[n][n - b + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // minList, maxList 생성
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - b + 1; j++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int r = j; r < j + b; r++) {
                    min = Math.min(min, map[i][r]);
                    max = Math.max(max, map[i][r]);
                }
                minList[i][j] = min;
                maxList[i][j] = max;
            }
        }

        for (int i = 0; i < n - b + 1; i++) {
            for (int j = 0; j < n - b + 1; j++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int r = j; r < j + b; r++) {
                    min = Math.min(min, minList[r][i]);
                    max = Math.max(max, maxList[r][i]);
                }
                minList[j][i] = min;
                maxList[j][i] = max;
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 세로
            int b = Integer.parseInt(st.nextToken()); // 가로
            sb.append(maxList[a - 1][b - 1] - minList[a - 1][b - 1]).append("\n");
        }

        System.out.print(sb);
    }
}
