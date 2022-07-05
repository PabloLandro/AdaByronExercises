#include <bits/stdc++.h>
using namespace std;
#define ll long long // 64 bits INT
#define ld long double // 80 bits FP
typedef vector<int> vi;
int maxdiff(int first, int second, int third) { 
    int diff21 = abs(second - first);
    int diff32 = abs(third - second);
    int diff31 = abs(third - first);
    return max(max(diff32, diff21), diff31);
}
bool solve() { 
    const ll INF = 1e9;
    // WA
    int N = 0;
    cin >> N;
    if (!N) return false;
    vi v; v.resize(N);
    int tmp = 0, sum = 0;
    for (int i = 0; i < N; i++) {
        cin >> tmp;
        v[i] = tmp;
        sum += tmp;
    }
    int first = 0, second = 0, third = sum;
    int i = 0, j = 0, diffVal = INF;
    for (int i = 0; i < N; i++) {
        while (second < third && j < N) {
            second += v[j];
            third -= v[j];
            j++;
        }
        int diffPos = second - third, secondPos = second, thirdPos = third, jPos = j;
        while (third < second && j >= i) {
            j--;
            second -= v[j];
            third += v[j];
        }
        if (third - second > diffPos) {
            second = secondPos;
            third = thirdPos;
            j = jPos;
        }
        int newdiff = maxdiff(first, second, third);
        if (newdiff < diffVal)
            diffVal = newdiff; 
            // Movemos la ventana con su suma parcial
        first += v[i];
        if (i != j) {
            second -= v[i];
        } else {
            j++;
            third -= v[i];
        }
    }
    cout << diffVal << "\n";
    return true;
}
int main() {

    // Optimizacion I/O
    //ios_base::sync_with_stdio(0);
    //cin.tie(0);
    //cout.tie(0); 
    // Número de casos
    while (solve());
}