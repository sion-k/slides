import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int dp[][];
	static int ary[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		dp = new int[301][301];
		ary = new int[301][301];
		for(int i = 0; i <= 300; i++) {
			for(int j = 0; j <= 300; j++)
				dp[i][j] = -1;
		}
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			ary[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		System.out.println(sol(300, 300));
	}
	static int sol(int x, int y) {
		if(x == 0 && y == 0) return 0;
		if(dp[x][y] >= 0) return dp[x][y];
		dp[x][y] = 0;
		if(x > 0)
			dp[x][y] = Math.max(dp[x][y], sol(x - 1, y));
		if(y > 0)
			dp[x][y] = Math.max(dp[x][y], sol(x, y - 1));
		if(ary[x][y] == 1)
			dp[x][y] += Math.max(0, m - (x + y));
		return dp[x][y];
	}
}
