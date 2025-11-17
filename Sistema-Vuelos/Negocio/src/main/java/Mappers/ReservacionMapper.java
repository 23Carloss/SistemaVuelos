/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Mappers;

import DTOs.ReservacionDTO;
import POJOs.Reservacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class ReservacionMapper {
    private AsientoMapper asientoMapper;
    private VueloMapper vueloMapper;

    public ReservacionMapper() {
        this.asientoMapper = new AsientoMapper();
        this.vueloMapper = new VueloMapper();
        
    }
    public Reservacion convertirAEntity(ReservacionDTO reservacion){
        var entity = new Reservacion();
        entity.setFechaReservacion(reservacion.getFechaReservacion());
        entity.setObjectID(reservacion.getId());
        entity.setAsientos(asientoMapper.convertirListaAEntity(reservacion.getAsientos()));
        entity.setUsuario(reservacion.getUsuario());
        entity.setVuelo(vueloMapper.convertirAEntity(reservacion.getVuelo()));
        return entity;

    }
    public ReservacionDTO convertirADto(Reservacion reservacion){
        var dto = new ReservacionDTO();
        dto.setFechaReservacion(reservacion.getFechaReservacion());
        dto.setId(reservacion.getObjectID());
        dto.setAsientos(asientoMapper.convertirListaADto(reservacion.getAsientos()));
        dto.setUsuario(reservacion.getUsuario());
        dto.setVuelo(vueloMapper.convertirADto(reservacion.getVuelo()));
        return dto;

    }
    public List<Reservacion> convertirListaAEntity(List<ReservacionDTO >listaEntity){
        ArrayList<Reservacion> listaReservaciones =  new  ArrayList<>();
        for(ReservacionDTO dto :listaEntity){
            listaReservaciones.add(convertirAEntity(dto));
        }
        return listaReservaciones;
  
    }
     public List<ReservacionDTO> convertirListaADTO(List<Reservacion> listaDTO){
        ArrayList<ReservacionDTO> listaReservaciones =  new  ArrayList<>();
        for(Reservacion entity :listaDTO){
            listaReservaciones.add(convertirADto(entity));
        }
        return listaReservaciones;
     }

    

}
