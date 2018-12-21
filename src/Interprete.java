import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interprete de datos de entrada
 *
 * @author Juan Francisco Casanova Ferrer
 */
class Interprete {
    private String x;
    private String y;

    /**
     * Constructor indicado cuando no se encuentra un archivo de entrada de datos
     */
    Interprete() {

        System.out.println();
        System.out.println("No se ha detectado un archivo de entrada, procedemos a la entrada de datos por consola");

        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Introducir cadena de caracteres X | Conjunto inicial");
            x = scanner.nextLine();

            System.out.println("Introducir cadena de caracteres Y | Conjunto al que deseamos llegar: ");
            y = scanner.nextLine();
        } while (encontrarError(x, y));

        scanner.close();
    }

    /**
     * Constructor indicado cuando se cuenta con un archivo de entrada
     *
     * @param archivoEntrada Archivo en el que se encuentran los datos
     * @throws IOException En el caso de que no se encuentre el archivo
     */
    Interprete(String archivoEntrada) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
        int i = 0;
        String line;
        while ((line = br.readLine()) != null && i < 2) {
            switch (i) {
                case 0:
                    x = line;
                case 1:
                    y = line;
            }
            i++;
        }

        if (encontrarError(x, y)) {
            throw new IllegalArgumentException("Error: El formato del archivo de datos de entrada no es compatible");
        }
    }

    // Getter X: cadena inicial
    String getX() {
        return x;
    }

    // Getter Y: cadena objetivo
    String getY() {
        return y;
    }

    /**
     * Verifica que los datos de entrada sean correctos
     *
     * @param x Cadena inicial
     * @param y Cadena objetivo
     * @return True si se encuentra un error en los datos
     */
    private boolean encontrarError(String x, String y) {

        // Los datos no existen
        if (x == null || y == null) {
            System.err.println("Error: Deben existir ambos datos de entrada");
            return true;
        }

        return false;
    }
}
