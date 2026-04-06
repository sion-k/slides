package BOJ1823;

import java.util.*;
import java.io.*;

public class TimeExceed {
	static int N;
	static int[] v;
	static class Harvest {
		@Override
		public String toString() {
			return "Harvest [profit=" + profit + ", visited=" + visited + "]";
		}
		int profit;
		int visited;
		public Harvest(int profit, int visited) {
			this.profit = profit;
			this.visited = visited;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		v = new int[N];
		for(int i=0; i<N; i++) {
			v[i] = Integer.parseInt(br.readLine());
		}
		List<Harvest>[] dp = new List[N+1];
		for(int i=1; i<=N; i++) {
			dp[i] = new ArrayList<>();
		}
		dp[1].add(new Harvest(v[0], 1 * 10000 + N-1));
		dp[1].add(new Harvest(v[N-1], 0 * 10000 + N-2));
		for(int i=1; i<N; i++) {
			for(Harvest h : dp[i]) {
				int profit = h.profit;
				int left = h.visited / 10000;
				int right = h.visited % 10000;
				Harvest nh1 = new Harvest(profit + v[left] * (i+1), (left + 1) * 10000 + right);
				Harvest nh2 = new Harvest(profit + v[right] * (i+1), left * 10000 + right-1);
				dp[i+1].add(nh1);
				dp[i+1].add(nh2);
			}
		}
		int answer = 0;
		for(Harvest h : dp[N]) {
			answer = Math.max(answer, h.profit);
		}
		bw.write(answer + "\n");
		bw.flush();
	}
}
