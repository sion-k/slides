import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj32198 {
	
	static int[][] dp;
	static int N;
	
	static ArrayList<Bomb> bombs = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; ++i) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			bombs.add(new Bomb(
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken())));
			
			
		}
		
		bombs.sort(new Comparator<Bomb>() {

			@Override
			public int compare(Bomb o1, Bomb o2) {
				return o1.T - o2.T;
			}
		});
		
		int max = bombs.get(bombs.size()-1).T;
		
		int size = max*2+1;
		
		int offset = size/2;
		
		dp = new int[N+1][size];
		int INF = (int)1e9;
		for(int i = 0 ; i <= N; ++i) {			
			Arrays.fill(dp[i], INF);
		}
		dp[0][offset] = 0; // 출발점 0으로 초기화
		
		int time = 0;
		int idx = 1;
		for(Bomb b : bombs) {
			int dt = b.T - time;
			time = b.T;
			
			for(int i = 0; i < size; ++i) {
				if(dp[idx-1][i] != INF) { // INF가 아니란 것은 출발점으로 할당 가능
//					dp[idx][i] = dp[idx-1][i]; // 이 위치는 그대로니까 ㅇㅇ
					for(int move = 0; move <= dt; ++move) {
						// 새로 이동한 것 vs 기존에 계산된 값
						int leftRange = b.A + offset;
						int rightRange = b.B + offset;
						if(i+move >= rightRange || i+move <= leftRange)
							dp[idx][i+move] = Math.min(dp[idx][i+move], dp[idx-1][i] + move);
						if(i-move <= leftRange || i-move >= rightRange)
							dp[idx][i-move] = Math.min(dp[idx][i-move], dp[idx-1][i] + move);
					}
				}
			}	
//			print(idx);
			idx++;
		}
		
		int min = INF;
		for(int i : dp[N]) {
			min = Math.min(min, i);
		}
		
		System.out.println(min == INF ? -1 : min);
		
		
		

		
	}
	
	static class Bomb{
		int T,A,B;

		public Bomb(int t, int a, int b) {
			super();
			T = t;
			A = a;
			B = b;
		}
		
	}
	
	static void print(int idx) {
		for(int i : dp[idx]) System.out.print(i + " ");
		System.out.println();
	}
}
