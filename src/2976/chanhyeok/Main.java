import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int ary[], dp[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n][n];
        ary = new int[n];
        for(int i = 0; i < n; i++)
        	ary[i] = Integer.parseInt(br.readLine());
        int res = 1000000000;
        for(int i = 1; i < n; i++)
        	res = Math.min(res, sol(n - 1, i));
        System.out.print(res);
    }
    
    static int sol(int idx, int jump) {
    	if(jump == 1) {
    		if(idx == 1)
    			return ary[1];
    		if(idx == 0)
    			return ary[1] + ary[0];
    		return 1000000000;
    	}
    	if(dp[idx][jump] > 0) return dp[idx][jump];
    	dp[idx][jump] = 1000000000;
    	
    	// 1. 이전에서 왔던가
    	if(idx - jump >= 0)
    		dp[idx][jump] = Math.min(dp[idx][jump], sol(idx - jump, jump - 1));
    	// 2. 이후에서 왔던가
    	if(idx + jump < n)
    		dp[idx][jump] = Math.min(dp[idx][jump], sol(idx + jump, jump));
    	dp[idx][jump] += ary[idx];
    	
    	return dp[idx][jump];
    }
}
