/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEEDERS;

import BOs.VueloBO;
import Config.MongoClientProvider;
import DTOs.VueloDTO;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Jesus Gammael Soto Escalante 248336
 */
public class SEEDERS_1 {

    public static void main(String[] args) {
        MongoClientProvider.INSTANCE.init();

        VueloBO vueloBO = new VueloBO();
        List<VueloDTO> vuelos = Arrays.asList(
                crearVueloDTO("Ciudad Obregón", "Hermosillo", LocalDateTime.of(2025, 12, 25, 9, 0), 45, "AeroSonora", 950),
                crearVueloDTO("Guadalajara", "Ciudad de México", LocalDateTime.of(2025, 12, 26, 14, 30), 90, "MandaALV", 1800),
                crearVueloDTO("Monterrey", "Cancún", LocalDateTime.of(2025, 12, 27, 7, 45), 120, "Volaris", 2500),
                crearVueloDTO("Tijuana", "Los Ángeles", LocalDateTime.of(2025, 12, 28, 10, 15), 60, "Aeroméxico", 2200),
                crearVueloDTO("Hermosillo", "Mazatlán", LocalDateTime.of(2025, 12, 29, 13, 0), 80, "AeroSonora", 1500),
                crearVueloDTO("Ciudad de México", "Nueva York", LocalDateTime.of(2025, 12, 30, 6, 30), 240, "Aeroméxico", 5500),
                crearVueloDTO("Cancún", "Miami", LocalDateTime.of(2025, 12, 31, 11, 0), 120, "VivaAerobus", 3200),
                crearVueloDTO("Toluca", "Monterrey", LocalDateTime.of(2026, 1, 1, 8, 45), 90, "Interjet", 1700),
                crearVueloDTO("Chihuahua", "Ciudad Juárez", LocalDateTime.of(2026, 1, 2, 15, 20), 40, "AeroSonora", 800),
                crearVueloDTO("Ciudad de México", "Toronto", LocalDateTime.of(2026, 1, 3, 7, 0), 300, "Aeroméxico", 6000),
                crearVueloDTO("Guadalajara", "Houston", LocalDateTime.of(2026, 1, 4, 12, 30), 180, "Volaris", 4000),
                crearVueloDTO("Monterrey", "Madrid", LocalDateTime.of(2026, 1, 5, 21, 0), 720, "Iberia", 12000),
                crearVueloDTO("Cancún", "París", LocalDateTime.of(2026, 1, 6, 19, 15), 600, "Air France", 13500),
                crearVueloDTO("Ciudad Obregón", "Guadalajara", LocalDateTime.of(2026, 1, 7, 5, 50), 120, "AeroSonora", 2100),
                crearVueloDTO("Hermosillo", "Ciudad de México", LocalDateTime.of(2026, 1, 8, 16, 10), 150, "Aeroméxico", 2800)
        );

        // Insertar cada vuelo usando BO
        try {
            for (VueloDTO v : vuelos) {
                vueloBO.crearVuelo(v);
            }
            System.out.println("SEEDERS HECHOS");
        } catch (Exception e) {
            System.out.println("Error en crear los SEEDERS");
        }
        

    }

    private static VueloDTO crearVueloDTO(String origen, String destino, LocalDateTime fechaSalida,
            int duracion, String aerolinea, float precio) {
        VueloDTO dto = new VueloDTO();
        dto.setOrigen(origen);
        dto.setDestino(destino);
        dto.setFechaSalida(fechaSalida);
        dto.setDuracion(duracion);
        dto.setAerolinea(aerolinea);
        dto.setPrecio(precio);
//        dto.setListaAsientos(new LinkedList<>()); // inicializar lista vacía
// utilizare la test del burgos
        return dto;
    }
}
