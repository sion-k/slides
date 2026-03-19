package algo_test;

import java.io.*;
import java.util.*;

class Main {
	static int[] parent;
	
	static int find(int a) {
		if(parent[a]==a)
			return a;
		return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		int ap = find(a);
		int bp = find(b);
		parent[bp] = ap;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		for(int i=1; i<=n; i++) 
			parent[i] = i;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[2], o2[2]));
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.add(new int[]{u,v,w});
		}
		
		int connectNum = 0;
		int ans=0;
		while(connectNum != n-1) {
			int[] c = pq.poll();
			if(find(c[0])!=find(c[1])) {
				union(c[0], c[1]);
				connectNum++;
				ans+=c[2];
			}
		}

		System.out.println(ans);
	}
}
