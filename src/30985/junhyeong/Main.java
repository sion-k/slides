import java.io.*;
import java.util.*;

public class Main {
    static class Cord implements Comparable<Cord> {
        int n;
        long w;
        boolean elev; // 엘리베이터를 탔는지?

        public Cord(int n, long w, boolean elev) {
            this.n = n;
            this.w = w;
            this.elev = elev;
        }

        @Override
        public int compareTo(Cord c) {
            return Long.compare(this.w, c.w);
        }
    }

    static int n, m, k;
    static List<List<Cord>> graph = new ArrayList<>();
    static List<Integer> elevator = new ArrayList<>();
    static Long[] bottomTable;
    static Long[] targetTable;
    static PriorityQueue<Cord> que = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        bottomTable = new Long[n + 1];
        targetTable = new Long[n + 1];
        Arrays.fill(bottomTable, Long.MAX_VALUE);
        Arrays.fill(targetTable, Long.MAX_VALUE);

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(s).add(new Cord(e, w, false));
            graph.get(e).add(new Cord(s, w, false));

        }

        elevator.add(-1);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int e = Integer.parseInt(st.nextToken());
            graph.get(i).add(new Cord(i, e, false));
            elevator.add(e);

        }

        que.offer(new Cord(1, 0, false));
        bottomTable[1] = 0L;

        while (!que.isEmpty()) {
            Cord cur = que.poll();
            int curN = cur.n;
            long curW = cur.w;
            boolean curE = cur.elev;

            if (curE) {
                if (curW > targetTable[curN]) {
                    continue;
                }
            } else {
                if (curW > bottomTable[curN]) {
                    continue;
                }
            }

            for (int i = 0; i < graph.get(curN).size(); i++) {
                Cord next = graph.get(curN).get(i);
                int nextN = next.n;
                long nextW = next.w + curW;
                boolean nextE = curE;
                if (nextN == curN) {
                    if (!nextE) {
                        long elevW = elevator.get(nextN);
                        if (elevW >= 1) {
                            nextW += (elevW * (k - 2));
                            if (nextW < targetTable[nextN]) {
                                targetTable[nextN] = nextW;
                                que.offer(new Cord(nextN, nextW, true));
                            }
                        }
                    }
                } else {
                    if (nextE) { // 엘리베이터를 탔었음
                        if (nextW < targetTable[nextN]) {
                            targetTable[nextN] = nextW;
                            que.offer(new Cord(nextN, nextW, nextE));
                        }
                    } else { // 엘리베이터를 타지 않았음
                        if (nextW < bottomTable[nextN]) {
                            bottomTable[nextN] = nextW;
                            que.offer(new Cord(nextN, nextW, nextE));
                        }
                    }
                }

            }
        }
        long ans = targetTable[n] == Long.MAX_VALUE ? -1 : targetTable[n];
        System.out.print(ans);
    }
}