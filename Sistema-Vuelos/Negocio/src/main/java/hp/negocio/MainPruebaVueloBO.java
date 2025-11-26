package hp.negocio;


import BOs.VueloBO;
import DTOs.AsientoDTO;
import DTOs.VueloDTO;
import NegocioException.NegocioException;
import Config.MongoClientProvider;
import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainPruebaVueloBO {

    private static void imprimirVuelos(String titulo, List<VueloDTO> vuelos) {
        System.out.println("\n=== " + titulo + " ===");
        if (vuelos == null || vuelos.isEmpty()) {
            System.out.println("Sin resultados.");
            return;
        }
        vuelos.forEach(System.out::println);
    }

    private static void imprimirAsientos(String titulo, List<AsientoDTO> asientos) {
        System.out.println("\n=== " + titulo + " ===");
        if (asientos == null || asientos.isEmpty()) {
            System.out.println("Sin resultados.");
            return;
        }
        asientos.forEach(System.out::println);
    }

    public static void main(String[] args) {
        // 1) Inicializa conexión
        MongoClientProvider.INTANCE.init();

        // 2) Instancia del BO
        VueloBO vueloBO = new VueloBO();

        // 3) Define parámetros de prueba (ajusta a tus datos reales en Mongo)
        String origen = "Guadalajara";
        String destino = "Ciudad de México"; // usa exactamente como lo insertaste
        String numVuelo = "ABC123";          // idVuelo real en tu BD
        Date fechaSalida = null;
        try {
            fechaSalida = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .parse("2025-12-01T09:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4) Pruebas de métodos
        try {
            imprimirVuelos("Buscar vuelos", vueloBO.getBuscarVuelos(origen, destino, fechaSalida));
        } catch (NegocioException e) {
            System.out.println("Error en getBuscarVuelos: " + e.getMessage());
        }

        try {
            System.out.println("\n=== Vuelo por número de vuelo ===");
            System.out.println(vueloBO.getVuelo(numVuelo));
        } catch (NegocioException e) {
            System.out.println("Error en getVuelo(String): " + e.getMessage());
        }

        try {
            ObjectId idReal = new ObjectId("6747ab45c8d1e45b11223344"); // ajusta a un _id real
            System.out.println("\n=== Vuelo por ObjectId ===");
            System.out.println(vueloBO.getVuelo(idReal));
        } catch (Exception e) {
            System.out.println("Error en getVuelo(ObjectId): " + e.getMessage());
        }

        try {
            imprimirVuelos("Filtrar vuelos por origen", vueloBO.filtrarVuelos(origen));
        } catch (NegocioException e) {
            System.out.println("Error en filtrarVuelos(origen): " + e.getMessage());
        }

        try {
            imprimirVuelos("Filtrar vuelos por origen y destino", vueloBO.filtrarVuelos(origen, destino));
        } catch (NegocioException e) {
            System.out.println("Error en filtrarVuelos(origen, destino): " + e.getMessage());
        }

        try {
            imprimirVuelos("Filtrar vuelos por origen, destino y precio", vueloBO.filtrarVuelos(origen, destino, 1500f));
        } catch (NegocioException e) {
            System.out.println("Error en filtrarVuelos(origen, destino, precio): " + e.getMessage());
        }

        try {
            imprimirAsientos("Asientos disponibles", vueloBO.getAsientosDisponibles(numVuelo));
        } catch (NegocioException e) {
            System.out.println("Error en getAsientosDisponibles: " + e.getMessage());
        }

        try {
            imprimirAsientos("Asientos ocupados", vueloBO.getAsientosOcupados(numVuelo));
        } catch (NegocioException e) {
            System.out.println("Error en getAsientosOcupados: " + e.getMessage());
        }

        System.out.println("\n✅ Pruebas terminadas.");
    }
}
