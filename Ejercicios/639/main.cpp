#include <iostream>

using namespace std;

int diff (int p0, int p1, int p2) {
    int aux = max(abs(p0-p1), abs(p1-p2));
    return max(aux, abs(p0-p2));
}

int main () {
    while (true) {
        int n;
        if (!cin) {
            return 0;
        }
        cin >> n;
        int arr[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            cin >> arr[i];
            total += arr[i];
        }
        int p0 = arr[0];
        int p1 = arr[1];
        int p2 = total - p0 - p1;

        int j = 1;
        while (j < n-2 && abs(p2-2*arr[j+1]-p1) <= abs(p1-p2)) {
            p1 += arr[j];
            p2 -= arr[j];
            j++;
        }

        int minimo = diff(p0, p1, p2);

        for (int i = 1; i < n-2; i++) {
            p0 += arr[i];
            p1 -= arr[i];
            if (i == j) {
                j++;
                p1 += arr[j];
                p2 -= arr[j];
            }
            if (p2 > p1) {
                while (j < n-2 && abs(p2-2*arr[j+1]-p1) <= abs(p1-p2)) {
                    p1 += arr[j];
                    p2 -= arr[j];
                    j++;
                }
            } else {
                while (j > i+1 && abs(p2+2*arr[j+1]-p1) <= abs(p1-p2)) {
                    p1 -= arr[j];
                    p2 += arr[j];
                    j--;
                }
            }
            minimo = min(minimo, diff(p0, p1, p2));
        }
        cout << minimo << "\n";
    }
}