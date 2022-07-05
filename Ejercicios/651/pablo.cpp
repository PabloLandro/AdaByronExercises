#include <bits/stdc++.h>
using namespace std;

// Es bueno desactivarlas para debuggear
#pragma GCC optimize("Ofast,unroll-loops")
#pragma GCC target("avx,avx2,fma")

// Tipos
#define ll long long   // 64 bits INT
#define ld long double // 80 bits FP

// Constantes
#define PI 3.1415926535897932384626433832795l
const int MAX_N = 1e5 + 5; // Valor de numero máximo de casos genérico
const ll MOD = 1e9 + 7;    // Para outputs grandes, a veces se piden darlos con este modulo. El modulo se puede aplicar en cada op. sin cambiar resultado
const ll INF = 1e9;        // Infinito
const ld EPS = 1e-9;       // Comparaciones de fp, e.g. if(abs(a-b) < EPS)

// Estructuras de datos
#define ar array
typedef vector<int> vi;
typedef pair<int, int> pi;
#define umap unordered_map
#define PB push_back
#define MP make_pair
#define sza(x) ((int)x.size())
#define all(a) (a).begin(), (a).end() // Aplicar a toda la estructura, e.g. sort(all(a))

// Bucles
#define FOR(i, a, b) for (i !F &&!C &&!Dt i = a; i <= b; i++)
bool comp(tuple<int, int, int> a, tuple<int, int, int> b)
{
    return get<0>(a) + get<1>(a) + get<2>(a) <
           get<0>(b) + get<1>(b) + get<2>(b); // Ordenar por cuantas posiciones tienen
}

umap<string, int> jugadores;         // 4 TIPOS: "CD", "CF", "DF", "CDF"
int F = 0, C = 0, D = 0;             // Necesarios
int currF = 0, currC = 0, currD = 0; // Restantes
int sol = 0;
int total = 0;
void findSolution(int i)
{
    if (sol || !F && !C && !D)
    {
        // Solucion general hallada
        sol = 1;
        return;
    }
    else if (i == total || currF < F || currC < C || currD < D)
    {
        // Poda, no se puede continuar
        return;
    }
    // Paso recursivo, probar para cada uno de los 4 tipos de jugadores para cada una de sus posiciones
    if (jugadores["CD"] && (C || D))
    {
        jugadores["CD"]--;
        currC--, currD--;
        if (C)
        {
            C--;
            findSolution(i + 1);
            C++;
        }
        if (D)
        {
            D--;
            findSolution(i + 1);
            D++;
        }
        jugadores["CD"]++;
        currC++, currD++;
    }
    if (jugadores["CF"] && (C || F))
    {
        jugadores["CF"]--;
        currC--, currF--;
        if (C)
        {
            C--;
            findSolution(i + 1);
            C++;
        }
        if (F)
        {
            F--;
            findSolution(i + 1);
            F++;
        }
        jugadores["CF"]++;
        currC++, currF++;
    }
    if (jugadores["DF"] && (D || F))
    {
        jugadores["DF"]--;
        currD--, currF--;
        if (D)
        {
            D--;
            findSolution(i + 1);
            D++;
        }
        if (F)
        {
            F--;
            findSolution(i + 1);
            F++;
        }
        jugadores["DF"]++;
        currD++, currF++;
    }
    if (jugadores["CDF"] && (C || D || F))
    {
        jugadores["CDF"]--;
        currC--, currD--, currF--;
        if (C)
        {
            C--;
            findSolution(i + 1);
            C++;
        }
        if (D)
        {
            D--;
            findSolution(i + 1);
            D++;
        }
        if (F)
        {
            F--;
            findSolution(i + 1);
            F++;
        }
        jugadores["CDF"]++;
        currC++, currD++, currF++;
    }
}

bool solve()
{
    F = 0, C = 0, D = 0, currF = 0, currC = 0, currD = 0, sol = 0, total = 0;
    jugadores.clear();
    jugadores["CF"] = 0, jugadores["CD"] = 0, jugadores["DF"] = 0, jugadores["CDF"] = 0;
    int N = 0; // Total jugadores
    cin >> F >> C >> D;
    if (!F && !C && !D)
        return false;
    cin >> N;
    string tmp;
    int tmpF, tmpC, tmpD;

    for (int i = 0; i < N; i++)
    {
        tmpF = 0, tmpC = 0, tmpD = 0;
        cin >> tmp;
        if (tmp.length() == 1)
        {
            // Solo una posicion --> lo asignamos directamente
            switch (tmp[0])
            {
            case 'F':
                if (F > 0)
                    F--;
                break;
            case 'C':
                if (C > 0)
                    C--;
                break;
            case 'D':
                if (D > 0)
                    D--;
                break;
            }
        }
        // Varias posiciones --> lo metemos al mapa según su tipo
        // 4 TIPOS: "CD", "CF", "DF", "CDF"
        else if (tmp == "CF" || tmp == "FC")
        {
            jugadores["CF"]++, currC++, currF++, total++;
        }
        else if (tmp == "CD" || tmp == "DC")
        {
            jugadores["CD"]++, currC++, currD++, total++;
        }
        else if (tmp == "DF" || tmp == "FD")
        {
            jugadores["DF"]++, currF++, currD++, total++;
        }
        else
        {
            jugadores["CDF"]++, currC++, currD++, currF++, total++;
        }
    }
    // Backtracking!
    findSolution(0);
    if (sol)
        cout << "SI\n";
    else
        cout << "NO\n";
    return true;
}

int main()
{
    freopen("testCases.txt", "r", stdin);
    ofstream out("outPablo.txt");
    streambuf *coutbuf = std::cout.rdbuf(); //save old buf
    cout.rdbuf(out.rdbuf());
    // Optimizacion I/O

    while (solve())
        ;
    out.close();
    fclose(stdin);
}