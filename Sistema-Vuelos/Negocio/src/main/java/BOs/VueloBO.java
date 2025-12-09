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
import com.mongodb.MongoException;
import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class VueloBO implements IVueloBO {

    private final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final SecureRandom random;
    private IVueloDAO dao;
    private VueloMapper Mapper;

    public VueloBO() {
        random = new SecureRandom();
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

            List<Vuelo> vuelos = dao.filtrarVuelos(origen, destino, precio);
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
    public List<VueloDTO> filtrarVuelos(String origen, String destino, float precio, Date fecha) throws NegocioException {
        try {
            validarCiudad(origen, "origen");
            validarCiudad(destino, "destino");
            if (precio < 1f) {
                throw new IllegalArgumentException("el precio tiene que ser mayor que 0");
            }

            if (fecha == null) {
                throw new IllegalArgumentException("La fecha no puede ser nula");
            }
            
            
            
            
            List<Vuelo> vuelos = dao.filtrarVuelos(origen, destino,precio,fecha);
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
    public List<VueloDTO> filtrarVuelos(String origen, String destino, float precio, Date fecha, LocalTime hora) throws NegocioException {
        try {
            validarCiudad(origen, "origen");
            validarCiudad(destino, "destino");
            if (precio < 1f) {
                throw new IllegalArgumentException("el precio tiene que ser mayor que 0");
            }
            
            if (fecha == null) {
                throw new IllegalArgumentException("La fecha no puede ser nula");
            }
            
            if (hora==null) {
                throw new IllegalArgumentException("La hora no puede ser nula");
            }
            

            List<Vuelo> vuelos = dao.filtrarVuelos(origen, destino,precio,fecha,hora);
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

    /**
     * Se crea un vuelo con un numero de vuelo generado de forma random
     *
     * @param vueloDTO
     * @return
     * @throws NegocioException
     */
    @Override
    public VueloDTO crearVuelo(VueloDTO vueloDTO) throws NegocioException {
        String numVueloNuevo;
        try {

            char letra1 = LETRAS.charAt(random.nextInt(LETRAS.length()));
            char letra2 = LETRAS.charAt(random.nextInt(LETRAS.length()));
            int numeros = random.nextInt(100, 1000); // rango 0–999
            numVueloNuevo = String.valueOf(letra1) + String.valueOf(letra2) + String.format("%03d", numeros);

            vueloDTO.setNumVuelo(numVueloNuevo);
            validarCiudad(vueloDTO.getDestino(), "Destino");
            validarCiudad(vueloDTO.getOrigen(), "Origen");

//            vueloDTO.setListaAsientos(new LinkedList<>()); utilizaremos el metodo de burgos

            Vuelo vuelo = Mapper.convertirAEntity(vueloDTO);

            dao.create(vuelo);

            return vueloDTO;

        } catch (MongoException ex) {
            throw new NegocioException("Error en VueloBO: crearVuelo: " + ex.getMessage());
        }

    }

    public boolean ocuparAsiento(String numVuelo, AsientoDTO asientoDTO) throws NegocioException {
        try {
            VueloDTO vueloDTO = getVuelo(numVuelo);

            List<AsientoDTO> asientos = vueloDTO.getListaAsientos();

            for (AsientoDTO asiento : asientos) {
                if (asiento.getFila() == asientoDTO.getFila()) {
                    if (asiento.getNumero() == asientoDTO.getNumero()) {

                        int indice = asientos.indexOf(asiento);
                        asientos.set(indice, asientoDTO);
                        break;
                    }
                }

            }

            vueloDTO.setListaAsientos(asientos);
            Vuelo vuelo = Mapper.convertirAEntity(vueloDTO);

            dao.actualizarAsientosPorVuelo(vuelo);

            return true;

        } catch (NegocioException e) {

            throw new NegocioException("Error en VueloBO: ocuparAsiento: getVuelo: " + e.getMessage());

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en VueloBO: ocuparAsiento: " + ex.getMessage());
        }
    }

    @Override
    public List<VueloDTO> obtenerTodos() throws NegocioException {
        List<Vuelo> listEntity = dao.findEntities();
        List<VueloDTO> listaVuelos = Mapper.ConvertirListaADto(listEntity);
        return listaVuelos;
    }

    @Override
    public boolean actualizarVuelo(VueloDTO vuelo) throws NegocioException {
        try {
            if (vuelo == null) {
                throw new NegocioException("Vuelo nulo");
            }
            Vuelo vueloEntity = Mapper.convertirAEntity(vuelo);
            return dao.actualizarPorNumeroVuelo(vueloEntity);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en VueloBO: actualizarVuelo: " + ex.getMessage());
        }
    }

    @Override
    public boolean eliminarVuelo(VueloDTO vuelo) throws NegocioException {
        try {
            if (vuelo == null) {
                throw new NegocioException("VUelo nulo");
            }
            Vuelo vueloEntity = Mapper.convertirAEntity(vuelo);
            return dao.eliminarPorNumeroVuelo(vueloEntity);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en VueloBO: eliminarVuelo: " + ex.getMessage());
        }
    }

}
