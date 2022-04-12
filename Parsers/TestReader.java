import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestReader {

    public static void main(String[] args) {
        try {
            List<List<String>> lineas = Files.lines(Paths.get("inputPrueba.txt"))    // Empiezo un stream con las líneas
                    .map(x -> Arrays.asList(x.split(" ")))  // Parto cada línea por espacios y meto
                                                            // los elementos en una lista (List<String>)
                    .filter(x -> x.size() > 1)       // Filtro las líneas que tienen un elemento o menos
                    .collect(Collectors.toList());   // Guardo todo en una lista

            // Imprimo todo
            lineas.forEach(lista -> {
                lista.forEach(x -> System.out.print(x + " "));
                System.out.println();
            });
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}