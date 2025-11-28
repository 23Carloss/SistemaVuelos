/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import CRUD.ICRUD;
import DTOs.ReservacionDTO;
import NegocioException.NegocioException;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IReservacionBO extends ICRUD{
    public ReservacionDTO crearObjeto(ReservacionDTO reservacion) throws NegocioException;
    public List<ReservacionDTO> obtenerTodos() throws NegocioException;
   
}
