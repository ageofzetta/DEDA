import java.util.Arrays;

class listac{
    public int pointer = 0;
    // public Array<int> listac = [0];
    public int[] listac = new int[0];
    public int[] listacB = new int[0];
    public int size = 0;

    public boolean estaVacia(){
        return (listac.length == 0);
    }

    public static void log(String str) {
        System.out.print("\n" + str);
    }
    public void listacrElementosLista() {
        listacrElementos(listac);
    }
    public void listacrElementos(int[] array){
        log(Arrays.toString(array));
    }
    public void insertarEnIndice(int indice, int elem){
        int indiceMasUno = indice + 1;
        int indiceMenosUno = indice - 1;
        int tamano = listac.length - 1;
        if (indice > tamano) {
            log("Error, fuera de límite");
            insertarAlFinal(elem);
        } else {
            if (tamano > 0) {
                int[] parte1 = Arrays.copyOfRange(listac, 0, indiceMasUno);
                parte1[parte1.length - 1] = elem;
                int[] parte2 = Arrays.copyOfRange(listac, indice, listac.length);
                log("" + indiceMasUno);
                listacrElementos(listac);
                listacrElementos(parte1);
                listacrElementos(parte2);
                int[] result = Arrays.copyOf(parte1, parte1.length + parte2.length);
                System.arraycopy(parte2, 0, result, parte1.length, parte2.length);
                listac = result;
                listacB = result;
            }
        }
    }
    public void insertarAlFinal(int elem){
        log("Insertando al final:");
        int tamano = listac.length + 1;
        redimensionarArray(tamano);
        int posicion = listac.length - 1;
        listac[posicion] = elem;
    }
    public void eliminarEnIndice(int indice){

    }
    public void redimensionarArray(int tamano){
        listacB = Arrays.copyOfRange(listac, 0, tamano);
        listac = Arrays.copyOfRange(listacB, 0, tamano);
    }


}

class ejemploListaC{
    public static listac listacM = new listac();
    public static void log(String str) {
        System.out.print(str);
    }
    public static void main(String[] args) {

        //if (listacM.estaVacia()) {
        //    log("La listac está vacía");
        //} else {
        //    log("La listac NO está vacía");
        //}

        listacM.insertarEnIndice(0, 12);
        listacM.insertarEnIndice(1, 24);
        listacM.insertarEnIndice(2, 36);
        listacM.insertarEnIndice(1, 15);
        listacM.listacrElementosLista();
    }
}