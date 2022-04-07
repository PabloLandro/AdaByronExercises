import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//main function
public class Main {
    public static void main(String[] args) {
        // read file
        File file = new File("input.txt");
        // read lines of file
        String[] lines = readFile(file);
        // read all lines except first as string
        String[] strings = new String[lines.length - 1];
        for (int i = 1; i < lines.length; i++) {
            strings[i - 1] = lines[i];
        }
        // create new file
        File output = new File("output.txt");
        // create new file writer
        FileWriter writer = null;
        try {
            writer = new FileWriter(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // iterate over strings
        for (String string : strings) {
            // write result of iterateOverString to file as string in single line
            try {
                writer.write(iterateOverString(string) + "\n");
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

    // function that returns file as array of strings
    static String[] readFile(File file) {
        // create array of strings
        String[] lines = new String[0];
        // read file
        try {
            // create file reader
            FileReader fileReader = new FileReader(file);
            // create buffered reader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // create string to store line
            String line;
            // while there are lines to read
            while ((line = bufferedReader.readLine()) != null) {
                // add line to array
                lines = addLine(lines, line);
            }
            // close buffered reader
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            // print error message
            System.out.println("File not found");
        } catch (IOException e) {
            // print error message
            System.out.println("Error reading file");
        }
        // return array of strings
        return lines;
    }

    // function that adds line to array of strings
    private static String[] addLine(String[] lines, String line) {
        // create new array of strings
        String[] newLines = new String[lines.length + 1];
        // copy old array to new array
        for (int i = 0; i < lines.length; i++) {
            newLines[i] = lines[i];
        }
        // add new line to new array
        newLines[lines.length] = line;
        // return new array
        return newLines;
    }

    // function that iterates over the characters of a string
    static int iterateOverString(String string) {
        int valids = 0;
        // iterate over characters
        // save countQuestionMarks of string as int n
        int n = countQuestionMarks(string);
        // iterate 10 to the power of n times
        for (int i = 0; i < (int) Math.pow(10, n); i++) {

            // sustitue n-th question mark with (i module 9 to the power of n)-th letter
            String newString = substituteQuestionMark(string, i);
            // checkDni on string
            if (checkDNI(newString)) {
                // print string
                valids++;
            }
        }
        return valids;
    }

    private static String substituteQuestionMark(String string, int i) {
        // create counter of ?
        int counter = 0;
        // create new object string equal to string
        String newString = string;
        // for every question mark in string
        for (int j = 0; j < string.length(); j++) {
            // if character is question mark, count it
            if (string.charAt(j) == '?') {
                // increment counter
                counter++;
                // replace the n-th ? char with (i mod 10 to the power of n divided by 10 to the power of n-1) digit
                newString = newString.replaceFirst("\\?", String.valueOf((i % (int) Math.pow(10, counter)) / (int) Math.pow(10, counter - 1)));
            }
        } // return string
        return newString;
    }

    // function that counts number of ? characters in string
    static int countQuestionMarks(String string) {
        // initialize counter
        int counter = 0;
        // iterate over characters
        for (int i = 0; i < string.length(); i++) {
            // if character is ?
            if (string.charAt(i) == '?') {
                // increment counter
                counter++;
            }
        }
        // return counter
        return counter;
    }

    static boolean checkDNI(String dni) {
        // save first 8 characters as int
        int dniInt = Integer.parseInt(dni.substring(0, 8));
        // save the value module 23
        int dniMod = dniInt % 23;
        // get last char of string
        char last = dni.charAt(8);
        // create DNI array
        char[] DNI = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
                'L', 'C', 'K', 'E' };
        // return true if last character is equal to DNI array
        return last == DNI[dniMod];
    }
}
