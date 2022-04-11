import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);
        GenericReader<ParseableObj> reader = new GenericReader<>(ParseableObj.class, "Parsers\\input.txt",
                Arrays.asList(1, 3, 5), " ");
        List<List<ParseableObj>> lines = reader.getLines();

        try {
            PrintWriter writer = new PrintWriter("Parsers\\output.txt", "UTF-8");

            for (int i = 0; i < lines.size(); i++) {
                writer.println(resolver(lines.get(i)));
            }

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String resolver(List<ParseableObj> listObj) {
        int result = 1;
        StringBuilder nameCombined = new StringBuilder();
        for (ParseableObj obj : listObj) {
            nameCombined.append(obj.name);
            result *= obj.id;
        }
        return nameCombined.toString() + String.valueOf(result);
    }
}
