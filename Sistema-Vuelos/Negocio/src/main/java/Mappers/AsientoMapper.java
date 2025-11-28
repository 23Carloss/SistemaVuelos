/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Mappers;

import DTOs.AsientoDTO;
import POJOs.Asiento;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class AsientoMapper {

    public AsientoMapper() {
    }
    
    public AsientoDTO convertirADto(Asiento asiento){
        AsientoDTO dto = new AsientoDTO();
        dto.setId(asiento.get_id());
        dto.setDisponibilidad(asiento.isDisponibilidad());
        dto.setFila(asiento.getFila());
        dto.setNumero(asiento.getNumero());
        return dto;
        
    }
    public Asiento convertirAEntity(AsientoDTO asiento){
        Asiento entity = new Asiento();
        entity.set_id(asiento.getId());
        entity.setDisponibilidad(asiento.isDisponibilidad());
        entity.setFila(asiento.getFila());
        entity.setNumero(asiento.getNumero());
        return entity;
        
    }

    public List<AsientoDTO> convertirListaADto(List<Asiento> listaEntity){
        ArrayList<AsientoDTO> listAsientoDTO =  new  ArrayList<>();
        for(Asiento a :listaEntity){
            listAsientoDTO.add(convertirADto(a));
        }
        return listAsientoDTO;
  
    }
     public List<Asiento> convertirListaAEntity(List<AsientoDTO> listaDTO){
        ArrayList<Asiento> listAsiento =  new  ArrayList<>();
        for(AsientoDTO dto :listaDTO){
            listAsiento.add(convertirAEntity(dto));
        }
        return listAsiento;
     }

}

