import java.io.*;
import java.util.*;

public class Main {
	static int p[];
	static int n, m, s;
	
	static int find(int v) {
		if(p[v] == 0) return v;
		return p[v] = find(p[v]);
	}
	
	static void merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return;
		p[a] = b;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = new int[n + 1];
		int res = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		int cnt = 1;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(find(node.s) == find(node.e)) continue;
			merge(node.s, node.e);
			cnt++;
			res += node.w;
			if(cnt == n) break;
		}
		System.out.println(res);
	}
}

class Node implements Comparable<Node> {
	int s;
	int e;
	int w;
	
	public Node(int s, int e, int w) {
		this.s = s;
		this.e = e;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.w, o.w);
	}
}
