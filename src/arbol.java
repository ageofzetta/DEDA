import java.util.Arrays;

class nodo {
    private String valor;
    private final nodo nodoPadre;
    public nodo[] hijos = new nodo[0];
    private nodo[] hijosTemporal = hijos;

    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println(str);
    }
    public nodo(nodo parent) {
        this.nodoPadre = parent;
    }

    /**
     * getValor: regresa el valor almacenado en el nodo
     * @return String valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * setValor: almacena un valor en el nodo
     * @param valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * agregarNodo: recibe un nodo como parametro, ése parámetro se agrega al array de hijos de éste nodo
     * @param nodoHijo
     */
    public void agregarNodo(nodo nodoHijo) {
        int dimension = hijosTemporal.length + 1;
        hijos = Arrays.copyOf(hijosTemporal, dimension);
        hijos[(hijos.length - 1)] = nodoHijo;
        hijosTemporal = hijos;
    }

    /**
     * imprimirHijos: imprime el valor de cada uno de los elementos del array de hijos junto con el índice en el que se
     * encuentra y agrega la tabulación que recibe al inicio del enunciado.
     * @param tabulacion
     */
    public void imprimirHijos(String tabulacion) {
        log(tabulacion + "**" +valor + "** Imprimiendo hijos : ");
        if (hijos.length == 0) {
            log(tabulacion + "--- No tiene hijos ---");
        }
        for (int i = 0; i < hijos.length; i++) {
            log(tabulacion +"Nodo " + hijos[i].valor + " en indice " + i );
        }
    }
}

class arbol {
    private nodo nodoRaiz;

    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println(str);
    }

    public nodo agregarNodo (nodo nodoPadre, String valor) {
        log("Agregando " + valor + " a " +nodoPadre.getValor());
        nodo nodo = new nodo(nodoPadre);
        nodo.setValor(valor);
        nodoPadre.agregarNodo(nodo);
        return nodo;
    }

    /**
     * imprimirArbol: recibe un nodo como parámetro y también un string que funciona como "tabulador"
     * llama la funcion imprimirHijos del nodo que recibe y finaliza autoinvocandose con cada uno de sus hijos
     * @param nodo
     * @param appender
     */
    public static void imprimirArbol(nodo nodo, String appender) {
        // System.out.println(appender + nodo.getValor());
        nodo.imprimirHijos(appender);

        for (nodo each : nodo.hijos) {
            imprimirArbol(each, " " + appender + appender);
        }
    }

    /**
     * imprimirArbolArray: Recibe un nodo como parámetro y se autoinvoca de forma recursiva con todos sus hijos
     * al finalizar regresa todos los arrays convertidos a string
     * @param nodo un nodo
     * @return
     */
    public static String imprimirArbolArray(nodo nodo) {
        String s = "";
        int i = 0;
        while (i < nodo.hijos.length) {
            s += imprimirArbolArray(nodo.hijos[i]);
            i++;
        }
        if (nodo.hijos.length > 0) {
            s += Arrays.toString(nodo.hijos).replace("[","").replace("]","") + ", ";
        }
        return s;
    }

    /**
     * iniciar: crea un nodo y lo asigna a nodoRaiz y lo regresa
     * @return nodo nodoRaiz
     */
    public nodo iniciar() {
        nodoRaiz = new nodo(null);
        nodoRaiz.setValor("raiz");
        return nodoRaiz;
    }

}

class ejemploArbol {
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println(str);
    }


    public static void main(String[] args) {

        arbol libroArbol = new arbol();
        nodo raiz = libroArbol.iniciar();
        nodo hijo1 = libroArbol.agregarNodo(raiz, "Capitulo 1");
        nodo nieto1_1 = libroArbol.agregarNodo(hijo1, "Sección 1.1");
        nodo nieto1_2 = libroArbol.agregarNodo(hijo1, "Sección 1.2");
        nodo nieto1_2_1 = libroArbol.agregarNodo(nieto1_2, "Sub Sección 1.2.1");
        nodo nieto1_2_2 = libroArbol.agregarNodo(nieto1_2, "Sub Sección 1.2.2");

        nodo hijo2 = libroArbol.agregarNodo(raiz, "Capitulo 2");
        nodo hijo3 = libroArbol.agregarNodo(raiz, "Capitulo 3");
        nodo nieto3_1 = libroArbol.agregarNodo(hijo3, "Sección 3.1");
        nodo nieto3_2 = libroArbol.agregarNodo(hijo3, "Sección 3.2");
        nodo nieto3_3 = libroArbol.agregarNodo(hijo3, "Sección 3.3");
        nodo nieto3_2_1 = libroArbol.agregarNodo(nieto3_2, "Sub Sección 3.2.1");
        nodo nieto3_2_2 = libroArbol.agregarNodo(nieto3_2, "Sub Sección 3.2.2");
        nodo hijo4 = libroArbol.agregarNodo(raiz, "Capitulo 4");
        log("\nLista de nodos almacenados en los arrays");
        String arbolPlano = arbol.imprimirArbolArray(raiz);
        arbolPlano = arbolPlano.substring(0, arbolPlano.length() - 2);
        log("[" + arbolPlano + "]");
        log("\nImprimiendo árbol completo");
        arbol.imprimirArbol(raiz, "");

    }

}