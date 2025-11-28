/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import Config.MongoClientProvider;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 * @param <T>
 */
public class CRUD<T> implements ICRUD {

    protected final MongoCollection<T> col;

    public CRUD(MongoDatabase db, String colleccion, Class clase) {
        this.col = db.getCollection(colleccion, clase);
    }

    @Override
    public T create(ObjetoMongo entity) throws MongoException {
        try {
            if(entity.get_id() == null) entity.set_id(new ObjectId());
            col.insertOne((T)entity);  
            return (T) entity;
        } catch (MongoException e) {

            throw e;
        }

    }

    @Override
    public T read(ObjectId _id) throws MongoException {

        try {
            return col.find(Filters.eq("_id", _id)).first();
        } catch (MongoException e) {
            throw e;
        }
    }

    @Override
    public boolean update(ObjetoMongo entity) throws MongoException {
        try {
          
            var filter = Filters.eq("_id", entity.get_id());
            var updates = entity.toUpdateOperations(); // método que tú defines en ObjetoMongo

            var result = col.updateOne(filter,updates);
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(ObjectId _id) throws MongoException {
        try {
            System.out.println("id q llega a delete: " + _id);
            var result = col.deleteOne(Filters.eq("_id", _id));

            return result.getDeletedCount() > 0;

        } catch (MongoException e) {
            throw e;
        }
    }

    @Override
    public List<T> findEntities() throws MongoException {
        try {
//            col.find().limit(100).into(new ArrayList<>()).forEach(Usuario -> System.out.println(Usuario));
            return col.find().limit(100).into(new ArrayList<>());
        } catch (MongoException e) {
            throw e;
        }

    }

    @Override
    public List findEntities(int maxResults, int firstResult) throws MongoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<T> findByName(String name) throws MongoException {
        try {
            return col.find(Filters.eq("nombre", name)).limit(100).into(new ArrayList<>());
        } catch (MongoException e) {
            throw e;
        }
    }

}
