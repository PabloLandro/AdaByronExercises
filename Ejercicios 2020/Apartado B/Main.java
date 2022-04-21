import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            Aplauso aplauso = new Aplauso(scanner.nextInt());
            if (aplauso.personas == 0) {
                scanner.close();
                System.exit(0);
            }
            for (int i = 0; i < aplauso.personas; i++) {
                aplauso.duracion[i] = scanner.nextInt();
                aplauso.minimo[i] = scanner.nextInt();
            }
            
            if (aplauso.personas > 0) {
                System.out.println(aplauso.calcularAplauso());
            }
        }
        scanner.close();
        System.exit(0);
    }

    
}

class Aplauso {
    public int personas;
    public int[] duracion;
    public int[] minimo;
    public boolean[] aplaudiendo;
    public Aplauso(int personas) {
        this.personas = personas;
        duracion = new int[personas];
        minimo = new int[personas];
        aplaudiendo = new boolean[personas];
        for (int i = 0; i < personas; i++) {
            aplaudiendo[i] = true;
        }
    }

    public int calcularAplauso () {
        int segundos = 0;
        ArrayList<Integer> personasAplaudiendo = new ArrayList<Integer>();
        
        for (int i = 0; i < personas; i++) {
            personasAplaudiendo.add(i);
        }

        while (personasAplaudiendo.size() > 0) {
            boolean changes = false;
            for (int i = 0; i < personasAplaudiendo.size(); i++) {
                if (personasAplaudiendo.size() < minimo[personasAplaudiendo.get(i)] || segundos >= duracion[personasAplaudiendo.get(i)]) {
                    personasAplaudiendo.remove(i);
                    changes = true;
                    if (personasAplaudiendo.size() == 0) {
                        segundos--;
                    }
                }
                if (changes && i == personasAplaudiendo.size()) {
                    changes = false;
                    i = -1;
                }
            }
            segundos++;
        }
        return segundos;
    }

}