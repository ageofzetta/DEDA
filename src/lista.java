import java.util.Arrays;
import java.util.Random;

/**
 * @author alejandrotapia
 * Class lista replica la funcionalidad de una lista utilizando un arreglo
 */
class lista{
    public int[] lista = new int[0];
    public int[] listaB = new int[0];
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    public static void log(String str) {
        System.out.print("\n" + str);
    }
    /**
     * estaVacia: evalua si la pila está vacia
     * @return boolean
     */
    public boolean estaVacia(){
        return (lista.length == 0);
    }
    /**
     * listarElementos: Imprime el contenido de un array
     * @param array es un arreglo
     */
    public void listarElementos(int[] array){
        log(Arrays.toString(array));
    }
    /**
     * listarElementosLista: invoca listarElementos y le pasa "lista" como parámetro
     */
    public void listarElementosLista() {
        listarElementos(lista);
    }
    /**
     * insertarEnIndice: Anexa un elemento elem al array en posición indicada por indice
     * si la dimension del array es menor al indice inserta el elemento al final invocando insertarAlFinal()
     * @param indice es un entero
     * @param elem es un entero
     */
    public void insertarEnIndice(int indice, int elem){
        int indiceMasUno = indice + 1;
        int indiceMenosUno = indice - 1;
        int tamano = lista.length - 1;
        if (indice > tamano) {
            log("Error, fuera de límite");
            insertarAlFinal(elem);
        } else {
            if (tamano > 0) {
                int[] parte1 = Arrays.copyOfRange(lista, 0, indiceMasUno);
                parte1[parte1.length - 1] = elem;
                int[] parte2 = Arrays.copyOfRange(lista, indice, lista.length);

                int[] result = Arrays.copyOf(parte1, parte1.length + parte2.length);
                System.arraycopy(parte2, 0, result, parte1.length, parte2.length);
                lista = result;
                listaB = result;
            }
        }
    }
    /**
     * insertarAlFinal: Redimensiona al array de forma array[array.length + 1] y posiciona elem en la ultima posición
     * @param elem es un entero
     */
    public void insertarAlFinal(int elem){
        log("Insertando al final:");
        int tamano = lista.length + 1;
        redimensionarArray(tamano);
        int posicion = lista.length - 1;
        lista[posicion] = elem;
    }
    /**
     * eliminarEnIndice: Elimina un elemento del array en la posición indicada por indice
     * si la dimension del array es menor al indice elimina el elemento al final invocando eliminarAlFinal()
     * @param indice es un entero
     */
    public boolean eliminarEnIndice(int indice){
        int tamano = lista.length - 1;
        if (indice > tamano) {
            log("Error, fuera de límite");
            eliminarAlFinal();
            return false;
        }
        int indiceMasUno = indice + 1;
        int[] parte1 = Arrays.copyOfRange(lista, 0, indice);
        int[] parte2 = Arrays.copyOfRange(lista, indiceMasUno, lista.length);
        int[] result = Arrays.copyOf(parte1, parte1.length + parte2.length);
        System.arraycopy(parte2, 0, result, parte1.length, parte2.length);
        lista = result;
        listaB = result;
        return true;
    }
    /**
     * eliminarAlFinal: Elimina el último elemento del array
     */
    public boolean eliminarAlFinal() {
        if (lista.length < 1) {
            log("Error lista vacía");
            return false;
        }
        log("Eliminando último elemento");
        int nuevo_tamano = lista.length - 1;
        int[] result = Arrays.copyOfRange(lista, 0, nuevo_tamano);
        lista = result;
        listaB = result;
        return true;
    }
    /**
     * redimensionarArray: Redimensiona el array lista con un nuevo tamaño determinado por el parametro tamano
     * @param tamano es un entero
     */
    public void redimensionarArray(int tamano){
        listaB = Arrays.copyOfRange(lista, 0, tamano);
        lista = Arrays.copyOfRange(listaB, 0, tamano);
    }


}

class ejemploLista{
    public static void log(String str) {
        System.out.print(str);
    }
    private static void examinarLista(lista lista) {
        log("\n Examinando lista...");
        if (lista.estaVacia()) {
            log("--- La Lista está vacía---");
        } else {
            log("--- La Lista NO está vacía---");

        }
        lista.listarElementosLista();
    }
    private static void addElement(int index, lista lista) {
        Random randomGenerator = new Random();
        log("\n");
        int randomInt = randomGenerator.nextInt(100);
        log("--- Se agregó elemento a la lista en la posición " + index + " ---");
        lista.insertarEnIndice(index, randomInt);
        lista.listarElementosLista();
    }

    private static void deleteElement(int index, lista lista) {
        log("\n");
        log("--- Se eliminó 1 elemento de la lista en la posición " + index + " ---");
        lista.eliminarEnIndice(index);
        lista.listarElementosLista();
    }


    public static void main(String[] args) {

        lista nuevaLista = new lista();
        int agregarXElementos = 10;
        int eliminrarXElementos = 10;
        examinarLista(nuevaLista);

        addElement(0, nuevaLista);
        addElement(3, nuevaLista);
        addElement(1, nuevaLista);
        addElement(1, nuevaLista);
        addElement(1, nuevaLista);

        examinarLista(nuevaLista);


        deleteElement(0, nuevaLista);
        deleteElement(3, nuevaLista);
        deleteElement(1, nuevaLista);
        deleteElement(3, nuevaLista);
        deleteElement(1, nuevaLista);

        examinarLista(nuevaLista);
    }
}