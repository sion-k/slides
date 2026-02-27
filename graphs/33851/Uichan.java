import java.io.*;
import java.util.*;

class Uichan {
    static int[][] distance;
    static List<Integer>[] parent;
    static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가장가까운 공통 부모찾아서 간선합 긴쪽 출력

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        distance = new int[n + 1][n + 1]; // idx1(자식)->idx2(부모) 거리
        parent = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            parent[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            parent[to].add(from);
        }

        for (int i = 1; i <= n; i++) {
            getDistance(i, i, 0);
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int answer = Integer.MAX_VALUE;
            for (int p = 1; p <= n; p++) {
                // 한쪽이라도 p가 부모가 아닌경우 continue
                if (distance[u][p] == Integer.MAX_VALUE || distance[v][p] == Integer.MAX_VALUE)
                    continue;
                int maxDist = Math.max(distance[u][p], distance[v][p]);
                answer = Math.min(answer, maxDist);
            }
            sb.append(answer == Integer.MAX_VALUE ? -1 : answer).append("\n");
        }

        System.out.println(sb);
    }

    static void getDistance(int start, int current, int depth) {
        distance[start][current] = Math.min(distance[start][current], depth);

        for (int parent : parent[current]) {
            getDistance(start, parent, depth + 1);
        }
    }
}
