#include <iostream>

using namespace std;

int obtenerPuntos(char letra);

int main () {
    int N;
    cin >> N;
    string entrada;
    getline(cin, entrada);
    while (N-- != 0) {
        getline (cin, entrada);
        int len = entrada.length();
        int count = 0;
        char previous = '.';
        for (int i = 0; i < len; i++) {
            char current = entrada.at(i);
            if (current != ' ') {
                count += obtenerPuntos(current);
                if (previous != '.') {
                    if (previous == ' ')
                        count += 5;
                    else
                        count += 3;
                }
            }
            previous = entrada.at(i);
        }
        cout << count << "\n";
    }
}

int obtenerPuntos(char letra) {
    switch (letra) {
        case ('A'):
            return 5;
        case ('B'):
            return 9;
        case ('C'):
            return 11;
        case ('D'):
            return 7;
        case ('E'):
            return 1;
        case ('F'):
            return 9;
        case ('G'):
            return 9;
        case ('H'):
            return 7;
        case ('I'):
            return 3;
        case ('J'):
            return 13;
        case ('K'):
            return 9;
        case ('L'):
            return 9;
        case ('M'):
            return 7;
        case ('N'):
            return 5;
        case ('O'):
            return 11;
        case ('P'):
            return 11;
        case ('Q'):
            return 13;
        case ('R'):
            return 7;
        case ('S'):
            return 5;
        case ('T'):
            return 3;
        case ('U'):
            return 7;
        case ('V'):
            return 9;
        case ('W'):
            return 9;
        case ('X'):
            return 11;
        case ('Y'):
            return 13;
        case ('Z'):
            return 11;
        case ('!'):
            return 19;
        case ('?'):
            return 15;
            
            
    }
}