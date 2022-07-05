#include <bits/stdc++.h>

using namespace std;

int N, K;
int *tamanos;
char *tipos;

int numeroVaciados (int capacidad) {
    int capacidadE = 0, capacidadR = 0;
    int vaciados = 0;
    for (int i = 0; i < N; i++) {
        char tipo = tipos[i];
        int tamano = tamanos[i];
        if (tipo == 'E') {
            if (capacidadE + tamano > 2 * capacidad) {
                vaciados++;
                capacidadE = 0;
                capacidadR = 0;
            }
            capacidadE += tamano;
        } else if (tipo ==  'R') {
            if (capacidadR + tamano > capacidad) {
                vaciados++;
                capacidadE = 0;
                capacidadR = 0;
            }
            capacidadR += tamano;
        }
        
    }
    if (capacidadR != 0 || capacidadE != 0)
        vaciados++;
    return vaciados;
}

int main () {
    while (true) {
        cin >> K >> N;
        if (K == 0 && N == 0)
            return 0;
        tamanos = (int*) malloc(sizeof(int) * N);
        tipos = (char*) malloc (sizeof(char)*N);
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
        int a = capacidadMinima;
        int b = capacidadMinima * N;
        int c = (a+b)/2;
        int ans = 0;
        while (a < b) {
            if (numeroVaciados(c) > K)
                a = c;
            else
                b = c;
            c = (a+b)/2;
        }
        if (numeroVaciados(c) > K)
            ans = c-1;
        else if (numeroVaciados(c) <= K) {
            if (numeroVaciados(c+1) > K)
                ans = c;
            else
                ans = c+1;
        }
        cout << c << "\n";
    }
}