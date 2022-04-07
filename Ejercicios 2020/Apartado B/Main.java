import java.io.*;

//main class
public class Main {
    public static void main(String[] args) {
        // open input.txt
        File file = new File("input.txt");
        // creamos filereader
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // creamos bufferedreader
        BufferedReader br = new BufferedReader(fr);
        // abrimos output.txt
        File file2 = new File("output.txt");
        // creamos filewriter
        FileWriter fw = null;
        try {
            fw = new FileWriter(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // creamos bufferedwriter
        BufferedWriter bw = new BufferedWriter(fw);

        // leemos el archivo hasta que no queden líneas
        String line;
        try {
            while ((line = br.readLine()) != null) {
                // creamos una nueva instancia de la clase Aplauso y le pasamos el valor de line
                // como int
                Aplauso aplauso = new Aplauso(Integer.parseInt(line));
                // leemos tantas líneas como personas
                for (int i = 0; i < aplauso.personas; i++) {
                    // leemos la línea y tomamos dos enteros que serán los valores de duración y
                    // minimo
                    line = br.readLine();
                    String[] values = line.split(" ");
                    aplauso.duracion[i] = Integer.parseInt(values[0]);
                    aplauso.minimo[i] = Integer.parseInt(values[1]);
                }
                // imprimimos la salida de duracionAplauso en una linea nueva del archivo
                // output.txt
                // si hay personas
                if (aplauso.personas > 0) {
                    bw.write(duracionAplauso(aplauso) + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // cerramos los archivos
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static int duracionAplauso(Aplauso aplauso) {
        int tiempo = 0;
        int totalPersonas = aplauso.personas;
        // mientras haya personas aplaudiendo
        while (totalPersonas > 0) {
            for (int i = 0; i < aplauso.personas; i++) {
                if (aplauso.aplaudiendo[i]) {
                    // si la duracion es menor que tiempo o totalPersonas es menor que mínimo,
                    // decrementamos totalPersonas en 1 y ponemos aplaudiendo a false
                    if (aplauso.duracion[i] <= tiempo || totalPersonas < aplauso.minimo[i]) {
                        aplauso.aplaudiendo[i] = false;
                        // si una persona deja de aplaudir, puede que otra también lo haga, tenemos que
                        // volver a iterar todo el bucle
                        i = 0;
                        totalPersonas--;
                    }
                }
            }
            // si quedan personas aplaudiendo, sumamos 1 a tiempo
            if (totalPersonas > 0) {
                tiempo++;
            }
        }
        return tiempo;

    }

}