import java.util.*;
import java.io.*;

public class Main {
    static int N, M, S, T;
    static long[] water;
    static HashMap<Integer, Long>[] roads;
    static Long[] minDistances;
    static Long[] maxWater;

    static class Road implements Comparable<Road> {
        int num;
        long distance;
        long water;

        public Road(int num, long distance, long water) {
            this.num = num;
            this.distance = distance;
            this.water = water;
        }

        @Override
        public int compareTo(Road r) {
            if (this.distance != r.distance) {
                if (this.distance < r.distance) {
                    return -1;
                }
                return 1;
            } else {
                if (this.water > r.water) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        water = new long[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            water[i] = Long.parseLong(st.nextToken());
        }
        roads = new HashMap[N + 1];
        for (int i = 1; i <= N; i++) {
            roads[i] = new HashMap<>();
        }
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            roads[u].put(v, Math.min(w, roads[u].getOrDefault(v, 1000000L)));
            roads[v].put(u, Math.min(w, roads[v].getOrDefault(u, 1000000L)));
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        dijkstra();
        if (minDistances[T] == null) {
            sb.append(-1);
        } else {
            sb.append(minDistances[T]).append(" ").append(maxWater[T]);
        }
        sb.append("\n");
        bw.write(sb.toString());
        bw.flush();
    }

    public static void dijkstra() {
        minDistances = new Long[N + 1];
        maxWater = new Long[N + 1];
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Road> pq = new PriorityQueue<>();
        visited[S] = true;
        minDistances[S] = 0L;
        maxWater[S] = water[S];
        for (int num : roads[S].keySet()) {
            long distance = roads[S].get(num);
            pq.offer(new Road(num, distance, water[S] + water[num]));
            minDistances[num] = distance;
            maxWater[num] = water[S] + water[num];
        }
        while (!pq.isEmpty()) {
            Road current = pq.poll();
            int cn = current.num;
            long cd = current.distance;
            long cw = current.water;
            if (!visited[cn]) {
                visited[cn] = true;
                for (int nn : roads[cn].keySet()) {
                    long nd = roads[cn].get(nn);
                    long nw = water[nn];
                    if (!visited[nn]) {
                        if (minDistances[nn] == null || minDistances[nn] > cd + nd) {
                            minDistances[nn] = cd + nd;
                            maxWater[nn] = cw + nw;
                            pq.offer(new Road(nn, cd + nd, cw + nw));
                        } else if (minDistances[nn] == cd + nd && maxWater[nn] < cw + nw) {
                            maxWater[nn] = cw + nw;
                            pq.offer(new Road(nn, cd + nd, cw + nw));
                        }
                    }
                }
            }
        }
    }
}
