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
    private String columna;
    private int fila;

    public AsientoDTO(String columna, int fila, boolean disponible) {
        this.columna = columna;
        this.fila = fila;
        this.disponibilidad = disponible;
    }
    
    public AsientoDTO() {
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }
    
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
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
        return "AsientoDTO{" + "disponibilidad=" + disponibilidad + ", columna=" + columna + ", fila=" + fila + '}';
    }

    

    


   
     
    
}
