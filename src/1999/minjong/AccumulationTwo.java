package BOJ1999;

import java.util.*;
import java.io.*;

public class AccumulationTwo {
	static int N, B, K;
	static int[][] arr;
	static int[][] horizontalArray;
	static int[][] verticalArray;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		horizontalArray = new int[N][N-B+1];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N-B+1; c++) {
				int max = 0;
				int min = 250;
				for(int d=0; d<B; d++) {
					max = Math.max(max, arr[r][c+d]);
					min = Math.min(min, arr[r][c+d]);
				}
				horizontalArray[r][c] = max * 1000 + min;
			}
		}
		verticalArray = new int[N-B+1][N-B+1];
		for(int r=0; r<N-B+1; r++) {
			for(int c=0; c<N-B+1; c++) {
				int max = 0;
				int min = 250;
				for(int d=0; d<B; d++) {
					int value = horizontalArray[r+d][c];
					int maxV = value / 1000;
					int minV = value % 1000;
					max = Math.max(max, maxV);
					min = Math.min(min, minV);
					verticalArray[r][c] = max * 1000 + min;
				}
			}
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int value = verticalArray[r][c];
			sb.append(value / 1000 - value % 1000).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	public static boolean checkRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
