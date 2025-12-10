/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DTOs;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class ReservacionDTO {
    
    private ObjectId _id;
    private String coreoUsuario;
    private String numReservacion;
    private Instant fechaReservacion;
    private AsientoDTO asiento;
    private VueloDTO vuelo;

    public ReservacionDTO() {
    }

    public String getCoreoUsuario() {
        return coreoUsuario;
    }

    public void setCoreoUsuario(String coreoUsuario) {
        this.coreoUsuario = coreoUsuario;
    }

    public Instant getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(Instant fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public AsientoDTO getAsiento() {
        return asiento;
    }

    public void setAsiento(AsientoDTO asiento) {
        this.asiento = asiento;
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

    public String getNumReservacion() {
        return numReservacion;
    }

    public void setNumReservacion(String numReservacion) {
        this.numReservacion = numReservacion;
    }

    @Override
    public String toString() {
        return "ReservacionDTO{" + "_id=" + _id + ", coreoUsuario=" + coreoUsuario + ", numReservacion=" + numReservacion + ", fechaReservacion=" + fechaReservacion + ", asiento=" + asiento + ", vuelo=" + vuelo + '}';
    }

    
    

   

}
