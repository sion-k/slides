import java.io.*;
import java.util.*;

public class Main {
    static class Bang implements Comparable<Bang>{
        int t;
        int a;
        int b;
        public Bang(int t, int a, int b) {
            this.t = t;
            this.a = a;
            this.b = b;
        }
        
        @Override
        public int compareTo(Bang b) {
            return Integer.compare(this.t, b.t);
        }
        
    }
    static Bang[] bangs;
    static int[][] dp = new int[100][2001];
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        bangs = new Bang[n];
        
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken())+1000;
            int b = Integer.parseInt(st.nextToken())+1000;
            bangs[i] = new Bang(t,a,b);
        }
        Arrays.sort(bangs);
        
        for(int i=0; i<100; i++) {
            Arrays.fill(dp[i],-1);
        }
        
        int ans = go(0,1000);
        System.out.print(ans==2000001?-1:ans);
    }
    
    static int go(int ac, int cur) {
        if(ac>=n) {
            return 0;
        }
        
        if(dp[ac][cur]!=-1) { // 갱신 했다는 것
            return dp[ac][cur];
        }
        
        int nt = bangs[ac].t; int bt = ac==0?0:bangs[ac-1].t;
        int a = bangs[ac].a; int b = bangs[ac].b;
        
        int minV = 2000001;
        for(int i=cur-(nt-bt); i<=cur+(nt-bt); i++) {
            if(i<=a || i>=b) {
            	minV = Math.min(minV, go(ac+1,i)+Math.abs(cur-i));
            }
        }
        dp[ac][cur] = minV;
        return dp[ac][cur];
    }
}
