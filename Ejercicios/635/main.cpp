#include <iostream>

using namespace std;

int main () {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int N;
    cin >> N;
    while (N-- != 0) {
        int entrada;
        cin >> entrada;
        cout << ((entrada-1) / 100) + 1 << "\n";
    }
}