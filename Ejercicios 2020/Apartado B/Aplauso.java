//clase pública Aplauso con atriubtos públicos entero personas y dos arrays de enteros del tamaño de personas
public class Aplauso {
    public int personas;
    public int[] duracion;
    public int[] minimo;
    public boolean[] aplaudiendo;

    //constructor de la clase Aplauso recibe personas e inicia los arrays al tamaño de personas
    public Aplauso(int personas) {
        this.personas = personas;
        duracion = new int[personas];
        minimo = new int[personas];
        aplaudiendo = new boolean[personas];
    }

}
