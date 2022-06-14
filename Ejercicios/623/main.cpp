#include <iostream>

using namespace std;

int N;
int arrBoleto[10], arrGanador[10];
string boleto, ganador;
bool gana;

int main () {

    cin >> N;

    

    while (N-- != 0) {
        cin >> boleto;
        cin >> ganador;
        gana = true;

        for (int i = 0; i < 10; i++) {
            arrBoleto[i] = 0;
            arrGanador[i] = 0;
        }

        for (int i = 0; i < 7; i++) {
            arrBoleto[boleto.at(i)-48]++;
            arrGanador[ganador.at(i)-48]++;
        }

        for (int i = 0; i < 10; i++) {
            if (arrBoleto[i] != arrGanador[i]) {
                gana = false;
                break;
            }
        }

        if (gana)
            cout << "GANA" << endl;
        else
            cout << "PIERDE" << endl;

    }

    return 0;

}