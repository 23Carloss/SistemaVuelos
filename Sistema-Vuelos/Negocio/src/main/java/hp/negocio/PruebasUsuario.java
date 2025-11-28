/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hp.negocio;

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
            MongoClientProvider.INTANCE.init();
            
            IUsuarioBO bo = new UsuarioBO();
            
            UsuarioDTO us = new UsuarioDTO();
                us.setNombre("carlos");
                us.setApellidoP("Manja");
                us.setApellidoM("Gonzalez");
                us.setCorreo("luis@gmail.com");
                us.setContrasenia("carlos23");
                us.setTipoUsuario(TipoUsuario.administrador);
                us.setReservaciones(new ArrayList<>());
            
            UsuarioDTO us1 = new UsuarioDTO();
                us1.setNombre("Luis");
                us1.setApellidoP("perez");
                us1.setApellidoM("g");
                us1.setCorreo("luis@Hotmail.com");
                us1.setContrasenia("carlos23");
                us1.setTipoUsuario(TipoUsuario.usuario);
                us1.setReservaciones(new ArrayList<>());
            //Insertamos usuario
            System.out.println("Pueba de agregar"); 
            us = bo.crearObjeto(us);
            us1 = bo.crearObjeto(us1);
                System.out.println(us);
                System.out.println(us1);
  
            System.out.println("Usuario0: " + us);
            System.out.println("Usuario1: " + us1.get_id().toString());
           System.out.println("Encontrado:  " + bo.buscarPorId(us.get_id()) );
           
            System.out.println("Pueba de obtener todo");
            //cosultamos todos los usuarios mediante la BO
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
