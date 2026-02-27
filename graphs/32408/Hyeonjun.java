import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Hyeonjun {
    static int N, l1Cnt;
    static List<Integer>[] connected;
    static boolean[] l1Visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        connected = new ArrayList[N];
        for (int i = 0; i < N; i++)
            connected[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            connected[from - 1].add(to - 1);
            connected[to - 1].add(from - 1);
        }

        // 1호선 역 받아오기
        l1Cnt = 0;
        boolean[] isL1Stations = getL1Stations();
        l1Visited = new boolean[N];
        System.arraycopy(isL1Stations, 0, l1Visited, 0, N);

        long l2Cnt = N - l1Cnt;
        long result = (l2Cnt * (l2Cnt - 1)) / 2;
        for (int i = 0; i < N; i++) {
            if (!isL1Stations[i])
                continue;

            for (int c : connected[i]) { // 연결된 정점으로 부터 정점 개수 탐색
                if (isL1Stations[c])
                    continue; // 1호선은 계산 x

                // 정점 개수 계산
                long groupCnt = getStationCnt(c);
                result -= (groupCnt * (groupCnt - 1)) / 2;
            }
        }

        System.out.println(result);
    }

    static boolean[] getL1Stations() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int[] parent = new int[N];
        boolean[] visited = new boolean[N];

        q.offer(0); // 0 ~ N - 1까지 경로 찾기
        parent[0] = -1;
        visited[0] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == N - 1)
                break; // N 까지의 경로만 찾으면 됨

            for (int c : connected[now]) {
                if (visited[c])
                    continue;
                visited[c] = true;
                parent[c] = now;
                q.offer(c);
            }
        }

        boolean[] isL1Stations = new boolean[N];
        int cur = N - 1; // N 부터 1까지 경로 체크
        while (cur != -1) {
            isL1Stations[cur] = true;
            cur = parent[cur];
            l1Cnt++;
        }

        return isL1Stations;
    }

    static int getStationCnt(int strNode) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        l1Visited[strNode] = true;
        q.offer(strNode);

        int stationCnt = 1;
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int c : connected[now]) {
                if (l1Visited[c])
                    continue;
                l1Visited[c] = true;
                q.offer(c);
                stationCnt++;
            }
        }

        return stationCnt;
    }

    /*
     * N개 교차로(정점), N-1개 도로(간선)
     * 
     * 1호선은 1부터 N까지를 연결하는 간선 (지나는 모든 정점에는 역이 있음)
     * 
     * S, E 정점(2호선)에 역 설치. 단, 1호선 역이 설치되어 있으면 안됨
     * └ 1호선 역이 있는 곳을 한번 이상 지나가야함
     * > 조건을 만족하는 S, E가 나오는 경우의 수 구하기(S, E 순서를 바꿔도 동일한 경우로 취급)
     * 
     * 1부터 N까지 연결하면서 각각 역에서부터 연결된 모든 정점을 탐색?
     * or 1부터 N위치까지 의 역을 담아두고 각 역에서 출발하는 노드들을 모두 하나의 집합으로 본다.
     * > 특정역으로 부터 연결된 그래프를 하나의 집합으로 생각한다.
     * 서로다른 집합에서 2개의 정점을 뽑을 경우의 수를 계산한다.
     * └ 집합 서로서로 다 비교하기 보단, 모든 경우의 수(1호선 정점 제외)에서 각 집합에서의 경우의 수를 빼주자
     */
}
