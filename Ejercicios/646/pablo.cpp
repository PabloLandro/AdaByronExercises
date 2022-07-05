#include <bits/stdc++.h>
using namespace std;

vector<vector<pair<int, int>>> adj; // no dirigido, con pesos

// Devuelve el ancho mínimo del camino más ancho de a hasta b
int widest_path(int N, int from, int to)
{
    // En primer lugar, hallar MAXIMUM spanning tree, ayer hicimos MINIMUM
    // Para hallarlo, solo multiplicar bordes por -1 (los leemos así)

    vector<bool> visitados(N, false);                                                  // Nodos visitados
    vector<int> conexiones(N, -1);                                                     // Conexiones finales
    vector<int> value(N, INT_MAX);                                                     // Peso minimo para un nodo
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> q; // Para extraer pesos minimos, formato (PESO, NODO)

    q.push({0, from}); // Meter el nodo fuente 0 y su peso 0
    value[from] = 0;     // El peso minimo para el nodo 0 es 0

    while (!q.empty())
    {
        int a = q.top().second; // Coger el primer nodo de la lista
        q.pop();
        visitados[a] = true; // Marcarlo como visitado
        for (auto b : adj[a])
        
            // Todos los vecinos w de a
        {
            int w = b.second;     // Peso de la conexión a->b
            int v = b.first; // Número de nodo
            if (!visitados[v] && value[v] > w)
            {
                value[v] = w;
                conexiones[v] = a;
                q.push(make_pair(value[v], v)); // Actualizamos la cola
            }
        }
    }

    // El camino con mayor ancho es aquel marcado por el MST, hallar el ancho máximo
    if (value[to] == INT_MAX)
        return INT_MIN; // No hay camino

    int anchoMax = INT_MAX;
    while (to != from)
    { 
        int peso = 0;
        vector<pair<int, int>> edges = adj[conexiones[to]];
        for (int i = 0; i < edges.size(); i++)
        {
            if (edges[i].first == to)
            {
                anchoMax = min(anchoMax, -edges[i].second);
                break;
            }
        }
        to = conexiones[to];
    }
    return anchoMax;
}

bool solve()
{
    int V = 0, E = 0, tmp1 = 0, tmp2 = 0, tmp3 = 0;
    cin >> V >> E;
    if (!V)
        return false;
    adj.clear();
    adj.resize(V);
    for (int i = 0; i < E; i++)
    {
        cin >> tmp1 >> tmp2 >> tmp3;
        adj[tmp1 - 1].push_back({tmp2 - 1, -tmp3});
        adj[tmp2 - 1].push_back({tmp1 - 1, -tmp3});
    }
    int K = 0;
    cin >> K;
    for (int i = 0; i < K; i++)
    {
        cin >> tmp1 >> tmp2 >> tmp3;
        if (tmp1 == tmp2) // Caso trivial a-->a
            cout << "SI\n";
        else if (tmp3 > widest_path(V, tmp1 - 1, tmp2 - 1)) // El camion es demasiado grande
            cout << "NO\n";
        else
            cout << "SI\n";
    }
    return true;
}

int main()
{
    // Optimizacion I/O
    ios_base::sync_with_stdio(0);
    cout.tie(0);

    while (solve())
        ;
}