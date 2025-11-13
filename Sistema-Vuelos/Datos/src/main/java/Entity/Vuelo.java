/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Entity;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Vuelo {
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

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Vuelo{" + "_id=" + _id + ", origen=" + origen + ", destino=" + destino + ", fechaSalida=" + fechaSalida + ", duracion=" + duracion + ", listaAsientos=" + listaAsientos + '}';
    }

    
    

}
