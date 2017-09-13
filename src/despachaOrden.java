import java.util.*;

public class despachaOrden {
    Queue cola  = new ArrayDeque();
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.print(str);
    }

    /**
     * agregarOrden: Agrega una orden a la cola de órdenes pendientes
     * invoca mostrarOrdenesPendientes  al final
     * @param ordenId
     */
    void agregarOrden(int ordenId) {
        log("\nAgregando orden #" + ordenId + " a la cola");
        cola.add(ordenId);
        mostrarOrdenesPendientes();
    }

    /**
     * despacharOrden: Toma la primera orden de la cola,
     * la saca de la cola y regresa la orden
     * invoca mostrarOrdenesPendientes  al final
     * @return orden
     */
    int despacharOrden() {
        if (!hayOrdenesPendientes()) {
            return 0;
        }
        Integer orden = (Integer) cola.poll();
        log("\nDespachando orden #" + orden );
        mostrarOrdenesPendientes();
        return orden;
    }

    /**
     * mostrarOrdenesPendientes:
     * Convierte la cola a array y este a un string y lo imprime
     */
    void mostrarOrdenesPendientes() {
        if (hayOrdenesPendientes()) {
            log("\nOrdenes pendientes:\n");
            Object[] colaArray = cola.toArray();
            System.out.println(Arrays.toString(colaArray));
        } else {
            log("\nNo hay ordenes pendientes\n");
        }

    }

    /**
     * hayOrdenesPendientes: evalua si hay ordenes pendientes en la cola
     * @return
     */
    boolean hayOrdenesPendientes() {
        return !cola.isEmpty();
    }
}


class ejemploDespachaOrden {

    public static void main(String args[]) {
        despachaOrden despachador = new despachaOrden();
        despachador.mostrarOrdenesPendientes();
        int agregarXElementos = 10;
        int eliminarXElementos = 10;
        Random randomGenerator = new Random();
        despachador.mostrarOrdenesPendientes();
        for (int idx = 1; idx <= agregarXElementos; ++idx){
            int randomInt = randomGenerator.nextInt(200);
            despachador.agregarOrden(randomInt);
        }
        despachador.mostrarOrdenesPendientes();
        for (int idx = 1; idx <= eliminarXElementos ; ++idx){
            despachador.despacharOrden();
        }

    }
}