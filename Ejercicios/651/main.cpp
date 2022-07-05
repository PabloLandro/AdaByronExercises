#include <bits/stdc++.h>

using namespace std;

vector<tuple<bool,bool,bool>> dosPosiciones;    //Cada bool representa F(defensa) C(centro) D(delantero)
int tresPosiciones;
int def, cen, del;

bool solve (int k) {
    if (def+cen+del <= tresPosiciones)
        return true;
    if (k == dosPosiciones.size())
        return def+cen+del <= tresPosiciones;
    bool call = false;
    for (int i = 0; i < 3; i++) {
        if (i == 0 && get<0>(dosPosiciones[k]) && def > 0) {
            def--;
            call = true;
            if (solve(k+1)) return true;
            def++;
        } else if (i == 1 && get<1>(dosPosiciones[k]) && cen > 0) {
            cen--;
            call = true;
            if (solve(k+1)) return true;
            cen++;
        } else if (i == 2 && get<2>(dosPosiciones[k]) && del > 0){
            del--;
            call = true;
            if (solve(k+1)) return true;
            del++;
        }

    }
    if (!call)
        return solve(k+1);
    return false;
}

int main () {
    while (true) {
        cin >> def >> cen >> del;

        if (def == 0 && cen == 0 && del == 0)
            return 0;
        int N;
        cin >> N;
        
        dosPosiciones.clear();
        dosPosiciones.shrink_to_fit();
        tresPosiciones = 0;

        for (int i = 0; i < N; i++) {
            string aux;
            cin >> aux;
            bool F = false, C = false, D = false;
            
            if (aux.length() == 1) {
                switch (aux.at(0)) {
                    case 'F':
                        def--;
                        break;
                    case 'C':
                        cen--;
                        break;
                    case 'D':
                        del--;
                        break;
                }
            } else if (aux.length() == 2){
                for (char tmp: aux) {
                    switch (tmp) {
                        case 'F':
                            F=true;
                            break;
                        case 'C':
                            C=true;
                            break;
                        case 'D':
                            D=true;
                            break;
                    }
                }
                dosPosiciones.push_back(make_tuple(F,C,D));
            } else {
                tresPosiciones++;
            }
        }
        def = max(def, 0); cen = max(cen, 0); del = max(del, 0);
        if (solve(0))
            cout << "SI\n";
        else
            cout << "NO\n";

    }
}