/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package POJOs;

import CRUD.ObjetoMongo;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Usuario implements ObjetoMongo {
    @BsonId
    private ObjectId _id;
    private String nombre, apellidoP, apellidoM,correo,contrasenia;    
    private String tipoUsuario;
    private List<Reservacion> reservaciones = new ArrayList<>();

    public Usuario() {
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
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

    public List<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }


    public String getTipoU() {
        return tipoUsuario;
    }

    public void setTipoU(String tipoU) {
        this.tipoUsuario = tipoU;
    }
    
    
    
    @Override
    public ObjectId get_id() {
        return _id;
    }

    @Override
    public void set_id(ObjectId _id) {
        this._id=_id;
    }

    @Override
    public Bson toUpdateOperations() {
        return Updates.combine(
            Updates.set("nombre", nombre),
            Updates.set("correo", correo),
            Updates.set("apellidoP", apellidoP),
            Updates.set("apellidoM", apellidoM),
            Updates.set("contrasenia", contrasenia), 
            Updates.set("tipoUsuario", tipoUsuario),
            Updates.set("reservaciones", reservaciones)
        );
    }

    @Override
    public String toString() {
        return "Usuario{" + "_id=" + _id + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", correo=" + correo + ", contrasenia=" + contrasenia + 
                ", tipoUsuario=" + tipoUsuario + ", reservaciones=" + reservaciones + '}';
    }
    
    
    
}
