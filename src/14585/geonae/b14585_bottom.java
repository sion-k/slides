import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[301][301];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			map[x][y] = 1;
		}

		int[][] dp = new int[301][301];
		int result = 0;

		for (int i = 0; i < 301; i++) {
			for (int j = 0; j < 301; j++) {
				if (i == 0 && j == 0)
					continue;
				if (i == 0) {
					dp[i][j] = dp[i][j - 1];
				} else if (j == 0) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}

				if (map[i][j] == 1) {
					int candy = M - (i + j);
					if (candy > 0) {
						dp[i][j] += candy;
					}
				}
				result = Math.max(result, dp[i][j]);
			}
		}

		System.out.println(result);
	}

}
