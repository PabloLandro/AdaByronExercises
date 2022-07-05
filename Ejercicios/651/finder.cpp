#include <bits/stdc++.h>

#define N 1000

using namespace std;

string mias[N];
string pablo[N];

int main () {
    freopen("outMio.txt", "r", stdin);
    for (int i = 0; i < N; i++) {
        cin >> mias[i];
    }
    freopen("outPablo.txt", "r", stdin);
    for (int i = 0; i < N; i++) {
        cin >> pablo[i];
    }
    for (int i = 0; i < N; i++) {
        if (mias[i] != pablo[i]) {
            cout << "Error en " << i << "\n";
            return 0;
        }
    }
}