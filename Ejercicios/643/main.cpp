#include <bits/stdc++.h>

using namespace std;

int main () {
    while (true) {
        int N, K;
        cin >> K >> N;
        if (K == 0 && N == 0)
            return 0;
        int tamanos[N];
        char tipos[N];
        int capacidadMinima = 0;
        for (int i = 0; i < N; i++) {
            int aux;
            char auxTipo;
            cin >> aux >> auxTipo;
            if (aux > capacidadMinima && auxTipo == 'R')
                capacidadMinima = aux;
            else if (aux > 2*capacidadMinima) {
                capacidadMinima = (aux + (aux%2))/2;
            }
            tamanos[i] = aux;
            tipos[i] = auxTipo;
        }
        bool found = false;
        while (!found) {
            int capacidadE = 0, capacidadR = 0;
            int vaciados = 0;
            for (int i = 0; i < N; i++) {
                char tipo = tipos[i];
                int tamano = tamanos[i];
                if (tipo == 'E') {
                    if (capacidadE + tamano > 2 * capacidadMinima) {
                        vaciados++;
                        capacidadE = 0;
                        capacidadR = 0;
                    }
                    capacidadE += tamano;
                } else if (tipo ==  'R') {
                    if (capacidadR + tamano > capacidadMinima) {
                        vaciados++;
                        capacidadE = 0;
                        capacidadR = 0;
                    }
                    capacidadR += tamano;
                }
                
            }
            if (capacidadE > 0 || capacidadR > 0) {
                vaciados++;
            }
            if (vaciados <= K)
                found = true;
            if (!found)
                capacidadMinima++;
        }
        cout << capacidadMinima << "\n";
    }
}