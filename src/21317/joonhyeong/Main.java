import java.io.*;
import java.util.*;

public class Main {
	static class Jump{
		int s;
		int m;
		public Jump(int s, int m) {
			this.s = s;
			this.m = m;
		}
	}
	static int n,k;
	static Jump[] jumpArr;
	static int dp[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		dp = new int[n+1][2];
		for(int i=0; i<n+1; i++) {			
			Arrays.fill(dp[i], 100000);
		}
		jumpArr = new Jump[n-1];
		
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			jumpArr[i] = new Jump(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		k = Integer.parseInt(br.readLine());
		
		dp[1][0] = 0; dp[1][1] = 0;
		for(int i=1; i<=n; i++) {
			// 슈퍼 점프 사용 안했을 때
			if(i+1<=n) {
				dp[i+1][0] = Math.min(dp[i+1][0],dp[i][0]+jumpArr[i-1].s);
			}
			if(i+2<=n) {
				dp[i+2][0] = Math.min(dp[i+2][0],dp[i][0]+jumpArr[i-1].m);
			}
			if(i+3<=n) {
				dp[i+3][1] = Math.min(dp[i+3][1],dp[i][0]+k);
			}
			
			// 슈퍼 점프 사용 했을 때
			if(i+1<=n && i+1>3) {
				dp[i+1][1] = Math.min(dp[i+1][1],dp[i][1]+jumpArr[i-1].s);
			}
			if(i+2<=n && i+2>3) {
				dp[i+2][1] = Math.min(dp[i+2][1],dp[i][1]+jumpArr[i-1].m);
			}
		}
		
		System.out.print(Math.min(dp[n][0], dp[n][1]));
	}
	
}
