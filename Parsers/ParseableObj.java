/**
 * Un objeto más complejo de parsear que el anterior, tenemos un nombre + ID
 * numérica
 */
public class ParseableObj extends Parseable {

    public String name;
    public int id;

    private ParseableObj(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static Parseable parse(String line) {
        int k;
        for (k = line.length() - 1; k >= 0; k--) {
            if (!Character.isDigit(line.charAt(k)))
                break;
        }
        return new ParseableObj(line.substring(0, k + 1), Integer.parseInt(line.substring(k + 1, line.length())));
    }
}
