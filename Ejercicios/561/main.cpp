#include <iostream>
#include <iomanip>
#include <math.h>
#include <limits>

using namespace std;

int prec;

int impar(int i)
{
    return 2 * i + 1;
}

long double termino(int i)
{
    long double valor = pow(-1, i) * pow(10, prec) / impar(i);
    //valor *= pow(10, prec);
    valor = trunc(valor);
    valor /= pow(10, prec);
    return valor;
}

int main(int argc, char *argv[])
{
    int N;

    while (true)
    {
        cin >> N;
        if (N == 0)
        {
            return 0;
        }

        cin >> prec;
        long double suma = 0;
        for (int i = 0; i < N; i++)
        {
            suma += termino(i);
        }
        cout << fixed << setprecision(prec) << suma << endl;
    }
}