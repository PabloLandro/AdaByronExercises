import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args){
        Reader reader = new Reader("C:\\Users\\Xiana\\Desktop\\Proyectos\\AdaByronExercises\\Ejercicios 2019\\input.txt");
        List<List<Integer>> lines = reader.getLines();

        try {
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            
            for (int i = 0; i < lines.size(); i++){
                writer.println(resolver(lines.get(i)));
            }

            writer.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public static Integer resolver(List<Integer> datos){
        List<Integer> montones = new ArrayList<>();

        if (datos.size() == 0) return 0;

        // Siempre hay al menos 1 montón
        montones.add(datos.get(0));

        // Para el resto, buscamos el menor montón donde quepa
        // Mantenemos el array ordenado de menor a mayor
        for (int i = 1; i < datos.size(); i++){
            // Si el dato es mayor que cualquier montón, le hacemos un hueco nuevo
            if (datos.get(i) >= montones.get(montones.size() - 1)){
                montones.add(datos.get(i));
                continue;       // Siguiente dato
            }

            int j = 0;
            // Recorremos el array en busca del primer montón en el que el dato quepa
            while (montones.get(j) <= datos.get(i)) j++;
            montones.set(j, datos.get(i));      // Sobreescribimos
            Collections.sort(montones);         // Ordenamos
        }

        // Destapar para ver los montones
        //montones.forEach(e -> System.out.print(e + " ")); System.out.println(" ");
        return montones.size();
    }
}