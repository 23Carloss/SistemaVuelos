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
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class ReservacionDTO {
    @BsonId
    private ObjectId _id;
    private ObjectId Usuario;
    private LocalDateTime fechaReservacion;
    private List<AsientoDTO> asientos;
    private VueloDTO vuelo;

    public ReservacionDTO() {
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

    public List<AsientoDTO> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<AsientoDTO> asientos) {
        this.asientos = asientos;
    }

    public VueloDTO getVuelo() {
        return vuelo;
    }

    public void setVuelo(VueloDTO vuelo) {
        this.vuelo = vuelo;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    @Override
    public String toString() {
        return "Reservacion{" + "id=" + _id + ", Usuario=" + Usuario + ", fechaReservacion=" + fechaReservacion + ", asientos=" + asientos + ", vuelo=" + vuelo + '}';
    }

}
