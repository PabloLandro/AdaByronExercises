#include <iostream>
#include <math.h>

using namespace std;

int D;

long long siguienteNumero(long long in) {
    string aux = to_string(in);
    for (int i = 0; i < aux.length(); i++) {
        if (aux.at(i) == D+48) {
            in += pow(10, aux.length()-i-1);
        }
    }
    return in;
}

int main () {

    while (true) {
        int N;
        cin >> N >> D;
        if (N == 0 && D == 0)
            return 0;
        long long numero = 0;
        while (N-- != 0) {
            numero++;
            numero = siguienteNumero(numero);
            cout << "numero: " << numero << "\n";
        }

        cout << numero << "\n";
        
    }

}