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
        super(MongoClientProvider.INSTANCE.database(),"Usuarios", Usuario.class);
        
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
    
    //devuelve false si el parametro correoE ya se encuentra registrado
    //true si no esta registrado
    @Override
    public boolean verificarCorreo(String correE)throws PersistenciaException{
        try{
            return col.find(Filters.eq("correo", correE)).first() != null;
        }catch(MongoException ex){
              throw new PersistenciaException("Error en verificar correo " + ex.getMessage());    
        }
        
    }
    
    @Override
    public Usuario buscarPorCorreo(String correE)throws PersistenciaException{
        try{
            return col.find(Filters.eq("correo", correE)).first();
        }catch(MongoException ex){
              throw new PersistenciaException("Error al buscar usuario por correo " + ex.getMessage());    
        }
    }
    
    @Override
    public boolean eliminarPorCorreo(String correoE)throws PersistenciaException{
        
        try {
            
            var result = col.deleteOne(Filters.eq("correo", correoE));

            return result.getDeletedCount() > 0;

        } catch (MongoException e) {
            throw new PersistenciaException("Error al eliminar por correo " + e.getMessage());
        }
    }
    
    @Override
    public boolean actualizarPorCorreo(Usuario entity)throws PersistenciaException{
        try{
            try {
          
                var filter = Filters.eq("correo", entity.getCorreo());
                var updates = entity.toUpdateOperations(); // método que tú defines en ObjetoMongo

                var result = col.updateOne(filter,updates);
                return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw e;
        }
        }catch(MongoException ex){
              throw new PersistenciaException("Error al actualizar por correo " + ex.getMessage());    
        }
        
    }

    
    
//    private EntityManager

    
    
    

}
