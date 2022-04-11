/**
 * Ejemplo básico, encapsulamos un int
 */
public class ParseableInt implements Parseable {
    public int value = 0;

    @Override
    public void parse(String line) {
        value = Integer.parseInt(line);
    }

}
