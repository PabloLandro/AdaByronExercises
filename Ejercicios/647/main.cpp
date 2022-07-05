#include <bits/stdc++.h>

using namespace std;

int main () {
    int N;
    cin >> N;
    while (N-- != 0) {
        int K;
        cin >> K;
        //vector<int> apariciones(K, -1);
        unordered_map<int, int> apariciones;
        int maximo = 0;
        int a = 1, b = 1;
        for (int i = 1; i <= K; i++) {
            int capitulo;
            cin >> capitulo;
            capitulo--;
            b++;
            int ultimaAparicion = apariciones[capitulo];
            if (a <= ultimaAparicion)
                a = ultimaAparicion+1;
            maximo = max(maximo, b-a);
            apariciones[capitulo] = i;
        }
        cout << maximo << "\n";
    }
    return 0;
}