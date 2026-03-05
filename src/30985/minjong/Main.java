import java.util.*;
import java.io.*;

public class Main {
    static int N, M, K;
    static long[] elevators;
    static Long[][] minDistances;
    static List<Room>[][] connections;

    static class Room implements Comparable<Room> {
        int number;
        int top;
        long distance;

        public Room(int number, int top, long distance) {
            this.number = number;
            this.top = top;
            this.distance = distance;
        }

        @Override
        public int compareTo(Room r) {
            if (this.distance < r.distance) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        connections = new List[N + 1][2];
        for (int i = 1; i <= N; i++) {
            connections[i][0] = new ArrayList<>();
            connections[i][1] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            connections[u][0].add(new Room(v, 0, c));
            connections[u][1].add(new Room(v, 1, c));
            connections[v][0].add(new Room(u, 0, c));
            connections[v][1].add(new Room(u, 1, c));
        }
        st = new StringTokenizer(br.readLine());
        elevators = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            elevators[i] = Long.parseLong(st.nextToken());
            if (elevators[i] >= 1) {
                connections[i][0].add(new Room(i, 1, elevators[i] * (K - 1)));
            }
        }
        long answer = dijkstra();
        bw.write(answer + "\n");
        bw.flush();
    }

    public static long dijkstra() {
        PriorityQueue<Room> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N + 1][2];
        minDistances = new Long[N + 1][2];
        minDistances[1][0] = 0L;
        pq.offer(new Room(1, 0, 0L));
        while (!pq.isEmpty()) {
            Room currentRoom = pq.poll();
            int cn = currentRoom.number;
            int ct = currentRoom.top;
            long cd = currentRoom.distance;
            if (visited[cn][ct]) {
                continue;
            } else {
                minDistances[cn][ct] = cd;
                visited[cn][ct] = true;
                if (cn == N && ct == 1) {
                    return cd;
                }
                for (Room nextRoom : connections[cn][ct]) {
                    int nn = nextRoom.number;
                    int nt = nextRoom.top;
                    long nd = nextRoom.distance;
                    if (!visited[nn][nt]) {
                        if (minDistances[nn][nt] == null || minDistances[nn][nt] > cd + nd) {
                            pq.offer(new Room(nn, nt, cd + nd));
                            minDistances[nn][nt] = cd + nd;
                        }
                    }
                }
            }
        }
        return -1L;
    }
}
