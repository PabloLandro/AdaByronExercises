import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Basado en Reader y generalizando para cualquier tipo de objeto, líneas objetivo y separador de palabras
 * Lee las líneas pasadas en targetLines y las almacena en listas de objetos T, que son construidas al separar mediante " "
 */
public class GenericReader<T extends Parseable> {
    private final String archivo;
    private final List<Integer> targetLines;
    private final Class<T> type;
    private final String separator;
    public GenericReader(Class<T> type, String archivo, List<Integer> targetLines, String separator) {
        this.type = type;
        this.archivo = archivo;
        this.targetLines = targetLines; // 1-indexed (la primera línea del archivo es la línea 1)
        this.separator = separator;
    }

    public List<List<T>> getLines() {
        String linea;
        List<List<T>> ret = new ArrayList<>();
        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)))){
            while ((linea = br.readLine()) != null) {
                lineCount++;
                if (!targetLines.contains(lineCount)) // No está en las líneas que queremos parsear
                    continue;
                ret.add(parseLine(linea));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
        return ret;
    }

    private List<T> parseLine(String line) {
        // Separamos por espacios, metemos los objetos de tipo T a la lista tras parsear con parse()
        return Arrays.stream(line.split(separator))
                     .map(d -> {
                         T obj = getInstanceOfT();
                         obj.parse(d);
                         return obj;
                     })
                     .collect(Collectors.toList()); 
    }
    
    private T getInstanceOfT()
    {
        try
        {
            return type.newInstance();
        }
        catch (Exception e)
        {
            throw new RuntimeException("No se puede instanciar la clase T del parser (" + type.getName() + ")");
        }
    }
}
