#include <iostream>
#include <queue>
#include <unordered_set>
#include <unordered_map>
#include <tuple>

using std::priority_queue;
using std::cout;
using std::pair;
using std::unordered_set;
using std::unordered_map;
using std::vector;
using std::greater;
using std::tuple;
using std::get;
using std::min;

struct RESULT
{
    int cnt;
    int IDs[5];
};

typedef pair<long long, int> t;

priority_queue<t, vector<t>, greater<t>> pq[6][6];
unordered_set<int> closed;
unordered_map<int, tuple<long long, int, int>> product;
long long acc[6][6], count[6][6];

void init() {
    closed = unordered_set<int>();
    product = unordered_map<int, tuple<long long, int, int>>();

    for (int i = 1; i <= 5; i++) {
        for (int j = 1; j <= 5; j++) {
            pq[i][j] = priority_queue<t, vector<t>, greater<t>>();
            acc[i][j] = 0;
            count[i][j] = 0;
        }
    }
}

void clean(int mCategory, int mCompany) {
    auto& q = pq[mCategory][mCompany];
    while (not q.empty() and (q.top().first - acc[mCategory][mCompany] <= 0 or closed.count(q.top().second))) {
        if (not closed.count(q.top().second)) {
            count[mCategory][mCompany]--;
        }
        closed.insert(q.top().second);
        q.pop();
    }
}

int sell(int mID, int mCategory, int mCompany, int mPrice) {
    pq[mCategory][mCompany].emplace(mPrice + acc[mCategory][mCompany], mID);
    product[mID] = { mPrice + acc[mCategory][mCompany] , mCategory, mCompany };
    count[mCategory][mCompany]++;

    return count[mCategory][mCompany];
}

int closeSale(int mID) {
    if (not product.count(mID)) {
        return -1;
    }

    auto found = product[mID];
    long long price = get<0>(found);
    int category = get<1>(found), company = get<2>(found);

    clean(category, company);
    if (closed.count(mID)) {
        return -1;
    }
    count[category][company]--;
    closed.insert(mID);

    return price - acc[category][company];
}

int discount(int mCategory, int mCompany, int mAmount) {
    acc[mCategory][mCompany] += mAmount;
    clean(mCategory, mCompany);
    return count[mCategory][mCompany];
}

RESULT show(int mHow, int mCode) {
    RESULT res;

    priority_queue<t> q;
    for (int i = 1; i <= 5; i++) {
        for (int j = 1; j <= 5; j++) {
            if (mHow == 0 or (mHow == 1 and i == mCode) or (mHow == 2 and j == mCode)) {
                vector<t> a;

                clean(i, j);
                while (not pq[i][j].empty() and a.size() < 5) {
                    auto p = pq[i][j].top();
                    pq[i][j].pop();

                    a.push_back(p);

                    p.first -= acc[i][j];
                    q.emplace(p);

                    if (q.size() > 5) {
                        q.pop();
                    }
                    clean(i, j);
                }

                for (auto p : a) {
                    pq[i][j].push(p);
                }
            }
        }
    }

    res.cnt = (int)q.size();
    for (int j = res.cnt - 1; j >= 0; j--) {
        res.IDs[j] = q.top().second;
        q.pop();
    }

    return res;
}
