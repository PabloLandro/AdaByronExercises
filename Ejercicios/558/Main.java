import java.io.*;
import java.util.*;

public class Main {

    static Scanner in;
    static ArrayList<String> variables;
    static ArrayList<String> camelCase;
    static int N;

    public static void main(String[] args) throws FileNotFoundException {
        in = new Scanner(System.in);
        String line;
        boolean found = false;
        String aux;
        TimedExit salida = new TimedExit();
        //while (in.hasNextInt()) {
        while (in.hasNextLine()) {
            aux = in.nextLine();
            if (!isInteger(aux)) {
                in.close();
                System.exit(0);
            }
            N = Integer.parseInt(aux);
            //N = in.nextInt();
            //in.nextLine();

            found = false;
            variables = new ArrayList<String>();
            camelCase = new ArrayList<String>();
            leerPalabras();
            generarCamelCase();
            imprimir();
        }
        in.close();
        System.exit(0);
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }
    
    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

    public static void leerPalabras() {
        for (int index = 0; index < N; index++) {
            variables.add(in.nextLine());
        }
    }

    public static void generarCamelCase() {
        for (int i = 0; i < N; i++) {
            String aux = variables.get(i);
            int index = existente(aux);
            if (index == -1) {
                camelCase.add(aux);
            } else {
                if (numMayus(aux) > numMayus(camelCase.get(index))) {
                    camelCase.set(index, aux);
                }
            }
        }
    }

    public static int existente(String aux) {
        aux = aux.toLowerCase();
        for (int i = 0; i < camelCase.size(); i++) {
            if (aux.equals(camelCase.get(i).toLowerCase()))
                return i;
        }
        return -1;
    }

    public static int numMayus(String input) {
        String lower = input.toLowerCase();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != lower.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    static void imprimir() {
        for (int i = 0; i < N; i++) {
            System.out.println(camelCase.get(existente(variables.get(i))));
        }
        System.out.println("---");
    }

    public static class TimedExit {
        Timer timer = new Timer();
        TimerTask exitApp = new TimerTask() {
        public void run() {
            System.exit(0);
            }
        };
        
        public TimedExit() {
        timer.schedule(exitApp, new Date(System.currentTimeMillis()+500));
            }
        
        }

}