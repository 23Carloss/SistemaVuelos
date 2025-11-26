package hp.negocio;

import Config.MongoClientProvider;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class MainSeeder {

    public static void main(String[] args) {
        // 1) Inicializar conexión
        MongoClientProvider.INTANCE.init();
        MongoDatabase db = MongoClientProvider.INTANCE.database();
        MongoCollection<Document> vuelosCollection = db.getCollection("Vuelos");

        // 2) Limpiar colección (opcional, solo para pruebas)
        

        // 3) Crear fechas de salida
        Date fecha1 = Date.from(LocalDateTime.of(2025, 12, 1, 9, 0)
                .atZone(ZoneId.systemDefault()).toInstant());
        Date fecha2 = Date.from(LocalDateTime.of(2025, 12, 2, 15, 30)
                .atZone(ZoneId.systemDefault()).toInstant());
        Date fecha3 = Date.from(LocalDateTime.of(2025, 12, 3, 20, 45)
                .atZone(ZoneId.systemDefault()).toInstant());

        // 4) Crear asientos
        Document asientoDisponible = new Document("_id", new ObjectId())
                .append("disponibilidad", true)
                .append("numero", 12)
                .append("fila", 3);

        Document asientoOcupado = new Document("_id", new ObjectId())
                .append("disponibilidad", false)
                .append("numero", 13)
                .append("fila", 3);

        // 5) Crear vuelos
        Document vuelo1 = new Document("_id", new ObjectId())
                .append("idVuelo", "ABC123")
                .append("origen", "Guadalajara")
                .append("destino", "Ciudad de México")
                .append("fechaSalida", fecha1)
                .append("duracion", 120)
                .append("aerolinea", "Aeromexico")
                .append("precio", 1500.0f)
                .append("listaAsientos", Arrays.asList(asientoDisponible, asientoOcupado));

        Document vuelo2 = new Document("_id", new ObjectId())
                .append("idVuelo", "DEF456")
                .append("origen", "Monterrey")
                .append("destino", "Cancún")
                .append("fechaSalida", fecha2)
                .append("duracion", 180)
                .append("aerolinea", "Volaris")
                .append("precio", 2200.0f)
                .append("listaAsientos", Arrays.asList(asientoDisponible, asientoOcupado));

        Document vuelo3 = new Document("_id", new ObjectId())
                .append("idVuelo", "GHI789")
                .append("origen", "Tijuana")
                .append("destino", "Ciudad de México")
                .append("fechaSalida", fecha3)
                .append("duracion", 210)
                .append("aerolinea", "Interjet")
                .append("precio", 1800.0f)
                .append("listaAsientos", Arrays.asList(asientoDisponible, asientoOcupado));

        // 6) Insertar en la colección
        vuelosCollection.insertMany(Arrays.asList(vuelo1, vuelo2, vuelo3));

        System.out.println("✅ Vuelos de prueba insertados en la colección 'Vuelos'.");
        System.out.println("Puedes probar ahora tus métodos con idVuelo = 'ABC123', 'DEF456', 'GHI789'.");
    }
}
