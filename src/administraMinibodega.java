
import java.util.*;
public class administraMinibodega {
    LinkedList lista = new LinkedList();
    int numeroMinibodegas = 10;
    /**
     * log: Wrapper de la funcion System.out.print
     * @param str para imprimir
     */
    private static void log(String str) {
        System.out.print(str);
    }

    /**
     * iniciarRentaMinibodega: Asocia una minibodega a un nombre
     * primero evalua si hay minibodegas disponibles
     * @param Nombre
     */
    void iniciarRentaMinibodega(String Nombre) {
        if (lista.size() < numeroMinibodegas) {
            log("\nIniciando renta para: " + Nombre);
            lista.add(Nombre);
        } else {
            log("\n*** No hay minibodegas disponibles ***");
        }
    }

    /**
     * terminarRentaMinibodega: Desvincula una minibodega por Nombre
     * @param Nombre
     */
    void terminarRentaMinibodega(String Nombre) {
        try {
            int idx = lista.indexOf(Nombre);
            if (idx >= 0) {
                log("\nTerminando renta para: " + Nombre);
                lista.remove(Nombre);
            } else {
                log("\nNo existe el elemento");
            }

        } catch (IndexOutOfBoundsException e) {
            log("\nNo existe el elemento");
        }
    }

    /**
     * terminarRentaMinibodegaPorIndice: Libera una minibodega por índice
     * @param indice
     */
    void terminarRentaMinibodegaPorIndice(int indice) {
        try {
            String Nombre = (String) lista.get(indice);
            log("\nTerminando renta para: " + Nombre);
            lista.remove(Nombre);
        } catch (IndexOutOfBoundsException e) {
            log("\nNo existe el elemento");
        }
    }

    /**
     * noHayMinibodegasRegistradas: Evalua si no existen minibodegas ocupadas
     * @return boolean
     */
    boolean noHayMinibodegasRegistradas() {
        return lista.isEmpty();
    }

    /**
     * mostrarOcupadas: Muestra las minibodegas ocupadas
     */
    void mostrarOcupadas() {
        if (noHayMinibodegasRegistradas()){
            log("\nNo hay minibodegas ocupadas");
        }
        log("\nTotal de Minibodegas ocupadas: " + lista.size());
        for(int num=0; num<lista.size(); num++)
        {
            int idx = num + 1;
            log("\n    La minibodega #" + idx +" está ocupada por " + lista.get(num));
        }
    }
}

class ejemploMinibodega {

    public static void log(String str) {
        System.out.print(str);
    }

    public static void main(String args[]) {
        administraMinibodega minibodega = new administraMinibodega();
        if (minibodega.noHayMinibodegasRegistradas()) {
            log("\nNo hay clientes\n");
        } else {
            log("\nSi hay clientes\n");
        }
        minibodega.iniciarRentaMinibodega("Alejandro");
        minibodega.iniciarRentaMinibodega("Juan");
        minibodega.iniciarRentaMinibodega("Pedro");
        minibodega.iniciarRentaMinibodega("Josué");
        minibodega.iniciarRentaMinibodega("Emir");
        minibodega.iniciarRentaMinibodega("Naomi");
        minibodega.iniciarRentaMinibodega("Hugo");
        minibodega.iniciarRentaMinibodega("Iván");
        minibodega.iniciarRentaMinibodega("Mafalda");
        minibodega.iniciarRentaMinibodega("Matilde");
        minibodega.iniciarRentaMinibodega("Rodolfo");
        minibodega.mostrarOcupadas();
        minibodega.terminarRentaMinibodega("Juan");
        minibodega.terminarRentaMinibodegaPorIndice(0);
        minibodega.mostrarOcupadas();
        if (minibodega.noHayMinibodegasRegistradas()) {
            log("\nNo hay clientes\n");
        } else {
            log("\nSi hay clientes\n");
        }
    }
}