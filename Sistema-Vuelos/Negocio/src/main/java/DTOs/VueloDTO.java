/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;
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
    private List<AsientoDTO> listaAsientos; //Lista de todos los asientos del vuelo
    private String aerolinea;
    private float precio;

    public VueloDTO() {
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

}
