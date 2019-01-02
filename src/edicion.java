import java.io.File;
import java.io.IOException;

/**
 * Clase que contiene el metodo Main del programa
 * Filtra los argumentos introducidos al invocar dicho metodo
 * Controla el orden de ejecucion del programa
 *
 * @author Juan Francisco Casanova Ferrer
 * teléfono: 625803490
 * email:    juancasanovaferrer@gmail.com
 * Programación y Estructuras de Datos Avanzadas - UNED - Centro Asociado Las Tablas
 */
class edicion {
    private boolean help = false;
    private boolean traza = false;
    private String archivoSalida = null;
    private String archivoEntrada = null;

    /**
     * @param argumentos Argumentos pasados por el ususario al iniciar el programa
     */
    private edicion(String[] argumentos) throws IOException {

        // Filtracion de los argumentos
        filtrar(argumentos);

        // Ejecucion del programa
        ejecutar();
    }

    /**
     * Metodo Main del programa
     *
     * @param args Argumentos de entrada
     * @throws IOException Si no se encuentra el archivo de entrada especificado
     */
    public static void main(String[] args) throws IOException {
        new edicion(args);
    }

    /**
     * Filtra los argumentos y los asigna a las variables correspondientes
     *
     * @param argumentos Argumentos de entrada
     */
    private void filtrar(String[] argumentos) {
        for (String argumento : argumentos) {
            if (argumento.equals("-h")) {
                help = true;
            } else if (argumento.equals("-t")) {
                traza = true;
            } else if (archivoEntrada != null) {
                archivoSalida = argumento;
                break;
            } else {
                archivoEntrada = argumento;
            }
        }
    }

    /**
     * Controlador del programa
     *
     * @throws IOException Si no se localiza el archivo de entrada
     */
    private void ejecutar() throws IOException {

        // Si se desea mostrar la ayuda, se muestra y termina la ejecucion
        if (help) {
            Impresor.imprimirHelp();
            System.exit(0);
        }

        // Se interpretan los datos de entrada obtenidos mediante consola o mendiante un archivo
        Interprete interprete = setInterprete();

        // Se realizan las operaciones del algoritmo
        Algoritmo algoritmo = new Algoritmo(new StringBuilder(interprete.getX()), new StringBuilder(interprete.getY()), traza);

        // Se muestran los resultados
        mostrarResultados(algoritmo);
    }

    /**
     * Crea un interprete para datos a traves de la consola o a traves de un archivo
     *
     * @return Interprete de datos de entrada
     * @throws IOException Si no se encuentra el archivo de entrada especificado
     */
    private Interprete setInterprete() throws IOException {
        if (archivoEntrada == null || !new File(archivoEntrada).exists()) {
            System.out.println();
            System.out.println("No se ha encontrado el archivo de entrada");
            return new Interprete();
        } else {
            return new Interprete(archivoEntrada);
        }
    }

    /**
     * Muestra los resultados de las operaciones
     *
     * @throws IOException Si no se encuentra el archivo de salida especificado
     */
    private void mostrarResultados(Algoritmo algoritmo) throws IOException {
        if (archivoSalida != null) {
            if (!new File(archivoSalida).exists()) {
                Impresor.archivoSalida(archivoSalida, algoritmo.getSolucion());
            } else {
                System.err.println("Atencion: El archivo de salida ya existe, procedemos a imprimir el resultado por pantalla");
                Impresor.imprimirSolucion(algoritmo.getSolucion());
            }
        } else {
            Impresor.imprimirSolucion(algoritmo.getSolucion());
        }
    }
}
