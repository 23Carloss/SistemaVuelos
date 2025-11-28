/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CRUD;


import com.mongodb.MongoException;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 * @param <T>
 */
public interface ICRUD<T> {

    public T create(ObjetoMongo entity) throws MongoException;

    public T read(ObjectId id) throws MongoException;

    public boolean update(ObjetoMongo entity) throws MongoException;

    public boolean delete(ObjectId id) throws MongoException;

    List<T> findEntities() throws MongoException;

    List<T> findEntities(int maxResults, int firstResult) throws MongoException;

}
