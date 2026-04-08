import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Top - Down  도전@@@@@@@@@@@
 * 
 * 상태공간트리에서 유지해야할 것.
 * 1. 이전 선택 꽃
 * 2. 현재 판단할 idx
 * 
 * 사이클이 돌지 않는 이유
 * idx가 +1로 넘어가기 때문
 * 
 * solve(prev, idx)
 * 
 * 기저 조건1 ) idx >= N;
 * 
 * dp의 축은 현재까지 탐색한 꽃의 갯수와 크기max를 축으로?
 * 
 */
public class Boj25759 {

	static int N;
	static int dp[][];
	static int[] flowers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		flowers = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; ++i) {
			flowers[i] = Integer.parseInt(st.nextToken());
		}

		dp = new int[101][N];
		solve(0, 0);

		System.out.println(solveDFS(0, 0));
	}

	public static int solve(int prev, int idx) {
		if (idx >= N)
			return 0;
		if (dp[prev][idx] != 0)
			return dp[prev][idx];

		// 현재 꽃을 고르지 않고 넘어감
		dp[prev][idx] = solve(prev, idx + 1);

		// 현재 꽃을 고르고 넘어감
		if (prev == 0) { // 이전에 고른 꽃이 없다면
			dp[prev][idx] = Math.max(dp[prev][idx], solve(flowers[idx], idx + 1));
		} else {
			int score = (int) Math.pow(Math.abs(prev - flowers[idx]), 2);
			dp[prev][idx] = Math.max(dp[prev][idx], score + solve(flowers[idx], idx + 1));
		}

		return dp[prev][idx];
	}

	public static int solveDFS(int prev, int idx) {
		if (idx >= N)
			return 0; // 현재 꽃을 고르지 않고 넘어감
		int nonePick = solve(prev, idx + 1);
		int pick; // 현재 꽃을 고르고 넘어감
		if (prev == 0) { // 이전에 고른 꽃이 없다면
			pick = solve(flowers[idx], idx + 1);
		} else {
			int score = (int) Math.pow(Math.abs(prev - flowers[idx]), 2);

			pick = score + solve(flowers[idx], idx + 1);
		}
		return Math.max(nonePick, pick);

	}
}
