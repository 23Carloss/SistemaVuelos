/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAOs;

import CRUD.CRUD;
import Config.MongoClientProvider;
import Exception.PersistenciaException;
import POJOs.Usuario;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class UsuarioDAO extends CRUD<Usuario> {

    private MongoCollection<Usuario> collection;
    
    public UsuarioDAO() {
        super("Usuarios", Usuario.class);
        collection = MongoClientProvider.INTANCE.getCollection("Usuarios", Usuario.class);
    }
    
    public Usuario autenticar(String correo, String contrasenia) throws PersistenciaException{
        try {
            
            Usuario user = (Usuario) collection.find(Filters.and(
                    Filters.eq("correo", correo),
                    Filters.eq("contrasenia", contrasenia)))
                    .first();            
            if(user == null)throw new PersistenciaException("Credenciales incorrectas");
            return user;
            
        } catch (Exception e) {
            throw new PersistenciaException("Error en autenticación");
        }
    }

    
    
//    private EntityManager
    
    

}
