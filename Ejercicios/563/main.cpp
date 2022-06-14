#include <iostream>
#include <algorithm>
#include <cctype>
#include <string>

using namespace std;


int main (int argc, char *argv[]) {

    ios::sync_with_stdio(0);
    cin.tie(0);

    int N;

    while (cin >> N) {
        for (int i = 0; i < N; i++) {
            string palabra;
            cin >> palabra;
            string lower;
            // To lower case
            transform(palabra.begin(), palabra.end(), lower.begin(),
                        [](unsigned char c){ return std::tolower(c); });
        }
    }


}