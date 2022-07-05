#include <bits/stdc++.h>

using namespace std;

int main () {

    while (true) {
        int i, n;
        cin >> i >> n;
        if (i == 0 && n == 0)
            return 0;
        int arr[n];
        for (int j = 0; j < n; j++)
            cin >> arr[j];
        long long current = 0;
        for (int j = 0; j < i; j++) {
            current += arr[j];
        }
        long long maximo = current;
        for (int j = 0; j <= n-i; j++) {
            if (current > maximo)
                maximo = current;
            current += arr[j+i];
            current -= arr[j];
        }
        cout << maximo << "\n";
    }

}