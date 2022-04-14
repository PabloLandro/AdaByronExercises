import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // read lines of file as array of ints
        int[] lines = readInput();

        // iterate over lines
        for (int line : lines) {
            System.out.println(poirot(line) +"");

        }
    }

    static int poirot (int n) {
        //create var count
        int count = 0;
        //iterate over n
        for (int i = 1; i <= n; i++) {
            //increment count if (2 to the power of i) - 1 is divisible by 7
            if (((long) Math.pow(2, i) - 1) % 7 == 0) {
                count++;
            }
        }
        //return count
        return count;
    }

    /*static int[] readInput(){
        int[] lines = null;

        // create buffered reader
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            // read first line as int n
            int n = Integer.parseInt(bufferedReader.readLine());
            // create array of n ints
            lines = new int[n];
            // read all lines except first as ints
            for (int i = 0; i < n; i++) {
                lines[i] = Integer.parseInt(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // close reader
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }*/

    static int[] readInput(){
        int[] lines = null;
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        lines = new int[size];
        for (int i = 0; i < size; i++) {
            lines[i] = in.nextInt();
        }

        in.close();

        return lines;
    }

}