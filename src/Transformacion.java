public class Transformacion {
    private String operacion;
    private int posicion;
    private char caracter;
    private String x;

    Transformacion(String operacion, int posicion, char caracter, String x) {
        this.operacion = operacion;
        this.posicion = posicion;
        this.caracter = caracter;
        this.x = x;
    }

    Transformacion(String operacion, int posicion, String x) {
        this.operacion = operacion;
        this.posicion = posicion;
        this.x = x;
    }

    public String getOperacion() {
        return operacion;
    }

    public int getPosicion() {
        return posicion;
    }

    public char getCaracter() {
        return caracter;
    }

    public String getX() {
        return x;
    }
}
