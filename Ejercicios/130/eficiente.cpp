#include <bits/stdc++.h>

using namespace std;

#define limit 1000000007

int N, K;
vector<int> peldanos;
int elements;

long long fact (long long x) {
    if (x <= 0) return 1;
    long long f = fact(x-1);
    long long out = x * f;
    return out;
}

int solve (int index) {
    //cout << "iteracion: " << index << " != " << peldanos.size()-1 << "\n";
    if (index == 0) {   //Inicializacion
        elements = 0;
    }
    if (index == peldanos.size()-1) {   //Ultimo caso
        //cout << fact(elements+N) << "\n";
        //cout << fact(N) << "\n";
        return (fact(elements+N)) / (fact(N));
    }
    int paso = peldanos[index];
    int aux = N;
    int total = 0;
    for (int i = 0; i <= aux/paso; i++) {
        //int tmp = solve(index+1);
        //cout << "tmp: " << tmp << "\t" << tmp/fact(i) << "\n";
        total = ((solve(index+1)/fact(i)) + total) % limit;
        elements++;
        N -= paso;
    }
    elements -= (aux / paso)+1;
    N = aux;    // Recuperamos el valor inicial
    return total;
}

int main () {

    while (true) {
        cin >> N;
        if (N == 0) return 0;
        cin >> K;
        peldanos.resize(K);
        for (int i = 0; i < K-1; i++) {
            int tmp;
            cin >> tmp;
            if (tmp == 1) {
                i--;
                continue;
            }
            peldanos[i] = tmp;
        }
        peldanos[K-1] = 1;
        //cout << "datos leidos: ";
        //for (int i = 0; i < K; i++) cout << peldanos[i] << " ";
        //cout << "\n";
        cout << solve(0) << "\n";
    }

}