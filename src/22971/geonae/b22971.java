import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] arr, memo;
	static final int MOD = 998244353;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		memo = new int[N];
		Arrays.fill(memo, -1);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			sb.append(solve(i)).append(" ");
		}
		
		System.out.println(sb);
	}
	
	static int solve(int idx) {
		if (memo[idx] != -1) return memo[idx];
		
		int count = 1;
		for (int i = 0; i < idx; i++) {
			if (arr[i] < arr[idx]) {
				count = (count + solve(i)) % MOD;
			}
		}
		
		return memo[idx] = count;
	}

}
