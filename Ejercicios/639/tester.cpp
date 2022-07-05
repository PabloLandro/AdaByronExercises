#include <iostream>
#include <math.h>
#include <time.h>

using namespace std;

int n, total;
int *arr;

int diff (int p0, int p1, int p2) {
    int aux = max(abs(p0-p1), abs(p1-p2));
    return max(aux, abs(p0-p2));
}

int solverPablo () {
    int j = 0;
        int p0 = 0, p1 = 0, p2 = total;
        int minimo = 1e9;

        for (int i = 0; i < n; i++) {

            while (p1 < p2 && j < n) {
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
            }
            minimo = min(minimo, diff(p0, p1, p2));
            p0 += arr[i];
            p1 -= arr[i];
            if (i == j) {
                j++;
                p1 += arr[j];
                p2 -= arr[j];
            }
        }
        return minimo;
}

int solverMio () {
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

        minimo = min(minimo, diff(p0, p1, p2));
        p0 += arr[i];
        p1 -= arr[i];
        if (i == j) {
            j++;
            p1 += arr[j];
            p2 -= arr[j];
        }
    }
    return minimo;
}

int main () {
    bool found = false;
    srand((unsigned)time(0));
    while (!found) {
        n = (rand()%4)+4;
        arr = (int*) malloc(sizeof(int)*n);
        for (int i = 0; i < n; i++) {
            arr[i] = (rand()%50)+1;
        }
        if (solverMio() == solverPablo()) {
            found = true;
            for (int i = 0; i < n; i++) {
                cout << arr[i] << " ";
            }
            cout << "\n";
        }
    }
}