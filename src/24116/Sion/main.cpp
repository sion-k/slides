#include <bits/stdc++.h>
using namespace std;

int main() {
    cin.tie(0)->sync_with_stdio(0);

    int n, m, k;
    cin >> n >> m >> k;

    vector<tuple<int, int, int>> e(m);
    for (auto& [w, u, v] : e) {
        cin >> u >> v >> w;
    }
    sort(e.begin(), e.end());

    vector<int> p(n + 1);
    auto find = [&](int u, auto& find) -> int {
        if (not p[u]) return u;
        return p[u] = find(p[u], find);
        };

    vector<int> a;
    for (auto [w, u, v] : e) {
        u = find(u, find), v = find(v, find);
        if (u != v) {
            a.push_back(w);
            p[u] = v;
        }
    }

    cout << accumulate(a.begin(), a.end() - (k - 1), 0) << '\n';
}
