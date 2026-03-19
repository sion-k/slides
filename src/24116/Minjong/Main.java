package BOJ24116;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M, K;
	static int[] roots;
	static class Road {
		int num1;
		int num2;
		int cost;
		public Road(int num1, int num2, int cost) {
			this.num1 = num1;
			this.num2 = num2;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		roots = new int[N+1];
		for(int i=1; i<=N; i++) {
			roots[i] = i;
		}
		PriorityQueue<Road> pq = new PriorityQueue<>((r1, r2) -> r1.cost - r2.cost);
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			pq.offer(new Road(A, B, C));
		}
		int sum = 0;
		int roadCount = 0;
		int cityCount = N;
		while(!pq.isEmpty()) {
			if(cityCount == K) {
				break;
			}
			Road r = pq.poll();
			if(union(r.num1, r.num2)) {
				sum += r.cost;
				roadCount++;
				cityCount--;
			}
		}
		bw.write(sum + "\n");
		bw.flush();
	}
	public static boolean union(int num1, int num2) {
		if(find(num1) == find(num2)) {
			return false;
		}
		roots[find(num1)] = find(num2);
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
