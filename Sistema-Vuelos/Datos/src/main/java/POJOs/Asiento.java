/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package POJOs;

import CRUD.ObjetoMongo;
import com.mongodb.client.model.Updates;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Asiento implements ObjetoMongo {
    
    @BsonId
    private ObjectId _id;
    private boolean disponibilidad;
    private int numero;
    private int fila;

    public Asiento() {
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }


    @Override
    public String toString() {
        return "Asiento{" + "id=" + ", disponibilidad=" + disponibilidad + ", numero=" + numero + ", fila=" + fila + '}';
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
           
            Updates.set("disponibilidad", disponibilidad),
            Updates.set("numero", numero),
            Updates.set("fila", fila)

        );
    }
    
}
