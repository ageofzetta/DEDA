import java.util.*;
class despachaCamion {
    Stack pila = new Stack();
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.print(str);
    }
    /**
     * iniciarCamiones: Se invoca desde el constructor
     * llena la pila con id's de camiones que se pueden despachar
     * @param arrayIds Array con los ids de los camiones a despachar
     */
    private void iniciarCamiones( int[] arrayIds){
        log("\nInicializando camiones");
        for (int i = 0; i < arrayIds.length; i++) {
            pila.push(arrayIds[i]);
        }
    }
    /**
     * obtenerCamion: Despacha un camion
     * regresa el primer id de la pila y lo remueve de la pila
     * @return int
     */
    int obtenerCamion(){
        Integer a = (Integer) pila.pop();
        log("\nDespachando camión: " + a);
        listarCamiones();
        return a;
    }
    /**
     * regresarCamion: Regresa un camión a la bodega de camiones
     * añade el id al tope de la pila
     * @param id Identificador de camión
     */
    void regresarCamion(int id){
        log("\nCamión " + id + " regresando...");
        pila.push(new Integer(id));
        listarCamiones();
    }
    /**
     * camionesDisponibles: Evalua si hay camiones disponibles
     * invoca isEmpty en la pila y regresa el valor negado de éste
     * @return boolean
     */
    boolean camionesDisponibles(){
        return !pila.empty();
    }
    /**
     * listarCamiones: Imprime la lista de camiones
     */
    Object[] listarCamiones() {
        if (camionesDisponibles()) {
            log("\nCamiones disponibles:");
            System.out.println(Arrays.toString(pila.toArray()));
            return pila.toArray();
        }
        log("\nYa no hay camiones disponibles");
        return new Object[0];

    }
    /**
     * constructor
     */
    despachaCamion(int[] arrayIds){
        iniciarCamiones(arrayIds);
    }
}


class ejemploDespachaCamion {
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    public static void log(String str) {
        System.out.print(str);
    }

    public static void main(String args[]) {
        int[] idsCamiones= new int[]{22,13,34,45,70};
        despachaCamion despachador = new despachaCamion(idsCamiones);
        despachador.listarCamiones();
        int primerCamion = despachador.obtenerCamion();
        int segundoCamion = despachador.obtenerCamion();
        int tercerCamion = despachador.obtenerCamion();
        int cuartoCamion = despachador.obtenerCamion();
        int quintoCamion = despachador.obtenerCamion();
        despachador.listarCamiones();
        despachador.regresarCamion(primerCamion);
        despachador.regresarCamion(segundoCamion);
        despachador.regresarCamion(tercerCamion);
    }
}