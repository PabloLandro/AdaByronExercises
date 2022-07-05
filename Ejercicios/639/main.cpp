#include <iostream>
#include <math.h>

using namespace std;

int diff (int p0, int p1, int p2) {
    int aux = max(abs(p0-p1), abs(p1-p2));
    return max(aux, abs(p0-p2));
}

int main () {
    while (true) {
        int n;
        
        cin >> n;
        int arr[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            cin >> arr[i];
            total += arr[i];
        }
        if (!cin) {
            return 0;
        }
        
        int p0 = 0, p1 = 0, p2 = total;
        int minimo = 1e9;
        int j = 0;

        for (int i = 0; i < n-1; i++) {
            
            if (p2 > p1) {
                while (j < n && abs(p2-2*arr[j+1]-p1) <= abs(p1-p2)) {
                    j++;
                    p1 += arr[j];
                    p2 -= arr[j];
                }
            } else if (p2 < p1){
                while (j >= i && abs(p2+2*arr[j+1]-p1) <= abs(p1-p2)) {
                    j--;
                    p1 -= arr[j];
                    p2 += arr[j];
                }
            }

            /*while (p1 < p2 && j < n) {
                p1 += arr[j];
                p2 -= arr[j];
                j++;
            }
            int diffPos = p1 - p2, secondPos = p1, thirdPos = p2, jPos = j;
            while (p2 < p1 && j >= i) {
                j--;
                p1 -= arr[j];
                p2 += arr[j];
            }
            if (p2 - p1 > diffPos) {
                p1 = secondPos;
                p2 = thirdPos;
                j = jPos;
            }*/


            minimo = min(minimo, diff(p0, p1, p2));
            p0 += arr[i];
            p1 -= arr[i];
            if (i == j) {
                j++;
                p1 += arr[j];
                p2 -= arr[j];
            }
        }
        cout << minimo << "\n";
    }
}