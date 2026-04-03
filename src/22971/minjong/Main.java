package BOJ22971;

import java.util.*;
import java.io.*;

public class Main {
	static long INF = 998244353; 
	static int N;
	static int[] numbers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		long[] dp = new long[N];
		dp[0] = 1;
		for(int i=1; i<N; i++) {
			long sum = 0;
			for(int j=0; j<i; j++) {
				if(numbers[j] < numbers[i]) {
					sum += dp[j];
				}
			}
			dp[i] = (sum + 1) % INF;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(dp[i]).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
	
