/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import CRUD.ICRUD;
import Exception.PersistenciaException;
import POJOs.Asiento;
import POJOs.Vuelo;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface IVueloDAO extends ICRUD {
    
    /**
     * Regresa una lista de los asientos DISPONIBLES sobre el vuelo con el numero de vuelo
     * @param numVuelo numero de vuelo del avion NO ID
     * @return regresa una lista tipo asiento 
     * @throws PersistenciaException exception si falla y que paso
     */
    List<Asiento> getAsientosDisponibles(String numVuelo) throws PersistenciaException;
    
    /**
     * Regresa una lista de los asientos DISPONIBLES sobre el vuelo con el id
     * @param _id id del vuelo
     * @return regresa una lista tipo asiento 
     * @throws PersistenciaException exception si falla y que paso
     */
    List<Asiento> getAsientosDisponibles(ObjectId _id) throws PersistenciaException;
    
    
    /**
     * Regresa una lista de los asientos OCUPADOS sobre el vuelo con el numero de vuelo
     * @param numVuelo id del vuelo
     * @return regresa una lista tipo asiento 
     * @throws PersistenciaException exception si falla y que paso
     */
    List<Asiento> getAsientosOcupados(String numVuelo) throws PersistenciaException;
    
    /**
     * Regresa una lista de los asientos OCUPADOS sobre el vuelo con el id
     * @param _id id del vuelo
     * @return regresa una lista tipo asiento 
     * @throws PersistenciaException exception si falla y que paso
     */
    List<Asiento> getAsientosOcupados(ObjectId _id) throws PersistenciaException;

    /**
     * Busca los vuelos que tengan el origen, destino y salida señalada
     * @param origen el origen tiene que ser señalado con la primera en mayuscula
     * @param destino el destino tiene que ser señalado con la primera en mayuscula
     * @param salida fecha la cual salió el vuelo
     * @return lista de los vuelos con los filtros puestos
     * @throws PersistenciaException exception si falla y que paso
     */
    List<Vuelo> getBuscarVuelos(String origen, String destino, Date salida) throws PersistenciaException;
    
    
    
    /**
     * regresa el vuelo con ObjectID
     * @param _id id del vuelo
     * @return regresa una entidad 
     * @throws PersistenciaException exception si falla y que paso
     */
    Vuelo getVuelo(ObjectId _id) throws PersistenciaException;
    
    
    /**
     * regresa el vuelo con el numero de vuelo
     * @param numVuelo numero de vuelo del vuelo
     * @return regresa una entidad
     * @throws PersistenciaException exception si falla y que paso
     */
    Vuelo getVuelo(String numVuelo) throws PersistenciaException;
    
    /**
     * regresa una lista de vuelos con el filtro de donde es el origen del vuelo
     * @param origen el origen tiene que ser usado con la primera en mayuscula
     * @return regresa una lista de vuelo
     * @throws PersistenciaException exception si falla y que paso
     */ 
    List<Vuelo> filtrarVuelos(String origen) throws PersistenciaException;

    /**
     * regresa una lista de vuelos con el filtro de donde es el origen del vuelo
     * @param origen el origen tiene que ser usado con la primera en mayuscula
     * @param destino el origen tiene que ser usado con la primera en mayuscula
     * @return regresa una lista de vuelo
     * @throws PersistenciaException exception si falla y que paso
     */
    List<Vuelo> filtrarVuelos(String origen, String destino) throws PersistenciaException;

    /**
     * regresa una lista de vuelos con el filtro de donde es el origen del vuelo
     * @param origen el origen tiene que ser usado con la primera en mayuscula
     * @param destino el origen tiene que ser usado con la primera en mayuscula
     * @param precio el precio de cuanto vale el viaje exactamente 
     * @return regresa una lista de vuelo
     * @throws PersistenciaException exception si falla y que paso
     */
    List<Vuelo> filtrarVuelos(String origen, String destino, float precio) throws PersistenciaException;
        
    List<Vuelo> filtrarVuelos(String origen, String destino, float precio, Date fecha) throws PersistenciaException;

    List<Vuelo> filtrarVuelos(String origen, String destino, float precio, Date fecha, LocalTime hora) throws PersistenciaException;
    
    
    boolean actualizarAsientosPorVuelo(Vuelo vuelo) throws  PersistenciaException;
    
    public boolean actualizarPorNumeroVuelo(Vuelo vuelo) throws PersistenciaException;
    
    public boolean eliminarPorNumeroVuelo(Vuelo vuelo) throws PersistenciaException;
    
}
