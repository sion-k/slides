import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int ary[], dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        ary = new int[n];
        dp = new int[n][n];
        for(int i = 0; i < n; i++)
        	ary[i] = Integer.parseInt(br.readLine());
        
        System.out.println(sol(0, n - 1, 1));
    }
    
    static int sol(int l, int r, int k) {
    	if(l == r) return k * ary[l];
    	if(dp[l][r] > 0) return dp[l][r];
    	dp[l][r] = Math.max(sol(l + 1, r, k + 1) + ary[l] * k, sol(l, r - 1, k + 1) + ary[r] * k);
    	return dp[l][r];
    }    
}
