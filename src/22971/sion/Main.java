import java.util.*;
import java.io.*;

public class Main {
	static final int mod = 998244353;

	static int n;
	static int[] a, c;

	// dp(i) = a_i에서 끝나는 LIS의 개수
	static int dp(int i) {
		if (c[i] > 0) {
			return c[i];
		}
		int sum = 1;
		for (int j = 0; j < i; j++) {
			if (a[j] < a[i]) {
				sum = (sum + dp(j)) % mod;
			}
		}
		return c[i] = sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		c = new int[n];
		for (int i = 0; i < n; i++) {
			bw.append(dp(i));
			if (i == n - 1) {
				bw.append('\n');
			} else {
				bw.append(' ');
			}
		}
		System.out.print(bw);
	}

}
