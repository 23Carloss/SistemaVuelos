/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAOs.UsuarioDAO;
import DTOs.UsuarioDTO;
import Exception.PersistenciaException;
import Mappers.UsuarioMapper;
import java.util.List;
import org.bson.types.ObjectId;
import NegocioException.NegocioException;
import POJOs.Usuario;
import Interfaces.IUsuarioBO;
import InterfacesDAO.IUsuarioDAO;
import com.mongodb.MongoException;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */

public class UsuarioBO implements IUsuarioBO{
    private IUsuarioDAO dao;
    private UsuarioMapper mapper;

    public UsuarioBO() {
        dao = new UsuarioDAO();
        mapper = new UsuarioMapper();
    }

    @Override

    public UsuarioDTO crearObjeto(UsuarioDTO object) throws NegocioException{
        try {
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

            Usuario usuario = mapper.convertirAEntity(object);
            System.out.println("Usuario convertido a Entity BO: " + usuario);
            Usuario us = (Usuario) dao.create(usuario);
            return mapper.convertirADTO(us);
        } catch (MongoException e) {
            throw new NegocioException("Error al agregar usuario" + e.getMessage());
        }
    }


    @Override
    public UsuarioDTO buscarPorId(ObjectId _id) throws NegocioException{
        try{
            return (UsuarioDTO) dao.read(_id);
        }catch(MongoException ex){
            throw new NegocioException("Error al buscar usuario" + ex.getMessage());
        }
            
     
    }


    @Override
    public List<UsuarioDTO> obtenerTodos() throws NegocioException{
        try{
            List<Usuario> usuarios = dao.findEntities();
            
            usuarios.forEach(u -> System.out.println("DEBUG Negocio: ID de entidad recibida del DAO: " + u.get_id()));

            return mapper.ConvertirListaADto(usuarios);
        }catch(MongoException ex){
            throw new NegocioException("Error al obtener todos los usuario" + ex.getMessage());
        }
    }

    @Override
    public boolean eliminarPorId(ObjectId _id) throws NegocioException{
        try{
            return dao.delete(_id);
        }catch(MongoException ex){
            throw new NegocioException("Error al buscar usuario" + ex.getMessage());
        }
        
    }

    @Override
    public UsuarioDTO signIn(String correo, String password) throws NegocioException {
        try {
            var usuario = dao.autenticar(correo, password);
            if (usuario == null) {
                throw new NegocioException("Credenciales incorrectas");
            }
            return (UsuarioDTO) mapper.convertirADTO(usuario);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al iniciar sesion");
        }

    }

    @Override
    public List<UsuarioDTO> buscarPorNombre(String name) throws NegocioException {
        try {
            return mapper.ConvertirListaADto(dao.buscarPorNombre(name));
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar por nombre: " + ex.getMessage());
        }
    }

  

    @Override
    public UsuarioDTO actualizarObjeto(UsuarioDTO usuario) throws NegocioException {
        try{
            System.out.println("UsDTO - Actualizar:  "  + usuario);
            Usuario us = mapper.convertirAEntity(usuario);
            System.out.println("UsBO entityMappeada: " + us);
            if(dao.update(us)){
                System.out.println("Entro al if");
                UsuarioDTO usAcualizado = mapper.convertirADTO(us);
                System.out.println("usDTO : " + usAcualizado);
                return usAcualizado;
            }
            System.out.println("Valio bergaaaas");
            return null;
        }catch(MongoException ex){
            throw new NegocioException("Error al actualizar: " + ex.getMessage());
        }
        
    }

}
