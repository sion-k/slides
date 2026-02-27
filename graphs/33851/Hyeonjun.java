import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Hyeonjun {
    static int N, M;
    static List<Integer>[] connected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // 입력 받기
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 정점의 수
        M = Integer.parseInt(st.nextToken()); // 간선의 수
        int Q = Integer.parseInt(st.nextToken()); // 입력 받을 쿼리의 수

        // 간선 정보를 가진 리스트 초기화
        connected = new ArrayList[N];
        for (int i = 0; i < N; i++)
            connected[i] = new ArrayList<>();
        // 간선 입력 받기
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int nodeFrom = Integer.parseInt(st.nextToken());
            int nodeTo = Integer.parseInt(st.nextToken());
            connected[nodeTo - 1].add(nodeFrom - 1);
        }

        // 쿼리 입력 받고 처리
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            sb.append(getResult(u - 1, v - 1)).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int getResult(int u, int v) {
        int result = Integer.MAX_VALUE;

        int[] uNodes = getAccessibleNodes(u);
        int[] vNodes = getAccessibleNodes(v);

        for (int i = 0; i < N; i++) {
            if (uNodes[i] <= 0 || vNodes[i] <= 0)
                continue;
            int len = Math.max(uNodes[i], vNodes[i]) - 1;
            result = Math.min(result, len);
        }

        return (result != Integer.MAX_VALUE ? result : -1);
    }

    static int[] getAccessibleNodes(int from) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] dist = new int[N];

        q.offer(from);
        dist[from] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            int len = dist[now] + 1;

            for (int c : connected[now]) {
                // BFS는 최단거리! > 처음 방문만 체크하도록 변경
                if (dist[c] != 0)
                    continue;
                dist[c] = len;
                q.offer(c);
            }
        }

        return dist;
    }

    /*
     * 각 정점이 어디로 연결되었는지 정보 입력 받음
     * u나 v 둘다 도달할 수 있는 정점들의 거리의 최댓값들을 구한다
     * ㄴ 이 최대값중에 최솟값을 구한다
     * 
     * 두 정점으로부터 방문 가능한 정점을 하나씩 체크하면서 이동.
     * int 배열에 거리 저장 (초기값 -1. 0보다 같거나 크면 연결됨)
     * ㄴ u, v 배열 둘다 0보다 같거나 큰 값 가지고 있는 정점 = 둘다 연결된 정점
     * ㄴ 둘중 큰값 가져옴
     * ㄴ 큰 값 중에 최솟값 찾음
     */
}
