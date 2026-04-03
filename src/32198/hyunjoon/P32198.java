package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class P32198 {
    static final int INF = Integer.MAX_VALUE;
    static int[][] dp;
    static boolean[][] visited;
    static HashMap<Integer, Bang> bang;
    static int maxT;
    
    static class Bang {
        int a, b;

        public Bang(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        bang = new HashMap<>();
        maxT = 0;
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken()) + 1000;
            int B = Integer.parseInt(st.nextToken()) + 1000;
            maxT = Math.max(maxT, T);
            bang.put(T, new Bang(A, B));
        }
        
        dp = new int[maxT + 1][2001]; // dp[시간][위치] > 위치index: 0~999(-1000~-1) - 1000(0) - 1001~2000(1~1000)
        visited = new boolean[maxT + 1][2001];
        // dp 배열 초기화
        for (int i = 0; i <= maxT; i++) {
            for (int j = 0; j <= 2000; j++) {
                dp[i][j] = INF;
            }
        }
        
        int result = solve(0, 1000);
        System.out.println(result == INF ? -1 : result);
    }
    
    /*
     * t: 시간
     * p: 위치
     */
    static int solve(int t, int p) {
        if (t == maxT) return 0;
        
        if (visited[t][p]) return dp[t][p];
        visited[t][p] = true;
        
        int result = INF;
        Bang nextB = bang.get(t + 1);
        // 왼쪽 오른쪽 끝을 넘어가지 않도록 처리
        if (p > 0) { // 왼쪽으로 갈 수 있을 때
            int nextP = p - 1;
            if (nextB == null || (nextP <= nextB.a || nextP >= nextB.b)) {
                int temp = solve(t + 1, nextP);
                if (temp != INF) result = Math.min(result, temp + 1);
            }
        }
        if (p < 2000) { // 오른쪽으로 갈 수 있을 때
            int nextP = p + 1;
            if (nextB == null || (nextP <= nextB.a || nextP >= nextB.b)) {
                int temp = solve(t + 1, nextP);
                if (temp != INF) result = Math.min(result, temp + 1);
            }
        }
        // 움직이지 않을 때
        if (nextB == null || (p <= nextB.a || p >= nextB.b)) {
            int temp = solve(t + 1, p);
            if (temp != INF) result = Math.min(result, temp);
        }
        
        return dp[t][p] = result;
    }
}
