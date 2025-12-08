/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BOs;

import DAOs.ReservacionDAO;
import DTOs.AsientoDTO;
import DTOs.ReservacionDTO;
import DTOs.UsuarioDTO;
import Exception.PersistenciaException;
import Interfaces.IReservacionBO;
import InterfacesDAO.IReservacionDAO;
import Mappers.ReservacionMapper;
import Mappers.UsuarioMapper;
import NegocioException.NegocioException;
import POJOs.Reservacion;
import POJOs.Usuario;
import com.mongodb.MongoException;
import java.util.List;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class ReservacionBO implements IReservacionBO{
    private IReservacionDAO reservacionDAO;
    private ReservacionMapper mapper;
    private UsuarioMapper usuarioMapper;
    
    public ReservacionBO() {
        reservacionDAO = new ReservacionDAO();
        mapper = new ReservacionMapper();
        usuarioMapper = new  UsuarioMapper();
    }
    
    

    @Override
    public ReservacionDTO crearReservacion(ReservacionDTO reservacion) throws NegocioException {
        try{
            Reservacion reservacionCreada = (Reservacion) reservacionDAO.create(reservacion);
            //verificar los asientos antes de creear la reservacion
            return mapper.convertirADto(reservacionCreada);
            
        }catch(MongoException ex){
            throw new NegocioException("Error al crear la reservacion " + ex.getMessage());
        }
        
        
    }

    @Override
    public List<ReservacionDTO> obtenerTodos() throws NegocioException {
        try{
            List<Reservacion> listaEntity = reservacionDAO.findEntities();
            return mapper.convertirListaADTO(listaEntity);
        }catch(MongoException ex){
            throw new NegocioException("Error al cargar las reservaciones " + ex.getMessage());
        }
    }

    @Override
    public Boolean verificarAsientosDisponibles(List<AsientoDTO> listaAscientos) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ReservacionDTO> obtenerReservacionesPorUsuario(UsuarioDTO usuario) throws NegocioException {
        
        try {
            Usuario usuarioEntity = usuarioMapper.convertirAEntity(usuario);
            List<Reservacion> listaEntity;
            listaEntity = reservacionDAO.obtenerReservacionesPorUsuario(usuarioEntity);
            List<ReservacionDTO> listaReservaciones = mapper.convertirListaADTO(listaEntity);
            return listaReservaciones;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al cargar las reservaciones del usuario " + ex.getMessage());
        }
        
    }

    @Override
    public boolean eliminarReservacion(ReservacionDTO reservacion) throws NegocioException {
        try {
            if(reservacion == null || reservacion.getNumReservacion() == null){
                throw new NegocioException("reservacion o numReservacion null");
                
            }
            return reservacionDAO.eliminarPorNumReservacion(reservacion.getNumReservacion());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar la reservacion" + ex.getMessage());
        }
    }

    @Override
    public ReservacionDTO actualizarReservacion(ReservacionDTO reservacion) throws NegocioException {
        try {
            Reservacion reservacionEntity = mapper.convertirAEntity(reservacion);
            Reservacion reservacionActualizada = reservacionDAO.actualizarPorNumReservacion(reservacionEntity);
            ReservacionDTO reservacionDTO = mapper.convertirADto(reservacionActualizada);
            return reservacionDTO;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar la reservacion" + ex.getMessage());
        }
    }
    
    @Override
    public ReservacionDTO buscarPorNumReservacion(ReservacionDTO reservacion){
        Reservacion reservacionMongo = reservacionDAO.buscarPorNumReservacion(reservacion.getNumReservacion());
        ReservacionDTO reservacionDTO = mapper.convertirADto(reservacionMongo);
        return reservacionDTO;
    }
    

}
