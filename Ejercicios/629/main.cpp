#include <iostream>

using namespace std;

int N;
int dist;
int casas;
int *arr;
int i;
int prevLen;
int nextLen;

int main () {

    while (true) {
        cin >> N;
        casas = 0;
        if (N == 0) {
            return 0;
        }
        arr = (int*) malloc (sizeof(int) * N);

        for (i = 0; i < N; i++) {
            cin >> arr[i];
        }
        prevLen = N-1;
        for (i = N-1; i >= 0; i--) {
            prevLen = min(i, prevLen);
            nextLen = max(i - arr[i], 0);
            if (nextLen < prevLen && arr[i] != 0) {
                casas += prevLen - nextLen;
                prevLen = nextLen;
            }
        }
        cout << casas << endl;
        free(arr);
    }

}