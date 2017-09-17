import java.util.Arrays;
import java.util.Random;

/**
 * ERROR: NO EXISTÍA LA CLASE Patente, SUS MÉTODOS Y PROPIEDADES ESTABAN FUERA DE UNA CLASE
 */
class Patente {

    private String letras = "AB" ;
    private int numero = 1234;
    /**
     * constructor
     */
    public Patente (int pnumero, String pletras) {
        numero = pnumero;
        letras = pletras;
    }
    public String obtLetras () { return letras ; }
    public int obtNumero () { return numero ; }

}
class TablaDePatentes {
    public String [] tabla ;

    /**
     * constructor
     */
    public TablaDePatentes () { tabla = new String [9999]; }
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    public static void log(String str) {
        System.out.print("\n" + str);
    }
    /**
     * eliminarEnIndice: Elimina un elemento del array en la posición indicada por indice
     * @param indice es un entero
     * @param array es un array
     */
    public static String[] eliminarEnIndice(int indice, String[] array){
        int tamano = array.length - 1;

        int indiceMasUno = indice + 1;
        String[] parte1 = Arrays.copyOfRange(array, 0, indice);
        String[] parte2 = Arrays.copyOfRange(array, indiceMasUno, array.length);
        String[] result = Arrays.copyOf(parte1, parte1.length + parte2.length);
        System.arraycopy(parte2, 0, result, parte1.length, parte2.length);
        array = result;
        return array;
    }
    /**
     * ERROR: NO SE EFECTUABA NINGÚN MÉTODO DE ORDENAMIENTO AL INGRESAR LAS PLACAS A LA TABLA,
     * AHORA SE IMPLEMENTA EL MÉTODO DE ORDENAMIENTO DE INSERCIÓN PARA FACILITAR LA BÚSQUEDA.
     */
    /**
     * ordenarRegistro: recibe un string y lo convierte a un array con cada elemento de dos letras, posteriormente
     * y utilizando el método de inserción se ordena el array y finalmente se convierte de nuevo en string y se devuelve
     * @param registroString
     * @return String junto
     */
    public static String ordenarRegistro(String registroString) {
        String[] registro = registroString.split("(?<=\\G..)");
        for (int i = 0; i < registro.length; i++) {
            for(int j = i ; j > 0 ; j--){
                int compare = registro[j - 1].compareToIgnoreCase(registro[j]);
                if (compare == 0) {
                    registro = eliminarEnIndice(j, registro);
                }
                if (compare > 0 ) {
                    String temp = registro[j];
                    registro[j] = registro[j-1];
                    registro[j-1] = temp;
                }
            }
        }
        String junto = String.join("", registro);
        return junto;
    }
    /**
     * ERROR: NO EXISTÍA LA UN MÉTODO PARA GUARDAR PLACAS EN LA CLASE TablaDePatentes
     */
    public void guardarPlaca(Patente placa) {
        log("\nGuardando placa " + placa.obtLetras() + placa.obtNumero() + " en posición " + placa.obtNumero());
        int num = placa.obtNumero(); // 45
        String letras = placa.obtLetras();
        String registro = tabla[num];
        if (registro == null) {
            registro = "";
        }
        registro = ordenarRegistro(registro + letras);
        tabla[num] = registro;
    }
    /**
     * ERROR: LA BÚSQUEDA SE REALIZABA DE FORMA SECUENCIAL, AL IMPLEMENTAR UN MÉTODO DE ORDENAMIENTO SE PUEDE USAR
     * LA BÚSQUEDA BINARIA
     */
    /**
     * busquedaBinaria: como su nombre indica realiza una busqueda binaria sobre el array que recibe como argumento
     * buscando la variable letras que tambien recibe como argumento.
     * @param array
     * @param letras
     * @return int
     */
    public static int busquedaBinaria(String[] array, String letras) {

        int izq = 0;
        int der = array.length - 1;
        while (izq <= der) {
            int mitad = (izq + der) / 2;
            if (array[mitad].compareToIgnoreCase(letras) < 0) {
                izq = mitad + 1;
            } else if (array[mitad].compareToIgnoreCase(letras) > 0) {
                der = mitad - 1;
            } else {
                return mitad;
            }
        }
        return -1;
    }

    /**
     * buscar: éste método recibe una placa o patente, ejecuta el método obtNumero y asigna num al resultado, luego
     * inspecciona la tabla en el indice num, si el valor de éste no es nulo efectua una búsqueda binaria con las letras
     * invocando el método busquedaBinaria
     *
     * @param patente
     * @return
     */
    public boolean buscar (Patente patente )  {
        int num = patente.obtNumero(); // 45
        String letras = patente.obtLetras();
        String validas = tabla [ num ];
        if( validas != null ) {
            String[] validasArray = validas.split("(?<=\\G..)");
            int result = busquedaBinaria(validasArray,letras);
            if (result == -1){
                log("\n    No se encontró la placa (No hay coincidencia de letras), NO PUEDE INGRESAR");
                return false;
            } else {
                log("\n    Se encontró la placa, PUEDE INGRESAR");
                return true;
            }
        }
        log("\n    No se encontró la placa (No hay coincidencia de números), NO PUEDE INGRESAR");
        return false ;
    }
}


class ejemploBuscarPlacas {
    public static int dimension = 500;
    public static Patente[] placasRegistradas = new Patente[dimension];
    public static Patente[] placasNoRegistradas = new Patente[dimension];
    public static Random random = new Random();
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    public static void log(String str) {
        System.out.print("\n" + str);
    }
    /**
     * crearPlacaRandomizada: crea una Placa nueva con letras y números aleatorios,
     * los números el límite superior es 10, solo se crean números aleatorios del 1 al 10
     * lo anterior para obligar a muchas placas diferentes a compartir el mismo número
     * @param límiteInferior: se suma al número aleatorio para obtener el límite inferior
     */
    public static Patente crearPlacaRandomizada(int límiteInferior) {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWX"; final int N = alphabet.length();
        String letras = "" + alphabet.charAt(random.nextInt(N)) + alphabet.charAt(random.nextInt(N));;
        int numeros= random.nextInt(10) + límiteInferior;
        return new Patente(numeros, letras);

    }

    /**
     * registrarPlacasCondominio: Éste metodo "inicializa" la tabla de patentes, inserta las placas de los
     * condóminos, en éste caso en particular crea placas randomizadas y las incluye, también crea placas
     * randomizadas y no las incluye para probar las funcionalidades de la clase.
     * @param tabla
     */
    public static void registrarPlacasCondominio(TablaDePatentes tabla) {

        int i = 0;
        while (i < dimension) {
            placasRegistradas[i] = crearPlacaRandomizada(1000);
            placasNoRegistradas[i] = crearPlacaRandomizada(2000);
            if (i % 3 == 0) {
                placasNoRegistradas[i] = new Patente(placasRegistradas[i].obtNumero(),"YZ");
            }
            i++;
        }
        placasRegistradas[(dimension - 1)] = new Patente(placasRegistradas[(dimension - 2)].obtNumero(), placasRegistradas[(dimension - 3)].obtLetras());
        for (int j = 0; j < dimension; j++) {
            tabla.guardarPlaca(placasRegistradas[j]);
        }

    }

    /**
     * buscarPlaca: recibe una tabla de patentes, genera un número aleatorio con límite superior de la dimensión
     * de un array de placas registradas, si ése número aleatorio es par toma una placa del array de placas no registradas
     * si no es par lo toma del array de placas registradas, posteriormente pasa la placa al método buscar de la clase
     * TablaDePatentes y regresa un boolean
     * @param tabla
     * @return boolean
     */
    public static boolean buscarPlaca(TablaDePatentes tabla) {
        int indicePlaca = random.nextInt(placasRegistradas.length);
        Patente[] pr = placasRegistradas;
        if (indicePlaca != 0 && (indicePlaca % 2) == 0) {
            pr = placasNoRegistradas;
        }

        log("\n--- Buscando placa " + pr[indicePlaca].obtLetras() + pr[indicePlaca].obtNumero());
        if (tabla.buscar(pr[indicePlaca])) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * buscarPlacas: invoca el método buscarPlaca el número indicado de veces por nPlacas sobre la tabla que recibe como
     * argumento
     * @param tabla
     * @param nPlacas
     */
    public static void buscarPlacas(TablaDePatentes tabla, int nPlacas) {
        int i = 0;
        while (i < nPlacas) {
            buscarPlaca(tabla);
            i++;
        }

    }
    public static void main(String[] args)
    {
        TablaDePatentes tabla = new TablaDePatentes();
        registrarPlacasCondominio(tabla);
        buscarPlacas(tabla,20);

    }

}