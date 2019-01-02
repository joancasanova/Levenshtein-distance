import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Encargado de imprimir en pantalla o de crear un archivo de impresion
 *
 * @author Juan Francisco Casanova Ferrer
 * teléfono: 625803490
 * email:    juancasanovaferrer@gmail.com
 * Programación y Estructuras de Datos Avanzadas - UNED - Centro Asociado Las Tablas
 */
final class Impresor {

    /**
     * Muestra una ayuda y la sintaxis del comando
     */
    static void imprimirHelp() {
        System.out.println();
        System.out.println("_______________________________________________________________________");
        System.out.println("DESCRIPCION:");
        System.out.println("Sean dos cadenas de caracteres, X e Y, de un alfabeto finito. Sea n la");
        System.out.println("longitud de la cadena X y m la longitud de la cadena Y. La cadena X se");
        System.out.println("puede transformar en la cadena Y realizando los siguientes tipos de");
        System.out.println("operaciones:");
        System.out.println("    - Borrado: borrar un carácter de X");
        System.out.println("    - Inserción: insertar uno de los caracteres de Y en X");
        System.out.println("    - Sustitución: sustituir un caracter de X por uno de Y");
        System.out.println("Se define la distancia mínima de edición como el número mínimo de");
        System.out.println("operaciones, de entre los tres tipos anteriores necesarias para");
        System.out.println("transformar la cadena X en la cadena Y");
        System.out.println("_______________________________________________________________________");
        System.out.println("SINTAXIS:");
        System.out.println("java edicion [-t] [-h] [fichero_entrada] [fichero_salida]");
        System.out.println("o");
        System.out.println("java –jar edicion.jar [-t][-h] [fichero_entrada] [fichero_salida]");
        System.out.println("_______________________________________________________________________");
        System.out.println("ARGUMENTOS:");
        System.out.println("-t                          Traza la seleccion de operaciones");
        System.out.println("-h                          Muestra esta ayuda");
        System.out.println("fichero_entrada             Nombre del fichero de entrada");
        System.out.println("fichero_salida              Nombre del fichero de salida");
        System.out.println("_______________________________________________________________________");
        System.out.println("FORMATO ENTRADA:");
        System.out.println("El fichero de datos de entrada consta de: ");
        System.out.println("    - Una primera línea que contiene la primera cadena de caracteres (X)");
        System.out.println("    - Una segunda línea que contiene la segunda cadena de caracteres (Y)");
        System.out.println();
        System.out.println("En el caso de que no se encuentre un fichero de entrada se procedera a");
        System.out.println("usar la entrada por consola.");
        System.out.println("___________________________________________________________");
        System.out.println("AUTOR: Juan Francisco Casanova Ferrer");
        System.out.println("Programacion y estructuras de datos avanzadas");
        System.out.println("Practica 1 - 2018/19");
        System.out.println();
    }

    /**
     * Imprime en pantalla la solucion del problema
     *
     * @param solucion Lista que almacena la distancia minima de edicion calculada y las transformaciones aplicadas
     */
    static void imprimirSolucion(List<String> solucion) {
        System.out.println();
        System.out.println("_______________________________________________________________________");
        System.out.println("SOLUCION:");
        for (String linea : solucion) {
            System.out.println(linea);
        }
    }

    /**
     * Genera un archivo que contiene los datos de la solucion
     *
     * @param archivoSalida Archivo donde se almacenara la solucion
     * @param solucion      Lista que almacena la distancia minima de edicion calculada y las transformaciones aplicadas
     */
    static void archivoSalida(String archivoSalida, List<String> solucion) throws IOException {
        Path file = Paths.get(archivoSalida);
        Files.write(file, solucion, Charset.forName("UTF-8"));
    }
}
