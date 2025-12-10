/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SEEDERS;

import BOs.VueloBO;
import Config.MongoClientProvider;
import DTOs.VueloDTO;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
                crearVueloDTO("Ciudad Obregon", "Hermosillo",
                        LocalDateTime.of(2025, 12, 25, 9, 0).atZone(ZoneId.systemDefault()).toInstant(),
                        45, "AeroSonora", 950),
                crearVueloDTO("Guadalajara", "CDMX",
                        LocalDateTime.of(2025, 12, 26, 14, 30).atZone(ZoneId.systemDefault()).toInstant(),
                        90, "MandaALV", 1800),
                crearVueloDTO("Monterrey", "Cancun",
                        LocalDateTime.of(2025, 12, 27, 7, 45).atZone(ZoneId.systemDefault()).toInstant(),
                        120, "Volaris", 2500),
                crearVueloDTO("Tijuana", "Mexicali",
                        LocalDateTime.of(2025, 12, 28, 10, 15).atZone(ZoneId.systemDefault()).toInstant(),
                        60, "Aeroméxico", 2200),
                crearVueloDTO("Hermosillo", "Mazatlan",
                        LocalDateTime.of(2025, 12, 29, 13, 0).atZone(ZoneId.systemDefault()).toInstant(),
                        80, "AeroSonora", 1500),
                crearVueloDTO("CDMX", "Merida",
                        LocalDateTime.of(2025, 12, 30, 6, 30).atZone(ZoneId.systemDefault()).toInstant(),
                        240, "Aeroméxico", 5500),
                crearVueloDTO("Cancun", "Villahermosa",
                        LocalDateTime.of(2025, 12, 31, 11, 0).atZone(ZoneId.systemDefault()).toInstant(),
                        120, "VivaAerobus", 3200),
                crearVueloDTO("Toluca", "Monterrey",
                        LocalDateTime.of(2026, 1, 1, 8, 45).atZone(ZoneId.systemDefault()).toInstant(),
                        90, "Interjet", 1700),
                crearVueloDTO("Chihuahua", "Ciudad Juarez",
                        LocalDateTime.of(2026, 1, 2, 15, 20).atZone(ZoneId.systemDefault()).toInstant(),
                        40, "AeroSonora", 800),
                crearVueloDTO("CDMX", "Acapulco",
                        LocalDateTime.of(2026, 1, 3, 7, 0).atZone(ZoneId.systemDefault()).toInstant(),
                        300, "Aeroméxico", 6000),
                crearVueloDTO("Guadalajara", "Leon",
                        LocalDateTime.of(2026, 1, 4, 12, 30).atZone(ZoneId.systemDefault()).toInstant(),
                        180, "Volaris", 4000),
                crearVueloDTO("Monterrey", "Puebla",
                        LocalDateTime.of(2026, 1, 5, 21, 0).atZone(ZoneId.systemDefault()).toInstant(),
                        120, "VivaAerobus", 2500),
                crearVueloDTO("Cancun", "Chetumal",
                        LocalDateTime.of(2026, 1, 6, 19, 15).atZone(ZoneId.systemDefault()).toInstant(),
                        90, "Aeroméxico", 1350),
                crearVueloDTO("Ciudad Obregon", "Guadalajara",
                        LocalDateTime.of(2026, 1, 7, 5, 50).atZone(ZoneId.systemDefault()).toInstant(),
                        120, "AeroSonora", 2100),
                crearVueloDTO("Hermosillo", "CDMX",
                        LocalDateTime.of(2026, 1, 8, 16, 10).atZone(ZoneId.systemDefault()).toInstant(),
                        150, "Aeroméxico", 2800)
        );

        // Insertar cada vuelo usando BO
        try {
            for (VueloDTO v : vuelos) {
                vueloBO.crearVuelo(v);
            }
            System.out.println("SEEDERS HECHOS");
        } catch (Exception e) {
            System.out.println("Error en crear los SEEDERS: " + e.getMessage());
        }
    }

    private static VueloDTO crearVueloDTO(String origen, String destino, Instant fechaSalida,
            int duracion, String aerolinea, float precio) {
        VueloDTO dto = new VueloDTO();
        dto.setOrigen(origen);
        dto.setDestino(destino);
        dto.setFechaSalida(fechaSalida);
        dto.setDuracion(duracion);
        dto.setAerolinea(aerolinea);
        dto.setPrecio(precio);
        return dto;
    }
}
