/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hp.datos;

import Config.MongoClientProvider;
import DAOs.UsuarioDAO;
import POJOs.TipoUsuario;
import POJOs.Usuario;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Datos {
    public static void main(String[] args) {
//       
            System.out.println("Hello World!");
            MongoClientProvider.INSTANCE.init();
            UsuarioDAO dao = new UsuarioDAO();
//            
//            Usuario us = new Usuario();
//            us.setNombre("Carlos");
//            us.setApellidoM("Gonzalez");
//            us.setCorreo("tuculito69");
////            System.out.println(dao.create(us));
//            
//            
            Usuario us2 = new Usuario();
            us2.setNombre("Luis");
                us2.setApellidoP("perez");
                us2.setApellidoM("g");
                us2.setCorreo("luis@gmail.com");
                us2.setContrasenia("carlos23");
                us2.setTipoUsuario("usuario");
//               
            System.out.println(dao.create(us2));
           
//            
////            System.out.println("Actualizo: " + dao.update(us));
//            
            dao.findEntities().forEach(System.out::println);
            
            
//            dao.findEntities().forEach(Usuario -> System.out.println(Usuario));
        
    }
}

