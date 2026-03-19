package BOJ28119;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M, S;
	static class Connection {
		int num1;
		int num2;
		int time;
		public Connection(int num1, int num2, int time) {
			this.num1 = num1;
			this.num2 = num2;
			this.time = time;
		}
	}
	static int[] roots;
	static int distanceSum;
	static int roadCount;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		roots = new int[N+1];
		for(int i=1; i<=N; i++) {
			roots[i] = i;
		}
		distanceSum = 0;
		roadCount = 0;
		PriorityQueue<Connection> pq = new PriorityQueue<>((c1, c2) -> (c1.time - c2.time));
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.offer(new Connection(u, v, w));
		}
		while(!pq.isEmpty()) {
			if(roadCount == N-1) {
				break;
			}
			Connection c = pq.poll();
			int num1 = c.num1;
			int num2 = c.num2;
			if(union(num1, num2)) {
				distanceSum += c.time;
				roadCount++;
			}
		}
		bw.write(distanceSum + "\n");
		bw.flush();
	}
	public static boolean union(int num1, int num2) {
		if(find(num1) == find(num2)) {
			return false;
		}
		roots[find(num1)] = roots[num2];
		return true;
	}
	public static int find(int num) {
		List<Integer> list = new ArrayList<>();
		while(roots[num] != num) {
			list.add(num);
			num = roots[num];
		}
		for(int n : list) {
			roots[n] = num;
		}
		return num;
	}
}
