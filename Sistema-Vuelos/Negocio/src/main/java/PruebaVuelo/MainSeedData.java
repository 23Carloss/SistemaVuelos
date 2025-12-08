package PruebaVuelo;


import Config.MongoClientProvider;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class MainSeedData {

    public static void main(String[] args) {
        // 1) Inicializar conexión
        MongoClientProvider.INSTANCE.init();
        MongoDatabase db = MongoClientProvider.INSTANCE.database();
        MongoCollection<Document> vuelosCollection = db.getCollection("Vuelos");

        // 2) Limpiar colección (opcional, solo para pruebas)
        

        // 3) Crear fecha de salida
        LocalDateTime salida = LocalDateTime.of(2025, 12, 1, 9, 0);
        Date fechaSalida = Date.from(salida.atZone(ZoneId.systemDefault()).toInstant());

        // 4) Crear asientos
        Document asiento1 = new Document("_id", new ObjectId())
                .append("disponibilidad", true)
                .append("numero", 12)
                .append("fila", 3);

        Document asiento2 = new Document("_id", new ObjectId())
                .append("disponibilidad", false)
                .append("numero", 13)
                .append("fila", 3);

        // 5) Crear vuelo
        Document vuelo = new Document("_id", new ObjectId())
                .append("numVuelo", "ABC123")
                .append("origen", "Guadalajara")
                .append("destino", "Ciudad de México")
                .append("fechaSalida", fechaSalida)
                .append("duracion", 120)
                .append("aerolinea", "Aeromexico")
                .append("precio", 1500.0f)
                .append("listaAsientos", Arrays.asList(asiento1, asiento2));

        // 6) Insertar en la colección
        vuelosCollection.insertOne(vuelo);

        System.out.println("✅ Vuelo de prueba insertado en la colección 'Vuelos'.");
        System.out.println("Puedes probar ahora tus métodos con idVuelo = 'ABC123', origen = 'Guadalajara', destino = 'Ciudad de México'.");
    }
}
