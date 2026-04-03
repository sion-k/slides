import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static boolean[][] a;
	static long[][] c;

	// dp(x, y) = (x, y)에서 시작해서 최대한 먹을 수 있는 사탕의 양
	static long dp(int x, int y) {
		if (c[x][y] != -1) {
			return c[x][y];
		}
		long max = 0;
		if (x + 1 <= 300) {
			max = Math.max(max, dp(x + 1, y));
		}
		if (y + 1 <= 300) {
			max = Math.max(max, dp(x, y + 1));
		}
		// x + 1, y + 1방향으로만 이동 가능하기 때문에 현재 시간 t = x + y
		long candy = a[x][y] ? Math.max(m - (x + y), 0) : 0;
		return c[x][y] = candy + max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		a = new boolean[301][301];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			a[x][y] = true;
		}

		// 메모이제이션은 문제의 정답이 될 수 없는 값으로 초기화해야 한다.
		c = new long[301][301];
		for (int x = 0; x <= 300; x++) {
			Arrays.fill(c[x], -1);
		}

		System.out.println(dp(0, 0));
	}
}
