/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Interfaces;

import DTOs.UsuarioDTO;
import NegocioException.NegocioException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public interface IUsuarioBO{
    
    public UsuarioDTO crearObjeto(UsuarioDTO usuario)throws NegocioException;
    public UsuarioDTO buscarPorId(ObjectId id) throws NegocioException;
    public boolean eliminarPorId(ObjectId id) throws NegocioException;
    public UsuarioDTO actualizarObjeto(UsuarioDTO usuario) throws NegocioException;
    public List<UsuarioDTO> buscarPorNombre(String name)throws NegocioException;
    public List<UsuarioDTO> obtenerTodos()throws NegocioException;
    public UsuarioDTO signIn(String correo, String password)throws NegocioException;
    public boolean verificarCorreo(String correE)throws NegocioException;
    public UsuarioDTO buscarPorCorreo(String correE)throws NegocioException;

}
