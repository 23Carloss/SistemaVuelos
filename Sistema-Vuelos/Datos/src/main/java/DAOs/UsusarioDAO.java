/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAOs;

import Entity.Usuario;
import Exception.PersistenciaExcetion;

/**
 *
 * @author $Luis Carlos Manjarrez Gonzalez
 */
public class UsusarioDAO {

    public UsusarioDAO() {
    }
    
//    private EntityManager
    
    public void agregarUsuario(Usuario usuario) throws PersistenciaExcetion{
        try{
            
        }catch(Exception e){
            throw new PersistenciaExcetion("Error al agregar ususario");
        }
    }

}
