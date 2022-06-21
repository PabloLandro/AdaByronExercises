#include <bits/stdc++.h>

using namespace std;

#pragma GCC optimize("Ofast,unroll-loops")
#pragma GCC target("avx,avx2,fma")

#define ar array
#define umap unordered_map
#define ll long long   // 64 bits
#define ld long double // 80 bits
#define sza(x) ((int)x.size())
#define all(a) (a).begin(), (a).end() // Todo el array, e.g. sort(all(a))

#define PI 3.1415926535897932384626433832795l
const int MAX_N = 1e5 + 5; // Valor de numero máximo de casos genérico
const ll MOD = 1e9 + 7;    // Para outputs grandes, a veces se piden darlos con este modulo. El modulo se puede aplicar en cada op. sin cambiar resultado
const ll INF = 1e9;        // Infinito
const ld EPS = 1e-9;       // Comparaciones de fp, e.g. if(abs(a-b) < EPS)

// Vectores y pares de enteros
typedef vector<int> vi;
typedef pair<int, int> pi;
#define F first
#define S second
#define PB push_back
#define MP make_pair

// Bucles
#define FOR(i, a, b) for (int i = a; i <= b; i++)
int N, indice, i, len;
string palabra;
int parejas;
int incompletas;
int sobran;

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(0);
    // Archivos I/O


    while (true)
    {
        cin >> N;
        if (N == 0)
            return 0;
        parejas = 0;
        incompletas = 0;
        sobran = 0;
        umap<string, int> especies; // Especie (sin sexo) -> Número (1 = macho, 2 = hembra, 3 = ambos)
        for (i = 0; i < N; i++)
        {
            cin >> palabra;
            char sexo = palabra.back();
            string especie = palabra.substr(0, palabra.length() - 1);
            int prev = especies[especie];
            if (prev != 0)
            {
                // La especie estaba en la lista
                if (sexo == 'o')
                {
                    if (prev == 1 || prev == 3) // Nuevo hombre
                    {
                        sobran++;
                    }
                    else // Hombre completa
                    {
                        especies[especie] = 3;
                        parejas++;
                        incompletas--;
                    }
                }
                else
                {
                    if (prev == 2 || prev == 3) // Nueva mujer
                    {
                        sobran++;
                    }
                    else // Mujer completa
                    {
                        especies[especie] = 3;
                        parejas++;
                        incompletas--;
                    }
                }
            }
            else
            {
                // La especie no estaba en la lista, no hemos leido a este animal antes
                if (sexo == 'o') // Hombre primero
                {
                        especies[especie] = 1;
                }
                else // Mujer primera
                {
                        especies[especie] = 2;
                }
                incompletas++;
            }
        }
        cout << parejas << " " << incompletas << " " << sobran << "\n";
    }
}