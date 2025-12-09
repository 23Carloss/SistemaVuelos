/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTOs.VueloDTO;
import POJOs.Vuelo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class VueloMapper {

    private AsientoMapper mapper;

    public VueloMapper() {
        this.mapper = new AsientoMapper();
    }

    public Vuelo convertirAEntity(VueloDTO vuelo) {
        Vuelo entity = new Vuelo();

        System.out.println("Vuelo en mapper: " + vuelo.getListaAsientos().toString());
        entity.setNumVuelo(vuelo.getNumVuelo());
        entity.setOrigen(vuelo.getOrigen());

        entity.set_id(vuelo.getId());
        entity.setAerolinea(vuelo.getAerolinea());
        entity.setPrecio(vuelo.getPrecio());
        entity.setDestino(vuelo.getDestino());
        entity.setDuracion(vuelo.getDuracion());
        entity.setFechaSalida(vuelo.getFechaSalida());
        entity.setListaAsientos(mapper.convertirListaAEntity(vuelo.getListaAsientos()));
        return entity;
    }

    public VueloDTO convertirADto(Vuelo vuelo) {
        VueloDTO dto = new VueloDTO();
        dto.setNumVuelo(vuelo.getNumVuelo());
        dto.setId(vuelo.get_id());
        dto.setAerolinea(vuelo.getAerolinea());
        dto.setPrecio(vuelo.getPrecio());
        dto.setDestino(vuelo.getDestino());
        dto.setDuracion(vuelo.getDuracion());
        dto.setFechaSalida(vuelo.getFechaSalida());
        dto.setListaAsientos(mapper.convertirListaADto(vuelo.getListaAsientos()));
        dto.setOrigen(vuelo.getOrigen());

        return dto;
    }

    public List<VueloDTO> ConvertirListaADto(List<Vuelo> listaEntity) {
        ArrayList<VueloDTO> listaVuelos = new ArrayList<>();
        for (Vuelo u : listaEntity) {
            listaVuelos.add(convertirADto(u));
        }
        return listaVuelos;

    }
}
