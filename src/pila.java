import java.util.Arrays;
import java.util.Random;

/**
 * @author alejandrotapia
 * Class pila replica la funcionalidad de una pila utilizando un arreglo
 */
public class pila {
    private static int[] pila;
    private static int[] pilaAnterior;
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
     * estaVacia: evalua si la pila está vacia
     * @return boolean
     */
    public static boolean estaVacia() {
        return (dimensiones(pila) > 0);
    }
    /**
     * dimensiones: evalua si la pila está vacia revisando el atributo dimension
     * @return int
     */
    public static int dimensiones(int[] array) {
        int dimensiones = 0;
        for (int i = 0; i < array.length; i++)
            dimensiones = i + 1;
        return dimensiones;
    }

    /**
     * listarElementos: Imprime el contenido de un array
     * @param array es un arreglo
     */
    public static void listarElementos(int[] array) {
        log("Enumerando elementos de la Pila  " + Arrays.toString(array));
    }

    /**
     * listarElementosPila: invoca listarElementos y le pasa "pila" como parámetro
     */
    public static void listarElementosPila() {
        contarElementosPila();
        listarElementos(pila);
    }

    /**
     * contarElementosPila: Cuenta los elementos que hay en la pila
     */
    public static void contarElementosPila() {
        log("La pila contiene " + dimensiones(pila) + " elementos");
    }

    /**
     * redimensionar: Reposiciona el puntero y crea una copia del array anterior
     */
    public static  void redimensionar() {
        puntero = dimension - 1;
        pila = Arrays.copyOf(pilaAnterior, dimension);
    }

    /**
     * actualizar: copia el resultado el array "pila" actual al array "pilaAnterior"
     */
    public static void actualizar() {
        pilaAnterior = Arrays.copyOf(pila, dimension);
    }
    /**
     * push: Anexa un elemento al array
     * @param value es un entero
     */
    static void push(int value){

        dimension = dimension + 1;
        redimensionar();
        pila[puntero] = value;
        actualizar();
    }
    /**
     * pop: Elimina el último elemento de los arreglos
     * @return
     */
    static boolean pop(){
        if (dimension == 0) return false;
        dimension = dimension - 1;
        redimensionar();
        actualizar();
        return true;
    }
    /**
     * constructor
     */
    pila(){
        dimension = 0;
        pilaAnterior = new int[dimension];
        redimensionar();
    }

}

class ejemploPila {
    private static void log(String str) {
        System.out.println(str);
    }
    private static void examinarPila(pila pila) {
        log("\n Examinando pila...");
        if (pila.estaVacia()) {
            log("--- La Pila NO está vacía---");
        } else {
            log("--- La Pila está vacía---");
        }
        pila.listarElementosPila();
    }
    public static void main(String[] args)
    {
        pila nuevaPila = new pila();
        int agregarXElementos = 10;
        int eliminrarXElementos = 10;
        Random randomGenerator = new Random();
        examinarPila(nuevaPila);
        for (int idx = 1; idx <= agregarXElementos; ++idx){
            log("\n");
            int randomInt = randomGenerator.nextInt(100);
            log("--- Se agregó elemento a la pila ---");
            nuevaPila.push(randomInt);
            nuevaPila.listarElementosPila();
        }
        examinarPila(nuevaPila);
        for (int idx = 1; idx <= eliminrarXElementos; ++idx){
            log("\n");
            log("--- Se eliminó 1 elemento de la pila ---");
            nuevaPila.pop();
            nuevaPila.listarElementosPila();
        }
        examinarPila(nuevaPila);
    }
}