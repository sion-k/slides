import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


/*
 * TOP - DOWN 도전@@@@@@@@@
 * 
 * 1. 분기처리가 가능하다. ( 좌 / 우 선택 )
 * 2. 현재 시점의 값 계산은 고른 값 * (idx+1)이다.  [ 0-based ]
 * 
 * 상태공간트리에 들고 갈 것. ( index, leftIndex, rightIndex )
 * index는 무조건 + 1 하므로, 사이클이 생기지 않는다.
 * 
 * 기저 조건 ) index >= N
 * 
 * dp[index]
 * dp[][] 
 * 
 */
public class Boj1823 {
	static int N;
	static int[] byeo;
	static int[][] dp;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		byeo = new int[N];
		dp = new int[N+1][N+1];
		
		for (int i = 0; i < N; i++) {
			byeo[i] = Integer.parseInt(br.readLine());;
		}
		for(int i = 0; i <= N; ++i)
			Arrays.fill(dp[i], -1);
		
		System.out.println(solve(0,N-1));
		
	}
	
	
	public static int solve(int li, int ri) {
		int idx = N - (ri - li + 1);
		if(idx >= N) return 0;
		if(dp[li][ri] != -1) return dp[li][ri];
		
		
//		dp[li+1][ri] = byeo[li] * (idx+1) + solve(li + 1, ri);
//		dp[li][ri-1] = byeo[ri] * (idx+1) + solve(li, ri - 1);
//		dp[li][ri] = Math.max(dp[li+1][ri], dp[li][ri-1]);

		if(li == ri)
			return dp[li][ri] = byeo[li] * (idx+1);
		
		int leftPick = byeo[li] * (idx+1) + solve(li + 1, ri);
		int rightPick = byeo[ri] * (idx+1) + solve(li, ri - 1);
		dp[li][ri] = Math.max(leftPick, rightPick);
	
		
		return dp[li][ri];
		
	}
}
