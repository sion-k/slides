import java.util.*;
import java.io.*;

public class Minjong {
    static int N, S, D;
    static List<Integer>[] connections;
    static int[] tree;
    static int[] depths;
    static int[] leafDistance;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        connections = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            connections[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            connections[x].add(y);
            connections[y].add(x);
        }
        answer = 0;
        getTree();
        getLeafDistance();
        bw.write(answer + "\n");
        bw.flush();
    }

    public static void getLeafDistance() {
        leafDistance = new int[N + 1];
        Arrays.fill(leafDistance, -1);
        for (int i = 1; i <= N; i++) {
            leafDistance[i] = getLeafDistance(i);
            if (i != S && leafDistance[i] >= D) {
                answer += 2;
            }
        }
    }

    public static int getLeafDistance(int n) {
        if (leafDistance[n] == -1) {
            if (connections[n].size() == 1 && n != S) {
                leafDistance[n] = 0;
            } else {
                int max = 0;
                for (int next : connections[n]) {
                    if (next == tree[n]) {
                        continue;
                    }
                    max = Math.max(max, getLeafDistance(next));
                }
                leafDistance[n] = max + 1;
            }
        }
        return leafDistance[n];
    }

    public static void getTree() {
        tree = new int[N + 1];
        depths = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(S);
        while (!queue.isEmpty()) {
            int parent = queue.poll();
            for (int child : connections[parent]) {
                if (child == tree[parent]) {
                    continue;
                }
                tree[child] = parent;
                depths[child] = depths[parent] + 1;
                queue.offer(child);
            }
        }
    }
}
