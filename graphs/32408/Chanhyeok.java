import java.util.*;
import java.io.*;

public class Chanhyeok {
    static boolean visited[], fir[];
    static List<Integer> e[];
    static int n;
    static long res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int u, v;
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        fir = new boolean[n];
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
        visited[0] = true;
        fir[0] = true;
        dfs(0);
        count(0);
        System.out.println(res);
    }

    static boolean dfs(int idx) {
        if (idx == n - 1) {
            fir[idx] = true;
            return true;
        }
        boolean ret = false;
        for (int it : e[idx]) {
            if (!visited[it]) {
                visited[it] = true;
                if (dfs(it)) {
                    ret = true;
                    fir[idx] = true;
                }
                visited[it] = false;
            }
        }
        return ret;
    }

    static long count(int idx) {
        long ret = 0, val = 0, temp;
        if (!fir[idx])
            ret++;
        for (int it : e[idx]) {
            if (!visited[it]) {
                visited[it] = true;
                temp = count(it);
                ret += temp;
                visited[it] = false;
                if (fir[idx]) {
                    res += val * temp;
                    val += temp;
                }
            }
        }
        return ret;
    }
}
