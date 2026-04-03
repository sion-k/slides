import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static long[] dp;
	static int[] arr;
	static final int DIV = 998244353;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		dp = new long[n+1];
		Arrays.fill(dp,1);
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<n; i++) {
			int temp = arr[i];
			for(int j=0; j<i; j++) {
				if(arr[j]<temp) {
					dp[i]=(dp[i]+dp[j])%DIV;
				}
			}
			sb.append(dp[i]%998244353).append(" ");
		}
		
		System.out.print(sb);
	}
	
}
