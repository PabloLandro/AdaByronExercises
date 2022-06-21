#include <iostream>
#include <bits/stdc++.h>

using namespace std;

void division(int num, int den);

int main () {
    while (true) {
        int numerador;
        int denominador;
        
        cin >> numerador;
        cin >> denominador;
        if (!cin)
            return 0;
        division(numerador, denominador);
    }
}

void division(int num, int den) {
    num *= 10;
    bool finished = false;
    string ans = "0.";
    unordered_map<int, int> restos;
    int index = 1;
    while (!finished && num != 0) {
        int cociente = num / den;
        int resto = num - (den*cociente);
        if (restos[resto] != 0) {
            if (ans.at(restos[resto]) != cociente+48) {
                ans += cociente+48;
                ans.insert(restos[resto]+1, "|");
            }
            else
                ans.insert(restos[resto], "|");
            finished = true;
            
            ans += '|';
            goto endcase;
        }
        ans += cociente + 48;
        restos[resto] = ans.length()-1;
        num = resto*10;
    }
    endcase:;
    cout << ans << "\n";
    restos.clear();
}