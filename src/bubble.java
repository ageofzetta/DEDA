import java.util.Arrays;
import java.util.Random;

class estudiante {
    String nombre;
    int peso;

    /** constructor
     *
     * @param providedName
     * @param providedWeight
     */
    public estudiante(String providedName, int providedWeight) {
        nombre = providedName;
        peso = providedWeight;
    }
}

class ordena {
    /** porPesoInsercion
     * Ordena una array de tipo estudiante por PESO utilizando el método de inserción
     * @param listaEstudiantes tipo estudiante[]
     * @return estudiante[]
     */
    public static estudiante[] porPesoInsercion(estudiante[] listaEstudiantes) {
        estudiante[] estudiantes = listaEstudiantes.clone();
        int n = estudiantes.length;
        for (int i=1; i<n; ++i)
        {
            estudiante key = estudiantes[i];
            int j = i-1;

            while (j>=0 && estudiantes[j].peso > key.peso )
            {
                estudiantes[j+1] = estudiantes[j];
                j = j-1;
            }
            estudiantes[j+1] = key;
        }
        return estudiantes;
    }
    /** porPesoBurbuja
     * Ordena una array de tipo estudiante por PESO utilizando el método de burbuja
     * @param listaEstudiantes tipo estudiante[]
     * @return estudiante[]
     */
    public static estudiante[] porPesoBurbuja(estudiante[] listaEstudiantes) {
        estudiante[] estudiantes = listaEstudiantes.clone();
        for (int i = 0; i < estudiantes.length; i++) {
            for(int j = i ; j > 0 ; j--){
                boolean compare = estudiantes[j-1].peso > estudiantes[j].peso;
                if (compare) {
                    estudiante temp = estudiantes[j];
                    estudiantes[j] = estudiantes[j-1];
                    estudiantes[j-1] = temp;
                }
            }
        }
        return estudiantes;
    }
    /** metodoInsercionTexto
     * Ordena una array de tipo estudiante por NOMBRE utilizando el método de inserción
     * @param listaEstudiantes tipo estudiante[]
     * @return estudiante[]
     */
    public static estudiante[] metodoInsercionTexto(estudiante[] listaEstudiantes) {
        estudiante[] estudiantes = listaEstudiantes.clone();
        int n = estudiantes.length;
        for (int i=1; i<n; ++i)
        {
            estudiante key = estudiantes[i];
            int j = i-1;

            while (j>=0 && (estudiantes[j].nombre.compareToIgnoreCase(key.nombre) > 0 ) )
            {
                estudiantes[j+1] = estudiantes[j];
                j = j-1;
            }
            estudiantes[j+1] = key;
        }
        return estudiantes;
    }
    /** metodoInsercionTexto
     * Ordena una array de tipo estudiante por NOMBRE utilizando el método de burbuja
     * @param listaEstudiantes tipo estudiante[]
     * @return estudiante[]
     */
    public static estudiante[] metodoBurbuja(estudiante[] listaEstudiantes) {
        estudiante[] estudiantes = listaEstudiantes.clone();
        for (int i = 0; i < estudiantes.length; i++) {
            for(int j = i ; j > 0 ; j--){

                int compare = estudiantes[j-1].nombre.compareToIgnoreCase(estudiantes[j].nombre);
                if (compare > 0) {
                    estudiante temp = estudiantes[j];
                    estudiantes[j] = estudiantes[j-1];
                    estudiantes[j-1] = temp;
                }
            }
        }
        return estudiantes;
    }

    /** dividir
     *
     * @param arr tipo int[] el array en cuestión
     * @param low tipo int limite inferior
     * @param high tipo int limite superior
     * @return
     */
    public static int dividir(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (arr[j] <= pivot)
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    /** quicksort
     * Ordena una array de enteros utilizando el método de quicksort
     * @param arr tipo int[] el array a ordenar
     * @param high tipo int límite superior del sort
     * @param low tipo int límite inferior del sort
     */
    public static void quicksort(int arr[], int low, int high)
    {
        if (low < high)
        {
            int pi = dividir(arr, low, high);
            quicksort(arr, low, pi-1);
            quicksort(arr, pi+1, high);
        }
    }

    /** metodoQuicksort
     * éste método es invocado desde otras clases y regresa un array de enteros ordenados, primero clona el array
     * y luego invoca quicksort
     * @param listaFacturas es de tipo int[], el array a ordenar
     * @return int[]
     */
    public static int[] metodoQuicksort(int[] listaFacturas) {
        int arr[] = listaFacturas.clone();
        int n = arr.length;
        quicksort(arr, 0, n-1);
        return arr;
    }
}

class buscador {
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println("\n" + str);
    }
    /**
     * metodoBinario: Realiza una busqueda binaria sobre el array que recibe como argumento
     * buscando la variable nombre que tambien recibe como argumento.
     * @param array tipo String[], array donde buscar
     * @param nombre tipo String, clave a buscar
     * @return int
     */
    public static int metodoBinario(String[] array, String nombre) {

        int izq = 0;
        int der = array.length - 1;
        while (izq <= der) {
            int mitad = (izq + der) / 2;
            int compare = array[mitad].compareToIgnoreCase(nombre);
            if (compare < 0) {
                izq = mitad + 1;
            } else if (compare > 0) {
                der = mitad - 1;
            } else {
                return mitad;
            }
        }
        return -1;
    }

    /** metodoSecuencial
     * Realiza una búsqueda secuencial sobre el array que recibe como argumento buscando la variable nombre
     * @param array tipo String[], array en donde buscar
     * @param nombre tipo String, clave a buscar.
     * @return int
     */
    public static int metodoSecuencial(String[] array, String nombre) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == nombre) {
                return i;
            }
        }
        return -1;

    }

    /** buscarAlumnoPorNombre
     * busca un alumno por NOMBRE en una lista de estudiantes utilizando el método de búsqueda proporcionado.
     * @param estudiantes tipo estudiante[], la lista de estudiantes donde buscar
     * @param nombre tipo String, el nombre del alumno a buscar
     * @param tipoBusqueda tipo String, el tipo de búsqueda a realizar
     * @return int
     */
    public static int buscarAlumnoPorNombre(estudiante[] estudiantes, String nombre, String tipoBusqueda) {
        String[] array = new String[estudiantes.length];
        for (int i = 0; i < estudiantes.length; i++) {
            array[i] = estudiantes[i].nombre;
        }
        // log("Enumerando elementos del array  " + Arrays.toString(array));
        if (tipoBusqueda == "secuencial") {
            return metodoSecuencial(array, nombre);
        } else {
            return metodoBinario(array, nombre);
        }

    }
}

class registroEstudiantes {
    /**
     * constructor
     */
    public registroEstudiantes() {
        registrarEstudiantes();
    }

    static int agregarXElementos = 15;
    static String[] nombres = {"Jose", "Julia", "Benjamin", "Abigail", "Victoria", "Jonathan", "Brian", "Eric", "Amanda", "Andrea", "Sara", "Aaron", "Maria", "Olivia", "Samuel", "Gabriel"};
    public static estudiante[] listaEstudiantes= new estudiante[agregarXElementos];
    static Random randomGenerator = new Random();
    static buscador busca = new buscador();
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println(str);
    }

    /**
     * registrarEstudiantes: crea una cantidad (determinada por agregarXElementos) de estudiantes de forma aleatoria.
     */
    private static void registrarEstudiantes() {
        for (int i = 0; i < agregarXElementos; i++) {
            int randomInt = randomGenerator.nextInt(50) + 50;
            listaEstudiantes[i] = new estudiante(nombres[i], randomInt);
            log("\n--- Se agregó al estudiante " + nombres[i] + ", pesa " + randomInt + "kgs ---");
        }
    }

    /** buscarEstudiantes
     * Toma 10 estudiantes al azar de una lista de estudiantes y los busca utilizando el método de búsqueda proporcionado.
     * @param estudiantes tipo estudiante[], la lista de estudiantes donde buscar
     * @param tipoBusqueda tipo String, el tipo de búsqueda a realizar, puede ser secuencial o binario.
     */
    public static void buscarEstudiantes(estudiante[] estudiantes, String tipoBusqueda) {
        int j = 0;
        while (j < 10) {
            int idx = randomGenerator.nextInt(nombres.length - 1);
            String nombre = nombres[j];
            int found = busca.buscarAlumnoPorNombre(estudiantes, nombre, tipoBusqueda);
            if (found > -1) {
                log("\nEncontramos a " + nombre + " y pesa " + estudiantes[found].peso + "kgs.");
            } else {
                log("\nNo encontramos a " + nombre);
            }
            j++;
        }
    }

    /** imprimirListaEstudiantes
     * Imprime una lista de estudiantes que recibe como parámetro de forma ascendente o descendete
     * @param estudiantes tipo estudiante[], la lista de estudiantes a imprimir
     * @param orderAsc tipo boolean, determina el orden de impresión, ascendente o descendente.
     */
    public static void imprimirListaEstudiantes(estudiante[] estudiantes, boolean orderAsc) {
        if (orderAsc) {
            log("\nImprimiendo pesos en orden Ascendente");
            for (int i = 0; i < estudiantes.length; i++) {
                log("\n--- " + estudiantes[i].nombre + ", pesa " + estudiantes[i].peso + "kgs ---");
            }
        } else {
            log("\nImprimiendo pesos en orden descendente");
            for (int i = estudiantes.length - 1; i >= 0; i--) {
                log("\n--- " + estudiantes[i].nombre + ", pesa " + estudiantes[i].peso + "kgs ---");
            }
        }
    }
}
class Caso1 {
    private static void log(String str) {
        System.out.println("\n" + str);
    }
    public static void main(String[] args)
    {
        log("Iniciando Caso 1");
        registroEstudiantes tablaEstudiantes = new registroEstudiantes();
        buscador buscador = new buscador();
        ordena ordenador = new ordena();
        estudiante[] listaEstudiantes = registroEstudiantes.listaEstudiantes;
        log("Termino el registro");
        estudiante[] estudiantesOrdenadosPorPeso = ordenador.porPesoBurbuja(listaEstudiantes);
        estudiante[] estudiantesOrdenadosPorBurbuja = ordenador.metodoBurbuja(listaEstudiantes);
        log("Buscando 10 alumnos al azar por nombre usando el método de búsqueda binaria");
        registroEstudiantes.buscarEstudiantes(estudiantesOrdenadosPorBurbuja, "binaria" );
        registroEstudiantes.imprimirListaEstudiantes(estudiantesOrdenadosPorPeso,true);
        registroEstudiantes.imprimirListaEstudiantes(estudiantesOrdenadosPorPeso,false);
    }
}
class Caso2 {
    private static void log(String str) {
        System.out.println("\n" + str);
    }
    public static void main(String[] args)
    {
        log("Iniciando Caso 2");
        registroEstudiantes tablaEstudiantes = new registroEstudiantes();
        buscador buscador = new buscador();
        ordena ordenador = new ordena();
        estudiante[] listaEstudiantes = registroEstudiantes.listaEstudiantes;
        log("Termino el registro");
        estudiante[] estudiantesOrdenadosPorPeso = ordenador.porPesoInsercion(listaEstudiantes);
        estudiante[] estudiantesOrdenadosPorBurbuja = ordenador.metodoInsercionTexto(listaEstudiantes);
        log("Buscando 10 alumnos al azar por nombre usando el método de búsqueda binaria");
        registroEstudiantes.buscarEstudiantes(estudiantesOrdenadosPorBurbuja, "binaria" );
        registroEstudiantes.imprimirListaEstudiantes(estudiantesOrdenadosPorPeso,true);
        registroEstudiantes.imprimirListaEstudiantes(estudiantesOrdenadosPorPeso,false);
    }
}

class Caso3 {
    private static void log(String str) {
        System.out.println("\n" + str);
    }
    public static void main(String[] args)
    {
        ordena ordenador = new ordena();
        int agregarXElementos = 20;
        int[] listaFacturas = new int[agregarXElementos];
        Random randomGenerator = new Random();
        for (int idx = 0; idx < agregarXElementos; ++idx){
            int randomInt = randomGenerator.nextInt(901) + 1000;
            listaFacturas[idx] = randomInt;
        }
        log("Facturas NO ordenadas " + Arrays.toString(listaFacturas));
        int[] facturasOrdenadas = ordenador.metodoQuicksort(listaFacturas);
        log("Facturas ordenadas " + Arrays.toString(facturasOrdenadas));
    }
}
