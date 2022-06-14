#include <iostream>
#include <bits/stdc++.h>

using namespace std;

int cases, N, li, lj, lk;
int *length;


int main() {
    cin >> cases;
    while (cases-- != 0) {
        cin >> N;
        length = (int*) malloc (N * sizeof(int));
        for (int i = 0; i < N; i++) {
            cin >> length[i];
        }
        sort(length, length+ N*(sizeof(int*) / sizeof(int)));
        for (int i = N-1; i >= 2; i--) {
            li = (i == N-1) ? length[N-1] : lj;
            lj = (i == N-1) ? length[N-2] : lk;
            lk = length[i-2];
            if (li < lj + lk) {
                cout << li+lj+lk << endl;
                goto end;
            }
        }
        cout << "NO HAY NINGUNO" << endl;
        end:;
        free(length);
    }
}

