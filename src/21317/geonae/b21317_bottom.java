import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] small = new int[N + 1];
		int[] big = new int[N + 1];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			small[i] = Integer.parseInt(st.nextToken());
			big[i] = Integer.parseInt(st.nextToken());
		}
		
		int k = Integer.parseInt(br.readLine());

		int[][] dp = new int[N + 1][2];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], 100001);
		}
		
		dp[1][0] = 0;
		dp[1][1] = 0;
		
		for (int i = 2; i <= N; i++) {
			dp[i][0] = dp[i - 1][0] + small[i - 1];
			dp[i][1] = dp[i - 1][1] + small[i - 1];
			
			if (i >= 3) {
				dp[i][0] = Math.min(dp[i][0], dp[i - 2][0] + big[i - 2]);
				dp[i][1] = Math.min(dp[i][1], dp[i - 2][1] + big[i - 2]);
			}
			
			if (i >= 4) {
				dp[i][1] = Math.min(dp[i][1], dp[i - 3][0] + k);
			}
		}

		System.out.println(Math.min(dp[N][0], dp[N][1]));
	}

}
