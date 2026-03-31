package BOJ1999;

import java.util.*;
import java.io.*;

public class VeryLong {
    static int N, B, K;
    static int[][] arr;
    static int[][] maxArray;
    static int[][] minArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        maxArray = new int[N][N];
        minArray = new int[N][N];
        for (int r = 0; r < N; r++) {
            Arrays.fill(minArray[r], 250);
        }
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int i = r; i >= r - (B - 1); i--) {
                    for (int j = c; j >= c - (B - 1); j--) {
                        if (!checkRange(i, j)) {
                            break;
                        }
                        maxArray[i][j] = Math.max(maxArray[i][j], arr[r][c]);
                        minArray[i][j] = Math.min(minArray[i][j], arr[r][c]);
                    }
                }
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            sb.append(maxArray[r][c] - minArray[r][c]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static boolean checkRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
