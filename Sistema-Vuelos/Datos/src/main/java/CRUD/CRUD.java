/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import Config.MongoClientProvider;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 * @param <T>
 */
public class CRUD<T> implements ICRUD {

    protected final MongoCollection<T> col;
    
    
    
    public CRUD(String colleccion, Class clase) {
        this.col = MongoClientProvider.INTANCE.getCollection(colleccion, clase);
    }

    @Override
    public T create(ObjetoMongo entity) throws MongoException {
        try {
            col.insertOne((T) entity);
        } catch (MongoException e) {

            throw e;
        }
        return null;

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
    public boolean  update(ObjetoMongo entity) throws MongoException {
        try {
            var filter = Filters.eq("_id", entity.getObjectID());
            var updates = entity.toUpdateOperations(); // método que tú defines en ObjetoMongo

            var result = col.updateOne(filter, updates);
            return result.getModifiedCount() > 0;
        } catch (MongoException e) {
            throw e;
        }
    }

    @Override
    public boolean delete(ObjectId _id) throws MongoException {
        try {
            var result = col.deleteOne(Filters.eq("_id", _id));

            return result.getDeletedCount() > 0;

        } catch (MongoException e) {
            throw e;
        }
    }

    @Override
    public List findEntities() throws MongoException {
        try {
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
