import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static List<Bridge>[] bridges;
    static long[] capacities;
    static int start, end;
    static boolean[] visited;

    static class Bridge {
        int end;
        long capacity;

        public Bridge(int end, long capacity) {
            this.end = end;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bridges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            bridges[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long C = Long.parseLong(st.nextToken());
            putInBridgeList(A, B, C);
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        dijkstra();
        bw.write(capacities[end] + "\n");
        bw.flush();
    }

    public static void dijkstra() {
        capacities = new long[N + 1];
        PriorityQueue<Bridge> pq = new PriorityQueue<>(new Comparator<Bridge>() {
            @Override
            public int compare(Bridge o1, Bridge o2) {
                return (int) (o2.capacity - o1.capacity);
            }
        });
        visited = new boolean[N + 1];
        for (Bridge b : bridges[start]) {
            pq.offer(b);
            capacities[b.end] = b.capacity;
        }
        visited[start] = true;
        while (!pq.isEmpty()) {
            Bridge b = pq.poll();
            int current = b.end;
            visited[current] = true;
            if (current == end) {
                return;
            }
            for (Bridge nb : bridges[current]) {
                int next = nb.end;
                long nc = Math.min(nb.capacity, capacities[current]);
                if (!visited[next] && capacities[next] < nc) {
                    capacities[next] = nc;
                    pq.offer(nb);
                }
            }
        }
    }

    public static void putInBridgeList(int A, int B, long C) {
        int index = -1;
        for (int j = 0; j < bridges[A].size(); j++) {
            if (bridges[A].get(j).end == B) {
                bridges[A].get(j).capacity = Math.max(bridges[A].get(j).capacity, C);
                index = j;
                break;
            }
        }
        if (index == -1) {
            bridges[A].add(new Bridge(B, C));
        }
        index = -1;
        for (int j = 0; j < bridges[B].size(); j++) {
            if (bridges[B].get(j).end == A) {
                bridges[B].get(j).capacity = Math.max(bridges[B].get(j).capacity, C);
                index = j;
                break;
            }
        }
        if (index == -1) {
            bridges[B].add(new Bridge(A, C));
        }
    }
}
