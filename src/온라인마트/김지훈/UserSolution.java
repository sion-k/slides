import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/*
 * [제약사항]
 * 1. 각 테스트 케이스 시작 시 init() 함수가 한 번 호출된다.
 * 2. 각 테스트 케이스에서 sell() 함수의 호출 횟수는 50,000 이하이다.
 * 3. 각 테스트 케이스에서 closeSale() 함수의 호출 횟수는 5,000 이하이다.
 * 4. 각 테스트 케이스에서 discount() 함수의 호출 횟수는 10,000 이하이다.
 * 5. 각 테스트 케이스에서 show() 함수의 호출 횟수는 1,000 이하이다.
 */
class UserSolution {

    static HashMap<Integer, Product> productMap;
    static long[][] groupOffset;
    static int[][] groupCount;
    static PriorityQueue<Product>[][] pq;

    /* 초기에 판매되는 상품은 없다. */
    @SuppressWarnings("unchecked")
    public void init() {
        productMap = new HashMap<>();
        groupOffset = new long[6][6];
        groupCount = new int[6][6];
        pq = new PriorityQueue[6][6];
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                pq[i][j] = new PriorityQueue<>();
            }
        }

        return;
    }

    /*
     * mID : 추가할 자료의 ID (1 ≤ mID ≤ 1,000,000,000)
     * mCategory : 상품의 품목 (1 ≤ mCategory ≤ 5)
     * mCompany : 상품의 제조사 (1 ≤ mCompany ≤ 5)
     * mPrice : 상품의 가격 (1 ≤ mPrice ≤ 1,000,000)
     * 같은 품목과 제조사를 가진 판매 중인 상품의 개수 반환
     */
    public int sell(int mID, int mCategory, int mCompany, int mPrice) {
        long curOffset = groupOffset[mCategory][mCompany];
        long priceToStore = mPrice + curOffset;
        Product newProduct = new Product(mID, mCategory, mCompany, priceToStore);

        productMap.put(mID, newProduct);
        pq[mCategory][mCompany].add(newProduct);

        return groupCount[mCategory][mCompany] += 1;
    }

    /*
     * mID : 판매를 종료할 상품의 ID (1 ≤ mID ≤ 1,000,000,000)
     * 판매를 종료할 때 상품의 가격을 반환하고 상품을 판매하지 않는 경우 -1 반환.
     */
    public int closeSale(int mID) {
        Product p = productMap.get(mID);

        if (p == null || p.isClosed)
            return -1;

        p.isClosed = true;
        groupCount[p.mCategory][p.mCompany]--;
        long closePrice = p.mPrice - groupOffset[p.mCategory][p.mCompany];
        productMap.remove(mID);

        return (int) closePrice;
    }

    /*
     * mCategory : 할인하고자 하는 상품의 품목 (1 ≤ mCategory ≤ 5)
     * mCompany : 할인하고자 하는 상품의 제조사 (1 ≤ mCompany ≤ 5)
     * mAmount : 할인되는 금액 (1 ≤ mAmount ≤ 1,000,000)
     * 품목과 제조사가 mCategory, mCompany인 판매 중인 상품 개수 반환
     */
    public int discount(int mCategory, int mCompany, int mAmount) {
        groupOffset[mCategory][mCompany] += mAmount;

        PriorityQueue<Product> curPq = pq[mCategory][mCompany];
        long curOffset = groupOffset[mCategory][mCompany];

        while (!curPq.isEmpty() && curPq.peek().mPrice <= curOffset) {
            Product p = curPq.poll();
            if (!p.isClosed) {
                p.isClosed = true;
                groupCount[mCategory][mCompany]--;
                productMap.remove(p.mId);
            }
        }

        return groupCount[mCategory][mCompany];
    }

    /*
     * mHow = 0인 경우 모든 상품에 대해서
     * mHow = 1인 경우 품목이 mCode인 모든 상품에 대해서
     * mHow = 2인 경우 제조사가 mCode인 모든 상품에 대해서
     * 반환할 때 저장된 상품의 개수를 RESULT.cnt에 저장하고 i번째 상품의 ID를 RESULT.IDs[i – 1]에 저장한다.
     * (1 ≤ i ≤ RESULT.cnt)
     * 조건에 만족하는 상품이 없는 경우 RESULT.cnt에 0을 저장한다.
     */
    Solution.RESULT show(int mHow, int mCode) {
        Solution.RESULT res = new Solution.RESULT();
        List<Product> candidates = new ArrayList<>();
        List<Product> temp = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                if (mHow == 1 && i != mCode)
                    continue;
                if (mHow == 2 && j != mCode)
                    continue;

                PriorityQueue<Product> curPq = pq[i][j];
                int count = 0;
                temp.clear();

                while (!curPq.isEmpty() && count < 5) {
                    Product p = curPq.poll();
                    if (p.isClosed)
                        continue;

                    candidates.add(p);
                    temp.add(p);
                    count++;
                }

                for (Product p : temp) {
                    curPq.add(p);
                }
            }
        }

        Collections.sort(candidates, (p1, p2) -> {
            long price1 = p1.mPrice - groupOffset[p1.mCategory][p1.mCompany];
            long price2 = p2.mPrice - groupOffset[p2.mCategory][p2.mCompany];

            if (price1 != price2)
                return Long.compare(price1, price2);
            else
                return Integer.compare(p1.mId, p2.mId);
        });

        res.cnt = Math.min(candidates.size(), 5);
        for (int i = 0; i < res.cnt; i++) {
            res.IDs[i] = candidates.get(i).mId;
        }

        return res;
    }

    class Product implements Comparable<Product> {
        int mId;
        int mCategory;
        int mCompany;
        long mPrice;
        boolean isClosed;

        public Product(int mId, int mCategory, int mCompany, long mPrice) {
            this.mId = mId;
            this.mCategory = mCategory;
            this.mCompany = mCompany;
            this.mPrice = mPrice;
            this.isClosed = false;
        }

        @Override
        public int compareTo(Product o) {
            if (this.mPrice != o.mPrice) {
                return Long.compare(this.mPrice, o.mPrice);
            }

            return Integer.compare(this.mId, o.mId);
        }
    }
}
