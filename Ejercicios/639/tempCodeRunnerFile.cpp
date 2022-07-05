int diff (int p0, int p1, int p2) {
    int aux = max(abs(p0-p1), abs(p1-p2));
    return max(aux, abs(p0-p2));
}