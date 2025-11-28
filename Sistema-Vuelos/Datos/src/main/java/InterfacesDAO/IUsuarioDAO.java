/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import CRUD.ICRUD;
import Exception.PersistenciaException;
import POJOs.Usuario;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface IUsuarioDAO extends ICRUD {
    public Usuario autenticar(String correo, String contrasenia) throws PersistenciaException;
    public List<Usuario> buscarPorNombre(String nombre) throws PersistenciaException;
    
}
