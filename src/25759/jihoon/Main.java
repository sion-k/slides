import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_25759 {

    static int N;
    static int[] flowers;
    static int[][] memo;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        memo = new int[N + 1][101]; // [index][flower_value]
        for (int i = 1; i <= N; i++) {
            Arrays.fill(memo[i], -1);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        flowers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            flowers[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(1, 1));
    }

    /* index: 현재 위치, prev: 마지막으로 딴 꽃의 위치 */
    private static int solve(int index, int prev) {
        if (index > N)
            return 0;

        int prev_val = flowers[prev]; // 마지막으로 딴 꽃의 아름다움
        if (memo[index][prev_val] != -1)
            return memo[index][prev_val];

        int abs_val = Math.abs(prev_val - flowers[index]);
        int val = (int) Math.pow(abs_val, 2);

        // 1. 꽃을 따는 경우
        int pick = val + solve(index + 1, index);
        // 2. 꽃을 따지 않는 경우
        int pass = solve(index + 1, prev);

        return memo[index][prev_val] = Math.max(pick, pass);
    }

}
