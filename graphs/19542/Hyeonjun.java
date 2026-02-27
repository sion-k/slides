import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hyeonjun {
    static int N, S, D;
    static List<Integer>[] graph;
    static int[] distance;
    static Queue<Integer> leafNodes;
    static boolean[] bikeVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 기본값 입력 받기
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 간선 정보 입력 받기
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from - 1].add(to - 1);
            graph[to - 1].add(from - 1);
        }

        distance = new int[N]; // 회사의 위치로부터 떨어져 있는 거리 저장
        leafNodes = new ArrayDeque<>();
        markDistances(S - 1, 0, new boolean[N]);

        int result = 0;
        bikeVisited = new boolean[N];
        while (!leafNodes.isEmpty()) {
            int now = leafNodes.poll();
            result += getDistance(now);
        }

        System.out.println(result * 2);
    }

    static void markDistances(int pos, int dist, boolean[] visited) {
        visited[pos] = true;
        distance[pos] = dist;

        boolean hasChild = false;
        for (int g : graph[pos]) {
            if (visited[g])
                continue;
            hasChild = true;
            markDistances(g, dist + 1, visited);
        }

        if (!hasChild)
            leafNodes.offer(pos);
    }

    static int getDistance(int start) {
        int target = -1;

        int now = start;
        int dist = 0; // start(리프)에서 now까지의 거리

        while (distance[now] > 0) { // 회사까지 최단경로 이동
            if (dist >= D) { // 현민이가 방문 할 필요 없는건 스킵
                if (bikeVisited[now]) {
                    if (target == -1)
                        return 0;
                    target -= distance[now];
                    break;
                }
                if (target == -1)
                    target = distance[now];
                bikeVisited[now] = true;
            }

            for (int g : graph[now]) { // 부모로 1칸 이동
                if (distance[g] == distance[now] - 1) {
                    now = g;
                    break;
                }
            }

            dist++;
        }

        return (target == -1) ? 0 : target;
    }

    /*
     * 입력 받을때 연결된 간선이 하나밖에 없다면 끝 노드 임으로 큐에 저장
     * 
     * 1. 출발점으로 부터의 모든 노드의 깊이 기록
     * 2. 끝노드를 큐에서 하나씩 꺼내면서 그래프 역탐색
     * BFS로 역탐색 할 때, 간선개수 V^2면 시간초과 될 듯..
     */
}
