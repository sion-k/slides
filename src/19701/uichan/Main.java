import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<int[]>[] nexts = new List[V + 1];
        for (int i = 1; i <= V; i++)
            nexts[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            nexts[u].add(new int[] { v, w, t });
            nexts[v].add(new int[] { u, w, t });
        }

        long[][] dist = new long[2][V + 1];
        // 이미 먹었을때 dist
        Arrays.fill(dist[0], Long.MAX_VALUE);
        // 먹기 전 dist
        Arrays.fill(dist[1], Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
        // 도착점, 거리, 먹을수있는횟수
        pq.add(new long[] { 1, 0, 1 });
        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            int currentN = (int) current[0];
            long distance = current[1];
            int chance = (int) current[2];

            if (dist[chance][currentN] < distance)
                continue;

            for (int[] next : nexts[currentN]) {
                long nextD = distance + next[1];
                if (chance == 1) {
                    if (dist[0][next[0]] > nextD - next[2]) {
                        dist[0][next[0]] = nextD - next[2];
                        pq.add(new long[] { next[0], nextD - next[2], 0 });
                    }
                }

                if (dist[chance][next[0]] > nextD) {
                    dist[chance][next[0]] = nextD;
                    pq.add(new long[] { next[0], nextD, chance });
                }
            }
        }

        for (int i = 2; i <= V; i++)
            sb.append(dist[0][i]).append("\n");

        System.out.println(sb);
    }
}
