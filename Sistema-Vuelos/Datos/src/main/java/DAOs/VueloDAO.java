/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import CRUD.CRUD;
import Config.MongoClientProvider;
import Exception.PersistenciaException;
import InterfacesDAO.IVueloDAO;
import POJOs.Asiento;
import POJOs.Vuelo;
import com.mongodb.MongoException;
import com.mongodb.client.model.Filters;

import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class VueloDAO extends CRUD implements IVueloDAO {

    public VueloDAO() {
        super(MongoClientProvider.INSTANCE.database(), "Vuelos", Vuelo.class);
    }

    @Override
    public List<Asiento> getAsientosDisponibles(ObjectId _id) throws PersistenciaException {
        try {
            Vuelo vuelo = (Vuelo) collection.find(new Document("_id", _id)).first();

            if (vuelo == null) {
                throw new PersistenciaException("Error en getAsientosDisponibles: El vuelo con numero de vuelo " + _id + " no existe.");
            }

            List<Asiento> listaAsientos = vuelo.getListaAsientos();
            List<Asiento> asientosDisponibles = new LinkedList<>();

            if (listaAsientos != null) {
                for (Asiento asiento : listaAsientos) {
                    if (asiento.isDisponibilidad()) {
                        asientosDisponibles.add(asiento);
                    }
                }
            }

            return asientosDisponibles;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en getAsientosDisponibles: " + e.getMessage());
        }
    }

    @Override
    public List<Asiento> getAsientosOcupados(ObjectId _id) throws PersistenciaException {
        try {
            Vuelo vuelo = (Vuelo) collection.find(new Document("_id", _id)).first();

            if (vuelo == null) {
                throw new PersistenciaException("Error en getAsientosOcupados: El vuelo con numero de vuelo " + _id + " no existe.");
            }

            List<Asiento> listaAsientos = vuelo.getListaAsientos();
            List<Asiento> asientosOcupados = new LinkedList<>();

            if (listaAsientos != null) {
                for (Asiento asiento : listaAsientos) {
                    if (!asiento.isDisponibilidad()) {
                        asientosOcupados.add(asiento);
                    }
                }
            }

            return asientosOcupados;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en getAsientosOcupados: " + e.getMessage());
        }
    }

    @Override
    public List<Asiento> getAsientosDisponibles(String numVuelo) throws PersistenciaException {
        try {
            Vuelo vuelo = (Vuelo) collection.find(new Document("numVuelo", numVuelo)).first(); // <-- corregido

            if (vuelo == null) {
                throw new PersistenciaException("Error en getAsientosDisponibles: El vuelo con numero de vuelo " + numVuelo + " no existe.");
            }

            List<Asiento> listaAsientos = vuelo.getListaAsientos();
            List<Asiento> asientosDisponibles = new LinkedList<>();

            if (listaAsientos != null) {
                for (Asiento asiento : listaAsientos) {
                    if (asiento.isDisponibilidad()) {
                        asientosDisponibles.add(asiento);
                    }
                }
            }

            return asientosDisponibles;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en getAsientosDisponibles: " + e.getMessage());
        }
    }

    @Override
    public List<Asiento> getAsientosOcupados(String numVuelo) throws PersistenciaException {
        try {
            Vuelo vuelo = (Vuelo) collection.find(new Document("numVuelo", numVuelo)).first(); // <-- corregido

            if (vuelo == null) {
                throw new PersistenciaException("Error en getAsientosOcupados: El vuelo con numero de vuelo " + numVuelo + " no existe.");
            }

            List<Asiento> listaAsientos = vuelo.getListaAsientos();
            List<Asiento> asientosOcupados = new LinkedList<>();

            if (listaAsientos != null) {
                for (Asiento asiento : listaAsientos) {
                    if (!asiento.isDisponibilidad()) {
                        asientosOcupados.add(asiento);
                    }
                }
            }

            return asientosOcupados;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en getAsientosOcupados: " + e.getMessage());
        }
    }

    @Override
    public List<Vuelo> getBuscarVuelos(String origen, String destino, Date salida) throws PersistenciaException {
        try {
            List<Vuelo> vuelos = (List<Vuelo>) collection.find(Filters.and(
                    Filters.eq("origen", origen),
                    Filters.eq("destino", destino),
                    Filters.eq("fechaSalida", salida)
            )).limit(50).into(new ArrayList<>());

            if (vuelos == null || vuelos.isEmpty()) {
                throw new PersistenciaException("Error en getBuscarVuelos: Error al buscar vuelos con origen,destino y salida");
            }

            return vuelos;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en getBuscarVuelos: " + e.getMessage());
        }

    }

    @Override
    public Vuelo getVuelo(ObjectId _id) throws PersistenciaException {
        return (Vuelo) collection.find(new Document("_id", _id)).first();
    }

    @Override
    public Vuelo getVuelo(String numVuelo) throws PersistenciaException {
        return (Vuelo) collection.find(new Document("numVuelo", numVuelo)).first();
    }

    @Override
    public List<Vuelo> filtrarVuelos(String origen) throws PersistenciaException {
        try {
            List<Vuelo> vuelos = (List<Vuelo>) collection.find(Filters.and(
                    Filters.eq("origen", origen)
            )).limit(50).into(new ArrayList<>());

            if (vuelos == null || vuelos.isEmpty()) {
                throw new PersistenciaException("Error en filtrarVuelos: Error al buscar vuelos con origen");
            }

            return vuelos;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en filtrarVuelos: " + e.getMessage());
        }

    }

    @Override
    public List<Vuelo> filtrarVuelos(String origen, String destino) throws PersistenciaException {
        try {
            List<Vuelo> vuelos = (List<Vuelo>) collection.find(Filters.and(
                    Filters.eq("origen", origen),
                    Filters.eq("destino", destino)
            )).limit(50).into(new ArrayList<>());

            if (vuelos == null || vuelos.isEmpty()) {
                throw new PersistenciaException("Error en filtrarVuelos: Error al filtrar vuelos con origen,destino");
            }

            return vuelos;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en getBuscarVuelos: " + e.getMessage());
        }

    }

    @Override
    public List<Vuelo> filtrarVuelos(String origen, String destino, float precio) throws PersistenciaException {
        try {
            List<Vuelo> vuelos = (List<Vuelo>) collection.find(Filters.and(
                    Filters.eq("origen", origen),
                    Filters.eq("destino", destino),
                    Filters.lt("precio", precio)
            )).limit(50).into(new ArrayList<>());

            if (vuelos == null || vuelos.isEmpty()) {
                throw new PersistenciaException("Error en filtrarVuelos: Error al filtrar vuelos con origen,destino y salida");
            }

            return vuelos;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en filtrarVuelos: " + e.getMessage());
        }

    }

    @Override
    public List<Vuelo> filtrarVuelos(String origen, String destino, float precio, Date fecha) throws PersistenciaException {
        try {

            List<Vuelo> vuelos = (List<Vuelo>) collection.find(Filters.and(
                    Filters.eq("origen", origen),
                    Filters.eq("destino", destino),
                    Filters.lt("precio", precio),
                    Filters.gte("fecha", fecha)
            )).limit(50).into(new ArrayList<>());

            if (vuelos == null || vuelos.isEmpty()) {
                throw new PersistenciaException("Error en filtrarVuelos: Error al filtrar vuelos con origen,destino y salida");
            }

            return vuelos;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en filtrarVuelos: " + e.getMessage());
        }
    }

    @Override
    public List<Vuelo> filtrarVuelos(String origen, String destino, float precio, Date fecha, LocalTime hora) throws PersistenciaException {
        try {
            LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDateTime inicio = LocalDateTime.of(localDate, hora);

            Date fch = Date.from(inicio.atZone(ZoneId.systemDefault()).toInstant());

            List<Vuelo> vuelos = (List<Vuelo>) collection.find(Filters.and(
                    Filters.eq("origen", origen),
                    Filters.eq("destino", destino),
                    Filters.lt("precio", precio),
                    Filters.gte("fecha", fch)
            )).limit(50).into(new ArrayList<>());

            if (vuelos == null || vuelos.isEmpty()) {
                throw new PersistenciaException("Error en filtrarVuelos: Error al filtrar vuelos con origen,destino y salida");
            }

            return vuelos;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en filtrarVuelos: " + e.getMessage());
        }
    }

    @Override
    public boolean actualizarAsientosPorVuelo(Vuelo vuelo) throws PersistenciaException {

        try {
            String numeroVuelo = vuelo.getNumVuelo();

            // Filtro por otro campo (numeroVuelo)
            UpdateResult result = collection.updateOne(
                    Filters.eq("numVuelo", numeroVuelo), // filtro por número de vuelo
                    Updates.set("listaAsientos", vuelo.getListaAsientos()) // reemplaza toda la lista
            );

            return result.getModifiedCount() > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error en ActualizarAsientosPorVuelo:" + e.getMessage());
        }
    }

    @Override
    public boolean actualizarPorNumeroVuelo(Vuelo vuelo) throws PersistenciaException {
        try {
            String numVuelo = vuelo.getNumVuelo();
            UpdateResult result = collection.updateOne(Filters.eq("numVuelo", numVuelo), vuelo.toUpdateOperations());
            return result.getModifiedCount() > 0;
        } catch (MongoException ex) {

            throw new PersistenciaException("Error al actualizar por numero de vuelo: " + ex.getMessage());
        }
    }

    public boolean eliminarPorNumeroVuelo(Vuelo vuelo) throws PersistenciaException {
        try {
            String numVuelo = vuelo.getNumVuelo();
            DeleteResult result = collection.deleteOne(Filters.eq("numVuelo", numVuelo));
            return result.getDeletedCount() > 0;
        } catch (MongoException ex) {

            throw new PersistenciaException("Error al actualizar por numero de vuelo: " + ex.getMessage());
        }
    }

}
