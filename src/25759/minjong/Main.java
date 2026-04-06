package BOJ25759;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] flowers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		flowers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			flowers[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[101];
		HashSet<Integer> hs = new HashSet<>();
		hs.add(flowers[0]);
		for(int i=1; i<N; i++) {
			int next = flowers[i];
			for(int current : hs) {
				dp[next] = Math.max(dp[next], dp[current] + difference(current, next));
			}
			hs.add(next);
		}
		int answer = dp[flowers[N-1]];
		bw.write(answer + "\n");
		bw.flush();
	}
	public static int difference(int a, int b) {
		return (b-a) * (b-a);
	}
}
