package BOJ19701;

import java.util.*;
import java.io.*;

public class Main {
    static int V, E;

    static class Connection {
        int num;
        int katsu;
        long distance;

        public Connection(int num, int katsu, long distance) {
            this.num = num;
            this.katsu = katsu;
            this.distance = distance;
        }
    }

    static List<Connection>[][] connections;
    static Long[][] minDistances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        connections = new List[V + 1][2];
        for (int i = 1; i <= V; i++) {
            connections[i][0] = new ArrayList<>();
            connections[i][1] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            long t = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            connections[x][0].add(new Connection(y, 0, t));
            connections[x][0].add(new Connection(y, 1, t - k));
            connections[x][1].add(new Connection(y, 1, t));
            connections[y][0].add(new Connection(x, 0, t));
            connections[y][0].add(new Connection(x, 1, t - k));
            connections[y][1].add(new Connection(x, 1, t));
        }
        dijkstra();
        for (int i = 2; i <= V; i++) {
            bw.write(minDistances[i][1] + "\n");
        }
        bw.flush();
    }

    public static void dijkstra() {
        boolean[][] visited = new boolean[V + 1][2];
        minDistances = new Long[V + 1][2];
        minDistances[1][0] = 0L;
        PriorityQueue<Connection> pq = new PriorityQueue<>(new Comparator<Connection>() {
            @Override
            public int compare(Connection c1, Connection c2) {
                if (c1.katsu != c2.katsu) {
                    return c1.katsu - c2.katsu;
                } else {
                    if (c1.distance < c2.distance) {
                        return -1;
                    }
                    return 1;
                }
            }
        });
        for (Connection c : connections[1][0]) {
            minDistances[c.num][c.katsu] = c.distance;
            pq.offer(c);
        }
        while (!pq.isEmpty()) {
            Connection current = pq.poll();
            int cn = current.num;
            int ck = current.katsu;
            long cd = current.distance;
            if (!visited[cn][ck]) {
                visited[cn][ck] = true;
                for (Connection next : connections[cn][ck]) {
                    int nn = next.num;
                    int nk = next.katsu;
                    long nd = next.distance;
                    if (!visited[nn][nk]) {
                        if (minDistances[nn][nk] == null || minDistances[nn][nk] > minDistances[cn][ck] + nd) {
                            minDistances[nn][nk] = minDistances[cn][ck] + nd;
                            pq.offer(new Connection(nn, nk, minDistances[nn][nk]));
                        }
                    }
                }
            }
        }
    }
}
