package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P24116 {
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N, M, K;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] lines = new int[M][3];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			lines[m][0] = Integer.parseInt(st.nextToken());
			lines[m][1] = Integer.parseInt(st.nextToken());
			lines[m][2] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(lines, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) parent[i] = i;
		int costSum = 0;
		int costCnt = N;
		for (int[] line : lines) {
			if (costCnt == K) break;
			if (find(line[0]) != find(line[1])) {
				union(line[0], line[1]);
				costSum += line[2];
				costCnt--;
			}
		}
		
		System.out.println(costSum);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

        if (a == b) return;
        
		if (a > b) parent[a] = b;
		else parent[b] = a;
	}

	private static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
}
