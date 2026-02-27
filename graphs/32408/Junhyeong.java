import java.io.*;
import java.util.*;

public class Junhyeong {
    static class Cord {
        int n;
        boolean visitOne;

        public Cord(int n, boolean visitOne) {
            this.n = n;
            this.visitOne = visitOne;
        }
    }

    static int n;
    static long ans;
    static List<List<Integer>> station = new ArrayList<List<Integer>>();
    static boolean[] oneStation;
    static List<Integer> twoStationSize = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        init();
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            station.get(start).add(end);
            station.get(end).add(start);
        }
        oneStation[1] = true;
        findOneStation(1, oneStation);

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        findTwoStation(1, false, visited);

        long sum = 0;
        long powSum = 0;
        for (int i = 0; i < twoStationSize.size(); i++) {
            int twoStation = twoStationSize.get(i);
            sum += twoStation;
            powSum += Math.pow(twoStation, 2);
        }
        ans = (long) (Math.pow(sum, 2) - powSum) / 2;
        System.out.print(ans);
    }

    static int findTwoStation(int cur, boolean counting, boolean[] visited) {
        int cnt = 1;
        for (int i = 0; i < station.get(cur).size(); i++) {
            int next = station.get(cur).get(i);
            if (!visited[next]) {
                visited[next] = true;
                if (!oneStation[next] && !counting) {
                    twoStationSize.add(findTwoStation(next, true, visited));
                } else {
                    cnt += findTwoStation(next, counting, visited);
                }
                visited[next] = false;
            }
        }
        return cnt;
    }

    static boolean findOneStation(int cur, boolean[] oneStation) {
        if (cur == n) {
            oneStation[cur] = true;
            return true;
        }
        boolean find = false;
        for (int i = 0; i < station.get(cur).size(); i++) {
            int next = station.get(cur).get(i);
            if (!oneStation[next]) {
                oneStation[next] = true;
                find = findOneStation(next, oneStation);
                if (find) {
                    oneStation[next] = true;
                    break;
                }
                oneStation[next] = false;
            }
        }
        return find;
    }

    static void init() {
        oneStation = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            station.add(new ArrayList<>());
        }
    }
}