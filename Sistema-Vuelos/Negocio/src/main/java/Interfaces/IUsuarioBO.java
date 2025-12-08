/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Interfaces;

import DTOs.UsuarioDTO;
import NegocioException.NegocioException;
import java.util.List;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public interface IUsuarioBO{
    
    public UsuarioDTO crearUsuario(UsuarioDTO usuario)throws NegocioException;
    public UsuarioDTO buscarPorCorreo(String  correo) throws NegocioException;
    public boolean eliminarPorCorreo(String correo) throws NegocioException;
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuario) throws NegocioException;
    public List<UsuarioDTO> buscarPorNombre(String name)throws NegocioException;
    public List<UsuarioDTO> obtenerTodos()throws NegocioException;
    public UsuarioDTO iniciarSesion(String correo, String password)throws NegocioException;
    //devuelve false si el parametro correoE ya se encuentra registrado
    //true si no esta registrado
    public boolean verificarCorreo(String correE)throws NegocioException;

}
