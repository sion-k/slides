import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_22971 {
    static int N;
    static int[] A;
    static long[] memo;
    static final int MOD = 998244353;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N];
        memo = new long[N];
        Arrays.fill(memo, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(solve(i)).append(" ");
        }
        System.out.println(sb);
    }

    private static long solve(int i) {

        if (memo[i] != -1)
            return memo[i];

        long count = 1;
        for (int j = 0; j < i; j++) {
            if (A[j] < A[i]) {
                count = (count + solve(j)) % MOD;
            }
        }

        return memo[i] = count;
    }
}
