import java.util.*;
import java.io.*;

public class MinjongOld {
    static int n, m, q;
    static List<Integer>[] connectedTo, connectedFrom;
    static Integer[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        connectedFrom = new List[n + 1];
        connectedTo = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            connectedFrom[i] = new ArrayList<>();
            connectedTo[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            connectedFrom[v].add(u);
            connectedTo[u].add(v);
        }
        memo = new Integer[n + 1][n + 1];
        for (int i = 0; i < q; i++) {
            int answer = 1000000;
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            List<Integer> list = getCommonAncestor(num1, num2);
            if (list.size() == 0) {
                answer = -1;
            } else {
                for (int ancestor : list) {
                    int dist1 = getDistance(ancestor, num1);
                    int dist2 = getDistance(ancestor, num2);
                    answer = Math.min(answer, Math.max(dist1, dist2));
                }
            }
            bw.write(answer + "\n");
        }
        bw.flush();
    }

    public static int getDistance(int ancestor, int num) {
        if (memo[num][ancestor] != null) {
            return memo[num][ancestor];
        } else {
            Queue<Integer> numQueue = new LinkedList<>();
            Queue<Integer> distQueue = new LinkedList<>();
            numQueue.offer(num);
            distQueue.offer(0);
            while (!numQueue.isEmpty()) {
                int current = numQueue.poll();
                int distance = distQueue.poll();
                if (memo[num][current] == null) {
                    memo[num][current] = distance;
                } else {
                    memo[num][current] = Math.min(memo[num][current], distance);
                }
                for (int next : connectedFrom[current]) {
                    numQueue.offer(next);
                    distQueue.offer(distance + 1);
                }
            }
        }
        return memo[num][ancestor];
    }

    public static List<Integer> getCommonAncestor(int num1, int num2) {
        boolean[] visited1 = new boolean[n + 1];
        List<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num1);
        visited1[num1] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : connectedFrom[current]) {
                if (!visited1[next]) {
                    queue.offer(next);
                    visited1[next] = true;
                }
            }
        }
        boolean[] visited2 = new boolean[n + 1];
        if (visited1[num2]) {
            list.add(num2);
        }
        queue.offer(num2);
        visited2[num2] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : connectedFrom[current]) {
                if (visited1[next]) {
                    list.add(next);
                }
                if (!visited2[next]) {
                    queue.offer(next);
                    visited2[next] = true;
                }
            }
        }
        return list;
    }
}
