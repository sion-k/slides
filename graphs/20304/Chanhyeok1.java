import java.util.*;
import java.io.*;

public class Chanhyeok1 {
    static boolean visited[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int res = -1, qsize, t, i, temp;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Queue<Integer> q = new ArrayDeque<>();
        visited = new boolean[n + 1];
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < m; i++) {
            t = Integer.parseInt(st.nextToken());
            visited[t] = true;
            q.offer(t);
        }

        while (!q.isEmpty()) {
            qsize = q.size();
            while (qsize-- > 0) {
                t = q.poll();
                for (i = 0; i < 32; i++) {
                    temp = t ^ (1 << i);
                    if (temp > n || temp < 0)
                        continue;
                    if (!visited[temp]) {
                        visited[temp] = true;
                        q.offer(temp);
                    }
                }
            }
            res++;
        }
        System.out.println(res);
    }
}
