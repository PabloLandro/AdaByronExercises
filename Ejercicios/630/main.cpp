#include <math.h>

#include <iostream>

using namespace std;

int N, rondas;
string linea;
int *arr;
int partidos;

void obtenerSiguienteCapa();

int main() {
    while (true) {
        cin >> N;
        cin >> rondas;
        partidos = 0;
        if (N == 0 && rondas == 0) {
            return 0;
        }
        cin >> linea;

        arr = (int *)malloc(N * sizeof(int));

        for (int i = 0; i < N; i++) {
            arr[i] = linea.at(i) - 48;
        }

        while (rondas-- != 0) {
            obtenerSiguienteCapa();
        }
        free(arr);
        cout << partidos << endl;
    }
}

void obtenerSiguienteCapa() {
    for (int i = 0; i < N; i+=2) {
        int j1 = arr[i];
        int j2 = arr[i+1];
        if (j1 == 1 && j2 == 1) {
            partidos++;
            arr[i/2] = 1;
        } else if (j1 == 0 && j2 == 0) {
            arr[i/2] = 0;
        } else {
            arr[i/2] = 1;
        }
    }
    N /= 2;
}