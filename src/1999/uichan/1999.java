import java.io.*;
import java.util.*;

class Main {
    static class Area {
        int[] num;
        int max;
        int min;

        Area() {
            num = new int[250 + 1];
            max = -1;
            min = Integer.MAX_VALUE;
        }

        void add(int n) {
            num[n]++;
            max = Math.max(n, max);
            min = Math.min(n, min);
        }

        void remove(int n) {
            num[n]--;
            if (num[n] == 0 && max == n) {
                int cursor = n - 1;
                while (cursor >= 0 && num[cursor] == 0)
                    cursor--;
                max = cursor;
            } else if (num[n] == 0 && min == n) {
                int cursor = n + 1;
                while (cursor <= 250 && num[cursor] == 0)
                    cursor++;
                min = cursor;
            }
        }

        int getDiff() {
            return max - min;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] memo = new int[n - b + 1][n - b + 1];
        Area area = new Area();

        for (int i = 0; i < b; i++) {
            for (int j = 0; j < b; j++) {
                area.add(map[i][j]);
            }
        }
        memo[0][0] = area.getDiff();

        for (int i = 0; i < n - b + 1; i++) {
            if (i % 2 == 0) { // 왼->오
                for (int j = 0; j < n - b; j++) {
                    for (int p = i; p < i + b; p++) {
                        area.remove(map[p][j]);
                        area.add(map[p][j + b]);
                    }
                    memo[i][j + 1] = area.getDiff();
                }

                // 오른쪽 끝에서 내려가기
                if (i + b < n) {
                    for (int j = n - b; j < n; j++) {
                        area.add(map[i + b][j]);
                        area.remove(map[i][j]);
                    }
                    memo[i + 1][n - b] = area.getDiff();
                }

            } else { // 오->왼
                for (int j = n - b - 1; j >= 0; j--) {
                    for (int p = i; p < i + b; p++) {
                        area.add(map[p][j]);
                        area.remove(map[p][j + b]);
                    }
                    memo[i][j] = area.getDiff();
                }

                // 왼쪽 끝에서 내려가기
                if (i + b < n) {
                    for (int j = 0; j < b; j++) {
                        area.add(map[i + b][j]);
                        area.remove(map[i][j]);
                    }
                    memo[i + 1][0] = area.getDiff();
                }
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            sb.append(memo[y][x]).append("\n");
        }
        System.out.println(sb);
    }
}
