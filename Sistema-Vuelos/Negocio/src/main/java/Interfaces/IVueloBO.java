/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTOs.AsientoDTO;
import DTOs.VueloDTO;
import NegocioException.NegocioException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface IVueloBO {

    List<AsientoDTO> getAsientosDisponibles(String numVuelo) throws NegocioException;

    List<AsientoDTO> getAsientosOcupados(String numVuelo) throws NegocioException;

    List<VueloDTO> getBuscarVuelos(String origen, String destino, Date salida) throws NegocioException;

    VueloDTO getVuelo(ObjectId _id) throws NegocioException;

    VueloDTO getVuelo(String numVuelo) throws NegocioException;

    List<VueloDTO> filtrarVuelos(String origen) throws NegocioException;

    List<VueloDTO> filtrarVuelos(String origen, String destino) throws NegocioException;

    List<VueloDTO> filtrarVuelos(String origen, String destino, float precio) throws NegocioException;

}
