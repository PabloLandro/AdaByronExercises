#include <bits/stdc++.h>

using namespace std;

#define INF 1000

vector<int> sectores;
vector<int> valor;
vector<int> primero;
vector<bool> calculadas;
int N;

int value (int x) {
    if (x < 0 || x >= INF)
        return INF;
    if (x == 0)
        return 0;
    if (calculadas[x])
        return valor[x];
    int minimo = INF;
    for (int sector: sectores) {
        int aux = value(x-sector)+1;
        if (aux <= minimo) {
            primero[x] = sector;
            minimo = aux;
        }
        //minimo = min(minimo, value(x-sector)+1);
    }
    calculadas[x] = true;
    valor[x] = minimo;
    //cout << "valor " << x << ": " << minimo << "\n";
    return minimo;
}


int main () {
    while (true) {
        
        int puntuacion, S;
        cin >> puntuacion >> S;
        sectores.clear();
        sectores.shrink_to_fit();
        int minimo = 500;
        for (int i = 0 ; i < S; i++) {
            int aux;
            cin >> aux;
            sectores.push_back(aux);
            minimo = min(minimo, aux);
        }
        if (!cin)
            return 0;
        N = puntuacion+1;
        valor.resize(N);
        valor.assign(N, INF);
        primero.resize(N);
        primero.assign(N, -1);
        calculadas.assign(N, false);
        int ans = value(puntuacion);
        if (ans == INF)
            cout << "Imposible\n";
        else {
            cout << ans << ": ";
            while (puntuacion > 0) {
                int aux = primero[puntuacion];
                int resta = puntuacion - aux;
                if (resta > 0)
                    cout << aux << " ";
                else
                    cout << aux;
               puntuacion = resta;
            }
            cout << "\n";
        }
    }
}