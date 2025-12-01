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
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Reservacion implements ObjetoMongo {
    @BsonId
    private ObjectId _id;
    private String numReservacion;
    private String correoUsuario;
    private LocalDateTime fechaReservacion;
    private List<Asiento> asientos;
    private Vuelo vuelo;
    private Instant creadoEn;
    private Instant editadoEn;

    public Reservacion() {
    }

    public String getNumReservacion() {
        return numReservacion;
    }

    public void setNumReservacion(String numReservacion) {
        this.numReservacion = numReservacion;
    }
    
    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
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
        return "Reservacion{" + "_id=" + _id + ", numReservacion=" + numReservacion + ", correoUsuario=" + correoUsuario + ", fechaReservacion=" + fechaReservacion + ", asientos=" + asientos + ", vuelo=" + vuelo + ", creadoEn=" + creadoEn + ", editadoEn=" + editadoEn + '}';
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
            Updates.set("fechaReservacion", fechaReservacion),
            Updates.set("asientos", asientos),
            Updates.set("vuelo", vuelo),
            Updates.set("editadoEn", editadoEn),
            Updates.set("creadoEn", creadoEn),
            Updates.set("correoUsuario", correoUsuario),
            Updates.set("numReservacion", numReservacion)
        );
    }
    
    
    

}
