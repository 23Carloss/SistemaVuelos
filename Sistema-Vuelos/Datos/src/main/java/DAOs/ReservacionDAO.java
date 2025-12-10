/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import CRUD.CRUD;
import Config.MongoClientProvider;
import Exception.PersistenciaException;
import InterfacesDAO.IReservacionDAO;
import POJOs.Asiento;
import POJOs.Reservacion;
import POJOs.Usuario;
import POJOs.Vuelo;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class ReservacionDAO extends CRUD implements IReservacionDAO {

    private final MongoCollection<Document> colDoc;
    private final MongoCollection<Usuario> colUs;

    public ReservacionDAO() {
        super(MongoClientProvider.INSTANCE.database(), "Reservacion", Reservacion.class);
        this.colDoc = MongoClientProvider.INSTANCE.getCollection("Reservacion", Document.class);
        this.colUs = MongoClientProvider.INSTANCE.getCollection("Usuarios", Usuario.class);
    }

    @Override
    public boolean eliminarPorNumReservacion(String numReservacion) throws PersistenciaException {
        try {
            Reservacion reservacionMongo = (Reservacion) collection.find(Filters.eq("numReservacion", numReservacion)).first();
            var resultado = collection.deleteOne(Filters.eq("numReservacion", numReservacion));
            return resultado.getDeletedCount() > 0;

        } catch (MongoException ex) {
            throw new PersistenciaException("Error al eliminar reservacion " + ex.getMessage());
        }
    }

    @Override
    public List<Reservacion> obtenerReservacionesPorUsuario(Usuario us) throws PersistenciaException {
        try {
            Usuario usMongo = colUs.find(Filters.eq("correo", us.getCorreo())).first();
            List<Document> pipeLine = List.of(
                    new Document("$match", new Document("correoUsuario", usMongo.getCorreo())),
                    new Document("$lookup",
                            new Document("from", "Usuarios")
                                    .append("localField", "correoUsuario")
                                    .append("foreignField", "correo")
                                    .append("as", "Mis reservaciones")
                    )
            );
            List<Document> docs = colDoc.aggregate(pipeLine).into(new ArrayList<>());
            List<Reservacion> reservaciones = convertirDocAReservacion(docs);

            return reservaciones;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al consultar reservaciones" + ex.getMessage());
        }

    }

    // Convierte un Document de Vuelo a un objeto Vuelo
    private Vuelo convertirDocAVuelo(Document vueloDoc) {
        Vuelo v1 = new Vuelo();
        if (vueloDoc == null) {
            return v1;
        }

        v1.set_id(vueloDoc.getObjectId("_id"));
        v1.setAerolinea(vueloDoc.getString("aerolinea"));
        v1.setDestino(vueloDoc.getString("destino"));
        v1.setDuracion(vueloDoc.getInteger("duracion"));
        v1.setNumVuelo(vueloDoc.getString("numVuelo"));


        // fechaSalida
        Date fechaSalidaDate = vueloDoc.getDate("fechaSalida");
        if (fechaSalidaDate != null) {
            Instant fechaSalidaLocalDate = Instant.now();
            v1.setFechaSalida(fechaSalidaLocalDate);
        }

        v1.setNumVuelo(vueloDoc.getString("numVuelo"));

        // listaAsientos
        List<Document> listaAsientos = vueloDoc.getList("listaAsientos", Document.class);
        if (listaAsientos != null) {
            v1.setListaAsientos(convertirDocAAsientos(listaAsientos));
        }

        v1.setOrigen(vueloDoc.getString("origen"));

        Double precio = vueloDoc.getDouble("precio");
        if (precio != null) {
            v1.setPrecio(precio.floatValue());
        }

        return v1;
    }

    // Convierte una lista de Document en una lista de Reservacion
    private List<Reservacion> convertirDocAReservacion(List<Document> docs) {
        List<Reservacion> reservaciones = new ArrayList<>();
        if (docs == null) {
            return reservaciones;
        }

        for (Document doc : docs) {
            Reservacion r1 = new Reservacion();

            // _id
            r1.set_id(doc.getObjectId("_id"));

            // numReservacion
            r1.setNumReservacion(doc.getString("numReservacion"));

            // correoUsuario
            r1.setCorreoUsuario(doc.getString("correoUsuario"));

            // fechaReservacion
            Date fecha = doc.getDate("fechaReservacion");
            if (fecha != null) {
                Instant reserva = fecha.toInstant();
                r1.setFechaReservacion(reserva);
            }

            // asiento (solo uno)
            Document asientoDoc = (Document) doc.get("asiento");
            if (asientoDoc != null) {
                Asiento asiento = convertirDocAAsiento(asientoDoc);
                r1.setAsiento(asiento);
            }

            // vuelo
            Document vueloDoc = (Document) doc.get("vuelo");
            if (vueloDoc != null) {
                r1.setVuelo(convertirDocAVuelo(vueloDoc));
            }

            // creadoEn
            Date creado = doc.getDate("creadoEn");
            if (creado != null) {
                r1.setCreadoEn(creado.toInstant());
            }

            // editadoEn
            Date editado = doc.getDate("editadoEn");
            if (editado != null) {
                r1.setEditadoEn(editado.toInstant());
            }

            reservaciones.add(r1);
        }
        return reservaciones;
    }

    // Convierte una lista de Document en una lista de Asiento
    private List<Asiento> convertirDocAAsientos(List<Document> listaAsientos) {
        List<Asiento> asientos = new ArrayList<>();
        if (listaAsientos == null) {
            return asientos;
        }

        for (Document d : listaAsientos) {
            Asiento a1 = new Asiento();
            a1.set_id(d.getObjectId("_id"));
            a1.setDisponibilidad(d.getBoolean("disponibilidad"));
            a1.setFila(d.getInteger("fila"));
            a1.setNumero(d.getInteger("numero"));
            a1.setColumna(d.getString("columna"));
            asientos.add(a1); // 🔑 faltaba agregarlo a la lista
        }
        return asientos;
    }

// Convierte un solo Document en un Asiento
    private Asiento convertirDocAAsiento(Document d) {
        Asiento a1 = new Asiento();
        if (d == null) {
            return a1;
        }

        a1.set_id(d.getObjectId("_id"));
        a1.setDisponibilidad(d.getBoolean("disponibilidad"));
        a1.setFila(d.getInteger("fila"));
        a1.setNumero(d.getInteger("numero"));
        a1.setColumna(d.getString("columna"));
        return a1;
    }

    @Override
    public Reservacion actualizarPorNumReservacion(Reservacion reservacion) throws PersistenciaException {
        try {
            var filter = Filters.eq("numReservacion", reservacion.getNumReservacion());
            var updates = reservacion.toUpdateOperations();

            var result = collection.updateOne(filter, updates);
            reservacion = (Reservacion) collection.find(Filters.eq("numReservacion", reservacion.getNumReservacion()));

            return reservacion;
        } catch (MongoException ex) {
            throw new PersistenciaException("Error al eliminar reservacion " + ex.getMessage());
        }
    }

    @Override
    public Reservacion buscarPorNumReservacion(String numReservacion) {
        Reservacion reservacionEncontrada = (Reservacion) collection.find(Filters.eq("numReservacion", numReservacion));
        return reservacionEncontrada;
    }
}
