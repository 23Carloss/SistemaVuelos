/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Apliacion;

import AdministracionUsuarios.AdministracionUsuarios;
import DTOs.UsuarioDTO;
import Vistas.Administrador.AdminUsuarios;
import Vistas.Administrador.PanelAdministrador;
import Vistas.Administrador.PanelLogIn;
import Vistas.AgregarUsuario;
import Vistas.MenuUsuario;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class Control {
    private JFrame frmePrincipl;
    
    //Modulos
    private AdministracionUsuarios administracionUsuarios;
    
    //paneles
    private PanelAdministrador panelAdmin;
    private AdminUsuarios adminUsuarios;
    private PanelLogIn logIn;
    private AgregarUsuario agregarUsuario;
    
    private MenuUsuario menuUsuarios;
    

    public Control() {
        this.administracionUsuarios = new AdministracionUsuarios();
        this.frmePrincipl = new JFrame();
        
    }
    public void iniciar()
    {
       mostrarLogIn();
    }
    public void mostrarPanelAdministrador(){
        panelAdmin = new PanelAdministrador(this);
        cambiarPantalla(panelAdmin);
    }
    public void mostrarAdministracionUsuarios(){
        adminUsuarios = new AdminUsuarios(this);
        cambiarPantalla(adminUsuarios);
    }
    public void mostrarLogIn(){
        logIn = new PanelLogIn(this);
        cambiarPantalla(logIn);
    }
    public void mostrarMenuUsuario(){
        menuUsuarios = new MenuUsuario(this);
        cambiarPantalla(menuUsuarios);
    }
    public void mostrarAgregarUsuario(boolean isAdmin){
        agregarUsuario = new AgregarUsuario(this, isAdmin);
        cambiarPantalla(agregarUsuario);
    }
    
    private void cambiarPantalla(JPanel nuevaPantalla) {
        frmePrincipl.getContentPane().removeAll();
        frmePrincipl.getContentPane().add(nuevaPantalla);
        frmePrincipl.pack();
        frmePrincipl.setLocationRelativeTo(null);
        frmePrincipl.setVisible(true);
    }
    
    public boolean signIn(String correo, String contrasenia){
        return administracionUsuarios.signIn(correo, contrasenia);
    }
    
    public UsuarioDTO getUsuarioDTO(){
        return administracionUsuarios.getUsuarioDTO();
        
    }
    public void setUsuarioDTO(UsuarioDTO u){
        administracionUsuarios.setUsuarioDTO(u);
    }
    
    public List<UsuarioDTO> consultarTodosUsuarios(){
        return administracionUsuarios.cargarTodos();
    } 
    public List<UsuarioDTO> buscarUsuariosPorNombre(String nombre){
        return administracionUsuarios.buscarUsuarioPorNombre(nombre);
    } 
    
    public UsuarioDTO agregarUsuario(UsuarioDTO usuario){
        return administracionUsuarios.registrarUsuario(usuario);
    }
    
    public void eliminarUsuario(ObjectId id){
        administracionUsuarios.eliminarUsuario(id);
    }
    
    
//    public Lis<ProductoDTO> cargarProductos(){
//        
//    }
            

}
