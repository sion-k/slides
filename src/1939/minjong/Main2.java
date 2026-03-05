import java.util.*;
import java.io.*;

public class Main2 {
    static int N, M;
    static HashMap<Integer, Long>[] bridges;
    static Long[] maxCapacities;
    static int start, end;

    static class Bridge {
        int num;
        long capacity;

        public Bridge(int num, long capacity) {
            this.num = num;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bridges = new HashMap[N + 1];
        for (int i = 1; i <= N; i++) {
            bridges[i] = new HashMap<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            bridges[A].put(B, Math.max(bridges[A].getOrDefault(B, 0L), C));
            bridges[B].put(A, Math.max(bridges[B].getOrDefault(A, 0L), C));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        dijkstra();
        bw.write(maxCapacities[end] + "\n");
        bw.flush();
    }

    public static void dijkstra() {
        maxCapacities = new Long[N + 1];
        PriorityQueue<Bridge> pq = new PriorityQueue<>(new Comparator<Bridge>() {
            @Override
            public int compare(Bridge o1, Bridge o2) {
                if (o1.capacity > o2.capacity) {
                    return -1;
                }
                return 1;
            }
        });
        boolean[] visited = new boolean[N + 1];
        for (int num : bridges[start].keySet()) {
            long capacity = bridges[start].get(num);
            pq.offer(new Bridge(num, capacity));
            maxCapacities[num] = capacity;
        }
        visited[start] = true;
        while (!pq.isEmpty()) {
            Bridge current = pq.poll();
            int cn = current.num;
            long cc = current.capacity;
            if (!visited[cn]) {
                visited[cn] = true;
                if (cn == end) {
                    return;
                }
                for (int nn : bridges[cn].keySet()) {
                    long nc = bridges[cn].get(nn);
                    if (!visited[nn]) {
                        if (maxCapacities[nn] == null || maxCapacities[nn] < Math.min(cc, nc)) {
                            maxCapacities[nn] = Math.min(cc, nc);
                            pq.offer(new Bridge(nn, maxCapacities[nn]));
                        }
                    }
                }
            }
        }
    }
}
