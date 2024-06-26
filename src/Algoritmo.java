import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene el algoritmo que calcula el resultado del problema de la Practica 2 de Programacion y Estructuras
 * de Datos Avanzadas del curso 2018-2019 por la U.N.E.D.
 *
 * @author Juan Francisco Casanova Ferrer
 * teléfono: 625803490
 * email:    juancasanovaferrer@gmail.com
 * Programación y Estructuras de Datos Avanzadas - UNED - Centro Asociado Las Tablas
 */
class Algoritmo {
    private List<String> solucion; // Lista que contiene los diferentes apartados de la solucion.

    /**
     * @param x Cadena de caracteres inicial
     * @param y Cadena de caracteres objetivo
     */
    Algoritmo(StringBuilder x, StringBuilder y, boolean traza) {
        solucion = new ArrayList<>();
        int n = x.length() + 1;
        int m = y.length() + 1;

        // Inicio del calculo.
        int[][] tabla = DistanciaEdicion(x, y, n - 1, m - 1, new int[n][m], traza);

        // Obtener las transformaciones.
        List<Transformacion> transformaciones = setTrans(x, y, n, m, tabla, new ArrayList<>(), traza);

        // Añadir los resultados a la solucion
        solucion.add(String.valueOf(tabla[n - 1][m - 1]));

        for (Transformacion trans : transformaciones) {
            solucion.add(trans.getOperacion() + " " + trans.getPosicion() + " " + trans.getX());
        }
    }

    /**
     * Algoritmo que toma el esquema de programacion dinamica
     *
     * @param x     Cadena inicial
     * @param y     Cadena objetivo
     * @param n     Longitud cadena inicial
     * @param m     Longitud cadena objetivo
     * @param c     Tabla que alberga el numero de operaciones minimas para transformar X en Y y los pasos intermedios
     * @param traza Valor booleano que indica si deseamos mostrar la traza o no
     */
    private int[][] DistanciaEdicion(StringBuilder x, StringBuilder y, int n, int m, int[][] c, boolean traza) {

        if (traza) {
            System.out.println();
            System.out.println("Inicializar tabla");
        }

        // Inicializamos la primera columna
        for (int i = 0; i <= n; i++) {
            c[i][0] = i;
        }

        // Inicializamos la primera fila
        for (int j = 0; j <= m; j++) {
            c[0][j] = j;
        }

        if (traza) {
            System.out.println();
            System.out.println("Rellenar tabla");
        }
        // Rellenamos tabla
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {

                int tmp = Math.min(1 + c[i - 1][j], 1 + c[i][j - 1]);

                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = Math.min(tmp, c[i - 1][j - 1]);
                } else {
                    c[i][j] = Math.min(tmp, c[i - 1][j - 1] + 1);
                }

                if (traza) {
                    System.out.println();
                    System.out.println("Introducir " + c[i][j] + " en fila " + i + ", columna " + j);
                    System.out.println("Tabla:");
                    for (int p = 0; p <= n; p++) {
                        for (int q = 0; q <= m; q++) {
                            System.out.print(c[p][q]);
                            System.out.print(" ");
                        }
                        System.out.println();
                    }
                }
            }
        }

        return c;
    }

    /**
     * Algoritmo para obtener los cambios realizados
     */
    private List<Transformacion> setTrans(StringBuilder x, StringBuilder y, int n, int m, int[][] c, List<Transformacion> trans, boolean traza) {

        if (traza) {
            System.out.println();
            System.out.println("Inicio de analisis de transformaciones");
            System.out.println();
        }

        int i = n - 1;
        int j = m - 1;
        int k = c[n - 1][m - 1];

        while (k > 0) {

            if (traza) {
                System.out.println("Analizado transformaciones... Fila " + i + ", Columna " + j);
            }

            if (i > 0 && c[i][j] == c[i - 1][j] + 1) {
                x.deleteCharAt(i - 1);
                trans.add(new Transformacion("borrado", i, x.toString()));
                k = k - 1;
                i = i - 1;
            } else if (j > 0 && c[i][j] == c[i][j - 1] + 1) {
                x.insert(i, y.charAt(j - 1));
                trans.add(new Transformacion("insercion", j, x.toString()));
                k = k - 1;
                j = j - 1;
            } else if (i > 0 && j > 0 && c[i][j] == c[i - 1][j - 1] + 1) {
                x.deleteCharAt(i - 1);
                x.insert(i - 1, y.charAt(j - 1));
                trans.add(new Transformacion("sustitucion", j, x.toString()));
                k = k - 1;
                i = i - 1;
                j = j - 1;
            } else if (i > 0 && j > 0 && c[i][j] == c[i - 1][j - 1]) {
                i = i - 1;
                j = j - 1;
            }
        }

        if (traza) {
            System.out.println();
            System.out.println("Fin de analisis de transformaciones");
            System.out.println();
        }

        return trans;
    }

    // Getter solucion: subconjuntos obtenidos por el algoritmo
    List<String> getSolucion() {
        return solucion;
    }
}