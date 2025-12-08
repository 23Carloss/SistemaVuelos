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
import NegocioException.NegocioException;
import POJOs.Usuario;
import Interfaces.IUsuarioBO;
import InterfacesDAO.IUsuarioDAO;
import com.mongodb.MongoException;
import javax.swing.JOptionPane;

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

    public UsuarioDTO crearUsuario(UsuarioDTO usuario) throws NegocioException{
        try {
            if (usuario == null) {
                    throw new IllegalArgumentException("Usuario no puede ser nulo");
                }
            if(verificarCorreo(usuario.getCorreo())){
                throw new NegocioException("Correo ya registrado");
            }
                if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
                    throw new IllegalArgumentException("Nombre es obligatorio");
                }
                if (usuario.getApellidoP() == null || usuario.getApellidoP().isBlank()) {
                    throw new IllegalArgumentException("Apellido paterno es obligatorio");
                }
                if (usuario.getCorreo() == null || !usuario.getCorreo().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new IllegalArgumentException("Formato de correo incorrecto");
                }
                if (usuario.getContrasenia() == null || usuario.getContrasenia().length() < 6) {
                    throw new IllegalArgumentException("Contraseña debe tener al menos 6 caracteres");
                }
//                if(usuario.getIdObject()== null) usuario.setIdObject(UUID.randomUUID());

                Usuario usuario1 = mapper.convertirAEntity(usuario);           
                Usuario us = (Usuario)dao.create(usuario1);
                return mapper.convertirADTO(us);
            
            
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Error al agregar usuario este" + ex.getMessage());
        }
    }


    @Override
    public UsuarioDTO buscarPorCorreo(String correo) throws NegocioException{
        try{
            if(correo.trim() == null)throw new NegocioException("Correo vacio");

            Usuario usuario = dao.buscarPorCorreo(correo);
            UsuarioDTO usuarioEncontrado = mapper.convertirADTO(usuario);
            return usuarioEncontrado;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar usuario" + ex.getMessage());
        }
    }
    
//    public UsuarioDTO buscarPorId(UUID id) throws NegocioException{
//        try{
//            return mapper.convertirADTO((Usuario) dao.read(id));
//        } catch (MongoException ex) {
//            throw new NegocioException("Error al buscar usuario" + ex.getMessage());
//        }
//    }


    @Override
    public List<UsuarioDTO> obtenerTodos() throws NegocioException{
        try{
            List<Usuario> usuarios = dao.findEntities();
            return mapper.ConvertirListaADto(usuarios);
        }catch(MongoException ex){
            throw new NegocioException("Error al obtener todos los usuario" + ex.getMessage());
        }
    }

    @Override
    public boolean eliminarPorCorreo(String correo) throws NegocioException{
        try{
            if(correo.trim() == null)throw new NegocioException("Correo vacio");

            return dao.eliminarPorCorreo(correo);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar usuario" + ex.getMessage());
        }
        
    }

    @Override
    public UsuarioDTO iniciarSesion(String correo, String password) throws NegocioException {
        try {
            if(correo.trim() == null || password.trim() == null)throw new NegocioException("Correo o contrasenia vacios");
            Usuario usuario = dao.autenticar(correo, password);
            if (usuario == null) {
                throw new NegocioException("Credenciales incorrectas");
            }
            
            return mapper.convertirADTO(usuario);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al iniciar sesion");
        }

    }

    @Override
    public List<UsuarioDTO> buscarPorNombre(String nombre) throws NegocioException {
        try {
            List<Usuario> listaEntity = dao.buscarPorNombre(nombre);
            List<UsuarioDTO> listaUsuarios = mapper.ConvertirListaADto(listaEntity);
            return listaUsuarios;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al buscar por nombre: " + ex.getMessage());
        }
    }

  

    @Override
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuario) throws NegocioException {
        try{
            Usuario us = mapper.convertirAEntity(usuario);
            if(dao.actualizarPorCorreo(us)){
                UsuarioDTO usAcualizado = mapper.convertirADTO(us);
                return usAcualizado;
            }
            return null;
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al actualizar: " + ex.getMessage());
        }
        
        
    }

    @Override
    public boolean verificarCorreo(String correE) throws NegocioException {
        try{
            
        if(correE.trim().isEmpty()){
            throw new IllegalArgumentException("correo no puede ser nulo");
        }
            return dao.verificarCorreo(correE);
        } catch (IllegalArgumentException ex) {
            throw new NegocioException("Error al verificar correo" + ex.getMessage());
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al verifciar correo: " + ex.getMessage());
        }
    }
    


}
