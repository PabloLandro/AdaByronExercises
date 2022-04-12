import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {
    // primer nacional resuelto!
    // fue dificil pero bastante interesante
    // el tiempo asintótico del algoritmo es O(F), se podria mejorar la velocidad de
    // cada figura cambiando el planteamiento de la resolución de piezas pero así va
    // bastante bien
    // te dejo convertir todo esto a lambdas Xiana, ily <3
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
                Figura[] figuras = new Figura[N];
                for (int i = 0; i < N; i++) {
                    linea = br.readLine();
                    campos = linea.split(" ");
                    figuras[i] = new Figura(Integer.parseInt(campos[0]),
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

    private static int[] solve(int c, Figura[] figuras) {
        int[] mapa = new int[c]; // 0 2 3 4 1 0 0 ... (c alturas)
        // O(F)
        for (int i = 0; i < figuras.length; i++) {
            resolverFigura(mapa, figuras[i]);
        }
        return mapa;
    }

    private static void resolverFigura(int[] mapa, Figura figura) {
        // Hallamos alturas del mapa minimas y maximas en el area de caida (donde la
        // pieza no es 0)
        int alturaMinima = Integer.MAX_VALUE;
        int alturaMaxima = 0;
        // O(1)
        for (int col = 0; col < 4; col++) {
            if (figura.alturasMaximas[col] == 0)
                break; // las piezas van de izqda a dcha, podemos hacer break temprano

            if (alturaMinima > mapa[col + figura.columna])
                alturaMinima = mapa[col + figura.columna];
            if (alturaMaxima < mapa[col + figura.columna])
                alturaMinima = mapa[col + figura.columna];
        }

        // Normalmente aquí sale la alturaMinima, sin embargo si hay huecos raros tipo 1
        // 100 1 en el mapa, una pieza por muy irregular que sea
        // nunca será de más de 3 de alto, así que podemos aprovechar solo 3 (creo que
        // realmente 2 porque los palos verticales solo ocupan 1 col.) hacia abajo
        int altura = Math.max(alturaMinima, alturaMaxima - 3);
        // Refinamos altura de caida detectando colisiones y ascendiendo
        // O(1) por lo anterior, seguramente nunca habrá más de 4 iteraciones, aunque no
        // lo comprobé
        while (colision(mapa, figura, altura)) {
            altura++;
        }

        // Actualizamos las alturas de las columnas del mapa
        // O(1)
        for (int col = 0; col < 4; col++) {
            if (figura.alturasMaximas[col] == 0)
                break;// las piezas van de izqda a dcha, podemos hacer break temprano

            // La nueva altura será la altura de caida + la altura máxima de la pieza en
            // esa columna
            mapa[figura.columna + col] = altura + figura.alturasMaximas[col];
        }
    }

    public static boolean colision(int[] mapa, Figura figura, int altura) {
        // O(1)
        for (int col = 0; col < 4; col++) {
            // HAY COLISION SI:
            // Hay al menos una pieza en esta columna Y
            // La altura del mapa en esa columna sobrepasa o iguala la suma de la latura
            // actual más la altura mínima que necesita la pieza en esta columna
            if ((figura.alturasMinimas[col] > 0) &&
                    (mapa[col + figura.columna] >= figura.alturasMinimas[col] + altura))
                return true;
        }
        return false;
    }
}