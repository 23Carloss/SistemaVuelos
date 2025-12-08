/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package PruebaReservaciones;

import BOs.VueloBO;
import Config.MongoClientProvider;
import DAOs.AsientoDAO;
import DAOs.ReservacionDAO;
import DAOs.UsuarioDAO;
import DAOs.VueloDAO;
import Exception.PersistenciaException;
import Mappers.ReservacionMapper;
import POJOs.Asiento;
import POJOs.Reservacion;
import POJOs.Usuario;
import POJOs.Vuelo;
import com.mongodb.client.MongoDatabase;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.types.ObjectId;

/**
 *
 * @author HP
 */
public class PruebasReservacion {

            /**
             * @param args the command line arguments
             */
            public static void main(String[] args) {
                // TODO code application logic here
                MongoClientProvider.INSTANCE.init();
                MongoDatabase db = MongoClientProvider.INSTANCE.database();
                AsientoDAO asientoDAO = new AsientoDAO();
                VueloDAO vueloDao = new VueloDAO();
                ReservacionDAO reservacionDAO = new ReservacionDAO();
                UsuarioDAO usDAO  = new UsuarioDAO();
                VueloBO vueloBo = new VueloBO();
                ReservacionMapper mapper = new ReservacionMapper();
                Asiento a1 = new Asiento();
                a1.setDisponibilidad(true);
                a1.setFila(1);
                a1.setNumero(1);
                Asiento a2 = new Asiento();
                a2.setDisponibilidad(true);
                a2.setFila(1);
                a2.setNumero(1);
                a2.setColumna("A");
                //        asientoDAO.create(a1);
                //        asientoDAO.create(a2);

                List<Asiento> asientosAvion = new ArrayList<>();
                asientosAvion.add(a1);
                asientosAvion.add(a2);
                LocalDateTime salidaVuelo = LocalDateTime.of(2025, 12, 1, 9, 0);
                Vuelo v1 = new Vuelo();
                v1.setAerolinea("Carrillo");
                v1.setDestino("Mexico");
                v1.setDuracion(200);
                v1.setFechaSalida(salidaVuelo);
                v1.setListaAsientos(asientosAvion);
                v1.setNumVuelo("A131");
                v1.setOrigen("Europa");
                v1.setNombre("Vuelo1");
                v1.setPrecio(300);
//                        vueloBo(v1); y el create?

                List<Asiento> asientosReservados = new ArrayList<>();
                asientosReservados.add(a1);
                //                Usuario us = usDAO.buscarPorCorreo("123123@gmail.com");
                Usuario us2 = new Usuario();
                us2.set_id(new ObjectId());
                us2.setNombre("Vuelo");
                us2.setApellidoP("perez");
                us2.setApellidoM("g");
                us2.setCorreo("luis@gmail.com");
                us2.setContrasenia("carlos23");
                us2.setTipoUsuario("usuario");
                       
//                us2 = mapper.convertirAEntity(usDAO.create(us2));
                System.out.println("user credo: " + us2);
                System.out.println("Vuelo creado : " + v1);
                LocalDateTime reservacion = LocalDateTime.of(2025, 12, 1, 9, 0);
                Reservacion r1 = new Reservacion();
                r1.setAsientos(asientosReservados);
                r1.setCreadoEn(Instant.now());
                r1.setFechaReservacion(reservacion);
                r1.setVuelo(v1);
                                r1.setCorreoUsuario("luis@gmail.com");
                                reservacionDAO.create(r1);
                                
                System.out.println(r1);
                
                System.out.println(us2);
                try {
                    //                for(Reservacion r : reservacionDAO.obtenerReservacionesPorUsuario(us)){
                    //                    System.out.println(r);
                    //                }
                    reservacionDAO.obtenerReservacionesPorUsuario(us2).forEach(System.out::println);
                    
                    //            reservacionDAO.obtenerReservacionesPorUsuario(us2).forEach(doc -> System.out.println(doc));
                } catch (PersistenciaException ex) {
                    Logger.getLogger(PruebasReservacion.class.getName()).log(Level.SEVERE, null, ex);
                }

    }
    
}
