/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAOs.VueloDAO;
import DTOs.AsientoDTO;
import DTOs.VueloDTO;
import Interfaces.IVueloBO;
import InterfacesDAO.IVueloDAO;
import Mappers.VueloMapper;
import POJOs.Vuelo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class VueloBO implements IVueloBO {

    IVueloDAO dao;
    VueloMapper Mapper;

    public VueloBO() {
        dao = new VueloDAO();
        Mapper = new VueloMapper();
    }

    @Override
    public List<AsientoDTO> getAsientosDisponibles() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AsientoDTO> getAsientosOcupados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<VueloDTO> getBuscarVuelos(String origen, String destino, LocalDateTime salida) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public VueloDTO getVuelo(ObjectId _id) {
        Vuelo vuelo = (Vuelo) dao.read(_id);
        VueloDTO dto = Mapper.convertirADto(vuelo);
        return dto;
    }

    @Override
    public VueloDTO getVuelo(String numVuelo) {

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public List<VueloDTO> filtrarVuelos(String origen) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<VueloDTO> filtrarVuelos(String origen, String destino) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<VueloDTO> filtrarVuelos(String origen, String destino, float precio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
