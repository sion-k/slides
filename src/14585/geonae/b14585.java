import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static int[][] map, memo;
	static int MAX = 300;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[MAX + 1][MAX + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			map[x][y] = 1;
		}

		memo = new int[MAX + 1][MAX + 1];
		for (int i = 0; i <= MAX; i++) {
			Arrays.fill(memo[i], -1);
		}

		solve(MAX, MAX);

		System.out.println(memo[MAX][MAX]);

	}

	static int solve(int i, int j) {
		if (i < 0 || j < 0) return 0;

		if (memo[i][j] != -1) return memo[i][j];

		int candy = 0;
		if (map[i][j] == 1) {
			candy = Math.max(0, M - (i + j));
		}

		return memo[i][j] = Math.max(solve(i - 1, j), solve(i, j - 1)) + candy;
	}

}
