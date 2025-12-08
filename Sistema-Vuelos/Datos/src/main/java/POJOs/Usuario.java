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


    @Override
    public Bson toUpdateOperations() {
        List<Bson> updates = new ArrayList<>();
        if(this.nombre != null )updates.add(Updates.set("nombre", this.nombre));
        if (this.apellidoP != null) updates.add(Updates.set("apellidoP", this.apellidoP));
        if (this.apellidoM != null) updates.add(Updates.set("apellidoM", this.apellidoM));
        if (this.correo != null) updates.add(Updates.set("correo", this.correo));
        if (this.contrasenia != null) updates.add(Updates.set("contrasenia", this.contrasenia));
        if (this.tipoUsuario != null) updates.add(Updates.set("tipoUsuario", this.tipoUsuario));

        return Updates.combine(updates);
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
    public String toString() {
        return "Usuario{" + "_id=" + _id + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", correo=" + correo + ", contrasenia=" + contrasenia + ", tipoUsuario=" + tipoUsuario + '}';
    }

}
