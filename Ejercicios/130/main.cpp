#include <bits/stdc++.h>

using namespace std;

#define limit 1000000007

int N, K;
vector<int> peldanos;
vector<int> anterior;
vector<bool> calculado;

int solve () {
    if (N == 0) return 1;
    if (calculado[N])
        return anterior[N];
    int total = 0;
    for (auto paso: peldanos) {
        if (paso <= N) {
            N -= paso;
            total = (total + solve()) % limit;
            N += paso;
        }
    }
    calculado[N] = true;
    anterior[N] = total;
    return total;
}

int main () {

    while (true) {
        cin >> N;
        if (N == 0) return 0;
        cin >> K;
        peldanos.clear();
        peldanos.shrink_to_fit();
        anterior.resize(N);
        anterior.assign(N, 0);
        calculado.resize(N);
        calculado.assign(N, false);
        for (int i = 0; i < K; i++) {
            int tmp;
            cin >> tmp;
            peldanos.push_back(tmp);
        }
        cout << solve() << "\n";
    }

}