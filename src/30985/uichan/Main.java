import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<int[]>[] nexts = new List[n + 1];
        int[] elev = new int[n + 1];

        for (int i = 1; i <= n; i++)
            nexts[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nexts[u].add(new int[] { v, w });
            nexts[v].add(new int[] { u, w });
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            elev[i] = Integer.parseInt(st.nextToken());
        }

        // 엘베타기전dist
        long[] beforeElevTime = new long[n + 1];
        // 엘베탄후dist
        long[] afterElevTime = new long[n + 1];
        Arrays.fill(beforeElevTime, Long.MAX_VALUE);
        Arrays.fill(afterElevTime, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
        pq.add(new long[] { 1, 0, 1 }); // 도착지, 걸린시간, 엘베탈수있는 기회

        while (!pq.isEmpty()) {
            long[] current = pq.poll();
            int currentN = (int) current[0];
            long currentTime = current[1];
            int chance = (int) current[2];

            if (currentN == n && chance == 0) {
                System.out.println(currentTime);
                return;
            }

            // 현위치 엘베 탈 수 있는경우
            long elevCost = (long) elev[currentN] * (k - 1);
            if (chance == 1 && elev[currentN] != -1 && (currentTime + elevCost < afterElevTime[currentN])) {
                afterElevTime[currentN] = currentTime + elevCost;
                pq.add(new long[] { currentN, currentTime + elevCost, 0 });
            }
            for (int[] next : nexts[currentN]) {
                long nextD = currentTime + next[1];
                // 엘베 이미탄경우
                if (chance == 0) {
                    if (nextD < afterElevTime[next[0]]) {
                        afterElevTime[next[0]] = nextD;
                        pq.add(new long[] { next[0], nextD, 0 });
                    }
                }
                // 엘베 안 탄경우
                else {
                    if (nextD < beforeElevTime[next[0]]) {
                        beforeElevTime[next[0]] = nextD;
                        pq.add(new long[] { next[0], nextD, 1 });
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
