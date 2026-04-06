package BOJ25759;

import java.util.*;
import java.io.*;

public class BaseCase1 {
	static int N;
	static int[] flowers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		flowers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			flowers[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[N];
		dp[0] = 0;
		dp[1] = (flowers[1] - flowers[0]) * (flowers[1] - flowers[0]);
		dp[2] = Math.max(dp[1] + difference(1, 2), difference(0, 2));
		for(int i=3; i<N; i++) {
			int max = 0;
			for(int j=0; j<i; j++) {
				max = Math.max(max, dp[j] + difference(i, j));
			}
			dp[i] = max;
		}
		int answer = dp[N-1];
		bw.write(answer + "\n");
		bw.flush();
	}
	public static int difference(int a, int b) {
		return (flowers[b] - flowers[a]) * (flowers[b] - flowers[a]);
	}
}
