/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BOs;

import CRUD.ObjetoMongo;
import DAOs.UsuarioDAO;
import DTOs.UsuarioDTO;
import Exception.PersistenciaException;
import Mappers.UsuarioMapper;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import NegocioException.NegocioException;
import POJOs.Usuario;
import Interfaces.IUsuarioBO;
import com.mongodb.MongoException;
import java.util.Optional;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class UsuarioBO implements IUsuarioBO{
    private UsuarioDAO dao;
    private UsuarioMapper mapper;

    public UsuarioBO() {
        dao = new UsuarioDAO();
        mapper = new UsuarioMapper();
    }

    @Override
    public UsuarioDTO createObject(UsuarioDTO object) throws NegocioException{
        try {
            System.out.println("Usuario q entra en BO : " + object.toString());
            if (object == null) {
                throw new IllegalArgumentException("Usuario no puede ser nulo");
            }
            if (object.getNombre() == null || object.getNombre().isBlank()) {
                throw new IllegalArgumentException("Nombre es obligatorio");
            }
            if (object.getApellidoP() == null || object.getApellidoP().isBlank()) {
                throw new IllegalArgumentException("Apellido paterno es obligatorio");
            }
            if (object.getCorreo() == null || !object.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                throw new IllegalArgumentException("Credenciales incorrectas");
            }
            if (object.getContrasenia() == null || object.getContrasenia().length() < 6) {
                throw new IllegalArgumentException("Contraseña debe tener al menos 6 caracteres");
            }

            if (object.getId() == null) {
                object.setId(new ObjectId());
            }
            Usuario usuario = mapper.convertirAEntity(object);
            System.out.println(usuario.toString());
            dao.create(usuario);
            
//            collection.create(mapper.convertirAEntity(object));
            
            return object;
        } catch (MongoException e) {
            throw new NegocioException("Error al agregar usuario" + e.getMessage());
        }
    }

    @Override
    public UsuarioDTO findById(ObjectId _id) {
        return (UsuarioDTO) dao.read(_id).get();
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return dao.findEntities();
    }

    @Override
    public UsuarioDTO update(ObjectId _id, Bson update) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(ObjectId _id) {
        dao.delete(_id);
    }

    @Override
    public UsuarioDTO signIn(String correo, String password) throws NegocioException {
        try {
            var usuario = dao.autenticar(correo, password);
            if(usuario == null)
                throw new NegocioException("Credenciales incorrectas");
            return (UsuarioDTO) mapper.convertirADTO(usuario);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al iniciar sesion");
        }
        
    }

    @Override
    public List<UsuarioDTO> findByName(String name) throws NegocioException {
        return mapper.ConvertirListaADto(dao.findByName(name));
    }

    @Override
    public Object create(ObjetoMongo entity) throws MongoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional read(ObjectId id) throws MongoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(ObjetoMongo entity) throws MongoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(ObjectId id) throws MongoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List findEntities() throws MongoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List findEntities(int maxResults, int firstResult) throws MongoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     

 

}
