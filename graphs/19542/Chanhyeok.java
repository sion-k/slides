import java.util.*;
import java.io.*;

public class Chanhyeok {
    static int n, s, d;
    static List<Integer> e[];
    static boolean visited[];
    static int dep[];
    static int res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int u, v;
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken()) - 1;
        d = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        dep = new int[n];
        e = new List[n];
        for (int i = 0; i < n; i++)
            e[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken()) - 1;
            v = Integer.parseInt(st.nextToken()) - 1;
            e[u].add(v);
            e[v].add(u);
        }
        visited[s] = true;
        dfs(s);
        sol(s);
        res *= 2;
        System.out.println(res);
    }

    static int dfs(int v) {
        dep[v] = 1;
        for (int it : e[v]) {
            if (!visited[it]) {
                visited[it] = true;
                dep[v] = Math.max(dep[v], dfs(it) + 1);
                visited[it] = false;
            }
        }
        return dep[v];
    }

    static void sol(int v) {
        for (int it : e[v]) {
            if (!visited[it]) {
                if (dep[it] > d) {
                    visited[it] = true;
                    res++;
                    sol(it);
                    visited[it] = false;
                }
            }
        }
    }
}
