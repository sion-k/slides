package algo_test;

import java.io.*;
import java.util.*;

public class Main {
	static int ary[], dp[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ary = new int[n];
		dp = new int[n];
		for(int i = 0; i < n; i++)
			ary[i] = Integer.parseInt(st.nextToken());
		dp[0] = 1;
		sb.append(1).append(" ");
		for(int i = 1; i < n; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(ary[j] < ary[i])
					dp[i] += dp[j];
				dp[i] %= 998244353;
			}
			sb.append(dp[i]).append(" ");
		}
		System.out.println(sb);
	}
}
