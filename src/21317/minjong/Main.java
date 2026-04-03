package BOJ21317;

import java.util.*;
import java.io.*;

public class Main {
	static int N, K;
	static int[][] jump;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		jump = new int[N][2];
		for(int i=1; i<=N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			jump[i][0] = Integer.parseInt(st.nextToken());
			jump[i][1] = Integer.parseInt(st.nextToken());
		}
		K = Integer.parseInt(br.readLine());
		int[][] dp = new int[2][N+1];
		Arrays.fill(dp[0], 1000000);
		Arrays.fill(dp[1], 1000000);
		dp[0][1] = 0;
		if(N >= 2) {
			dp[0][2] = jump[1][0];
		}
		if(N >= 3) {
			dp[0][3] = Math.min(dp[0][1] + jump[1][1], dp[0][2] + jump[2][0]);
		}
		for(int i=3; i<=N; i++) {
			dp[0][i] = Math.min(dp[0][i-2] + jump[i-2][1], dp[0][i-1] + jump[i-1][0]);
			dp[1][i] = Math.min(dp[1][i-2] + jump[i-2][1], dp[1][i-1] + jump[i-1][0]);
			dp[1][i] = Math.min(dp[1][i], dp[0][i-3] + K);
		}
		int answer = Math.min(dp[0][N], dp[1][N]);
		bw.write(answer + "\n");
		bw.flush();
	}
}
