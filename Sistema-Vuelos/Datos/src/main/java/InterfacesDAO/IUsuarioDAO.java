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
    //devuelve false si el parametro correoE ya se encuentra registrado
    //true si no esta registrado
    public boolean verificarCorreo(String correE)throws PersistenciaException;
    public Usuario buscarPorCorreo(String correE)throws PersistenciaException;
    public boolean eliminarPorCorreo(String correoE)throws PersistenciaException;
    public boolean actualizarPorCorreo(Usuario entity)throws PersistenciaException;
}
