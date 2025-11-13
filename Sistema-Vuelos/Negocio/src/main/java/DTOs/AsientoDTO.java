/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DTOs;


/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class AsientoDTO {
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AsientoDTO{" + "id=" + id + ", disponibilidad=" + disponibilidad + ", numero=" + numero + ", fila=" + fila + '}';
    }


   
     
    
}
