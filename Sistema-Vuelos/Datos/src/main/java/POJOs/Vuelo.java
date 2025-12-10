/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJOs;

import CRUD.ObjetoMongo;
import com.mongodb.client.model.Updates;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Vuelo implements ObjetoMongo {

    @BsonId
    private ObjectId _id;
    private String numVuelo; //Id del vuelo para hacer gets
    private String origen, destino; //usar la primera mayuscula (ej. Guadalajara)
    private Instant fechaSalida; //hora y fecha que sale el avion
    private int duracion; // duración en minutos del vuelo
    private List<Asiento> listaAsientos; //Lista de todos los asientos del vuelo
    private String aerolinea;
    private float precio;

    public Vuelo() {
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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

    public Instant getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Instant fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void setListaAsientos(List<Asiento> listaAsientos) {
        this.listaAsientos = listaAsientos;
    }

    @Override
    public String toString() {
        return "Vuelo{" + "_id=" + _id + ", numVuelo=" + numVuelo + ", origen=" + origen + ", destino=" + destino + ", fechaSalida=" + fechaSalida + ", duracion=" + duracion + ", listaAsientos=" + listaAsientos + ", aerolinea=" + aerolinea + ", precio=" + precio + '}';
    }

    @Override
    public ObjectId get_id() {
        return _id;
    }

    @Override
    public void set_id(ObjectId objectID) {
        this._id = objectID;
    }

    @Override
    public Bson toUpdateOperations() {
        return Updates.combine(
                Updates.set("aerolinea", aerolinea),
                Updates.set("origen", origen),
                Updates.set("precio", precio),
                Updates.set("destino", destino),
                Updates.set("duracion", duracion),
                Updates.set("fechaSalida", fechaSalida),
                Updates.set("listaAsientos", listaAsientos)
        );
    }

}
