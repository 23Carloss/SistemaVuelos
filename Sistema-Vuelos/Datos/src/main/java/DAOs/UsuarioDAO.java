/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAOs;

import CRUD.CRUD;
import Config.MongoClientProvider;
import Exception.PersistenciaException;
import InterfacesDAO.IUsuarioDAO;
import POJOs.Usuario;
import com.mongodb.MongoException;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class UsuarioDAO extends CRUD<Usuario> implements IUsuarioDAO{
    
    public UsuarioDAO() {
        super(MongoClientProvider.INTANCE.database(),"Usuarios", Usuario.class);
        
    }
    
    @Override
    public Usuario autenticar(String correo, String contrasenia) throws PersistenciaException{
        try {
            
            Usuario user = (Usuario) col.find(Filters.and(
                    Filters.eq("correo", correo),
                    Filters.eq("contrasenia", contrasenia)))
                    .first();            
            if(user == null)throw new PersistenciaException("Credenciales incorrectas");
            return user;
            
        } catch (MongoException e) {
            throw new PersistenciaException("Error en autenticación " + e.getMessage());
        }
    }
    
    @Override
    public List<Usuario> buscarPorNombre(String nombre)throws PersistenciaException{
        try{
            return col.find(Filters.eq("nombre", nombre)).into(new ArrayList<>());
        }catch(MongoException e){
            throw new PersistenciaException("Error en autenticación " + e.getMessage());
        }
    }

    
    
//    private EntityManager

    
    
    

}
