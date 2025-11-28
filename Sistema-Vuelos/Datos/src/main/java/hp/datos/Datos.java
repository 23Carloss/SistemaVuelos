/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hp.datos;

import Config.MongoClientProvider;
import DAOs.UsuarioDAO;
import POJOs.Usuario;

/**
 *
 * @author HP
 */
public class Datos {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        MongoClientProvider.INTANCE.init();
        UsuarioDAO dao = new UsuarioDAO();
        
        Usuario us = new Usuario();
        us.setNombre("Carlos");
        System.out.println(dao.create(us));
        System.out.println(us.get_id().toHexString());
        
        dao.findEntities().forEach(Usuario -> System.out.println(Usuario));
    }
}
