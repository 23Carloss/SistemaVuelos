/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAOs.VueloDAO;
import DTOs.AsientoDTO;
import DTOs.VueloDTO;
import Exception.PersistenciaException;
import Interfaces.IVueloBO;
import InterfacesDAO.IVueloDAO;
import Mappers.AsientoMapper;
import Mappers.VueloMapper;
import NegocioException.NegocioException;
import POJOs.Asiento;
import POJOs.Vuelo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    // Para ciudades (origen/destino)
private boolean validarCiudad(String valor, String nombreCampo) {
    if (valor == null || valor.trim().isEmpty()) {
        throw new IllegalArgumentException("El campo " + nombreCampo + " no puede estar vacío.");
    }
    valor = valor.trim();

    // Permitir varias palabras con mayúscula inicial
    

    return true;
}

// Para número de vuelo (alfanumérico)
private boolean validarNumVuelo(String valor) {
    if (valor == null || valor.trim().isEmpty()) {
        throw new IllegalArgumentException("El número de vuelo no puede estar vacío.");
    }
    valor = valor.trim();

    if (!valor.matches("^[A-Za-z0-9]+$")) {
        throw new IllegalArgumentException("El número de vuelo solo puede contener letras y números.");
    }

    return true;
}


    @Override
    public List<VueloDTO> getBuscarVuelos(String origen, String destino, Date salida) throws NegocioException {

        try {
            validarCiudad(origen, "origen");
            validarCiudad(destino, "destino");

            List<Vuelo> vuelos = dao.getBuscarVuelos(origen, destino, salida);

            List<VueloDTO> dtos = new ArrayList<>();

            for (Vuelo vuelo : vuelos) {
                VueloDTO dto = Mapper.convertirADto(vuelo);
                dtos.add(dto);
            }
            return dtos;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error en VueloBO: getBuscarVuelos: " + e.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Error en VueloBO en los campos: getBuscarVuelos: " + ex.getMessage());
        }

    }

    @Override
    public VueloDTO getVuelo(ObjectId _id) throws NegocioException {
        Vuelo vuelo = (Vuelo) dao.read(_id);
        if (vuelo == null) {
            throw new NegocioException("No existe un vuelo con el id proporcionado.");
        }
        return Mapper.convertirADto(vuelo);
    }

    @Override
    public VueloDTO getVuelo(String numVuelo) throws NegocioException {
        try {
            validarCiudad(numVuelo, "numero de vuelo");
            VueloDTO dto = Mapper.convertirADto(dao.getVuelo(numVuelo));

            return dto;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en VueloBO: getVuelo: " + e.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Error en VueloBO en los campos: getVuelo: " + ex.getMessage());
        }

    }

    @Override
    public List<VueloDTO> filtrarVuelos(String origen) throws NegocioException {

        try {
            validarCiudad(origen, "origen");

            List<Vuelo> vuelos = dao.filtrarVuelos(origen);
            List<VueloDTO> dtos = new ArrayList<>();

            for (Vuelo vuelo : vuelos) {
                VueloDTO dto = Mapper.convertirADto(vuelo);
                dtos.add(dto);
            }

            return dtos;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en VueloBO: filtrarVuelos: " + e.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Error en VueloBO en los campos: filtrarVuelos: " + ex.getMessage());
        }

    }

    @Override
    public List<VueloDTO> filtrarVuelos(String origen, String destino) throws NegocioException {

        try {
            validarCiudad(origen, "origen");
            validarCiudad(destino, "destino");

            List<Vuelo> vuelos = dao.filtrarVuelos(origen, destino);
            List<VueloDTO> dtos = new ArrayList<>();

            for (Vuelo vuelo : vuelos) {
                VueloDTO dto = Mapper.convertirADto(vuelo);
                dtos.add(dto);
            }

            return dtos;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en VueloBO: filtrarVuelos: " + e.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Error en VueloBO en los campos: filtrarVuelos: " + ex.getMessage());
        }

    }

    @Override
    public List<VueloDTO> filtrarVuelos(String origen, String destino, float precio) throws NegocioException {
        try {
            validarCiudad(origen, "origen");
            validarCiudad(destino, "destino");
            if (precio < 1f) {
                throw new IllegalArgumentException("el precio tiene que ser mayor que 0");
            }

            List<Vuelo> vuelos = dao.filtrarVuelos(origen, destino);
            List<VueloDTO> dtos = new ArrayList<>();

            for (Vuelo vuelo : vuelos) {
                VueloDTO dto = Mapper.convertirADto(vuelo);
                dtos.add(dto);
            }

            return dtos;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en VueloBO: filtrarVuelos: " + e.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Error en VueloBO en los campos: filtrarVuelos: " + ex.getMessage());
        }
    }

    @Override
    public List<AsientoDTO> getAsientosDisponibles(String numVuelo) throws NegocioException {
        try {
            AsientoMapper asientoMapper = new AsientoMapper();
            validarNumVuelo(numVuelo);

            List<Asiento> asientos = dao.getAsientosDisponibles(numVuelo);

            List<AsientoDTO> dtos = new ArrayList<>();

            for (Asiento asiento : asientos) {
                AsientoDTO dto = asientoMapper.convertirADto(asiento);
                dtos.add(dto);
            }

            return dtos;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en VueloBO: getAsientosDisponibles: " + e.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Error en VueloBO en los campos: getAsientosDisponibles: " + ex.getMessage());
        }

    }

    @Override
    public List<AsientoDTO> getAsientosOcupados(String numVuelo) throws NegocioException {
        try {
            AsientoMapper asientoMapper = new AsientoMapper();
            validarNumVuelo(numVuelo);

            List<Asiento> asientos = dao.getAsientosOcupados(numVuelo);

            List<AsientoDTO> dtos = new ArrayList<>();

            for (Asiento asiento : asientos) {
                AsientoDTO dto = asientoMapper.convertirADto(asiento);
                dtos.add(dto);
            }

            return dtos;

        } catch (PersistenciaException e) {
            throw new NegocioException("Error en VueloBO: getAsientosOcupados: " + e.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Error en VueloBO en los campos: getAsientosOcupados: " + ex.getMessage());
        }
    }

}
