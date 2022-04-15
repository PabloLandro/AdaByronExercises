import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        in.nextInt();
        while(in.hasNextInt()) {
            int aux = (int) in.nextInt()/3;
            System.out.println(aux);
        }
        in.close();
        System.exit(0);
    }
}