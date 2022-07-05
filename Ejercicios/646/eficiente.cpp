#include <bits/stdc++.h>

using namespace std;

int *link;
int *size;
pair<int,int> *lista;
//vector<pair<int,int>> *adj;

int find (int x) {
    while (x != link[x]) x = link[x];
    return x;
}

bool same (int a, int b) {
    return find(a) == find(b);
}

void unite (int a, int b, int w) {
    a = find(a);
    b = find(b);
    if (size[a] < size[b]) {
        int aux = a;
        a = b;
        b = aux;
    }
    size[a] += size[b];
    link[b] = a;
    //adj[a].push_back({b, w});
    //adj[b].push_back({a, w});

    if (lista[a].first != -1) {
        int aux = a;
        a = b;
        b = aux;
    }

    lista[a] = {b,w};

}

int main () {
    int V, E;
    cin >> V >> E;
    link = (int*) malloc(sizeof(int)*V);
    size = (int*) malloc(sizeof(int)*V);
    auto cmp = [](tuple<int,int,int> left, tuple<int,int,int> right) { return get<2>(left) > get<2>(right); };
    priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, decltype(cmp)> q(cmp);
    //adj = new vector<pair<int,int>>[V];
    lista = new pair<int,int>[V];
    for (int i = 0; i < E; i++) {
        int a, b, w;
        cin >> a >> b >> w;
        q.push(make_tuple(a-1,b-1,w));
    }

    for (int i = 0; i < V; i++) {
        link[i] = i;
        size[i] = 1;
    }

    for (int i = 0; i < V; i++)
        lista[i] = {-1,0};

    for (int i = 0; i < E; i++) {
        tuple<int,int,int> aux = q.top();
        q.pop();
        int a = get<0>(aux);
        int b = get<1>(aux);
        if (!same(a, b)) unite(a, b, get<2>(aux));
    }
    free(size);
    free(link);
    
    int K;
    cin >> K;
    for (int i = 0; i < K; i++) {
        int a, b, limit;
        cin >> a >> b >> limit;
        a--;b--;

        bool valid = true;
        pair<int, int> u;
        int c = a;
        do {
            if (c == -1) {
                valid = false;
                goto end;
            }
            u = lista[c];
            if (u.second < limit) {
                valid = false;
                goto end;
            }
            c = u.first;
        } while (c != b);
        end:;
        if (valid) {
            cout << "SI\n";
            goto endcase;
        }
        valid = true;
        c = b;
        do {
            u = lista[c];
            if (c == -1) {
                valid = false;
                goto end2;
            }
            if (u.second < limit) {
                valid = false;
                goto end2;
            }
            c = u.first;
        } while (c != a);
        end2:;
        if (valid) {
            cout << "SI\n";
            goto endcase;
        }
        /*bool visited[V];
        for (int i = 0; i < V; i++) visited[i] = false;
        for (pair<int, int> u: adj[a]) {
            visited[a] = true;
            int minimo = u.second;
            bool found = false;
            int c = u.first;
            for (int j = 0; j < V; j++) {
                for (pair<int, int> v: adj[c]) {
                    if (visited[v.first]) continue;
                    visited[v.first] = true;
                    minimo = min(minimo, v.second);
                    if (v.first == b) {
                        found = true;
                        goto end;
                    }
                    c = v.first;
                }
            }
            end:;
            if (found && limit <= minimo) {
                cout << "SI\n";
                goto endcase;
            }
        }
        cout << "NO\n";
        */
        cout << "NO\n";
        endcase:;
        
        
    }

}