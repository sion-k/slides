import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node>{
		int s;
		int e;
		int w;

		public Node(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node n) {
			return Integer.compare(this.w, n.w);
		}
	}

	static int n, m, s, ans;
	static Node[] cities;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		cities = new Node[m];
		parent = new int[n+1];
		Arrays.fill(parent, -1);
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			cities[i] = new Node(start,end,w);
		}
		st = new StringTokenizer(br.readLine());
		
		Arrays.sort(cities);

		for(int i=0; i<m; i++) {
			Node cur = cities[i];
			if(find(cur.s)!=find(cur.e)) {
				union(cur.s,cur.e);
				ans+=cur.w;
			}
		}
		System.out.print(ans);
	}
	
	static int find(int n) {
		if(parent[n]<0) return n;
		parent[n] = find(parent[n]);
		return parent[n];
	}
	
	static void union(int a, int b) {
		int parentA = find(a); int parentB = find(b);
		if(parent[parentA]<=parent[parentB]) { // parentA가 크기가 더 큰 집합 대표
			parent[parentA]+=parent[parentB];
			parent[parentB] = parentA;
		}
		else {
			parent[parentB]+=parent[parentA];
			parent[parentA] = parentB;	
		}
	}
	
}