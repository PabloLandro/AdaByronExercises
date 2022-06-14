#include <iostream>
#include <algorithm>

using namespace std;

int N, indice, maxIndice, i, len;
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
        maxIndice = 0;  //Guarda el número de animales distintos leidos en cada momento
        parejas = 0;incompletas=0;sobran=0;
        string nombres[N];  //Para guardar los nombres
        //int hembras[N]; //Array para contar las hembras de cada animal
        //int machos[N];  //Array para contar los machos de cada animal
        int arr[N];
        for (i = 0; i < N; i++) {
            cin >> entrada;
            len = entrada.length();
            for (indice = 0; indice <= maxIndice; indice++) {   //Buscamos el indice en el que se guarda este animal
                if (nombres[indice].empty()) {  //Si llegamos a un string vacío, significa que no hemos leido a este animal antes
                    nombres[indice] = entrada;
                    //machos[indice] = 0;
                    //hembras[indice] = 0;
                    maxIndice++;
                    arr[indice] = 2;
                    if (entrada.at(len-1) == 'o') {
                        arr[indice] = 1;
                    }
                    incompletas++;
                    break;
                }
                if (entrada.compare(0, len-1, nombres[indice], 0, len-1) == 0) {  //Comprobamos si es igual a uno de los animales anteriores
                    int aux = arr[indice];
                    if (entrada.at(len-1) == 'o') {
                        if (aux == 1 || aux == 3) {
                            sobran++;
                        } else {
                            arr[indice] = 3;
                            parejas++;
                            incompletas--;
                        }
                    } else {
                        if (aux == 2 || aux == 3) {
                            sobran++;
                        } else {
                            arr[indice] = 3;
                            parejas++;
                            incompletas--;
                        }
                    }
                    break;
                }
            }
            //int m = machos[indice];
            //int h = hembras[indice];
            /*if (entrada.at(len-1) == 'o') {
                if (m == 0) {
                    if (h == 0) {
                        incompletas++;
                    } else {
                        incompletas--;
                        parejas++;
                    }
                } else
                    sobran++;
                machos[indice]++;
            }
            else {
                if (h == 0) {
                    if (m == 0) {
                        incompletas++;
                    } else {
                        incompletas--;
                        parejas++;
                    }
                } else
                    sobran++;
                hembras[indice]++;
            }*/

        }

        cout << parejas << " " << incompletas << " " << sobran << "\n";
    }
}