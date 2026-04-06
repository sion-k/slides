package BOJ25759;

import java.util.*;
import java.io.*;

public class BaseCase2 {
	static int N;
	static int[] flowers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		flowers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			flowers[i] = Integer.parseInt(st.nextToken());
		}
		List<Integer> list = new ArrayList<>();
		list.add(flowers[0]);
		int index = 1;
		for(int i=1; i<N; i++) {
			int flower = flowers[index++];
			if(flower != flowers[0]) {
				list.add(flower);
				break;
			}
		}
		for(int i=index; i<N; i++) {
			int flower = flowers[i];
			int prev1 = list.get(list.size() - 2);
			int prev2 = list.get(list.size() - 1);
			if(prev2 == flower) {
				continue;
			}
			if((prev1-prev2) * (prev2-flower) > 0) {
				list.set(list.size() - 1, flower);
			} else {
				list.add(flower);
			}
		}
		int[] dp = new int[list.size()];
		dp[0] = 0;
		dp[1] = difference(0, 1, list);
		for(int i=2; i<dp.length; i++) {
			int max = 0;
			for(int j=0; j<i; j++) {
				max = Math.max(max, dp[j] + difference(j, i, list));				
			}
			dp[i] = max;
		}
		int answer = dp[dp.length - 1];
		bw.write(answer + "\n");
		bw.flush();
	}
	public static int difference(int a, int b, List<Integer> list) {
		return (list.get(b) - list.get(a)) * (list.get(b) - list.get(a));
	}
}
