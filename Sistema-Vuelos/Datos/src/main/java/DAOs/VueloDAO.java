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
public class VueloDAO extends CRUD<Vuelo> implements IVueloDAO {

    public VueloDAO() {
        super(MongoClientProvider.INTANCE.database(),"Vuelos", Vuelo.class);
    }

    @Override
    public List<Asiento> getAsientosDisponibles(ObjectId _id) throws PersistenciaException {
        try {
            Vuelo vuelo = col.find(new Document("_id", _id)).first();

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
            Vuelo vuelo = col.find(new Document("_id", _id)).first();

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
            Vuelo vuelo = col.find(new Document("idVuelo", numVuelo)).first(); // <-- corregido

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
            Vuelo vuelo = col.find(new Document("idVuelo", numVuelo)).first(); // <-- corregido

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
            List<Vuelo> vuelos = col.find(Filters.and(
                    Filters.eq("origen", origen),
                    Filters.eq("destino", destino),
                    Filters.eq("fechaSalida", salida)
            )).into(new ArrayList<>());

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
        return col.find(new Document("_id", _id)).first();
    }

    @Override
    public Vuelo getVuelo(String numVuelo) throws PersistenciaException {
        return col.find(new Document("idVuelo", numVuelo)).first();
    }

    @Override
    public List<Vuelo> filtrarVuelos(String origen) throws PersistenciaException {
        try {
            List<Vuelo> vuelos = col.find(Filters.and(
                    Filters.eq("origen", origen)
            )).into(new ArrayList<>());

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
            List<Vuelo> vuelos = col.find(Filters.and(
                    Filters.eq("origen", origen),
                    Filters.eq("destino", destino)
            )).into(new ArrayList<>());

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
            List<Vuelo> vuelos = col.find(Filters.and(
                    Filters.eq("origen", origen),
                    Filters.eq("destino", destino),
                    Filters.eq("precio", precio)
            )).into(new ArrayList<>());

            if (vuelos == null || vuelos.isEmpty()) {
                throw new PersistenciaException("Error en filtrarVuelos: Error al filtrar vuelos con origen,destino y salida");
            }

            return vuelos;

        } catch (MongoException e) {
            throw new PersistenciaException("Error en filtrarVuelos: " + e.getMessage());
        }

    }

}
