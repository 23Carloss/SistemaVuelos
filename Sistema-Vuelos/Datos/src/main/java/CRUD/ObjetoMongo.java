/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface ObjetoMongo {

     ObjectId get_id();

     void set_id(ObjectId objectID);
    
     Bson toUpdateOperations();
    
}
