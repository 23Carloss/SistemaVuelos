/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Interfaces;

import CRUD.ICRUD;
import DTOs.UsuarioDTO;
import NegocioException.NegocioException;
import java.util.List;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public interface IUsuarioBO extends ICRUD {
    
    
    public UsuarioDTO createObject(UsuarioDTO object)throws NegocioException;
    public UsuarioDTO findById(ObjectId _id)throws NegocioException;
    public List<UsuarioDTO> findByName(String name)throws NegocioException;
    public List<UsuarioDTO> findAll()throws NegocioException;
    public UsuarioDTO update(ObjectId _id, Bson update)throws NegocioException;
    public void deleteById(ObjectId _id)throws NegocioException;
    public UsuarioDTO signIn(String correo, String password)throws NegocioException;
    
    

}
