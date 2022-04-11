import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        DirectedGraph g;
        int N, C, K;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("Ejercicios 2019\\C\\input.txt")));
                PrintWriter writer = new PrintWriter("Ejercicios 2019\\C\\output.txt", "UTF-8");) {
            String linea;
            while ((linea = br.readLine()) != null) { // mientras queden casos de prueba
                N = Integer.parseInt(linea);
                g = new DirectedGraph(N);
                linea = br.readLine();
                C = Integer.parseInt(linea);
                for (int i = 0; i < C; i++) {
                    String[] campos;
                    linea = br.readLine();
                    campos = linea.split(" ");
                    // indexamos en 0
                    g.addEdge(Integer.parseInt(campos[0]) - 1,
                            Integer.parseInt(campos[1]) - 1,
                            Integer.parseInt(campos[2]));
                }
                // debug: imprimir grafo
                // g.printadjacencylist();

                // caminos a hallar
                linea = br.readLine();
                K = Integer.parseInt(linea);
                for (int i = 0; i < K; i++) {
                    String[] campos;
                    linea = br.readLine();
                    campos = linea.split(" ");
                    String sol = g.solve(Integer.parseInt(campos[0]) - 1,
                            Integer.parseInt(campos[1]) - 1);
                    writer.println(sol);
                }
                writer.println("----");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}