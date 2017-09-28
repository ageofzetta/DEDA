import java.util.Arrays;

class nodoBinario{
    private Object valor;
    private nodoBinario nodoPadre;
    public nodoBinario[] hijos = new nodoBinario[2];
    public nodoBinario hijoIzq = hijos[0];
    public nodoBinario hijoDer = hijos[1];
    private int nivel = 0;
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println(str);
    }

    /**
     * constructor: recibe como parametro un nodo que se guarda como el padre de éste nodo
     * @param parent
     */
    public nodoBinario(nodoBinario parent) {
        this.nodoPadre = parent;
        if (parent != null) {
            this.nivel = parent.nivel + 1;
        }
        hijoIzq = hijoDer = null;
    }
    /**
     * getValor: regresa el valor almacenado en el nodo
     * @return String valor
     */
    public Object getValor() {
        return valor;
    }

    /**
     * getValor: regresa el valor almacenado en el nodo
     * @return String valor
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * setValor: almacena un valor en el nodo
     * @param valor
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }

    /**
     * agregarNodoIzq: recibe un nodo y lo añade como hijo izquierdo y lo regresa
     * @param nodoIzq nodo
     * @return nodoBinario hijoIzq
     */
    public nodoBinario agregarNodoIzq(nodoBinario nodoIzq) {
        hijoIzq = nodoIzq;
        return hijoIzq;
    }
    /**
     * agregarNodoDer: recibe un nodo y lo añade como hijo derecho y lo regresa
     * @param nodoDer nodo
     * @return nodoBinario hijoDer
     */
    public nodoBinario agregarNodoDer(nodoBinario nodoDer) {
        hijoDer = nodoDer;
        return hijoDer;
    }

}
class arbolBinario {
    public nodoBinario nodoRaiz;
    public Object[] registroNodos = new Object[0];

    /**
     * log: Wrapper de la funcion System.out.print
     *
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println(str);
    }
    /**
     * iniciar: crea un nodo y lo asigna a nodoRaiz y lo regresa
     * @return nodo nodoRaiz
     */
    public nodoBinario iniciar(Object valorRaiz) {
        nodoRaiz = new nodoBinario(null);
        nodoRaiz.setValor(valorRaiz);
        return nodoRaiz;
    }

    /**
     * agregarNodo: Agrega un nodo al árbol.
     * Primero crea un nodoNuevo usando el parámetro valor, el nodoNuevo se añade al nodoObjetivo que recibe como
     * parámetro y se agrega a la izquierda si insertarALaIzquierda es true o a la derecha si es false
     * @param nodoObjetivo de tipo nodoBinario
     * @param valor de tipo int
     * @param insertarALaIzquierda de tipo boolean
     * @return
     */
    public nodoBinario agregarNodo(nodoBinario nodoObjetivo, Object valor, boolean insertarALaIzquierda) {
        nodoBinario nodoNuevo = new nodoBinario(nodoObjetivo);
        nodoNuevo.setValor(valor);
        if (insertarALaIzquierda) {
            log("Añadiendo " + valor + " como nodo izquierdo de " + nodoObjetivo.getValor());
            return nodoObjetivo.agregarNodoIzq(nodoNuevo);
        } else {
            log("Añadiendo " + valor + " como nodo derecho de " + nodoObjetivo.getValor());
            return nodoObjetivo.agregarNodoDer(nodoNuevo);
        }
    }

    /**
     * convertirNodoAArray: recibe un nodo y un índice,
     * añade el valor del nodo al array registroNodos en la posición determinada por índice y se auto invoca
     * con cada uno de sus hijos.
     * @param nodo de tipo nodoBinario
     * @param indice de tipo int
     */
    public void convertirNodoAArray(nodoBinario nodo, int indice) {
        if (nodo != null) {
            int max = Math.max(registroNodos.length, (indice + 1));
            Object[] tmpArray = new Object[max];

            for (int i = 0; i < registroNodos.length; i++) {
                tmpArray[i] = registroNodos[i];
            }
            tmpArray[indice] = nodo.getValor();
            registroNodos = tmpArray.clone();
            convertirNodoAArray(nodo.hijoIzq, (2 * indice) +  1 );
            convertirNodoAArray(nodo.hijoDer, (2 * indice) +  2 );
        }
    }

    /**
     * recorridoAmplitud: Realiza el recorrido por amplitud (por nivel),
     * de forma recursiva evalua si el nivel de un nodo es igual al nivel que buscamos
     * @param nodo un nodoBinario
     * @param nivelABuscar entero que representa el nivel a buscar
     * @return String
     */
    public String recorridoAmplitud(nodoBinario nodo,  int nivelABuscar) {
        String cadena = "";
        if (nodo == null) {
            return  cadena;
        }
        if (nodo.getNivel() == nivelABuscar) {
            return " " + nodo.getValor();
        } else {
            cadena = cadena + recorridoAmplitud(nodo.hijoIzq, nivelABuscar);
            cadena = cadena + recorridoAmplitud(nodo.hijoDer, nivelABuscar);
        }
        return cadena;
    }

    /** convertirArbolAArray:
     * Invoca convertirNodoAArray utilizando como parámetros la raiz y el índice 0
     */
    public void convertirArbolAArray() {
        int indice = 0;
        convertirNodoAArray(nodoRaiz, indice);
        log("Recorriendo elementos del Árbol por nivel  " + Arrays.toString(registroNodos));

    }
}


class ejemploArbolBinario {
    /**
     * log: Wrapper de la funcion System.out.print
     *
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println(str);
    }


    public static void main(String[] args) {
        arbolBinario cuentas = new arbolBinario();
        nodoBinario raiz = cuentas.iniciar(32);
        nodoBinario hijoIzq = cuentas.agregarNodo(raiz, 18, true);
        nodoBinario hijoIzq_nietoIzq = cuentas.agregarNodo(hijoIzq, 13, true);
        nodoBinario hijoIzq_nietoDer = cuentas.agregarNodo(hijoIzq, 27, false);

        nodoBinario hI_nietoIzq_izq = cuentas.agregarNodo(hijoIzq_nietoIzq, 6, true);
        nodoBinario hI_nietoIzq_der = cuentas.agregarNodo(hijoIzq_nietoIzq, 15, false);

        nodoBinario hI_nietoDer_izq = cuentas.agregarNodo(hijoIzq_nietoDer, 24, true);
        nodoBinario hI_nietoDer_der = cuentas.agregarNodo(hijoIzq_nietoDer, 30, false);

        nodoBinario hijoDer = cuentas.agregarNodo(raiz, 64, false);
        nodoBinario hijoDer_nietoIzq = cuentas.agregarNodo(hijoDer, 43, true);
        nodoBinario hijoDer_nietoDer = cuentas.agregarNodo(hijoDer, 80, false);

        nodoBinario hD_nietoIzq_izq = cuentas.agregarNodo(hijoDer_nietoIzq, 35, true);
        nodoBinario hD_nietoIzq_der = cuentas.agregarNodo(hijoDer_nietoIzq, 49, false);

        nodoBinario hD_nietoDer_izq = cuentas.agregarNodo(hijoDer_nietoDer, 75, true);
        nodoBinario hD_nietoDer_der = cuentas.agregarNodo(hijoDer_nietoDer, 89, false);

        cuentas.convertirArbolAArray();

        log("Buscando nodos en el nivel 0: \n" + cuentas.recorridoAmplitud(raiz,0));
        log("Buscando nodos en el nivel 1: \n" + cuentas.recorridoAmplitud(raiz,1));
        log("Buscando nodos en el nivel 2: \n" + cuentas.recorridoAmplitud(raiz,2));
        log("Buscando nodos en el nivel 3: \n" + cuentas.recorridoAmplitud(raiz,3));
        log("Buscando nodos en el nivel 4: \n" + cuentas.recorridoAmplitud(raiz,4));
        log("Buscando nodos en el nivel 5: \n" + cuentas.recorridoAmplitud(raiz,5));

    }
}