import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static int[] lines;
    static int[] cases;
    static Integer[] indexes;
    static int size;

    public static void main(String[] args) {

        // read lines of file as array of ints
        crearArchivo();
        readInput();

        orderArray();

        poirot();
        crash();
        System.exit(0);
        printResults();

        System.exit(0);
    }

    static void crearArchivo() {
        // create file
        File file = new File("output.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // tomar Integer max value -1
        // write to file
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            //guardar en variable integer max value -1
            bw.write(100+ "\n");
            //escribir tantas ints aleatorias en el archivo como integer max value -1
            for (int i = 500000; i > 499900; i--) {
                bw.write(i + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static void orderArray () {
        cases = new int[size];
        indexes = new Integer[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = new Integer(i);
        }
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(final Integer o1, final Integer o2) {
                return Integer.compare(lines[o1], lines[o2]);
            }
        };
        Arrays.sort(indexes, comp);
    }

    static void crash () {
        for (int i = 0; i < size; i++) {
            System.out.println("0");
        }
    }

    static void poirot () {
        int index = 0;
        int count = 0;
        BigInteger num = new BigInteger("2");
        BigInteger one = new BigInteger("1");
        BigInteger two = new BigInteger("2");
        BigInteger seven = new BigInteger("7");
        BigInteger zero = new BigInteger("0");
        int aux;
        for (int i = 1; i <= lines[indexes[size-1]]; i++) {
            if (i <= 0) {
                crash();
                System.exit(0);
            }
            BigInteger copy = num;
            if (copy.subtract(one).mod(seven).compareTo(zero) == 0) {
                count++;
            }
            aux = indexes[index];
            if (i == lines[aux]) {
                cases[aux] = count;
                index++;
                if (index > size) {
                    crash();
                    System.exit(0);
                }
            }
            num = num.multiply(two);
        }
    }

    static void printResults() {
        for (int i = 0; i < size; i++) {
            System.out.println(cases[i]);
        }
    }

    static void readInput() {
        Scanner in = new Scanner(System.in);
        size = in.nextInt();
        lines = new int[size];
        for (int i = 0; i < size; i++) {
            lines[i] = in.nextInt();
        }
        in.close();
    }

}