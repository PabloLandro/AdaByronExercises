import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {
    private final String archivo;

    public Reader(String archivo) {
        this.archivo = archivo;
    }

    public List<List<Integer>> getLines(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo)));
            String linea;
            List<List<Integer>> ret = new ArrayList<>();

            int i = -1;
            while ((linea = br.readLine()) != null) {
                i++;       
                if (i % 2 == 0) continue;       // Las líneas pares solo indican el número de elementos de las siguientes
                ret.add(parseLine(linea));
            }

            br.close();
            return ret;
        } catch (IOException ex){
            ex.printStackTrace();
            return null;
        } 
    }

    private List<Integer> parseLine(String line){
        // Separamos por espacios, convertimos a int y guardamos todo como una lista
        return Arrays.stream(line.split(" "))
                     .map(d -> Integer.parseInt(d))
                     .collect(Collectors.toList());
    }
}
