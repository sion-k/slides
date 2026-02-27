import java.util.*;
import java.io.*;

public class Minjong {
    static int N;
    static int[] tree;
    static List<Integer>[] connection;
    static HashSet<Integer> line1;
    static Integer[] subTreeSizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        tree = new int[N + 1];
        connection = new List[N + 1];
        subTreeSizes = new Integer[N + 1];
        for (int i = 1; i <= N; i++) {
            connection[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            connection[u].add(v);
            connection[v].add(u);
        }
        getTree();
        line1 = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        int current = N;
        int previous = N;
        while (current != 0) {
            line1.add(current);
            for (int child : connection[current]) {
                if (child == tree[current] || child == previous) {
                    continue;
                }
                list.add(child);
            }
            previous = current;
            current = tree[current];
        }
        long answer = calculateC((long) N - line1.size());
        for (int i : list) {
            int n = getSubTreeSize(i);
            answer -= calculateC(n);
        }
        bw.write(answer + "\n");
        bw.flush();
    }

    public static long calculateC(long n) {
        return n * (n - 1) / 2;
    }

    public static void getTree() {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : connection[current]) {
                if (!visited[next]) {
                    tree[next] = current;
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }

    public static int getSubTreeSize(int parent) {
        if (subTreeSizes[parent] == null) {
            int result = 1;
            for (int child : connection[parent]) {
                if (child == tree[parent]) {
                    continue;
                }
                result += getSubTreeSize(child);
            }
            subTreeSizes[parent] = result;
        }
        return subTreeSizes[parent];
    }
}
