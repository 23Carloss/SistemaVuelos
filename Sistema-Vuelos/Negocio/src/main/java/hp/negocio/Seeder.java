package hp.negocio;


import BOs.VueloBO;
import DTOs.AsientoDTO;
import DTOs.VueloDTO;
import NegocioException.NegocioException;
import Config.MongoClientProvider;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Seeder {

    public static void main(String[] args) {
        // 1) Inicializar conexión
        MongoClientProvider.INTANCE.init();
        MongoDatabase db = MongoClientProvider.INTANCE.database();
        MongoCollection<Document> vuelosCollection = db.getCollection("Vuelos");

        // 2) Insertar un vuelo de prueba si no existe
        String numVuelo = "ABC123";
        Document existente = vuelosCollection.find(new Document("idVuelo", numVuelo)).first();
        if (existente == null) {
            Date fechaSalida = Date.from(LocalDateTime.of(2025, 12, 1, 9, 0)
                    .atZone(ZoneId.systemDefault()).toInstant());

            Document asiento1 = new Document("_id", new ObjectId())
                    .append("disponibilidad", true)
                    .append("numero", 12)
                    .append("fila", 3);

            Document asiento2 = new Document("_id", new ObjectId())
                    .append("disponibilidad", false)
                    .append("numero", 13)
                    .append("fila", 3);

            Document vuelo = new Document("_id", new ObjectId())
                    .append("idVuelo", numVuelo)
                    .append("origen", "Guadalajara")
                    .append("destino", "Ciudad de México")
                    .append("fechaSalida", fechaSalida)
                    .append("duracion", 120)
                    .append("aerolinea", "Aeromexico")
                    .append("precio", 1500.0f)
                    .append("listaAsientos", Arrays.asList(asiento1, asiento2));

            vuelosCollection.insertOne(vuelo);
            System.out.println("✅ Vuelo de prueba insertado.");
        }

        // 3) Instancia del BO
        VueloBO vueloBO = new VueloBO();

        // 4) Pruebas de métodos
        try {
            VueloDTO vueloPorNum = vueloBO.getVuelo(numVuelo);
            System.out.println("\n=== Vuelo por número de vuelo ===");
            System.out.println(vueloPorNum);
        } catch (NegocioException e) {
            System.out.println("Error en getVuelo(String): " + e.getMessage());
        }

        try {
            List<AsientoDTO> disponibles = vueloBO.getAsientosDisponibles(numVuelo);
            System.out.println("\n=== Asientos disponibles ===");
            disponibles.forEach(System.out::println);
        } catch (NegocioException e) {
            System.out.println("Error en getAsientosDisponibles: " + e.getMessage());
        }

        try {
            List<AsientoDTO> ocupados = vueloBO.getAsientosOcupados(numVuelo);
            System.out.println("\n=== Asientos ocupados ===");
            ocupados.forEach(System.out::println);
        } catch (NegocioException e) {
            System.out.println("Error en getAsientosOcupados: " + e.getMessage());
        }

        System.out.println("\nPruebas terminadas.");
    }
}
