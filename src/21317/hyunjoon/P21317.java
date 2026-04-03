package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P21317 {
	static class JumpCost {
		int jumpS, jumpL;

		public JumpCost(int jumpS, int jumpL) {
			this.jumpS = jumpS;
			this.jumpL = jumpL;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		JumpCost[] jumpCost = new JumpCost[N - 1];
		for (int n = 0; n < (N - 1); n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int jumpS = Integer.parseInt(st.nextToken());
			int jumpL = Integer.parseInt(st.nextToken());
			jumpCost[n] = new JumpCost(jumpS, jumpL);
		}
		int K = Integer.parseInt(br.readLine());
		
		// dp[바위 번호][매우 큰 점프 사용 여부]
		int[][] dp = new int[N][2];
		
		for (int n = 0; n < (N - 1); n++) {
			int nCost = 0;
			
			// 작은 점프 - K 사용 안한 상태
			nCost = dp[n][0] + jumpCost[n].jumpS;
			dp[n + 1][0] = (dp[n + 1][0] == 0) ? nCost : Integer.min(dp[n + 1][0], nCost);
			// 작은 점프 - K 사용한 상태
			if (dp[n][1] != 0) {
				nCost = dp[n][1] + jumpCost[n].jumpS;
				dp[n + 1][1] = (dp[n + 1][1] == 0) ? nCost : Integer.min(dp[n + 1][1], nCost);
			}
			
			// 큰 점프 - K 사용 안한 상태
			if (n == (N - 2)) continue;
			nCost = dp[n][0] + jumpCost[n].jumpL;
			dp[n + 2][0] = (dp[n + 2][0] == 0) ? nCost : Integer.min(dp[n + 2][0], nCost);
			// 큰 점프 - K 사용한 상태
			if (dp[n][1] != 0) {
				nCost = dp[n][1] + jumpCost[n].jumpL;
				dp[n + 2][1] = (dp[n + 2][1] == 0) ? nCost : Integer.min(dp[n + 2][1], nCost);
			}
			
			// 매우 큰 점프
			if (n == (N - 3)) continue;
			nCost = dp[n][0] + K;
			dp[n + 3][1] = (dp[n + 3][1] == 0) ? nCost : Integer.min(dp[n + 3][1], nCost);
		}
		
		System.out.println((dp[N - 1][1] == 0) ? dp[N - 1][0] : Integer.min(dp[N - 1][0], dp[N - 1][1]));
	}
}
