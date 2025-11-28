/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import CRUD.CRUD;
import Config.MongoClientProvider;
import POJOs.Asiento;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class AsientoDAO extends CRUD<Asiento> {
    
    public AsientoDAO() {
        super(MongoClientProvider.INTANCE.database(),"Asientos", Asiento.class);
    }
    
}
