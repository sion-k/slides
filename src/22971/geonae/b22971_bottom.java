import java.util.*;
import java.io.*;

public class Main {
	
	static final int temp = 998244353;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		StringBuilder sb = new StringBuilder();
		int[] dp = new int[N];
		dp[0] = 1;
		sb.append(dp[0]).append(" ");
		for (int i = 1; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = (dp[i] + dp[j]) % temp;
				}
			}
			sb.append(dp[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
}
