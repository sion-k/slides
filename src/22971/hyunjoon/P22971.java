package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P22971 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int MOD = 998244353;
		
		int N = Integer.parseInt(br.readLine());
		int[] num = new int[N];
		long[] dp = new long[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			num[n] = Integer.parseInt(st.nextToken());
		}
		
		for (int n = 0; n < N; n++) {
			int tNum = num[n];
			long sum = 1;
			
			for (int i = n - 1; i >= 0; i--) {
				if (num[i] < tNum) sum = (sum + dp[i]) % MOD; 
			}
			
			dp[n] = sum;
			sb.append(sum).append(" ");
		}
		System.out.println(sb.toString());
	}
}
