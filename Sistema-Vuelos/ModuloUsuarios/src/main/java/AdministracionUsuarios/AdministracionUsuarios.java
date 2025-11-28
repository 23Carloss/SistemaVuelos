/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package AdministracionUsuarios;

import BOs.UsuarioBO;
import DTOs.UsuarioDTO;
import Interfaces.IUsuarioBO;
import NegocioException.NegocioException;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class AdministracionUsuarios {
    private IUsuarioBO usuarioBO;
    private UsuarioDTO usuarioTemporal;

    public AdministracionUsuarios() {
        usuarioBO = new UsuarioBO();
        
    }
    public List<UsuarioDTO> cargarTodos(){
        try {
            return usuarioBO.obtenerTodos();
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"Error al cargar usuarios","Error", JOptionPane.ERROR_MESSAGE);
            return null;                 
            }
    }
    public UsuarioDTO registrarUsuario(UsuarioDTO u){
        try {
            var usuario =usuarioBO.crearObjeto(u);
            JOptionPane.showMessageDialog(null, "Registrado con exito", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            return usuario;  
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"Error al registrar usuario","Error:  " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            return null;  
        }
    }
    public void eliminarUsuario(ObjectId _id){
        try {
            usuarioBO.eliminarPorId(_id);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(null,"Error al eliminar usuario","Error", JOptionPane.ERROR);
        }
    }
    
    public UsuarioDTO buscarPorId(ObjectId _id){
        try {
            var usuario =usuarioBO.buscarPorId(_id);
            return usuario;  
            
        } catch (NegocioException ex) {
            JOptionPane.showConfirmDialog(null, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
            
        }
    }
    
    public boolean actualizarUsuario(UsuarioDTO usuario){
        try {
            var usuarioA =usuarioBO.actualizarObjeto(usuario);
            return usuarioA != null; 
            
        } catch (NegocioException ex) {
            JOptionPane.showConfirmDialog(null, "Error al actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean signIn(String correo, String contrasenia){
        try {
            setUsuarioDTO(usuarioBO.signIn(correo, correo));
            return true;
        } catch (NegocioException ex) {
            JOptionPane.showConfirmDialog(null, "Credenciales Incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public List<UsuarioDTO> buscarUsuarioPorNombre(String nombre){
        try {
            return usuarioBO.buscarPorNombre(nombre);
        } catch (NegocioException ex) {
            JOptionPane.showConfirmDialog(null, "Error al buscar por nombre", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public UsuarioDTO getUsuarioDTO(){
        return this.usuarioTemporal;
    }
    public void setUsuarioDTO(UsuarioDTO u){
        this.usuarioTemporal = u;
    }
}
