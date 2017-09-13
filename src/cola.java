import java.util.Arrays;
import java.util.Random;

/**
 * @author alejandrotapia
 * Class cola replica la funcionalidad de una cola utilizando un arreglo
 */
public class cola {
    private static int[] cola;
    private static int[] colaAnterior;
    private static int dimension;
    private static int puntero;

    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println(str);
    }
    /**
     * invertir: Reacomoda los elementos de un array de forma invertida
     * @param array un arreglo a invertir
     */
    private static int[] invertir(int[] array) {
        int[] arrayTemporal = Arrays.copyOf(array, dimensiones(array));
        for (int i = 0; i < array.length; i++) {
            int newIndex = (array.length - i) - 1;
            array[i] = arrayTemporal[newIndex];
        }
        return array;
    }
    /**
     * estaVacia: evalua si la cola está vacia
     * @return boolean
     */
    public static boolean estaVacia() {
        return (dimensiones(cola) > 0);
    }
    /**
     * dimensiones: evalua si la cola está vacia revisando el atributo dimension
     * @return int
     */
    public static int dimensiones(int[] array) {
        int dimensiones = 0;
        for (int i = 0; i < array.length; i++)
            dimensiones = i + 1;  // +1 since we want 1-100 and not 0-99
        return dimensiones;
    }

    /**
     * listarElementos: Imprime el contenido de un array
     * @param array es un arreglo
     */
    public static void listarElementos(int[] array) {
        log("Enumerando elementos de la Cola  " + Arrays.toString(array));
    }

    /**
     * listarElementosCola: invoca listarElementos y le pasa "cola" como parámetro
     */
    public static void listarElementosCola() {
        contarElementosCola();
        listarElementos(cola);
    }

    /**
     * contarElementosCola: invoca listarElementos y le pasa "cola" como parámetro
     */
    public static void contarElementosCola() {
        log("La cola contiene " + dimensiones(cola) + " elementos");
    }

    /**
     * redimensionar: Reposiciona el puntero y crea una copia del array anterior
     */
    public static  void redimensionar() {
        puntero = dimension - 1;
        cola = Arrays.copyOf(colaAnterior, dimension);
    }

    /**
     * actualizar: copia el resultado el array "cola" actual al array "colaAnterior"
     */
    public static void actualizar() {
        colaAnterior = Arrays.copyOf(cola, dimension);
    }
    /**
     * push: Anexa un elemento al array
     * @param value es un entero
     */
    static void push(int value){

        dimension = dimension + 1;
        redimensionar();
        cola[puntero] = value;
        actualizar();
    }
    /**
     * pop: Elimina el primer elemento de los arreglos
     * @return boolean
     */
    static boolean pop(){
        if (dimension == 0) return false;
        dimension = dimension - 1;
        colaAnterior = invertir(colaAnterior);
        redimensionar();
        cola = invertir(cola);
        actualizar();
        return true;
    }

    /**
     * constructor
     */
    cola(){
        dimension = 0;
        colaAnterior = new int[dimension];
        redimensionar();
    }

}

class ejemploCola {
    private static void log(String str) {
        System.out.println(str);
    }
    private static void examinarCola(cola cola) {
        log("\n Examinando cola...");
        if (cola.estaVacia()) {
            log("--- La Cola NO está vacía---");
        } else {
            log("--- La Cola está vacía---");
        }
        cola.listarElementosCola();
    }
    public static void main(String[] args)
    {
        cola nuevaCola = new cola();
        int agregarXElementos = 3;
        int eliminrarXElementos = 3;
        Random randomGenerator = new Random();
        examinarCola(nuevaCola);
        for (int idx = 1; idx <= agregarXElementos; ++idx){
            log("\n");
            int randomInt = randomGenerator.nextInt(100);
            log("--- Se agregó elemento a la cola ---");
            nuevaCola.push(randomInt);
            nuevaCola.listarElementosCola();
        }
        examinarCola(nuevaCola);
        for (int idx = 1; idx <= eliminrarXElementos; ++idx){
            log("\n");
            log("--- Se eliminó 1 elemento de la cola ---");
            nuevaCola.pop();
            nuevaCola.listarElementosCola();
        }
        examinarCola(nuevaCola);
    }
}