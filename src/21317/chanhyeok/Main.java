package algo_test;

import java.io.*;
import java.util.*;

public class Main {
	static int miniJump[], bigJump[], dp[][], cheatJump;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		miniJump = new int[n];
		bigJump = new int[n];
		dp = new int[n][2];
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			miniJump[i] = Integer.parseInt(st.nextToken());
			bigJump[i] = Integer.parseInt(st.nextToken());
		}
		cheatJump = Integer.parseInt(br.readLine());
		System.out.println(Math.min(sol(n - 1, 1), sol(n - 1, 0)));
	}
	
	static int sol(int idx, int m) {
		if(idx <= 0)
			return 0;
		if(dp[idx][m] > 0)
			return dp[idx][m];
		dp[idx][m] = 1000000000;
		if(m > 0) {
			if(idx >= 3)
				dp[idx][m] = sol(idx - 3, 0) + cheatJump;
		}
		
		dp[idx][m] = Math.min(dp[idx][m], sol(idx - 1, m) + miniJump[idx - 1]);
		if(idx > 1)
			dp[idx][m] = Math.min(dp[idx][m], sol(idx - 2, m) + bigJump[idx - 2]);
		
		return dp[idx][m];
	}
}
