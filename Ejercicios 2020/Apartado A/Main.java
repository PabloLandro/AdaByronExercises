import java.io.*;

public class Main {

    public static void main(String[] args) {
        // read file
        File file = new File("input.txt");
        // read lines of file as array of ints
        int[] lines = readFile(file);
        // create new file
        File output = new File("output.txt");
        // create new file writer
        FileWriter writer = null;
        try {
            writer = new FileWriter(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // iterate over lines
        for (int line : lines) {
            //write results of poirot function to file as string in single line
            try {
                writer.write(poirot(line) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // close writer
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int poirot (int n) {
        //create var count
        int count = 0;
        //iterate over n
        for (int i = 1; i <= n; i++) {
            //increment count if (2 to the power of i) - 1 is divisible by 7
            if (((int) Math.pow(2, i) - 1) % 7 == 0) {
                count++;
            }
        }
        //return count
        return count;
    }

    static int[] readFile(File file){
        int[] lines = null;
        // create reader
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // create buffered reader
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(fileReader);
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
    }
}