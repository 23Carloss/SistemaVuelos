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
import java.util.ArrayList;

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
            us.set_id(null);
                us.setNombre("carlos");
                us.setApellidoP("Manja");
                us.setApellidoM("Gonzalez");
                us.setCorreo("tuculit@o69.com");
                us.setContrasenia("carlos23");
                us.setTipoUsuario(TipoUsuario.administrador);
                
            
            UsuarioDTO us1 = new UsuarioDTO();
            us1.set_id(null);
                us1.setNombre("Crlos");
                us1.setApellidoP("perez");
                us1.setApellidoM("g");
                us1.setCorreo("luis@gmail.com");
                us1.setContrasenia("carlos23");
                us1.setTipoUsuario(TipoUsuario.usuario);
                
            //Insertamos usuario
//            System.out.println("Pueba de agregar"); 
            System.out.println("correo registrdo us : " + bo.verificarCorreo(us.getCorreo()));
            us = bo.crearObjeto(us);
            System.out.println(us);
            System.out.println("correo registrdo us1: " + bo.verificarCorreo(us1.getCorreo()));
            us1 = bo.crearObjeto(us1); 
                
                System.out.println(us1);
//  
//            System.out.println("Usuario0: " + us);
////            System.out.println("Usuario1: " + us1.get_id().toString());
//           System.out.println("Encontrado:  " + bo.buscarPorId(us.get_id()) );
//           
//            System.out.println("Pueba de obtener todo");
//            //cosultamos todos los usuarios mediante la BO
            bo.obtenerTodos().forEach(System.out::println);
//            
//            System.out.println("Pueba de actualizar");^
//            
//                us.setNombre("Luis Carlos");
//                us.setCorreo("luis23@gmail.com");
//                
//            System.out.println(bo.actualizarObjeto(us));
//            
//            System.out.println("Pueba de buscar por nombre");
//            
//            bo.buscarPorNombre("Luis Carlos").forEach(System.out::println);
//            
//            System.out.println("Prueba de eliminar por id: " + us1.get_id());
//            System.out.println( bo.eliminarPorId(us1.get_id()));
//            bo.obtenerTodos().forEach(System.out::println);
////            
        } catch (NegocioException ex) {
            System.out.println("mensaje de error : " + ex.getMessage());
        }
    }
    
}
