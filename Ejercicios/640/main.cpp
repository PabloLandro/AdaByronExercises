#include <bits/stdc++.h>

using namespace std;

int D;

string siguienteNumero(string in) {
    for (int i = in.length()-1; i >= 0; i--) {
        if (in.at(i) >= D+48) {
            int acarreo = 1;
            for (int j = i; j >= 0 && acarreo == 1; j--) {
                int aux = in.at(j) + 1;
                if (aux == 9) {
                    acarreo = 1;
                    aux = 0;
                } else {
                    acarreo = 0;
                }
                in.replace(j, 1, string(1, (char)aux));
            }
        }
    }
    return in;
}


string fromDeci(string& res, int base, long inputNum)
{
    int index = 0;

    while (inputNum > 0) {
        res.push_back(48+(inputNum % base));
        index++;
        inputNum /= base;
    }
 
    reverse(res.begin(), res.end());
 
    return res;
}

int main () {

    while (true) {
        long N;
        cin >> N >> D;
        if (N == 0 && D == 0)
            return 0;
        
        string base;
        fromDeci(base, 9, N);


        
        cout << siguienteNumero(base) << "\n";       
    }

}