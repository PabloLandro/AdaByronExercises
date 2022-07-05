#include <bits/stdc++.h>

using namespace std;

int main () {
    ios::sync_with_stdio(0);
    cin.tie(0);
    while (true) {
        int N;
        cin >> N;
        if (!cin)
            return 0;
        unordered_map<int, int[2]> map;
        for (int i = 1; i <= N; i++) {
            cin >> map[i][0];
        }
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (map[i][1] != 0)
                continue;
            int aux = i;
            while (map[aux][1] == 0) {
                map[aux][1] = i;
                aux = map[aux][0];
            }
            if (map[aux][1] == i)
                count++;
        }

        cout << count << "\n";

    }
}