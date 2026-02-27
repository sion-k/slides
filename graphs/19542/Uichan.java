import java.io.*;
import java.util.*;

class Uichan {
    static boolean[] visited;
    static List<Integer>[] nexts;
    static int dist;
    static int d;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 노드개수
        int s = Integer.parseInt(st.nextToken()); // 위치
        d = Integer.parseInt(st.nextToken()); // 힘

        visited = new boolean[n + 1];
        nexts = new List[n + 1];
        dist = 0;

        for (int i = 1; i <= n; i++)
            nexts[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            nexts[n1].add(n2);
            nexts[n2].add(n1);
        }

        dfs(s);

        System.out.println(dist * 2);
    }

    static int dfs(int current) {
        visited[current] = true;
        int ret = 0;
        for (int next : nexts[current]) {
            if (visited[next])
                continue;
            int nextNodeDepth = dfs(next);
            if (nextNodeDepth > d)
                dist++;
            ret = Math.max(ret, nextNodeDepth);
        }
        return ret + 1;
    }
}
