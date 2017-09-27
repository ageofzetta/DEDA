import java.util.Arrays;

class nodoBinarioModificado {
    private Object valor;
    private String nombreCuenta;
    public nodoBinarioModificado nodoPadre;
    public nodoBinarioModificado[] hijos = new nodoBinarioModificado[2];
    public nodoBinarioModificado hijoIzq = hijos[0];
    public nodoBinarioModificado hijoDer = hijos[1];
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
    public nodoBinarioModificado(nodoBinarioModificado parent) {
        this.nodoPadre = parent;
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
     * setValor: almacena un valor en el nodo
     * @param valor
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }

    /**
     * agregarNodoIzq: recibe un nodo y lo añade como hijo izquierdo y lo regresa
     * @param nodoIzq nodo
     * @return nodoBinarioModificado hijoIzq
     */
    public nodoBinarioModificado agregarNodoIzq(nodoBinarioModificado nodoIzq) {
        hijoIzq = nodoIzq;
        return hijoIzq;
    }
    /**
     * agregarNodoDer: recibe un nodo y lo añade como hijo derecho y lo regresa
     * @param nodoDer nodo
     * @return nodoBinarioModificado hijoDer
     */
    public nodoBinarioModificado agregarNodoDer(nodoBinarioModificado nodoDer) {
        hijoDer = nodoDer;
        return hijoDer;
    }
    /**
     * setValor: almacena el nombre de la cuenta asociada al nodo
     * @param nombreCuenta
     */
    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }
    /**
     * getNombreCuenta: regresa el nombre de la cuenta asociada al nodo
     * @return String nombreCuenta
     */
    public String getNombreCuenta() {
        return this.nombreCuenta;
    }

    /**
     * eliminarHijo: Elimina un hijo del nodo
     * @param nodoObjetivo
     */
    public void eliminarHijo(nodoBinarioModificado nodoObjetivo) {
        if (hijoIzq.getValor() == nodoObjetivo.getValor()) {
            hijoIzq = null;
        }
        if (hijoDer.getValor() == nodoObjetivo.getValor()) {
            hijoDer = null;
        }
    }
}

class arbolBinarioModificado {
    public nodoBinarioModificado nodoRaiz;
    public Object[] registroNodos = new Object[0];
    public boolean busquedaActiva = true;

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
    public nodoBinarioModificado iniciar(Object valorRaiz) {
        nodoRaiz = new nodoBinarioModificado(null);
        nodoRaiz.setValor(valorRaiz);
        return nodoRaiz;
    }

    /**
     * agregarNodo: Agrega un nodo al árbol.
     * Primero crea un nodoNuevo usando el parámetro valor, el nodoNuevo se añade al nodoObjetivo que recibe como
     * parámetro y se agrega a la izquierda si insertarALaIzquierda es true o a la derecha si es false
     * @param nodoObjetivo de tipo nodoBinarioModificado
     * @param valor de tipo int
     * @param insertarALaIzquierda de tipo boolean
     * @return
     */
    public nodoBinarioModificado agregarNodo(nodoBinarioModificado nodoObjetivo, Object valor, String nombreCuenta, boolean insertarALaIzquierda) {
        nodoBinarioModificado nodoNuevo = new nodoBinarioModificado(nodoObjetivo);
        nodoNuevo.setValor(valor);
        nodoNuevo.setNombreCuenta(nombreCuenta);
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
     * @param nodo de tipo nodoBinarioModificado
     * @param indice de tipo int
     */
    public void convertirNodoAArray(nodoBinarioModificado nodo, int indice) {
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

    /** convertirArbolAArray:
     * Invoca convertirNodoAArray utilizando como parámetros la raiz y el índice 0
     */
    public void convertirArbolAArray() {
        int indice = 0;
        registroNodos = new Object[0];
        convertirNodoAArray(nodoRaiz, indice);
        log("Recorriendo elementos del Árbol por nivel  " + Arrays.toString(registroNodos));
    }

    /**
     * actuarEnNodo: Efectua una acción en un nodo si se cumple un cierto criterio
     * @param nodo nodo en donde buscar
     * @param objetivo que buscar
     * @param accion acción a efectuar
     */
    public void actuarEnNodo(nodoBinarioModificado nodo, Object objetivo, String accion) {
        if (!this.busquedaActiva) return;
        log("Nombre: " + nodo.getNombreCuenta() + ", número de cuenta: " + nodo.getValor());
        Object compara = -1;
        if (objetivo instanceof String) {
            compara = nodo.getNombreCuenta();
        }
        if (objetivo instanceof Integer) {
            compara = nodo.getValor();
        }

        if (accion == "buscar" && compara == objetivo) {
            log("Se encontro el nodo su cuenta es la # " + nodo.getValor());
            this.busquedaActiva = false;
        }
        if (accion == "eliminar" && compara == objetivo) {
            log("Se encontro el nodo con " + nodo.getValor() + " se eliminará de " + nodo.nodoPadre.getValor());
            nodoBinarioModificado papa = nodo.nodoPadre;
            papa.eliminarHijo(nodo);
            this.busquedaActiva = false;
        }
    }

    /**
     * recorridoPreOrden: recorre nodos de un árbol de forma recursiva utilizando la estrategia de PreOrden
     * invoca actuar en Nodo antes de invocarse a si mismo con los hijos de nodo
     * @param nodo nodo a recorrer
     * @param objetivo que se busca
     * @param accion accion a efectuar cuando se complete la búsqueda
     */
    public void recorridoPreOrden(nodoBinarioModificado nodo, Object objetivo, String accion) {
        if (!this.busquedaActiva) return;
        actuarEnNodo(nodo, objetivo, accion);
        if (nodo.hijoIzq != null) {
            recorridoPreOrden(nodo.hijoIzq, objetivo, accion);
        }
        if (nodo.hijoDer != null) {
            recorridoPreOrden(nodo.hijoDer, objetivo, accion);
        }
    }
    /**
     * recorridoPostOrden: recorre nodos de un árbol de forma recursiva utilizando la estrategia de PostOrden
     * invoca actuar en Nodo después de invocarse a si mismo con los hijos de nodo
     * @param nodo nodo a recorrer
     * @param objetivo que se busca
     * @param accion accion a efectuar cuando se complete la búsqueda
     */
    public void recorridoPostOrden(nodoBinarioModificado nodo, Object objetivo, String accion) {
        if (!this.busquedaActiva) return;
        if (nodo.hijoIzq != null) {
            recorridoPostOrden(nodo.hijoIzq, objetivo, accion);
        }
        if (nodo.hijoDer != null) {
            recorridoPostOrden(nodo.hijoDer, objetivo, accion);
        }
        actuarEnNodo(nodo, objetivo, accion);
    }
    /**
     * recorridoEntreOrden: recorre nodos de un árbol de forma recursiva utilizando la estrategia de EntreOrden
     * invoca actuar en Nodo antes de invocarse usando el hijo derecho pero
     * después de invocarse con el hijo izquierdo
     * @param nodo nodo a recorrer
     * @param objetivo que se busca
     * @param accion accion a efectuar cuando se complete la búsqueda
     */
    public void recorridoEntreOrden(nodoBinarioModificado nodo, Object objetivo, String accion) {
        if (!this.busquedaActiva) return;
        if (nodo.hijoIzq != null) {
            recorridoEntreOrden(nodo.hijoIzq, objetivo, accion);
        }
        actuarEnNodo(nodo, objetivo, accion);
        if (nodo.hijoDer != null) {
            recorridoEntreOrden(nodo.hijoDer, objetivo, accion);
        }
    }

    /**
     * buscar: busca un nodo en el árbol mediante una estrategia determinada
     * @param objetivo que se busca
     * @param estrategia que estrategia se utilizará
     */
    public void buscar(Object objetivo, String estrategia) {
        reiniciarBusqueda();
        log("\nBuscando " + objetivo);
        if (estrategia == "preorden") {
            log("Realizando búsqueda con estrategia de Pre orden");
            recorridoPreOrden(nodoRaiz, objetivo, "buscar");
        }
        if (estrategia == "postorden") {
            log("Realizando búsqueda con estrategia de Post orden");
            recorridoPostOrden(nodoRaiz, objetivo, "buscar");
        }
        if (estrategia == "entreorden") {
            log("Realizando búsqueda con estrategia de Entre orden");
            recorridoEntreOrden(nodoRaiz, objetivo, "buscar");
        }
        if (this.busquedaActiva) {
            log("\nNo se encontró el nodo");
        }
    }
    /**
     * buscar: busca un nodo en el árbol mediante una estrategia determinada y luego lo elimina
     * @param objetivo que se busca
     * @param estrategia que estrategia se utilizará
     */
    public void eliminar(Object objetivo, String estrategia) {
        reiniciarBusqueda();
        log("\nEliminando " + objetivo);
        if (estrategia == "preorden") {
            log("Realizando búsqueda con estrategia de Pre orden para eliminar");
            recorridoPreOrden(nodoRaiz, objetivo, "eliminar");
        }
        if (estrategia == "postorden") {
            log("Realizando búsqueda con estrategia de Post orden para eliminar");
            recorridoPostOrden(nodoRaiz, objetivo, "eliminar");
        }
        if (estrategia == "entreorden") {
            log("Realizando búsqueda con estrategia de Entre orden para eliminar");
            recorridoEntreOrden(nodoRaiz, objetivo, "eliminar");
        }
        if (this.busquedaActiva) {
            log("\nNo se encontró el nodo");
        }
    }

    /**
     * reiniciarBusqueda: reinicia el estado de la bandera busquedaActiva
     */
    public void reiniciarBusqueda() {
        this.busquedaActiva = true;
    }
}


class ejemploArbolBinarioModificado {
    /**
     * log: Wrapper de la funcion System.out.print
     *
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.println(str);
    }


    public static void main(String[] args) {
        arbolBinarioModificado cuentas = new arbolBinarioModificado();
        nodoBinarioModificado raiz = cuentas.iniciar(32);
        raiz.setNombreCuenta("Sucursal");

        // Hijos de 32
        nodoBinarioModificado hI = cuentas.agregarNodo(raiz, 18, "Activos", true);
        nodoBinarioModificado hD = cuentas.agregarNodo(raiz, 64, "Pasivos", false);

        // Hijos de 18
        nodoBinarioModificado hI_nI = cuentas.agregarNodo(hI, 13, "Activo circulante", true);
        nodoBinarioModificado hI_nD = cuentas.agregarNodo(hI, 27, "Activo fijo", false);

        // Hijos de 13
        nodoBinarioModificado hI_nI_bnI = cuentas.agregarNodo(hI_nI, 6, "Caja", true);
        nodoBinarioModificado hI_nI_bnD = cuentas.agregarNodo(hI_nI, 15, "Bancos", false);

        // Hijos de 27
        nodoBinarioModificado hI_nD_bnI = cuentas.agregarNodo(hI_nD, 24, "Mobiliario", true);
        nodoBinarioModificado hI_nD_bnD = cuentas.agregarNodo(hI_nD, 30, "Maquinaria", false);

        // Hijos de 64
        nodoBinarioModificado hD_nI = cuentas.agregarNodo(hD, 43, "Corto plazo", true);
        nodoBinarioModificado hD_nD = cuentas.agregarNodo(hD, 80, "Largo plazo", false);

        // Hijos de 43
        nodoBinarioModificado hD_nI_bnI = cuentas.agregarNodo(hD_nI, 35, "Proovedores", true);
        nodoBinarioModificado hD_nI_bnD = cuentas.agregarNodo(hD_nI, 49, "Cuentas por pagar", false);

        // Hijos de 80
        nodoBinarioModificado hD_nD_bnI = cuentas.agregarNodo(hD_nD, 75, "Intereses pagos", true);
        nodoBinarioModificado hD_nD_bnD = cuentas.agregarNodo(hD_nD, 89, "Rentas pagadas", false);


        log("\n");
        cuentas.convertirArbolAArray();
        cuentas.buscar("Proovedores", "postorden");
        cuentas.buscar("Proovedores", "preorden");
        cuentas.buscar("Caja", "entreorden");

        log("\n");
        cuentas.eliminar(43, "postorden");
        cuentas.eliminar("Pasivos", "postorden");
        cuentas.buscar("Cuentas por pagar", "postorden");
        cuentas.convertirArbolAArray();


    }
}