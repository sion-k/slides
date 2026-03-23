import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static int max[][], min[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		max = new int[n][n];
		min = new int[n][n];
		int a, b;
		ArrayDeque<Node> maxdq, mindq;
		maxdq = new ArrayDeque<>();
		mindq = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			maxdq.clear();
			mindq.clear();
			for(int j = 0; j < n; j++) {
				a = Integer.parseInt(st.nextToken());
				while(!maxdq.isEmpty() && maxdq.peek().j + m <= j) maxdq.poll();
				while(!mindq.isEmpty() && mindq.peek().j + m <= j) mindq.poll();
				// max갱신
				Node node = new Node(a, i, j);
				if(maxdq.isEmpty())
					maxdq.add(node);
				else {
					if(maxdq.peek().val <= a)
						maxdq.push(node);
					else {
						while(maxdq.peekLast().val <= a)
							maxdq.pollLast();
						maxdq.add(node);
					}
				}
				// min 갱신
				if(mindq.isEmpty())
					mindq.add(node);
				else {
					if(mindq.peek().val >= a)
						mindq.push(node);
					else {
						while(mindq.peekLast().val >= a)
							mindq.pollLast();
						mindq.add(node);
					}
				}
				max[i][j] = maxdq.peek().val;
				min[i][j] = mindq.peek().val;
			}
		}
		for(int j = 0; j < n; j++) {
			maxdq.clear();
			mindq.clear();
			for(int i = 0; i < n; i++) {
				while(!maxdq.isEmpty() && maxdq.peek().i + m <= i) maxdq.poll();
				while(!mindq.isEmpty() && mindq.peek().i + m <= i) mindq.poll();
				// max갱신
				Node maxNode = new Node(max[i][j], i, j);
				Node minNode = new Node(min[i][j], i, j);
				if(maxdq.isEmpty())
					maxdq.add(maxNode);
				else {
					if(maxdq.peek().val <= max[i][j])
						maxdq.push(maxNode);
					else {
						while(maxdq.peekLast().val <= max[i][j])
							maxdq.pollLast();
						maxdq.add(maxNode);
					}
				}
				// min 갱신
				if(mindq.isEmpty())
					mindq.add(minNode);
				else {
					if(mindq.peek().val >= min[i][j])
						mindq.push(minNode);
					else {
						while(mindq.peekLast().val >= min[i][j])
							mindq.pollLast();
						mindq.add(minNode);
					}
				}
				max[i][j] = maxdq.peek().val;
				min[i][j] = mindq.peek().val;
			}
		}
		while(k-- > 0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 2 + m;
			b = Integer.parseInt(st.nextToken()) - 2 + m;
			sb.append(max[a][b] - min[a][b]).append("\n");
		}
		System.out.print(sb);
	}
}

class Node {
	int val;
	int i;
	int j;
	public Node(int val, int i, int j) {
		this.val = val;
		this.i = i;
		this.j = j;
	}
}
