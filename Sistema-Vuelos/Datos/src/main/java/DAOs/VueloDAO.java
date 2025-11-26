/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import CRUD.CRUD;
import InterfacesDAO.IVueloDAO;
import POJOs.Vuelo;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class VueloDAO  extends CRUD<Vuelo> implements IVueloDAO{
    
    public VueloDAO() {
        super("Vuelos",Vuelo.class);
    }
    
    
    
    
}
