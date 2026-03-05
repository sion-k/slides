import java.io.*;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {
    static class Cord implements Comparable<Cord> {
        int n;
        int w;

        public Cord(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Cord c) {
            return c.w - this.w;
        }
    }

    static int n, m, s, e;
    static int[] minTable;
    static List<List<Cord>> graph = new ArrayList<>();
    static PriorityQueue<Cord> que = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        minTable = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Cord(e, w));
            graph.get(e).add(new Cord(s, w));

        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        minTable[s] = 1000000001;

        for (int i = 0; i < graph.get(s).size(); i++) {
            Cord cur = graph.get(s).get(i);
            que.offer(cur);
        }

        while (!que.isEmpty()) {

            Cord cur = que.poll();
            int curN = cur.n;
            int curW = cur.w;

            if (curW <= minTable[curN]) { // 이 다리까지 현재무게 보다 더 크게 가져왔다면 생략
                continue;
            } else {
                minTable[curN] = curW; // 이 다리까지 현재무게가 최대치라면 갱신
            }

            for (int i = 0; i < graph.get(curN).size(); i++) {
                Cord next = graph.get(curN).get(i);
                int nextN = next.n;
                int nextW = next.w;

                int minW = Math.min(curW, nextW);
                if (minW > minTable[nextN]) {
                    que.offer(new Cord(nextN, minW));
                }
            }
        }
        System.out.print(minTable[e]);
    }
}