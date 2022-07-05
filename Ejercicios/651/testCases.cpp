#include <bits/stdc++.h>

using namespace std;

int randomNumber (int min, int max) {
    return rand()%(max-min + 1) + min;
}

int main () {
    ofstream out("testCases.txt");
    streambuf *coutbuf = std::cout.rdbuf(); //save old buf
    cout.rdbuf(out.rdbuf());

    for (int i = 0; i < 3; i++) {
        int def = randomNumber(0, 10);
        int cen = randomNumber(0, 10-def);
        int del = randomNumber(0, 10-def-cen);
        int N = randomNumber(10, 25);
        cout << def << " " << cen << " " << del << "\n" << N << "\n";
        while (N-- != 0) {
            string aux;
            for (int j = 0; j < 3; j++) {
                if (randomNumber(0,1)) {
                    if (j == 0) aux += 'F';
                    if (j == 1) aux += 'C';
                    if (j == 2) aux += 'D';
                }
            }
            cout << aux;
            if (N != 0)
                cout << " ";
        }
        cout << "\n";
    }
    cout << "0 0 0\n";
}