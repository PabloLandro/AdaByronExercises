/**
 * Ejemplo básico, encapsulamos un int
 */
public class ParseableInt extends Parseable {
    public int value = 0;

    private ParseableInt(int value) {
        this.value = value;
    }

    public static Parseable parse(String line) {
        return new ParseableInt(Integer.parseInt(line));
    }
}
