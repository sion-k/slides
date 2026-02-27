import java.util.*;
import java.io.*;

public class Keonae {

    static int N, M, q; // 정점, 간선, 쿼리
    static List<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        dist = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
        }

        // 거리 계산
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            Arrays.fill(dist[i], -1);
            cal(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= N; j++) {
                if (dist[j][u] != -1 && dist[j][v] != -1) {
                    min = Math.min(min, Math.max(dist[j][u], dist[j][v]));
                }
            }

            sb.append(min == Integer.MAX_VALUE ? -1 : min).append("\n");
        }

        System.out.println(sb);
    }

    static void cal(int start) {
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start][start] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    dist[start][next] = dist[start][cur] + 1;
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

    }

}