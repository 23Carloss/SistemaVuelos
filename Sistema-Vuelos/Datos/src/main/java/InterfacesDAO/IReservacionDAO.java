/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import CRUD.ICRUD;
import Exception.PersistenciaException;
import POJOs.Reservacion;
import POJOs.Usuario;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface IReservacionDAO extends ICRUD {
    public List<Reservacion> obtenerReservacionesPorUsuario(Usuario us) throws PersistenciaException;
    public boolean eliminarPorNumReservacion(String numReservacion)throws PersistenciaException;
    public Reservacion actualizarPorNumReservacion(Reservacion reservacion)throws PersistenciaException;
    public Reservacion buscarPorNumReservacion(String numReservacion);
}
