package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14585_TopDown {
    static int N, M;
    static int[][] basket;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        basket = new int[301][301];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            basket[x][y] = 1; // 해당 좌표에 바구니가 있는지 기록
        }
        
        dp = new int[301][301];
        for (int x = 0; x <= 300; x++) {
            for (int y = 0; y <= 300; y++) {
                dp[x][y] = -1;
            }
        }
        
        System.out.println(func(300, 300));
    }
    
    static int func(int x, int y) {
        if (x == 0 && y == 0) return 0;
        if (dp[x][y] >= 0) return dp[x][y];

        if (x > 0) dp[x][y] = Math.max(dp[x][y], func(x - 1, y)); 
        if (y > 0) dp[x][y] = Math.max(dp[x][y], func(x, y - 1)); 
        if (basket[x][y] == 1) { // 바구니가 존재할때, 바구니에 남은 사탕 수 더해줌
            int t = x + y;
            dp[x][y] += (M < t) ? 0 : M - t;
        }
        
        return dp[x][y];
    }
}
