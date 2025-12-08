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
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.bson.conversions.Bson;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class UsuarioDAO extends CRUD implements IUsuarioDAO{
    
    public UsuarioDAO() {
        super(MongoClientProvider.INSTANCE.database(),"Usuarios", Usuario.class);
        
    }
    
    @Override
    public Usuario autenticar(String correo, String contrasenia) throws PersistenciaException{
        try {
            
            
            Usuario user1 = buscarPorCorreo(correo);
            
            if(user1 == null)throw new PersistenciaException("Credenciales incorrectas");
            if(user1.getContrasenia().equals(contrasenia)){
                return user1;
            }else{
                return null;
            }

        } catch (MongoException e) {
            throw new PersistenciaException("Error en autenticación " + e.getMessage());
            
        }
    }
    
    @Override
    public List<Usuario> buscarPorNombre(String nombre)throws PersistenciaException{
        try{         
//            @SuppressWarnings("unchecked")  
            Collection col = collection.find(Filters.regex("nombre", nombre, "i")).into(new ArrayList<>());
            List<Usuario> listaUsuarios = (List<Usuario>) col;
            return listaUsuarios;
        }catch(MongoException e){
            throw new PersistenciaException("Error en autenticación " + e.getMessage());
        }
    }
    
    //devuelve false si el parametro correoE no se encuentra registrado
    //true si esta registrado
    @Override
    public boolean verificarCorreo(String correE)throws PersistenciaException{
        try{
            return collection.find(Filters.eq("correo", correE)).first() != null;
        }catch(MongoException ex){
              throw new PersistenciaException("Error en verificar correo " + ex.getMessage());    
        }
        
    }
    
    @Override
    public Usuario buscarPorCorreo(String correE)throws PersistenciaException{
        try{
            return (Usuario) collection.find(Filters.eq("correo", correE)).first();
        }catch(MongoException ex){
              throw new PersistenciaException("Error al buscar usuario por correo " + ex.getMessage());    
        }
    }
    
    @Override
    public boolean eliminarPorCorreo(String correoE)throws PersistenciaException{
        
        try {
            
            var result = collection.deleteOne(Filters.eq("correo", correoE));

            return result.getDeletedCount() > 0;

        } catch (MongoException e) {
            throw new PersistenciaException("Error al eliminar por correo " + e.getMessage());
        }
    }
    
    @Override
    public boolean actualizarPorCorreo(Usuario entity)throws PersistenciaException{
        try{
            Bson filter = Filters.eq("correo", entity.getCorreo());  
            UpdateResult resultado = collection.updateOne(filter, entity.toUpdateOperations());
            if (resultado.getModifiedCount() == 0) {
                 System.out.println("no se encontro o modifico ningun documento.");
            }
            return resultado.getModifiedCount() > 0;
        }catch(MongoException ex){
              throw new PersistenciaException("Error al actualizar por correo " + ex.getMessage());    
        }

    }

    
    
//    private EntityManager

    
    
    

}
