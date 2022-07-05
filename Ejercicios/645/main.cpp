#include <bits/stdc++.h>

using namespace std;

int main () {
    while (true) {
        int I, P;
        cin >> I >> P;
        if (!cin)
            return 0;
        //Calcularemos el árbol de menor coste
        vector<bool> visitada(I, false);
        vector<int> presupuesto(I, INT_MAX);  //Del puente para llegar a la isla desde la anterior en el árbol
        vector<int> conexion(I, 0);
        vector<pair<int,int>> adj[I];
        while (P-- != 0) {
            int a, b, w;
            cin >> a >> b >> w;
            a--;b--;
            adj[a].push_back({b,w});
            adj[b].push_back({a,w});
        }
        presupuesto[0] = 0;
        priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> q;
        q.push({0,0});
        while (!q.empty()) {
            pair<int, int> puenteAnterior = q.top();
            q.pop();
            int anterior = puenteAnterior.second;
            visitada[anterior] = true;
            for (pair<int,int> puente: adj[anterior]) {
                int siguiente = puente.first;
                int coste = puente.second;
                if (presupuesto[siguiente] > coste && !visitada[siguiente]) {
                    presupuesto[siguiente] = coste;
                    conexion[siguiente] = anterior;
                    q.push({coste, siguiente});
                }
                //cout << "anterior: " << anterior << "\tsiguiente: " << siguiente << "\n";
            }
        }
        int coste = 0;
        for (int i = 0; i < I; i++) {
            if (presupuesto[i] == INT_MAX) {
                cout << "No hay puentes suficientes\n";
                goto endcase;
            }
            coste += presupuesto[i];
        }
        cout << coste << "\n";
        endcase:;
    }
}