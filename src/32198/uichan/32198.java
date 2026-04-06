import java.io.*;
import java.util.*;

class Main {
	static final int IMPOSSIBLE = 10000000;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[n + 1][2001];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], IMPOSSIBLE);
		}
		dp[0][1000] = 0; // 첫위치:1000

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int[] input = new int[3];
			input[0] = Integer.parseInt(st.nextToken());
			input[1] = Integer.parseInt(st.nextToken()) + 1000;
			input[2] = Integer.parseInt(st.nextToken()) + 1000;
			pq.add(input);
		}

		int prevT = 0;
		for (int i = 0; i < n; i++) {
			int[] c = pq.poll();
			int currentT = c[0];
			int t = currentT - prevT;
			int l = c[1];
			int r = c[2];

			for (int j = 0; j <= 2000; j++) {
				if (dp[i][j] == IMPOSSIBLE)
					continue;
				for (int k = -t; k <= t; k++) {
					if (j + k <= l || r <= j + k)
						dp[i + 1][j + k] = Math.min(dp[i][j] + Math.abs(k), dp[i + 1][j + k]);
				}
			}
			prevT = currentT;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i <= 2000; i++) {
			if (dp[n][i] != IMPOSSIBLE) {
				ans = Math.min(ans, dp[n][i]);
			}
		}

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

	}
}
