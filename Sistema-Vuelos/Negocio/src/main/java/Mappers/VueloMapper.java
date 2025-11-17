/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Mappers;

import DTOs.VueloDTO;
import POJOs.Vuelo;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class VueloMapper {
    private AsientoMapper mapper;

    public VueloMapper() {
        this.mapper = new  AsientoMapper();
    }

    public Vuelo convertirAEntity(VueloDTO vuelo){
        Vuelo entity =  new Vuelo();
        entity.setObjectID(vuelo.getId());
        entity.setDestino(vuelo.getDestino());
        entity.setDuracion(vuelo.getDuracion());
        entity.setFechaSalida(vuelo.getFechaSalida());
        entity.setListaAsientos(mapper.convertirListaAEntity(vuelo.getListaAsientos()));
        System.out.println("Vuelo antes de devolver entity: VueloMapper");
        return entity;
    }
    public VueloDTO convertirADto(Vuelo vuelo){
        System.out.println("Vuelo a converti a DTO: VueloMapper" + vuelo.toString()) ;       
        VueloDTO dto =  new VueloDTO();
        dto.setId(vuelo.getObjectID());
        dto.setDestino(vuelo.getDestino());
        dto.setDuracion(vuelo.getDuracion());
        dto.setFechaSalida(vuelo.getFechaSalida());
        dto.setListaAsientos(mapper.convertirListaADto(vuelo.getListaAsientos()));
        dto.setOrigen(vuelo.getOrigen());
        
        System.out.println("VueloDTO: VueloMapper" + dto.toString());
        return dto;
    }
}
