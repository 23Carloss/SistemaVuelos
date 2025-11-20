/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package POJOs;

import CRUD.ObjetoMongo;
import com.mongodb.client.model.Updates;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Reservacion implements ObjetoMongo {
    
    private ObjectId _id;
    private ObjectId Usuario;
    private LocalDateTime fechaReservacion;
    private List<Asiento> asientos;
    private Vuelo vuelo;
    private Instant creadoEn;
    private Instant editadoEn;

    public Reservacion() {
    }


    public ObjectId getUsuario() {
        return Usuario;
    }

    public void setUsuario(ObjectId Usuario) {
        this.Usuario = Usuario;
    }

    public LocalDateTime getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(LocalDateTime fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Instant creadoEn) {
        this.creadoEn = creadoEn;
    }

    public Instant getEditadoEn() {
        return editadoEn;
    }

    public void setEditadoEn(Instant editadoEn) {
        this.editadoEn = editadoEn;
    }
    

    @Override
    public String toString() {
        return "Reservacion{" + "id=" + _id + ", Usuario=" + Usuario + ", fechaReservacion=" + fechaReservacion + ", asientos=" + asientos + ", vuelo=" + vuelo + '}';
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
            Updates.set("Usuario", Usuario),
            Updates.set("fechaReservacion", fechaReservacion),
            Updates.set("asientos", asientos),
            Updates.set("vuelo", vuelo)
        );
    }
    
    
    

}
