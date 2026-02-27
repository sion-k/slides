import java.util.*;
import java.io.*;

public class Keonae {

    static int N, S, D;
    static List<ArrayList<Integer>> graph;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        count = 0;
        visited = new boolean[N + 1];
        DFS(S);

        System.out.println(count);
    }

    static int DFS(int start) {
        visited[start] = true;
        int max = 0;

        for (int next : graph.get(start)) {
            if (!visited[next]) {
                int depth = DFS(next);

                if (depth >= D)
                    count += 2;

                max = Math.max(max, depth + 1);
            }
        }

        return max;
    }

}
