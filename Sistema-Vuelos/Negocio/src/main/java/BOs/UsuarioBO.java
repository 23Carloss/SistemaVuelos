/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BOs;

import CRUD.CRUD;
import DTOs.UsuarioDTO;
import Mappers.UsuarioMapper;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import NegocioException.NegocioException;
import POJOs.Usuario;
import Interfaces.IUsuarioBO;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class UsuarioBO implements IUsuarioBO{
    private CRUD collection;
    private UsuarioMapper mapper;

    public UsuarioBO() {
        collection = new CRUD("usuarios", Usuario.class);
    }

    @Override
    public UsuarioDTO createObject(UsuarioDTO object) throws NegocioException{
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

            if (object.getId() == null) {
                object.setId(new ObjectId());
            }
    //
    //        collection.insertOne(object);
            return object;
        } catch (Exception e) {
            throw new NegocioException("Error al agregar usuario");
        }
    }

    @Override
    public UsuarioDTO findById(ObjectId _id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UsuarioDTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO update(ObjectId _id, Bson update) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(ObjectId _id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     

 

}
