package BOJ2976;

import java.util.*;
import java.io.*;

public class Main {
	static int INF = 500 * 1000 * 10;
	static int N;
	static int[] fee;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		fee = new int[N+1];
		for(int i=1; i<=N; i++) {
			fee[i] = Integer.parseInt(br.readLine());
		}
		int[][] dp = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(dp[i], INF);
		}
		dp[1][2] = fee[2];
		dp[1][1] = dp[1][2] + fee[1];
		int answer = INF;
		for(int jump = 2; jump<=N; jump++) {
			for(int i=N; i>=1; i--) {
				if(i - jump >= 1) {
					dp[jump][i] = Math.min(dp[jump][i], dp[jump-1][i-jump] + fee[i]);
				}
				if(i + jump <= N) {
					dp[jump][i] = Math.min(dp[jump][i], dp[jump][i+jump] + fee[i]);
				}
			}
			answer = Math.min(answer, dp[jump][N]);
		}
		bw.write(answer + "\n");
		bw.flush();
	}
}
