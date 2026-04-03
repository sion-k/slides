import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] a;
	static int[][] c;

	static int dp(int t, int x) {
		if (t == 1001) {
			return 0;
		}
		if (c[t][x] != -1) {
			return c[t][x];
		}

		int l = a[t][0];
		int r = a[t][1];
		if (l < x && x < r) {
			// 정답이 될 수 없는 값을 반환
			return c[t][x] = (int) 1e9;
		}

		// 1. 가만히 있기
		int min = dp(t + 1, x);
		// 2. 왼쪽으로 이동
		if (x - 1 >= 0) {
			min = Math.min(min, 1 + dp(t + 1, x - 1));
		}
		// 3. 오른쪽으로 이동
		if (x + 1 <= 2000) {
			min = Math.min(min, 1 + dp(t + 1, x + 1));
		}

		return c[t][x] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		a = new int[1001][2];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			a[t][0] = l + 1000;
			a[t][1] = r + 1000;
		}

		c = new int[1001][2001];
		for (int i = 0; i <= 1000; i++) {
			Arrays.fill(c[i], -1);
		}

		int r = dp(0, 1000);
		if (r == (int) 1e9) {
			System.out.println(-1);
		} else {
			System.out.println(r);
		}
	}
}
