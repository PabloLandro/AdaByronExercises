import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("Ejercicios 2018 Final\\B\\input.txt")));
                PrintWriter writer = new PrintWriter("Ejercicios 2018 Final\\B\\output.txt", "UTF-8")) {
            String primeraLinea, linea;
            String[] campos;
            while ((primeraLinea = br.readLine()) != null) { // mientras queden casos de prueba nuevos
                int C, N;
                campos = primeraLinea.split(" ");
                if (campos.length == 2 && Integer.parseInt(campos[0]) == 0 && Integer.parseInt(campos[1]) == 0)
                    break; // fin input

                C = Integer.parseInt(campos[0]);
                N = Integer.parseInt(campos[1]);
                FiguraP[] figuras = new FiguraP[N];
                for (int i = 0; i < N; i++) {
                    linea = br.readLine();
                    campos = linea.split(" ");
                    figuras[i] = new FiguraP(Integer.parseInt(campos[0]),
                            Integer.parseInt(campos[1]),
                            Integer.parseInt(campos[2]) - 1);
                }
                int[] solucion = solve(C, figuras);
                for (int valorCol : solucion) {
                    writer.print(valorCol + " ");
                }
                writer.println("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static int[] solve(int c, FiguraP[] figuras) {
        int[] map = new int[c]; // 0 2 3 4 1 0 0 ... (c alturas)

        for (int i = 0; i < figuras.length; i++) {
            resolverFigura(map, figuras[i]);
        }

        return map;
    }

    private static void resolverFigura(int[] map, FiguraP figura) {
        // Hallar altura minima de caida
        // X
        // X X X
        // 2 3 2 --> 3
        int c = map.length;
        int alturaMinima = map[figura.columna];
        for (int j = 1; j <= 3; j++) { // 1 2 3 a la izqda
            // saltamos columnas donde no hay piezas
            boolean hayPieza = false;
            for (int i = 0; i < 4; i++) {
                if (figura.forma[i][j] == 1) {
                    hayPieza = true;
                    break;
                }
            }
            if (!hayPieza) // ya no hay mas piezas aqui
                break;
            if (alturaMinima < map[j + figura.columna]) {
                alturaMinima = map[j + figura.columna];
            }
        }
        int alturaActual = alturaMinima;
        // Detectar colision en altura minima de caida
        while (colision(map, c, figura, alturaActual)) {
            alturaActual++;
        }

        // Actualizar las alturas de map
        // 1. Hallar las alturas maximas con 1 en cada columna
        // 2. Actualizar map[j] = alturaMax[j] + alturaActual ( -1 )

        // O O O A = 3; C = {2, 1, 3}
        // O X matriz[1][0] = matriz[1][1] = matriz[1][2] = matriz[0][1] = 1;
        // X X
        // X X X
        // 2 1 3 alturas mapa
        // 2 2 2 altura maximas pieza
        // 3 altura actual
        // 4 4 4
        for (int j = 0; j < 4; j++) {
            int alturaMas = 0;
            for (int i = 0; i < 4; i++) {
                if (figura.forma[i][j] == 0)
                    continue;
                alturaMas = i + 1;
            }
            if (alturaMas == 0)
                continue;
            map[figura.columna + j] = alturaActual + alturaMas;
        }
    }

    public static boolean colision(int[] map, int c, FiguraP figura, int alturaActual) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // O O O A = 3; C = {2, 1, 3}
                // O X matriz[1][0] = matriz[1][1] = matriz[1][2] = matriz[0][1] = 1;
                // X X
                // X X X

                // X
                // O O X
                // X O X
                // X X X
                if (figura.forma[i][j] == 0)
                    continue; // si tenemos un 0, seguimos

                if (map[figura.columna + j] >= i + alturaActual)
                    return true;
            }
        }
        return false;
    }
}