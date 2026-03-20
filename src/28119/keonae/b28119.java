import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M, S;
	static int[][] edge;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		edge = new int[M][3];

		for (int i = 0; i < M; i++) {
		    st = new StringTokenizer(br.readLine());
		    edge[i][0] = Integer.parseInt(st.nextToken());
		    edge[i][1] = Integer.parseInt(st.nextToken());
		    edge[i][2] = Integer.parseInt(st.nextToken());
		}
		
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		Arrays.sort(edge, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		int total = 0, count = 0;
		for (int[] e : edge) {
			int u = e[0], v = e[1], w = e[2];
			
			if (find(u) != find(v)) {
				union(u, v);
				total += w;
			}
			
			if (count == N) break;
		}
		
		System.out.println(total);
		
	}
	
	static int find(int n) {
		if (parent[n] == n) return n;
		
		return parent[n] = find(parent[n]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
		}
	}
}
