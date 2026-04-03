import java.io.*;
import java.util.*;

public class Main {
	static final int inf = 100000000;
	static int n;
	static int dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        int t = 0, a, b, res = inf, maxt = 0;
        dp = new int[1001][2001];
        for(int i = 0; i <= 1000; i++)
        	for(int j = 0; j <= 2000; j++)
        		dp[i][j] = -1;
        while(n-- > 0 ) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken()) + 1000;
            b = Integer.parseInt(st.nextToken()) + 1000;
            maxt = Math.max(maxt, t);
            for(int i = a + 1; i < b; i++)
            	dp[t][i] = inf;
        }
        for(int i = 0; i <= 2000; i++)
        	res = Math.min(res, sol(maxt, i));
        
        if(res == inf) System.out.println(-1);
        else System.out.println(res);
    }
    
    static int sol(int t, int loc) {
    	if(loc < 0 || loc > 2000) return inf;
    	if(t == 0) {
    		if(loc == 1000) return 0;
    		return inf; // 불가능
    	}
    	if(dp[t][loc] >= 0) return dp[t][loc];
    	// 정지, 왼쪽, 오른쪽
    	dp[t][loc] = sol(t - 1, loc);
    	dp[t][loc] = Math.min(dp[t][loc], sol(t - 1, loc - 1) + 1);
    	dp[t][loc] = Math.min(dp[t][loc], sol(t - 1, loc + 1) + 1);
    	return dp[t][loc];
    }
}
