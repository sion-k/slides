import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jihoon {

    static int N, M, Q, res;
    static ArrayList<Integer>[] adjList;
    static int[][] dist;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 정점의 개수 [5, 2,000]
        M = Integer.parseInt(st.nextToken()); // 간선의 개수 [0, 4,000]
        Q = Integer.parseInt(st.nextToken()); // 질의의 개수 [1, 1,000]

        // adjList init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // input
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
        }

        // dist init
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], -1);
        }

        // dist bfs, O(N(V+E))
        for (int j = 1; j <= N; j++) {
            bfs(j);
        }

        // query, O(Q*N)
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            res = Integer.MAX_VALUE;
            boolean found = false;
            for (int j = 1; j <= N; j++) {
                if (dist[j][a] == -1 || dist[j][b] == -1)
                    continue;
                int temp = Math.max(dist[j][a], dist[j][b]);
                res = Math.min(res, temp);
                found = true;
            }

            if (!found)
                sb.append("-1\n");
            else
                sb.append(res).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(start);
        dist[start][start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adjList[cur]) {
                if (dist[start][next] == -1) {
                    dist[start][next] = dist[start][cur] + 1;
                    q.offer(next);
                }
            }
        }
    }
}