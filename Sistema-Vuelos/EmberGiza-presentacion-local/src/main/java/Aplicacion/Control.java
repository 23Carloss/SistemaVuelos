 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Aplicacion;

import AdministracionUsuarios.AdministracionUsuarios;
import BOs.VueloBO;
import DTOs.ReservacionDTO;
import DTOs.UsuarioDTO;
import DTOs.VueloDTO;
import Interfaces.IVueloBO;
import NegocioException.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**s
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Control {
    private AdministracionUsuarios administracionUsuarios;
    private IVueloBO vueloBO;
    
    public Control() {
        this.administracionUsuarios = new AdministracionUsuarios();
        vueloBO = new VueloBO();
    }
    
    public UsuarioDTO crearUsuario(UsuarioDTO us){
        return administracionUsuarios.registrarUsuario(us);
    }
    
    public boolean eliminarUsuarioPorCorreo(String correo){
        return administracionUsuarios.eliminarUsuario(correo);
    }
    
    public boolean actualizarUsuarioPorCorreo(UsuarioDTO us){
        return administracionUsuarios.actualizarUsuario(us);
    }
    
    public UsuarioDTO buscarPorCorreo(String correo){
        return administracionUsuarios.buscarPorCorreo(correo);
    }
    public List<UsuarioDTO> buscarPorNombre(String nombre){
        return administracionUsuarios.buscarUsuarioPorNombre(nombre);
    }
    public List<UsuarioDTO> obtenerTodos(){
        return administracionUsuarios.cargarTodos();
      
    }
    
    public UsuarioDTO getUsuario(){
        return administracionUsuarios.getUsuarioDTO();
    }
    
    public void setUsuario(UsuarioDTO us){
        administracionUsuarios.setUsuarioDTO(us);
    }
    
    public boolean iniciarSesion(String correo, String contrasenia){
        return administracionUsuarios.iniciarSesion(correo, contrasenia);
    }
    
    public List<ReservacionDTO> cargarReservacionesPorUsuario(UsuarioDTO usuario){
        return administracionUsuarios.cargarReservacionesPorUsuario(usuario);
    }
    
    public boolean eliminarReservacion(ReservacionDTO reservacion){
        return administracionUsuarios.eliminarReservacion(reservacion);
    }
    public ReservacionDTO crearrReservacion(ReservacionDTO reservacion){
        return administracionUsuarios.registrarReservacion(reservacion);
    }
    
    public List<VueloDTO> cargarVuelos(){
        try {
            return vueloBO.obtenerTodos();
        } catch (NegocioException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void crearVuelo(VueloDTO vuelo){
        try {
            vueloBO.crearVuelo(vuelo);
        } catch (NegocioException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
