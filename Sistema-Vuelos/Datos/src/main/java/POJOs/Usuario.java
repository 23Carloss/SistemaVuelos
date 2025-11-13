/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package POJOs;

import CRUD.ObjetoMongo;
import com.mongodb.client.model.Updates;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Usuario implements ObjetoMongo {
    
    private ObjectId id;
    private String nombre, apellidoP, apellidoM,correo,contraseña;
    private List<Reservacion> reservaciones;

    @Override
    public ObjectId getObjectID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setObjectID(ObjectId objectID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Bson toUpdateOperations() {
        return Updates.combine(
            Updates.set("nombre", nombre),
            Updates.set("correo", correo)
        );
    }
    
    
}
