import java.util.*;
 
class UserSolution {
    static int idx;
    static PriorityQueue<int[]>[][] pq;
    static Map<Integer, Integer> idToIdx;
    static boolean isSell[];
    static int[] idxToId;
    static int[][] discount;
    static int[][] num;
    static int[] idxToCategoryAndCompany;
    static int[] idxToPrice;
 
    public void init() {
        idx = 0;
        pq = new PriorityQueue[5 + 1][5 + 1];
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                int cate = i;
                int comp = j;
                pq[i][j] = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
            }
        }
        isSell = new boolean[50000 + 100];
        idToIdx = new HashMap<Integer, Integer>();
        idxToId = new int[50000 + 100];
        discount = new int[5 + 1][5 + 1];
        num = new int[5 + 1][5 + 1];
        idxToCategoryAndCompany = new int[50000 + 100];
        idxToPrice = new int[50000 + 100];
    }
 
    public int sell(int mID, int mCategory, int mCompany, int mPrice) {
        // System.out.println("sell- mID:" + mID + " mCategory:" + mCategory + "
        // mCompany:" + mCompany + " mPrice:" + mPrice);
        idToIdx.put(mID, idx);
        idxToId[idx] = mID;
        isSell[idx] = true;
        pq[mCategory][mCompany].add(new int[] { mPrice+discount[mCategory][mCompany], idx});
        num[mCategory][mCompany]++;
        idxToCategoryAndCompany[idx] = mCategory * 10 + mCompany;
        idxToPrice[idx] = mPrice+discount[mCategory][mCompany];
        idx++;
        //System.out.println("카테고리:" + mCategory + " 회사:"+ mCompany +" v:" + num[mCategory][mCompany]);
        return num[mCategory][mCompany];
    }
 
    public int closeSale(int mID) {
        // System.out.println("closeSale- mID:" + mID);
        int targetIdx = idToIdx.getOrDefault(mID, -1);
        if (targetIdx == -1 || !isSell[targetIdx])
            return -1;
 
        isSell[targetIdx] = false;
        int v = idxToCategoryAndCompany[targetIdx];
        num[v / 10][v % 10]--;
 
 
        return idxToPrice[targetIdx] - discount[v / 10][v % 10];
    }
 
    public int discount(int mCategory, int mCompany, int mAmount) {
//       System.out.println("discount- mCategory:" + mCategory + " mCompany:" +
//       mCompany + " mAmount:" + mAmount);
        discount[mCategory][mCompany] += mAmount;
        while (!pq[mCategory][mCompany].isEmpty()) {
            int[] current = pq[mCategory][mCompany].peek();
            if (0 < current[0] - discount[mCategory][mCompany]) {
                break;
            }
            if(isSell[current[1]])
                num[mCategory][mCompany]--;
            isSell[current[1]] = false;
            pq[mCategory][mCompany].poll();
        }
 
        //System.out.println("카테고리:" + mCategory + " 회사:"+ mCompany +" v:" + num[mCategory][mCompany]);
        return num[mCategory][mCompany];
    }
 
    Solution.RESULT show(int mHow, int mCode) {
        // if (mHow == 2 && mCode == 1)
        // System.out.println("debug");
        // System.out.println("mHow:" + mHow + " mCode:" + mCode);
        Solution.RESULT res = new Solution.RESULT();
        res.cnt = 0;
        int ansNum = 1;
        PriorityQueue<int[]> candi = new PriorityQueue<>(
                (o1, o2) -> o1[0] == o2[0] ? Integer.compare(o1[6], o2[6]) : Integer.compare(o1[0], o2[0]));
        if (mHow == 0) {
            for (int cate = 1; cate <= 5; cate++) {
                for (int com = 1; com <= 5; com++) {
                    ansNum = 1;
                    while (!pq[cate][com].isEmpty() && ansNum <= 5) {
                        int[] c = pq[cate][com].poll();
                        int d = discount[cate][com];
                        int id = idxToId[c[1]];
                        candi.add(new int[] { c[0] - d, c[0], c[1], 0, cate, com, id });
                        if (isSell[c[1]])
                            ansNum++;
                    }
                }
            }
        } else if (mHow == 1) {
            int cate = mCode;
            for (int com = 1; com <= 5; com++) {
                ansNum = 1;
                while (pq[cate][com].size() != 0 && ansNum <= 5) {
                    int[] c = pq[cate][com].poll();
                    int d = discount[cate][com];
                    int id = idxToId[c[1]];
                    candi.add(new int[] { c[0] - d, c[0], c[1], 0, cate, com, id });
                    if (isSell[c[1]])
                        ansNum++;
                }
            }
        } else if (mHow == 2) {
            int com = mCode;
            for (int cate = 1; cate <= 5; cate++) {
                ansNum = 1;
                while (pq[cate][com].size() != 0 && ansNum <= 5) {
                    int[] c = pq[cate][com].poll();
                    int d = discount[cate][com];
                    int id = idxToId[c[1]];
                    candi.add(new int[] { c[0] - d, c[0], c[1], 0, cate, com, id });
                    if (isSell[c[1]])
                        ansNum++;
                }
            }
        }
        int ans = 1;
        while (!candi.isEmpty()) {
            int[] current = candi.poll();
            if (isSell[current[2]] && ans <= 5) {
                int id = idxToId[current[2]];
                res.IDs[res.cnt] = id;
                res.cnt++;
                ans++;
            }
            pq[current[4]][current[5]].add(new int[] { current[1], current[2], current[3] });
        }
 
        return res;
    }
}
