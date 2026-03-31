package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1999 {
	static int N, B, K;
	static int[][] map;
	static Answer[][] answer;
	
	static class Answer {
		int max, min;

		public Answer(int max, int min) {
			this.max = max;
			this.min = min;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		int range = N - B + 1;
		answer = new Answer[range][range];
		for (int r = 0; r < range; r++) {
			for (int c = 0; c < range; c++) {
				answer[r][c] = new Answer(Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
		}
		calculateAnswer();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			sb.append(answer[i][j].max - answer[i][j].min).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void calculateAnswer() {
		// 모든 i, j칸의 숫자에 대해 계산
		int range = N - B + 1;
		for (int r = 0; r < range; r++) {
			for (int c = 0; c < range; c++) {
				// B x B 범위에 대해 최소 최대값 구하기
				for (int br = 0; br < B; br++) {
					for (int bc = 0; bc < B; bc++) {
						int now = map[r + br][c + bc];
						answer[r][c].max = Math.max(answer[r][c].max, now);
						answer[r][c].min = Math.min(answer[r][c].min, now);
					}
				}
			}
		}
	}
}
