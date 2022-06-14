#include <iostream>
#include <iomanip>

using namespace std;

int main () {
    int N;
    int dia, mes, ano;
    cin >> N;
    for (int i = 0; i < N; i++) {
        scanf("%d/%d/%d", &dia, &mes, &ano);
        if (((mes != 1) && (mes != 2 || dia >= 29)) || ano %4 != 0)
            ano += 4 - (ano%4);
        printf("29/02/%04d\n", ano);
    }
}