package algo_test;

import java.io.*;
import java.util.*;

public class Main {
	static int ary[], dp[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		ary = new int[n];
		dp = new int[101];
		for(int i = 0; i < n; i++)
			ary[i] = Integer.parseInt(st.nextToken());
		for(int i = 1; i <= 100; i++)
			dp[i] = -1;
		dp[ary[0]] = 0;
		for(int i = 1; i < n; i++) {
			for(int j = 1; j <= 100; j++) {
				if(dp[j] >= 0)
					dp[ary[i]] = Math.max(dp[ary[i]], dp[j] + (j - ary[i]) * (j - ary[i]));
			}
		}
		System.out.println(dp[ary[n - 1]]);
	}
}
