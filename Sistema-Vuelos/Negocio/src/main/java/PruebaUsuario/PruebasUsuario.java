/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PruebaUsuario;

import BOs.UsuarioBO;
import Config.MongoClientProvider;
import DTOs.UsuarioDTO;
import Interfaces.IUsuarioBO;
import Mappers.UsuarioMapper;
import NegocioException.NegocioException;
import POJOs.TipoUsuario;
import java.util.UUID;

/**
 *
 * @author HP
 */
public class PruebasUsuario {

    private static UsuarioMapper mapper;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            MongoClientProvider.INSTANCE.init();
            
            IUsuarioBO bo = new UsuarioBO();
            
            UsuarioDTO us = new UsuarioDTO();
            us.setNombre("carlos");
                us.setApellidoP("Manja");
                us.setApellidoM("Gonzalez");
                us.setCorreo("carlos23@o69.com");
                us.setContrasenia("carlos23");
                us.setTipoUsuario(TipoUsuario.administrador);
                
            
            UsuarioDTO us1 = new UsuarioDTO();            
                us1.setNombre("Crlos");
                us1.setApellidoP("perez");
                us1.setApellidoM("g");
                us1.setCorreo("luis@gmail.com");
                us1.setContrasenia("carlos23");
                us1.setTipoUsuario(TipoUsuario.usuario);
                
            //Insertamos usuario
            System.out.println("Pueba de agregar"); 
            System.out.println("correo registrdo us : " + bo.verificarCorreo(us.getCorreo()));
            us = bo.crearUsuario(us);
                System.out.println(us);
            System.out.println("correo registrdo us1: " + bo.verificarCorreo(us1.getCorreo()));
            us1 = bo.crearUsuario(us1); 
                System.out.println(us1);
  
            System.out.println("Usuario0: " + us);
           System.out.println("buscarPorCorreo:  " + bo.buscarPorCorreo(us.getCorreo()) );
//           
//            System.out.println("Pueba de obtener todo");
//            //cosultamos todos los usuarios mediante la BO
//            bo.obtenerTodos().forEach(System.out::println);
//            
            System.out.println("Pueba de actualizar, us a act: "+ us1);
            
                us1.setNombre("Carlos");
//                us.setCorreo("luis23@gmail.com");
//                
            System.out.println("User Act" + bo.actualizarUsuario(us1));
//            
//            System.out.println("Pueba de buscar por nombre");
//            
//            bo.buscarPorNombre("Carlos").forEach(System.out::println);
//            
////            System.out.println("Prueba de eliminar por correo: " + us1.getCorreo());
////            System.out.println(bo.eliminarPorCorreo(us1.getCorreo()));
            bo.obtenerTodos().forEach(System.out::println);
//            
        } catch (NegocioException ex) {
            System.out.println("mensaje de error : " + ex.getMessage());
        }
    }
    
}
