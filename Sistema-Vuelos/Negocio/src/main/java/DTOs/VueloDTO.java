/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez & Jesus Gammael Soto Escalante
 */
public class VueloDTO {

    @BsonId
    private ObjectId _id;
    private String numVuelo; //Id del vuelo para hacer gets
    private String origen, destino; //usar la primera mayuscula (ej. Guadalajara)
    private LocalDateTime fechaSalida; //hora y fecha que sale el avion
    private int duracion; // duración en minutos del vuelo
    private List<AsientoDTO> listaAsientos = new ArrayList<>();; //Lista de todos los asientos del vuelo
    private String aerolinea;
    private float precio;

    public VueloDTO() {
       testeoAsignacionASientos();

    }

    public VueloDTO(long precio, String nombre, String origen, String destino, LocalDateTime fechaSalida, int duracion, String aerolinea) {
        this.precio = precio;

        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.duracion = duracion;
        this.aerolinea = aerolinea;
        this.listaAsientos = new ArrayList<>();
        testeoAsignacionASientos();
    }

    public String getNumVuelo() {
        return numVuelo;
    }

    public void setNumVuelo(String numVuelo) {
        this.numVuelo = numVuelo;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<AsientoDTO> getListaAsientos() {
        return listaAsientos;
    }

    public void setListaAsientos(List<AsientoDTO> listaAsientos) {
        this.listaAsientos = listaAsientos;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "VueloDTO{" + "id=" + _id + ", origen=" + origen + ", destino=" + destino + ", fechaSalida=" + fechaSalida + ", duracion=" + duracion + ", listaAsientos=" + listaAsientos + '}';
    }

    public AsientoDTO getAsiento(int index) {
        if (listaAsientos == null) {
            throw new IllegalStateException("El arreglo de asientos no ha sido inicializado.");
        }
        if (index < 0 || index >= listaAsientos.size()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        return listaAsientos.get(index);    
    }

    private void testeoAsignacionASientos() {
        System.out.println("Entra?");
        var asientos = Arrays.asList(
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
        );
        listaAsientos.addAll(asientos);
        System.out.println(listaAsientos.size());
    }
}
