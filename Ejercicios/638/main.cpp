#include <iostream>
using namespace std;

int main () {
    ios::sync_with_stdio(0);
    cin.tie(0);
    int N;
    cin >> N;
    while (N-- != 0) {
        int c, n;
        cin >> c;
        cin >> n;
        float calculo = ((float)n*(float)c)/((float)c-1);
        int maximo = (int) calculo;
        int minimo = (int) calculo;
        while ((int)((minimo-1) - (int)((minimo-1)/(float)c)) == n) {
            minimo--;
        }
        cout << minimo << " " << maximo << "\n";
    }
    return 0;
}