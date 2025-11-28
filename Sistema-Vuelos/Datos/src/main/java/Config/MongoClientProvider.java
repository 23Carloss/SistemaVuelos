/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public enum MongoClientProvider {
    INTANCE;

    private MongoClient client;
    private String dbName = "EmberGuiza";
    private String uri = "mongodb://localhost:27017/";
    private MongoClientSettings settings;
    
    public synchronized void init() {
        if (client == null) {
                 
           settings = MongoConfig.build(this.uri);
            
            client = MongoClients.create(settings);

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
        return client().getDatabase(this.dbName).withCodecRegistry(settings.getCodecRegistry());
    }

    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        if (client == null) {
            throw new IllegalStateException("necesitas iniciar una conexión para una base de datos");
        }
        MongoDatabase db = this.database();
//        MongoDatabase db = client.getDatabase(this.dbName);
        return db.getCollection(collectionName, clazz);

    }

}
