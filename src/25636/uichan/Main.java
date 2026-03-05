import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] water = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            water[i] = Integer.parseInt(st.nextToken());
        }
        List<int[]>[] nexts = new List[n + 1];
        for (int i = 1; i <= n; i++)
            nexts[i] = new ArrayList<>();
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nexts[u].add(new int[] { v, w });
            nexts[v].add(new int[] { u, w });
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        long[] maxWater = new long[n + 1];
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>(
                (o1, o2) -> o1[1] == o2[1] ? Long.compare(o2[2], o1[2]) : Long.compare(o1[1], o2[1]));
        // 도착지, 거리, 물
        pq.add(new long[] { s, 0, water[s] });

        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            int currentN = (int) current[0];
            long currentDist = (long) current[1];
            long currentWater = (long) current[2];

            if (currentWater <= maxWater[currentN])
                continue;
            maxWater[currentN] = currentWater;

            if (currentN == e) {
                System.out.println(currentDist + " " + currentWater);
                return;
            }

            for (int[] next : nexts[currentN]) {
                long nextD = currentDist + next[1];
                long nextWater = currentWater + water[next[0]];
                if (nextD <= dist[next[0]]) {
                    dist[next[0]] = nextD;
                    pq.add(new long[] { next[0], nextD, nextWater });
                }
            }
        }

        System.out.println(-1);
    }
}
