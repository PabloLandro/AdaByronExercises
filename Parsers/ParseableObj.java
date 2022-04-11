/**
 * Un objeto más complejo de parsear que el anterior, tenemos un nombre + ID numérica
 */
public class ParseableObj implements Parseable {

    public String name;
    public int id;

    @Override
    public void parse(String line) {
        int k;
        for (k = line.length() - 1; k >= 0; k--) {
            if (!Character.isDigit(line.charAt(k)))
                break;
        }
        name = line.substring(0, k + 1);
        id = Integer.parseInt(line.substring(k + 1, line.length()));
    }
}
