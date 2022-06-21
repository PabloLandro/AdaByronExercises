#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int N;
    scanf("%d", &N);
    while (N-- != 0) {
        string entrada;
        cin >> entrada;
        int ans = 0;
        int combo = 0;
        for (int i = 0; i < entrada.length(); i++) {
            if (entrada.at(i) == 'O') {
                combo++;
            } else if (combo!=0){
                ans += combo*(combo+1);
                combo=0;
            }
        }
        if (combo!=0)
            ans += combo*(combo+1);
        cout << ans*5 << "\n";
    }
}