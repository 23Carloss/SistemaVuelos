/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DTOs;

import java.util.List;
import org.bson.types.ObjectId;


/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */

public class UsuarioDTO {
    private ObjectId id;
    private String nombre, apellidoP, apellidoM;
    private List<ReservacionDTO> reservaciones;

    public UsuarioDTO() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public List<ReservacionDTO> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<ReservacionDTO> reservaciones) {
        this.reservaciones = reservaciones;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", reservaciones=" + reservaciones + '}';
    }

}
