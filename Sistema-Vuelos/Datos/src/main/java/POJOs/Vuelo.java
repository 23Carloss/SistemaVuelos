/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package POJOs;

import CRUD.ObjetoMongo;
import com.mongodb.client.model.Updates;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Vuelo implements ObjetoMongo{
    
    private ObjectId _id;
    private String origen, destino;
    private LocalDateTime fechaSalida;
    private int duracion;
    private List<Asiento> listaAsientos;

    public Vuelo() {
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

    public List<Asiento> getListaAsientos() {
        return listaAsientos;
    }

    public void setListaAsientos(List<Asiento> listaAsientos) {
        this.listaAsientos = listaAsientos;
    }

    @Override
    public String toString() {
        return "Vuelo{" + "id=" + _id + ", origen=" + origen + ", destino=" + destino + ", fechaSalida=" + fechaSalida + 
                ", duracion=" + duracion + ", listaAsientos=" + listaAsientos + '}';
    }

    @Override
    public ObjectId getObjectID() {
        return _id;
    }

    @Override
    public void setObjectID(ObjectId objectID) {
        this._id = objectID;
    }

    @Override
    public Bson toUpdateOperations() {
        return Updates.combine(
            Updates.set("_id", _id),
            Updates.set("origen", origen),
            Updates.set("destino", destino),
            Updates.set("duracion", duracion),
            Updates.set("fechaSalida", fechaSalida),
            Updates.set("listaAsientos", listaAsientos)

        );
    }
    
    

}
