#include <bits/stdc++.h>

using namespace std;

int main () {
    while (true) {
        int N;
        cin >> N;
        if (N == 0) return 0;
        int tiempos[N];
        int a = 0, b = 9, min = 0, current = 0;
        for (int i = 0; i < N; i++) {
            cin >> tiempos[i];
            if (i == 10)
                current = min;
            if (i < 10)
                min += tiempos[i];
            else {
                current += tiempos[i];
                current -= tiempos[i-10];
                if (current <= min) {
                    a = i-9;
                    b = i;
                    min = current;
                }
            }
        }
        int horas = min / 60, minutos = min%60;
        if (minutos >= 10)
            cout << a*100 << "-" << (b+1)*100 << " " << horas << ":" << minutos << "\n";
        else
            cout << a*100 << "-" << (b+1)*100 << " " << horas << ":0" << minutos << "\n";

    }
}