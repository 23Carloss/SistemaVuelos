/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public enum MongoClientProvider {
    INSTANCE;

    private MongoClient client;
    private String dbName = "EmberGuiza";
    private String uri = "mongodb://localhost:27017/";
    private CodecRegistry pojoCodecRegistry;
    
    public synchronized void init() {
        if (client == null) {
                 
            client = new MongoClient(new MongoClientURI(uri)) {};

            
            pojoCodecRegistry = fromRegistries(
                    MongoClient.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build())
            );

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    client.close();
                } catch (Exception e) {
                }
            }));
        }

    }

    public MongoClient client() {
        if (client == null) {
            throw new IllegalStateException("necesitas iniciar una conexión para una base de datos");
        }
        return client;
    }

    public MongoDatabase database() {
        return client().getDatabase(this.dbName).withCodecRegistry(pojoCodecRegistry);
    }

    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        if (client == null) {
            throw new IllegalStateException("necesitas iniciar una conexión para una base de datos");
        }
        MongoDatabase db = this.database();
        return db.getCollection(collectionName, clazz);

    }

}
