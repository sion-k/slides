package BOJ14585;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[301][301];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = M;
		}
		int[][] dp = new int[301][301];
		for(int x=0; x<=300; x++) {
			for(int y=0; y<=300; y++) {
				if(x == 0 && y == 0) {
					continue;
				}
				int candy = (map[x][y] > x + y) ? M - x - y : 0;
				if(x == 0) {
					dp[x][y] = dp[x][y-1] + candy;
				} else if(y == 0) {
					dp[x][y] = dp[x-1][y] + candy;
				} else {
					dp[x][y] = Math.max(dp[x-1][y], dp[x][y-1]) + candy;
				}
			}
		}
		int answer = dp[300][300];
		bw.write(answer + "\n");
		bw.flush();
	}
}
