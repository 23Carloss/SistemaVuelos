/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DTOs;

import POJOs.TipoUsuario;
import java.util.ArrayList;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;


/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */

public class UsuarioDTO {
    @BsonId
    private ObjectId _id;
    private String nombre, apellidoP, apellidoM, correo, contrasenia;
    private TipoUsuario tipoUsuario;
    private List<ReservacionDTO> reservaciones = new ArrayList<>();

    public UsuarioDTO() {
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId id) {
        this._id = id;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "id=" + _id + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", correo=" + correo + ", contrasenia=" + contrasenia + ", tipoUsuario=" + tipoUsuario + ", reservaciones=" + reservaciones + '}';
    }
    

}
