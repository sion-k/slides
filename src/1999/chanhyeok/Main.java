import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static int ary[][], max[][], min[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		ary = new int[n][n];
		max = new int[n][n];
		min = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				ary[i][j] = Integer.parseInt(st.nextToken());
				max[i][j] = -1;
				min[i][j] = 260;
			}
		}
		int a, b;
		while(k-- > 0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			if(max[a][b] == -1) {
				for(int i = a; i < a + m; i++)
					for(int j = b; j < b + m; j++)
						max[a][b] = Math.max(max[a][b], ary[i][j]);
			}
			if(min[a][b] == 260) {
				for(int i = a; i < a + m; i++)
					for(int j = b; j < b + m; j++)
						min[a][b] = Math.min(min[a][b], ary[i][j]);
			}
			sb.append(max[a][b] - min[a][b]).append("\n");
		}
		System.out.print(sb);
	}
}
