/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package AdministracionUsuarios;

import BOs.ReservacionBO;
import BOs.UsuarioBO;
import DTOs.ReservacionDTO;
import DTOs.UsuarioDTO;
import Interfaces.IReservacionBO;
import Interfaces.IUsuarioBO;
import NegocioException.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class AdministracionUsuarios {
    private IUsuarioBO usuarioBO;
    private IReservacionBO reservacionBO;
    private UsuarioDTO usuarioTemporal;

    public AdministracionUsuarios() {
        usuarioBO = new UsuarioBO();
        reservacionBO = new ReservacionBO();
        
    }
    public UsuarioDTO registrarUsuario(UsuarioDTO  us){
        try {
            var usuario =usuarioBO.crearUsuario(us);
            JOptionPane.showMessageDialog(null, "Registrado con exito", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            return usuario;  
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"Error al registrar usuario" + ex.getMessage(),"Error" + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            return null;  
        }
    }
    public List<UsuarioDTO> cargarTodos(){
        try {
            return usuarioBO.obtenerTodos();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"Error al cargar usuarios","Error", JOptionPane.ERROR_MESSAGE);
            return null;                 
            }
    }
    
    public boolean eliminarUsuario(String correo){
        try {
            
            return usuarioBO.eliminarPorCorreo(correo);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"Error al eliminar usuario" + ex.getMessage(),"Error", JOptionPane.ERROR);
            return false;
        }
    }
    
    public UsuarioDTO buscarPorCorreo(String correo){
        try {

            var usuario =usuarioBO.buscarPorCorreo(correo);
            return usuario;  
            
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado:" + ex.getMessage() , "Error", JOptionPane.ERROR_MESSAGE);
            return null;
            
        }
    }
    
    public boolean actualizarUsuario(UsuarioDTO usuario){
        try {
            var usuarioA =usuarioBO.actualizarUsuario(usuario);
            return usuarioA != null; 
            
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean iniciarSesion(String correo, String contrasenia){
        try {        
            setUsuarioDTO(usuarioBO.iniciarSesion(correo, contrasenia));
            return true;
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Credenciales Incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public List<UsuarioDTO> buscarUsuarioPorNombre(String nombre){
        try {
            return usuarioBO.buscarPorNombre(nombre);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar por nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public UsuarioDTO getUsuarioDTO(){
        return this.usuarioTemporal;
    }
    public void setUsuarioDTO(UsuarioDTO u){
        this.usuarioTemporal = u;
    }
    
    public List<ReservacionDTO> cargarReservacionesPorUsuario(UsuarioDTO usuario){
        try {
            return reservacionBO.obtenerReservacionesPorUsuario(usuario);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar reservaciones : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public boolean eliminarReservacion(ReservacionDTO reservacion){
        try {
            boolean resultado  = reservacionBO.eliminarReservacion(reservacion);
            JOptionPane.showMessageDialog(null, "Reservacion cancelada", "Exito", JOptionPane.ERROR_MESSAGE);
            return resultado;
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar reservacion : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
