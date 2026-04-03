import java.util.*;
import java.io.*;

public class Main {
	static int n, k;
	static int[] a, b;
	static int[][] c;

	// dp(i, j) = (i, i + 1)번째 돌 사이를 뛰어 넘으려고 한다. j는 매우 큰 점프를 했는지 여부.
	static int dp(int i, int j) {
		if (i == n - 1) {
			return 0;
		}
		if (c[j][i] != -1) {
			return c[j][i];
		}
		// 1. 작은 점프
		int min = a[i] + dp(i + 1, j);
		// 2. 큰 점프
		if (i + 2 < n) {
			min = Math.min(min, b[i] + dp(i + 2, j));
		}
		// 3. 매우 큰 점프
		if (i + 3 < n && j == 0) {
			min = Math.min(min, k + dp(i + 3, 1));
		}
		return c[j][i] = min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		b = new int[n];
		c = new int[2][n];
		Arrays.fill(c[0], -1);
		Arrays.fill(c[1], -1);

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}
		k = Integer.parseInt(br.readLine());
		System.out.println(dp(0, 0));
	}
}
