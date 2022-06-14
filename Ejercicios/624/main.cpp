#include <iostream>

using namespace std;

int N, count, ans, aux, caseIndex;
int arr[100];

int main () {

    caseIndex = 1;
    while (true) {
        cin >> N;
        if (N == 0)
            return 0;
        count = 0;
        ans = 0;
        for (int i = 0; i < N; i++) {
            cin >> aux;
            aux--;
            if (arr[aux] != caseIndex) {
                arr[aux] = caseIndex;
                count++;
                if (count > ans)
                    ans = count;
            } else {
                count--;
                arr[aux] = 0;
            }

        }
        cout << ans << endl;
        caseIndex++;
    }

}

