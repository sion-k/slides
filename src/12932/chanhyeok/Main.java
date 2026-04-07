import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int ary[];
	static int dp[][];
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        ary = new int[n];
        for(int i = 0; i < n; i++) {
        	ary[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n + 1][n + 1];
        for(int i = 0 ; i <= n; i++) {
        	for(int j = 0; j <= n; j++)
        		dp[i][j] = -1;
        }
        int res = 2000000001;
        for(int i = 0; i < n; i++) {
        	res = Math.min(res, sol(i, n));
        	res = Math.min(res, sol(n, i));
        }
        System.out.println(res);
    }
	
	static int sol(int idx, int idx2) {
		if(idx == 0 && idx2 == 1) return 0;
		if(idx == 1 && idx2 == 0) return 0;
		
		if(dp[idx][idx2] >= 0)
			return dp[idx][idx2];
		
		dp[idx][idx2] = 2000000001;
		if(idx < idx2) {
			if(idx2 - idx == 1) {
				// 차이가 1이다? 이전에 idx를 선택했다
				for(int i = 0; i < idx; i++)
					dp[idx][idx2] = Math.min(dp[idx][idx2], sol(idx, i) + ((i > 0) ? Math.abs(ary[i - 1] - ary[idx2 - 1]) : 0));
			}
			else
				dp[idx][idx2] = sol(idx, idx2 - 1) + Math.abs(ary[idx2 - 1] - ary[idx2 - 2]);
		}
		else {
			if(idx - idx2 == 1) {
				// 차이가 1이다? 이전에 idx2를 선택했다
				for(int i = 0; i < idx2; i++)
					dp[idx][idx2] = Math.min(dp[idx][idx2], sol(i, idx2) + ((i > 0) ? Math.abs(ary[i - 1] - ary[idx - 1]) : 0));
			}
			else
				dp[idx][idx2] = sol(idx - 1, idx2) + Math.abs(ary[idx - 1] - ary[idx - 2]);
		}
		return dp[idx][idx2];
	}
}
