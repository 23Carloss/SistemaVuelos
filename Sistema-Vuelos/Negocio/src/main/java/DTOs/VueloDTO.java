/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DTOs;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class VueloDTO {
    private long id;
    private String origen, destino;
    private LocalDateTime fechaSalida;
    private int duracion;
    private List<AsientoDTO> listaAsientos;

    public VueloDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public List<AsientoDTO> getListaAsientos() {
        return listaAsientos;
    }

    public void setListaAsientos(List<AsientoDTO> listaAsientos) {
        this.listaAsientos = listaAsientos;
    }

    @Override
    public String toString() {
        return "Vuelo{" + "id=" + id + ", origen=" + origen + ", destino=" + destino + ", fechaSalida=" + fechaSalida + ", duracion=" + duracion + ", listaAsientos=" + listaAsientos + '}';
    }
    
    

}
