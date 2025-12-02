package DTOs;

public class AsientoDTO {

    String columna;
    int fila;
    //final String nombre = columna+fila;
    boolean disponible;

    public AsientoDTO(String columna, int fila, boolean disponible) {
        this.columna = columna;
        this.fila = fila;
        this.disponible = disponible;
    }

    public String getColumna() {
        return columna;
    }
    public int getFila() {
        return fila;
    }
    /*
    public String getNombre() {
        return nombre;
    }*/
    public boolean isDisponible() {
        return disponible;
    }

}
