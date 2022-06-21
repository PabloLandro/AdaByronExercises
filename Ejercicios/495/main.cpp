#include <iostream>

using namespace std;

int N, maximo;
int *alturas, *costes;

void leerDatos ();
void liberarDatos();
int encontrarMinimo(int partida);

int main () {
    ios::sync_with_stdio(0);
    cin.tie(0);
    while (true) {
        //if (!cin)
        //    return 0;
        leerDatos();
        cout << encontrarMinimo(0) << "\n";
        liberarDatos();
    }

}

void leerDatos () {
    cin >> N;
    alturas = (int*) malloc(sizeof(int)*N);
    for (int i = 0; i < N; i++) {
        cin >> alturas[i];
    }
    costes = (int*) malloc(sizeof(int)*N);
    for (int i = 0; i < N; i++) {
        cin >> costes[i];
    }
    cin >> maximo;
}

void liberarDatos() {
    free(alturas);
    free(costes);
}

int encontrarMinimo(int partida) {
    if (partida == N-1)
        return 0;
    int minimo = INT32_MAX;
    int i = partida;
    do {
        i++;
        int aux = encontrarMinimo(i);
        if (aux < minimo)
            aux = minimo;
    } while (alturas[i] - alturas[partida] <= maximo);
    return minimo + costes[partida];
}