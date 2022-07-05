#include <bits/stdc++.h>

using namespace std;

void solve(int N)
{
    int crio;
    vector<pair<int, int>> parejas;   // first -> menor, second -> mayor

    int estado = 0; 

    cin >> crio;
    parejas.push_back({crio, crio});

    for (int i = 1; i < N; i++){
        cin >> crio;

        if (estado == 2) continue;
        else if (estado == 0){
            if (crio < parejas.back().first){
                parejas.back().first = crio;
            } else if (crio > parejas.back().first){
                parejas.back().second = crio;
                estado = 1;
            }

            for (int j = 0; j < parejas.size() - 1; j++){
                if (crio > parejas[j].first && crio < parejas[j].second){
                    estado = 2;
                    //goto end;
                    break;
                } else if (crio > parejas[j].second){
                    parejas[j].second = crio;
                }
            }
        } else if (estado == 1){
            if (crio < parejas.back().first){
                parejas.push_back({crio, crio});
                estado = 0;
                continue;
            }
            
            for (int j = 0; j < parejas.size(); j++){
                if (crio > parejas[j].first && crio < parejas[j].second){
                    estado = 2;
                    //goto end;
                    break;
                } else if (crio > parejas[j].second){
                    parejas[j].second = crio;
                }
            }
        }
    }
    end:;
    if (estado != 2) cout << "SIEMPRE PREMIO\n";
    else cout << "ELEGIR OTRA\n";
}

int main () {
    while (true) {
        if (!cin)
            return 0;
        int N;
        cin >> N;
        solve(N);
    }
}