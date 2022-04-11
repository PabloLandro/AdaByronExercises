import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("Ejercicios 2019\\D\\input.txt")));
                PrintWriter writer = new PrintWriter("Ejercicios 2019\\D\\output.txt", "UTF-8");) {
            int N, A, B;
            String primeraLinea, linea;
            String[] campos;
            while ((primeraLinea = br.readLine()) != null) { // mientras queden casos de prueba nuevos
                campos = primeraLinea.split(" ");
                N = Integer.parseInt(campos[0]);
                A = Integer.parseInt(campos[1]);
                B = Integer.parseInt(campos[2]);
                int[] cargas9V = new int[A];
                int[] cargas15V = new int[B];
                linea = br.readLine();
                campos = linea.split(" ");
                for (int i = 0; i < A; i++) {
                    cargas9V[i] = Integer.parseInt(campos[i]);
                }
                linea = br.readLine();
                campos = linea.split(" ");
                for (int i = 0; i < B; i++) {
                    cargas15V[i] = Integer.parseInt(campos[i]);
                }
                List<Integer> horasSabado = solve(N, A, B, cargas9V, cargas15V);
                for (int horas : horasSabado) {
                    writer.print(horas + " ");
                }
                writer.println("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static List<Integer> solve(int n, int a, int b, int[] cargas9v, int[] cargas15v) {
        List<Integer> horasSabado = new ArrayList<>();
        // Ordenar cargas9v y cargas15v
        sortDescending(cargas9v);
        sortDescending(cargas15v);
        do {
            // Hacer volar drones
            int horasSabadoActual = 0;
            for (int i = 0; i < Math.min(a, Math.min(b, n)); i++) {
                int horasDron = Math.min(cargas9v[i], cargas15v[i]);
                if (horasDron == 0)
                    break;
                cargas9v[i] -= horasDron;
                cargas15v[i] -= horasDron;
                horasSabadoActual += horasDron;
            }
            horasSabado.add(horasSabadoActual);
            // volver a ordenar aqui para ver la condicion de bucle (aun tenemos combinaciones posibles) bien
            sortDescending(cargas9v);
            sortDescending(cargas15v);
        } while (cargas9v[0] > 0 && cargas15v[0] > 0);
        return horasSabado;
    }

    public static void sortDescending(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < (arr.length / 2); i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}