import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberOfCases = 0;
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("Ejercicios 2019\\B\\input2.txt")))) {
            String linea;
            if ((linea = br.readLine()) != null) {
                numberOfCases = Integer.parseInt(linea);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        List<Integer> range = new ArrayList<>();
        for (int i = 0; i < numberOfCases; i++) {
            range.add(i + 2);
        }
        GenericReader<ParseableInt> reader = new GenericReader<>(ParseableInt.class, "Ejercicios 2019\\B\\input2.txt",
                range, " ");
        List<List<ParseableInt>> lines = reader.getLines();
        try {
            PrintWriter writer = new PrintWriter("Ejercicios 2019\\B\\output2.txt", "UTF-8");
            for (int i = 0; i < lines.size(); i++) {
                writer.println(resolver(lines.get(i)));
            }
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String resolver(List<ParseableInt> list) {
        int[] datos = new int[list.size()];
        int sum = 0;

        // convertir a array y obtener valor máximo que pueden sumar los dados
        for (int i = 0; i < list.size(); i++) {
            datos[i] = list.get(i).value;
            sum += datos[i];
        }

        // hallar la suma de las distribuciones uniformes de los dados
        int[] distribucion = new int[sum + 1];
        for (int i = 1; i <= datos[0]; i++) {
            for (int j = 1; j <= datos[1]; j++) {
                distribucion[i + j]++;
            }
        }

        // hallar el valor maximo de distr
        int valorMax = 0;
        for (int i = 0; i < distribucion.length; i++) {
            if (distribucion[i] > valorMax) {
                valorMax = distribucion[i];
            }
        }

        StringBuilder sol = new StringBuilder();
        for (int i = 0; i < distribucion.length; i++) {
            if (distribucion[i] == valorMax) {
                sol.append(i + " ");
            }
        }
        return sol.toString();
    }
}
