import java.io.*;
import java.util.*;

class BinarySearch {
    static int n;
    static List<int[]>[] nexts;
    static int s;
    static int e;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        nexts = new List[n + 1];
        for (int i = 1; i <= n; i++)
            nexts[i] = new ArrayList<>();

        int maxW = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nexts[u].add(new int[] { v, w });
            nexts[v].add(new int[] { u, w });
            maxW = Math.max(w, maxW);
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int ans = 0;
        int l = 1;
        int r = maxW;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (valid(mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(ans);

    }

    static boolean valid(int min) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(e);
        visited[e] = true;

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int[] next : nexts[current]) {
                if (!visited[next[0]] && (min <= next[1])) {
                    visited[next[0]] = true;
                    q.add(next[0]);
                }
            }
        }
        return visited[s];
    }
}
