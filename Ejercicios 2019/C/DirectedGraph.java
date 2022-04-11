
import java.util.*;

class DirectedGraph {

    private LinkedList<LinkedList<WeightedEdge>> adj;

    public DirectedGraph(int size) {
        adj = new LinkedList<>();
        for (int i = 0; i < size; i++)
            adj.add(new LinkedList<>());
    }

    public void addEdge(int source, int dest, int weight) {
        adj.get(source).add(new WeightedEdge(dest, weight));
        adj.get(dest).add(new WeightedEdge(source, weight)); // EL GRAFO ES BIDIRECIONAL
    }

    /*
     * Usamos el algoritmo de Dijkstra primero con los pesos de las aristas y luego
     * con peso = 1 (simulando que no hay pesos)
     * para hallar el camino mas corto entre dos nodos. Esta no es la mejor solucion
     * ni de cerca tho, pero funciona
     */
    public String solve(int source, int dest) {
        // ty github copilot!
        // primero teniendo en cuenta los weights
        int[] dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        boolean[] visited = new boolean[adj.size()];
        visited[source] = true;
        int[] prev = new int[adj.size()];
        Arrays.fill(prev, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (WeightedEdge v : adj.get(u)) {
                if (dist[v.dest] > dist[u] + v.weight) {
                    dist[v.dest] = dist[u] + v.weight;
                    prev[v.dest] = u;
                    if (!visited[v.dest]) {
                        visited[v.dest] = true;
                        q.add(v.dest);
                    }
                }
            }
        }
        int destTemp = dest;
        int weightCount = 0;
        int totalPathWeight = dist[dest];
        while (prev[destTemp] != -1) {
            weightCount++;
            destTemp = prev[destTemp];
        }

        // Inicializamos de nuevo
        dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        visited = new boolean[adj.size()];
        visited[source] = true;
        prev = new int[adj.size()];
        Arrays.fill(prev, -1);
        q = new LinkedList<>();
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (WeightedEdge v : adj.get(u)) {
                // Ahora sin tener en cuenta los pesos: el weight siempre será 1
                if (dist[v.dest] > dist[u] + 1) {
                    dist[v.dest] = dist[u] + 1;
                    prev[v.dest] = u;
                    if (!visited[v.dest]) {
                        visited[v.dest] = true;
                        q.add(v.dest);
                    }
                }
            }
        }

        destTemp = dest;
        int edgesCount = 0;
        while (prev[destTemp] != -1) {
            edgesCount++;
            destTemp = prev[destTemp];
        }

        if (totalPathWeight == Integer.MAX_VALUE)
            return "SIN CAMINO";
        else if (edgesCount == weightCount)
            return totalPathWeight + " SI";
        else
            return totalPathWeight + " NO";
    }

    public void printadjacencylist() {
        for (int i = 0; i < adj.size(); ++i) {
            System.out.print((i + 1) + "->");
            for (WeightedEdge v : adj.get(i)) {
                System.out.print((v.dest + 1) + "(w=" + v.weight + ") ");
            }
            System.out.println();
        }
    }

    private class WeightedEdge {
        int dest;
        int weight;

        WeightedEdge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

}