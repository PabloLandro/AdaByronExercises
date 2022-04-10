import java.io.*;
import java.util.*;

//main class
public class Main {

    public static void main(String[] args) {
        File file = new File("input.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        File file2 = new File("output.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineSplit = line.split(" ");
            int nEstudiantes = Integer.parseInt(lineSplit[0]);
            int N = Integer.parseInt(lineSplit[1]);
            int M = Integer.parseInt(lineSplit[2]);
            int[][] matriz = new int[N][nEstudiantes];
            int[] solucion = new int[N];
            int[] notas = new int[nEstudiantes];

            // Transformamos los datos del archivo a un sistema lineal para poder aplicar eliminación
            // gaussiana y despejar las notas de estudiantes
            leerSistema(matriz, solucion, nEstudiantes, N, scanner);

            // Hacemos el algoritmo de eliminación gaussiana
            resolverMatriz(matriz, solucion, nEstudiantes, N);

            // Guardamos las notas obtenidas en un array
            obtenerNotas(matriz, solucion, notas, nEstudiantes, N);
            
            // Imprimimos el array de las notas de estudiantes
            for (int i = 0; i < nEstudiantes; i++) {
                System.out.print(notas[i] + " ");
            }
            System.out.print("\n\n");

            // Leemos ahora los examenes para los que tenemos que dar respuesta
            for (int i = 0; i < M; i++) {
                line = scanner.nextLine();
                lineSplit = line.split(" ");
                int estudiante1 = Integer.parseInt(lineSplit[1])-1;   // Restamos 1 porque se empiezan a enumerar en 1
                try {
                    // Si solo tiene 2 elementos es un exmanen individual, la respuesta es inmediata
                    // si se conoce la nota del estudiante, la escribimos, si no, "CUIDADO"
                    if (lineSplit.length == 2) {
                        int nota = notas[estudiante1];
                        if (nota != -1) {
                            fw.write(nota + "\n");
                        } else {
                            fw.write("CUIDADO\n");
                        }
                    } else {    // Si tiene más de 2 argumentos, entonces el examen es hecho por parejas
                        int estudiante2 = Integer.parseInt(lineSplit[2])-1;
                        int nota1 = notas[estudiante1];
                        int nota2 = notas[estudiante2];
                        // Si alguna de las dos no es conocida, entonces no fue despejada por la eliminación
                        // gaussiana, pero aún cabe la posibilidad de obtener la nota del examen mediante
                        // combinaciones lineales de filas de la matriz que no hayan sido despejadas
                        if (nota1 == -1 || nota2 == -1) {
                            int nota = notaPareja(matriz, solucion, notas, estudiante1, estudiante2, nEstudiantes, N);
                            // Si aún así, la nota no es conocida, imprimimos "CUIDADO"
                            if (nota == -1)
                                fw.write("CUIDADO\n");
                            else {
                                fw.write(nota + "\n");
                            }
                        } else {
                            fw.write(nota1+nota2 + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void leerSistema(int[][] matriz, int[] solucion, int nEstudiantes, int N, Scanner scanner) {
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ");
            if (splitLine.length == 2) {
                int estudiante = Integer.parseInt(splitLine[1]) - 1;
                int nota = Integer.parseInt(splitLine[2]);
                matriz[i][estudiante] = 1;
                solucion[i] = nota;
            } else {
                int estudiante1 = Integer.parseInt(splitLine[1]) - 1;
                int estudiante2 = Integer.parseInt(splitLine[2]) - 1;
                int nota = Integer.parseInt(splitLine[3]);
                matriz[i][estudiante1] = 1;
                matriz[i][estudiante2] = 1;
                solucion[i] = nota;
            }
        }
    }

    public static void resolverMatriz(int[][] matriz, int[] solucion, int nEstudiantes, int N) {
        for (int i = 0; i < N; i++) {
            // Buscamos una fila con pivote mínimo distinto de 0
            int pivote = 200001;    // Inicializamos una unidad por encima del máximo para poder hacer
                                    // la comprobación del mínimo
            int indicePivote = -1;
            // Recorremos la columna i-ésima para encontrar el elemento mínimo
            for (int k = i; k < N; k++) {
                int elemento = matriz[k][i];
                if (elemento != 0 && elemento < pivote) {
                    indicePivote = k;
                    pivote = elemento;
                }
            }
            // Intercambiamos las filas para eliminar utilizando el pivote mínimo
            if (indicePivote != -1) {
                int[] aux = matriz[i];
                matriz[i] = matriz[indicePivote];
                matriz[indicePivote] = aux;
                int aux2 = solucion[i]; // Finally: aux2
                solucion[i] = solucion[indicePivote];
                solucion[indicePivote] = aux2;
            }
            System.out.println("Swap");
            imprimirSistema(matriz, solucion, nEstudiantes, N);


            // Eliminamos los elementos de la columna i-ésima
            // Comprobamos que es distinto de 0 (se puede dar el caso si no se tienen examenes del
            // estudiante)
            if (matriz[i][i] != 0) {
                // Recorremos todas las filas por debajo de la del pivote
                for (int k = i + 1; k < N; k++) {
                    int factor = matriz[k][i] / matriz[i][i];
                    // Modificamos toda la fila
                    for (int j = i; j < nEstudiantes; j++) {
                        matriz[k][j] -= factor * matriz[i][j];
                    }
                    // Cambiamos la solución del sistema
                    solucion[k] -= factor * solucion[i];
                }
            }
            System.out.println("Elimination");
            imprimirSistema(matriz, solucion, nEstudiantes, N);
        }

        // Ahora vamos a limpiar el sistema utilizando las filas que tienen un solo elemento
        // Repetimos el bucle hasta que no sea posible simplificar más
        boolean changes = true;
        while (changes) {
            changes = false;
            // Recorremos las filas y buscamos las que solo tienen 1 elemento
            for (int i = 0; i < N; i++) {
                int elementos = 0;
                for (int j = 0; j < nEstudiantes; j++) {
                    if (matriz[i][j] != 0)
                        elementos++;
                }
                if (elementos==1) {
                    // Ahora buscamos en las que el elemento de la columna i es no nulo, y por tanto
                    // lo podemos eliminar
                    // Esto es porque el elemento que encontramos en el paso anterior está en la posición (i, i)
                    for (int j = 0; j < N; j++) {
                        if (j != i && matriz[j][i] != 0) {
                            float factor = (float)((float)matriz[j][i] / (float)matriz[i][i]);
                            // Eliminamos el elemento
                            matriz[j][i] = 0;
                            // Modificamos la solución
                            solucion[j] -= factor * (float)solucion[i];
                            changes = true;
                        }
                    }
                }
            }
        }

        System.out.println("Clean");
        imprimirSistema(matriz, solucion, nEstudiantes, N);

    }

    // Imprime la matriz del sistema junto el vector solución
    public static void imprimirSistema(int[][] matriz, int[] solucion, int nEstudiantes, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < nEstudiantes; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.print("    " + solucion[i] + "\n");
        }
        System.out.print("\n");
    }


    // Esta función busca las filas en las que solo hay un elemento no nulo y por tanto, podemos conocer la
    // nota de ese estudiante
    public static void obtenerNotas(int[][] matriz, int[] solucion, int[] notas, int nEstudiantes, int N) {
        
        for (int j = 0; j < nEstudiantes; j++) {
            notas[j] = -1;
            for (int i = 0; i < N; i++) {
                if (matriz[i][j] != 0) {
                    boolean valido = true;
                    for (int k = 0; k < nEstudiantes; k++) {
                        if (matriz[i][k] != 0 && k != j)
                            valido = false;
                    }
                    if (valido) // Puede que el elemento de la matriz sea distinto de 1, por lo que dividimos
                        notas[j] = solucion[i] / matriz[i][j];
                }
            }
        }
    }

    // Obtener nota difícil de calcular
    // Si tenemos que calcular la nota de un examen de dos estudiantes de los que no sabemos la nota despejada
    // tendremos que obtener el resultado mediante combinaciones lineales de las filas de la matriz
    public static int notaPareja(int[][] matriz, int[] solucion, int[]notas, int estudiante1, int estudiante2, int nEstudiantes, int N) {
        int out = -1;

        return out;
    }

}