/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Config;

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

    public synchronized void init() {
        if (client == null) {
            client = MongoClients.create(MongoConfig.build(this.uri));

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
        return client().getDatabase(this.dbName);
    }

    public <T> MongoCollection<T> getCollection(String collectionName, Class<T> clazz) {
        if (client == null) {
            throw new IllegalStateException("necesitas iniciar una conexión para una base de datos");
        }
        MongoDatabase db = client.getDatabase(this.dbName);
        return db.getCollection(collectionName, clazz);

    }

}
