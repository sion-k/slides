package BOJ1823;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] v;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		v = new int[N+1];
		for(int i=1; i<=N; i++) {
			v[i] = Integer.parseInt(br.readLine());
		}
		int answer = 0;
		dp = new int[N+1][N+1];
		for(int r=0; r<=N; r++) {
			Arrays.fill(dp[r], -1);
		}
		dp[0][0] = 0;
		dp[1][0] = v[1];
		dp[0][1] = v[N];
		for(int i=0; i<=N; i++) {
			answer = Math.max(answer, get(i, N-i));
		}
		bw.write(answer + "\n");
		bw.flush();
	}
	public static int get(int left, int right) {
		if(dp[left][right] == -1) {
			if(left >= 1) {
				int leftValue = get(left-1, right) + v[left] * (left + right);
				dp[left][right] = Math.max(dp[left][right], leftValue);
			}
			if(right >= 1) {
				int rightValue = get(left, right-1) + v[N+1-right] * (left + right);
				dp[left][right] = Math.max(dp[left][right], rightValue);
			}
		}
		return dp[left][right];
	}
}
