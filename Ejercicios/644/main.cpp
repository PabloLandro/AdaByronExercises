#include <bits/stdc++.h>

using namespace std;

inline int resto (int num, int den) {
    int cociente = num / den;
    return num - (cociente*den);
}

int main () {

    int M = 24, N = 5;
    while (true) {
        int A;
        cin >> A;
        if (A == 0)
            return 0;
        int a = resto(A, 19);
        int b = resto(A, 4);
        int c = resto(A, 7);
        int d = resto((19*a)+M, 30);
        int e = resto(2*b+4*c+6*d+N, 7);
        if (d+e < 10) {
            cout << d + e + 22 << " de marzo\n";
        } else {
            int aux = d + e - 9;
            if (aux == 26) {
                cout << "19 de abril\n";
            } else if (aux == 25 && d == 28){
                cout << "18 de abril\n";
            } else {
                cout << aux << " de abril\n"; 
            }
        }
    }

}