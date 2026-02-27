import java.util.*;
import java.io.*;

public class Keonae {

    static int N;
    static List<ArrayList<Integer>> list;
    static boolean[] visited;
    static boolean[] isLine1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        isLine1 = new boolean[N + 1];
        check();

        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            if (isLine1[i]) {
                visited[i] = true;
            }
        }

        List<Integer> size = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                size.add(BFS(i));
            }
        }

        long result = 0, sum = 0;
        for (long s : size) {
            result += sum * s;
            sum += s;
        }

        System.out.println(result);
    }

    static void check() { // 1호선 체크
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        int[] parent = new int[N + 1];
        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == N)
                break;

            for (int next : list.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = cur;
                    q.add(next);
                }
            }
        }

        int track = N;
        while (track != 0) {
            isLine1[track] = true;
            track = parent[track];
        }
    }

    static int BFS(int start) {
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        int count = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                    count++;
                }
            }
        }

        return count;
    }

}
