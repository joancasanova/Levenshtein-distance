import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase que contiene el algoritmo que calcula el resultado del problema de la Practica 2 de Programacion y Estructuras
 * de Datos Avanzadas del curso 2018-2019 por la U.N.E.D.
 *
 * @author Juan Francisco Casanova Ferrer
 */
class Algoritmo {
    private List<String> solucion; // Lista que contiene los diferentes apartados de la solucion.

    /**
     * @param x Cadena de caracteres inicial
     * @param y Cadena de caracteres objetivo
     */
    Algoritmo(String x, String y, boolean traza) {
        solucion = new ArrayList<>();
        int n = x.length() + 1;
        int m = y.length() + 1;

        // Inicio del calculo.
        int[][] tabla = DistanciaEdicion(x, y, n - 1, m - 1, new int[n][m], traza);

        System.out.println(Arrays.deepToString(tabla));

        List<Transformacion> transformaciones = setTrans(x, y, n, m, tabla, new ArrayList<>(), traza);

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
    private int[][] DistanciaEdicion(String x, String y, int n, int m, int[][] c, boolean traza) {

        for (int i = 0; i <= n; i++) {
            c[i][0] = i;
        }

        for (int j = 0; j <= m; j++) {
            c[0][j] = j;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int tmp = Math.min(1 + c[i - 1][j], 1 + c[i][j - 1]);

                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = Math.min(tmp, c[i - 1][j - 1]);
                } else {
                    c[i][j] = Math.min(tmp, c[i - 1][j - 1] + 1);
                }
            }
        }

        return c;
    }

    private List<Transformacion> setTrans(String x, String y, int n, int m, int[][] c, List<Transformacion> trans, boolean traza) {

        int i = n - 1;
        int j = m - 1;
        int k = c[n - 1][m - 1];

        while (k > 0) {

            if (i > 0 && c[i][j] == c[i - 1][j] + 1) {
                x = x.substring(0, j + 1) + x.substring(j + 2);
                trans.add(new Transformacion("borrado", j + 2, x));
                k = k - 1;
                i = i - 1;
            }

            if (j > 0 && c[i][j] == c[i][j - 1] + 1) {
                x = x.substring(0, j - 1) + y.charAt(j - 1) + x.substring(j - 1);
                trans.add(new Transformacion("insercion", j + 1, x));
                k = k - 1;
                j = j - 1;
            }

            if (i > 0 && j > 0 && c[i][j] == c[i - 1][j - 1] + 1) {
                x = x.substring(0, j - 1) + y.charAt(j - 1) + x.substring(j);
                trans.add(new Transformacion("sustitucion", j + 1, x));
                k = k - 1;
                i = i - 1;
                j = j - 1;
            }

            if (i > 0 && j > 0 && c[i][j] == c[i - 1][j - 1]) {
                i = i - 1;
                j = j - 1;
            }
        }

        return trans;
    }

    // Getter solucion: subconjuntos obtenidos por el algoritmo
    List<String> getSolucion() {
        return solucion;
    }
}