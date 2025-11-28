/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DTOs;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;


/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class AsientoDTO {
    @BsonId
    private ObjectId _id;
    private boolean disponibilidad;
    private int numero;
    private int fila;

    public AsientoDTO() {
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

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId id) {
        this._id = id;
    }

    @Override
    public String toString() {
        return "AsientoDTO{" + "id=" + _id + ", disponibilidad=" + disponibilidad + ", numero=" + numero + ", fila=" + fila + '}';
    }


   
     
    
}
