/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package POJOs;

import CRUD.ObjetoMongo;
import com.mongodb.client.model.Updates;
import java.util.ArrayList;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Usuario implements ObjetoMongo {
    
    private ObjectId _id;
    private String nombre, apellidoP, apellidoM,correo,contrasenia;    
    private TipoUsuario tipoUsuario;
    private List<Reservacion> reservaciones = new ArrayList<>();

    public Usuario() {
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


    public TipoUsuario getTipoU() {
        return tipoUsuario;
    }

    public void setTipoU(TipoUsuario tipoU) {
        this.tipoUsuario = tipoU;
    }
    
    
    
    @Override
    public ObjectId getObjectID() {
        return _id;
    }

    @Override
    public void setObjectID(ObjectId objectID) {
        this._id=objectID;
    }

    @Override
    public Bson toUpdateOperations() {
        return Updates.combine(
            Updates.set("_id", _id),
            Updates.set("nombre", nombre),
            Updates.set("correo", correo),
            Updates.set("apellidoPaterno", apellidoP),
            Updates.set("apellidoMaterno", apellidoM),
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
