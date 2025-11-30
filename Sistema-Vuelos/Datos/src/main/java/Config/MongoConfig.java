/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class MongoConfig {

    private MongoConfig() {

    }

    public static MongoClientSettings build(String uri) {
        ConnectionString connectionString = new ConnectionString(uri);

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
            MongoClient.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
    );
        
        
        return MongoClientSettings.builder().
                applyConnectionString(connectionString).
                codecRegistry(pojoCodecRegistry)
                .build();
        
    }

}
