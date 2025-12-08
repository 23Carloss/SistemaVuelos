/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTOs.AsientoDTO;
import DTOs.ReservacionDTO;
import DTOs.UsuarioDTO;
import NegocioException.NegocioException;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IReservacionBO{
    public ReservacionDTO crearReservacion(ReservacionDTO reservacion) throws NegocioException;
    public boolean eliminarReservacion(ReservacionDTO reservacion) throws NegocioException;
    public ReservacionDTO actualizarReservacion(ReservacionDTO reseracion) throws NegocioException;
    public List<ReservacionDTO> obtenerTodos() throws NegocioException;
    public Boolean verificarAsientosDisponibles(List<AsientoDTO> listaAscientos) throws NegocioException;
    public List<ReservacionDTO> obtenerReservacionesPorUsuario(UsuarioDTO usuario) throws NegocioException;
    public ReservacionDTO buscarPorNumReservacion(ReservacionDTO reservacion);

}
