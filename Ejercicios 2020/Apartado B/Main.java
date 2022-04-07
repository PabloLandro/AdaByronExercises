import java.io.*;

//main class
public class Main {
    public static void main(String[] args) {
        //open input.txt
        File file = new File("input.txt");
        //creamos filereader
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //creamos bufferedreader
        BufferedReader br = new BufferedReader(fr);
        //leemos el archivo hasta que no queden líneas
        String line;
        try {
            while ((line = br.readLine()) != null) {
                //creamos una nueva instancia de la clase Aplauso y le pasamos el valor de line como int
                Aplauso aplauso = new Aplauso(Integer.parseInt(line));
                //leemos tantas líneas como personas
                for (int i = 0; i < aplauso.personas; i++) {
                    //leemos la línea y tomamos dos enteros que serán los valores de duración y minimo
                    line = br.readLine();
                    String[] values = line.split(" ");
                    aplauso.duracion[i] = Integer.parseInt(values[0]);
                    
    }
}