package BOJ1999;

import java.util.*;
import java.io.*;

public class TreeMapSolution {
    static int N, B, K;
    static int[][] arr;
    static int[][] horizontalArray;
    static int[][] verticalArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        horizontalArray = new int[N][N - B + 1];
        for (int r = 0; r < N; r++) {
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int leftIndex = 0;
            int rightIndex = B - 1;
            for (int c = leftIndex; c <= rightIndex; c++) {
                int num = arr[r][c];
                tm.put(num, tm.getOrDefault(num, 0) + 1);
            }
            int max = tm.lastKey();
            int min = tm.firstKey();
            horizontalArray[r][leftIndex] = max * 1000 + min;
            while (true) {
                int left = arr[r][leftIndex];
                remove(tm, left);
                leftIndex++;
                rightIndex++;
                if (rightIndex == N) {
                    break;
                }
                int right = arr[r][rightIndex];
                tm.put(right, tm.getOrDefault(right, 0) + 1);
                max = tm.lastKey();
                min = tm.firstKey();
                horizontalArray[r][leftIndex] = max * 1000 + min;
            }
        }
        verticalArray = new int[N - B + 1][N - B + 1];
        for (int c = 0; c < N - B + 1; c++) {
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int upIndex = 0;
            int downIndex = B - 1;
            for (int r = upIndex; r <= downIndex; r++) {
                int value = horizontalArray[r][c];
                int max = value / 1000;
                int min = value % 1000;
                tm.put(max, tm.getOrDefault(max, 0) + 1);
                tm.put(min, tm.getOrDefault(min, 0) + 1);
            }
            int max = tm.lastKey();
            int min = tm.firstKey();
            verticalArray[upIndex][c] = max * 1000 + min;
            while (true) {
                int up = horizontalArray[upIndex][c];
                int upMax = up / 1000;
                int upMin = up % 1000;
                remove(tm, upMax);
                remove(tm, upMin);
                upIndex++;
                downIndex++;
                if (downIndex == N) {
                    break;
                }
                int down = horizontalArray[downIndex][c];
                int downMax = down / 1000;
                int downMin = down % 1000;
                tm.put(downMax, tm.getOrDefault(downMax, 0) + 1);
                tm.put(downMin, tm.getOrDefault(downMin, 0) + 1);
                max = tm.lastKey();
                min = tm.firstKey();
                verticalArray[upIndex][c] = max * 1000 + min;
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int value = verticalArray[r][c];
            sb.append(value / 1000 - value % 1000).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void remove(TreeMap<Integer, Integer> tm, int num) {
        if (tm.containsKey(num)) {
            tm.replace(num, tm.get(num) - 1);
            if (tm.get(num) == 0) {
                tm.remove(num);
            }
        }
    }
}
