package DTOs;

import java.time.LocalDateTime;

public class VueloDTO {

    long precio;
    String nombre;
    String origen;
    String destino;
    LocalDateTime fechaSalida;
    int duracion;
    String aerolinea;
    AsientoDTO asientos[];

    public VueloDTO(long precio, String nombre, String origen, String destino, LocalDateTime fechaSalida, int duracion, String aerolinea) {
        this.precio = precio;
        this.nombre = nombre;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.duracion = duracion;
        this.aerolinea = aerolinea;

        //Placeholder, cambiar por asignación real
        testeoAsignacionASientos();

    }

    public long getPrecio() { return precio; }
    public String getNombre() { return nombre; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public int getDuracion() { return duracion; }
    public String getAerolinea() { return aerolinea; }

    //Getter de asiento específico según su índice en el arreglo
    public AsientoDTO getAsiento(int index) {
        if (asientos == null) {
            throw new IllegalStateException("El arreglo de asientos no ha sido inicializado.");
        }
        if (index < 0 || index >= asientos.length) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        return asientos[index];
    }


    //PLACEHOLDER DE TESTEO - BORRAR
    public void testeoAsignacionASientos() {

        asientos = new AsientoDTO[]{

                new AsientoDTO("A", 1, true),
                new AsientoDTO("B", 1, true),
                new AsientoDTO("C", 1, false),
                new AsientoDTO("D", 1, true),
                new AsientoDTO("E", 1, true),
                new AsientoDTO("F", 1, true),

                new AsientoDTO("A", 2, true),
                new AsientoDTO("B", 2, true),
                new AsientoDTO("C", 2, true),
                new AsientoDTO("D", 2, false),
                new AsientoDTO("E", 2, true),
                new AsientoDTO("F", 2, true),

                new AsientoDTO("A", 3, true),
                new AsientoDTO("B", 3, true),
                new AsientoDTO("C", 3, true),
                new AsientoDTO("D", 3, true),
                new AsientoDTO("E", 3, false),
                new AsientoDTO("F", 3, true),

                new AsientoDTO("A", 4, true),
                new AsientoDTO("B", 4, true),
                new AsientoDTO("C", 4, true),
                new AsientoDTO("D", 4, true),
                new AsientoDTO("E", 4, true),
                new AsientoDTO("F", 4, false),

                new AsientoDTO("A", 5, true),
                new AsientoDTO("B", 5, false),
                new AsientoDTO("C", 5, true),
                new AsientoDTO("D", 5, true),
                new AsientoDTO("E", 5, true),
                new AsientoDTO("F", 5, true),

                new AsientoDTO("A", 6, true),
                new AsientoDTO("B", 6, true),
                new AsientoDTO("C", 6, true),
                new AsientoDTO("D", 6, true),
                new AsientoDTO("E", 6, false),
                new AsientoDTO("F", 6, true),

                new AsientoDTO("A", 7, false),
                new AsientoDTO("B", 7, true),
                new AsientoDTO("C", 7, true),
                new AsientoDTO("D", 7, true),
                new AsientoDTO("E", 7, true),
                new AsientoDTO("F", 7, true),

                new AsientoDTO("A", 8, true),
                new AsientoDTO("B", 8, true),
                new AsientoDTO("C", 8, true),
                new AsientoDTO("D", 8, false),
                new AsientoDTO("E", 8, true),
                new AsientoDTO("F", 8, true),

                new AsientoDTO("A", 9, true),
                new AsientoDTO("B", 9, true),
                new AsientoDTO("C", 9, true),
                new AsientoDTO("D", 9, true),
                new AsientoDTO("E", 9, true),
                new AsientoDTO("F", 9, false),

                new AsientoDTO("A", 10, true),
                new AsientoDTO("B", 10, true),
                new AsientoDTO("C", 10, false),
                new AsientoDTO("D", 10, true),
                new AsientoDTO("E", 10, true),
                new AsientoDTO("F", 10, true)
        };
    }


}