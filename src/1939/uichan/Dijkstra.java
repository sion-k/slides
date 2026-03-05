import java.io.*;
import java.util.*;

class Dijkstra {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] nexts = new List[n + 1];
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
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        int[] visited = new int[n + 1];
        q.add(new int[] { s, Integer.MAX_VALUE });
        visited[s] = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            if (current[0] == e)
                break;
            for (int[] next : nexts[current[0]]) {
                int nextW = next[1] > current[1] ? current[1] : next[1];

                if (visited[next[0]] < nextW) {
                    visited[next[0]] = nextW;

                    q.add(new int[] { next[0], nextW });
                }
            }
        }

        System.out.println(visited[e]);
    }
}
