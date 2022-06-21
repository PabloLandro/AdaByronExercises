#include <iostream>
#include <bits/stdc++.h>

using namespace std;

int N, len;
string entrada;
int parejas;
int incompletas;
int sobran;

int main () {
    ios::sync_with_stdio(0);
    cin.tie(0);

    while (true) {
        cin >> N;
        if (N == 0)
            return 0;
        parejas = 0;incompletas=0;sobran=0;
        unordered_map<string, int[2]> especies;
        while (N-- != 0) {
            cin >> entrada;
            char aux = entrada.back();
            entrada.pop_back();
            if (aux == 'o')
                especies[entrada][0]++;
            else
                especies[entrada][1]++;
        }

        for ( auto it = especies.begin(); it != especies.end(); ++it ){
            if (it->first.empty())
                continue;
            int h = it->second[0];
            int m = it->second[1];
            if (m > 0 && h > 0) {
                parejas++;
                sobran += m + h - 2;
            } else {
                incompletas++;
                sobran += m + h -1;
            }
        }

        cout << parejas << " " << incompletas << " " << sobran << "\n";
    }
}