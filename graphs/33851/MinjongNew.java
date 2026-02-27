import java.util.*;
import java.io.*;

public class MinjongNew {
    static int n, m, q;
    static List<Integer>[] connectedFrom;
    static Integer[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        connectedFrom = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            connectedFrom[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            connectedFrom[v].add(u);
        }
        memo = new Integer[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            BFS(i);
        }
        for (int i = 0; i < q; i++) {
            int answer = 1000000;
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= n; j++) {
                if (memo[num1][j] != null && memo[num2][j] != null) {
                    answer = Math.min(answer, Math.max(memo[num1][j], memo[num2][j]));
                }
            }
            if (answer == 1000000) {
                answer = -1;
            }
            bw.write(answer + "\n");
        }
        bw.flush();
    }

    public static void BFS(int startNum) {
        Queue<Integer> numQueue = new LinkedList<>();
        Queue<Integer> distQueue = new LinkedList<>();
        numQueue.offer(startNum);
        distQueue.offer(0);
        while (!numQueue.isEmpty()) {
            int current = numQueue.poll();
            int distance = distQueue.poll();
            if (memo[startNum][current] == null) {
                memo[startNum][current] = distance;
            } else {
                memo[startNum][current] = Math.min(memo[startNum][current], distance);
            }
            for (int next : connectedFrom[current]) {
                numQueue.offer(next);
                distQueue.offer(distance + 1);
            }
        }
    }
}
