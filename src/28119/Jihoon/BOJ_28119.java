package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_28119 {

	static boolean[] visited;
	static ArrayList<ArrayList<Edge>> edgeList;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		edgeList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			edgeList.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edgeList.get(u).add(new Edge(v, w));
			edgeList.get(v).add(new Edge(u, w));
		}

		st = new StringTokenizer(br.readLine());
		int[] route = new int[N];
		for (int i = 0; i < N; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}

		visited = new boolean[N + 1];
		int res = search(S, N);

		System.out.println(res);
	}

	public static int search(int start, int nodeCount) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		pq.offer(new Edge(start, 0));
		int totalWeight = 0;
		int count = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (visited[cur.to])
				continue;

			visited[cur.to] = true;
			totalWeight += cur.weight;
			count++;

			if (count == nodeCount)
				break;

			for (Edge next : edgeList.get(cur.to)) {
				if (!visited[next.to]) {
					pq.offer(next);
				}
			}
		}

		return totalWeight;
	}

	static class Edge implements Comparable<Edge> {
		int to, weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}
