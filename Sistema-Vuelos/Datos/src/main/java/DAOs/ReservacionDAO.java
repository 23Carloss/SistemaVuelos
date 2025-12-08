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
        super(MongoClientProvider.INSTANCE.database(),"Reservacion", Reservacion.class);
        this.colDoc = MongoClientProvider.INSTANCE.getCollection("Reservacion", Document.class);
        this.colUs = MongoClientProvider.INSTANCE.getCollection("Usuarios", Usuario.class);
    }
    
    @Override
    public boolean eliminarPorNumReservacion(String numReservacion)throws PersistenciaException{
        try{     
            Reservacion reservacionMongo = (Reservacion) collection.find(Filters.eq("numReservacion", numReservacion)).first();
            var resultado = collection.deleteOne(Filters.eq("numReservacion",reservacionMongo.getNumReservacion()));
            return resultado.getDeletedCount() > 0;
            
        }catch(MongoException ex){
            throw new PersistenciaException("Error al eliminar reservacion "+ ex.getMessage());
        }
    }
    
    @Override
    public List<Reservacion> obtenerReservacionesPorUsuario(Usuario us) throws PersistenciaException{
        try{
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
        }catch(MongoException ex){
            throw new PersistenciaException("Error al consultar reservaciones" + ex.getMessage());
        }
      
    }
    
    private Vuelo convertirDocAVuelo(Document vueloDoc){
        
                Vuelo v1 = new Vuelo();
                v1.set_id(vueloDoc.getObjectId("_id"));
                v1.setAerolinea(vueloDoc.getString("aerolinea"));
                v1.setDestino(vueloDoc.getString("destino"));
                v1.setDuracion(vueloDoc.getInteger("duracion"));
                v1.setAerolinea(vueloDoc.getString("aerolinea"));
                v1.setNombre(vueloDoc.getString("nombre"));
                
                
                Date fechaSalidaDate = vueloDoc.getDate("fechaSalida");
                LocalDateTime fechaSalidaLocalDate = null;
                if (fechaSalidaDate != null) {
                    fechaSalidaLocalDate = fechaSalidaDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
                }
                v1.setFechaSalida(fechaSalidaLocalDate);
                v1.setNumVuelo(vueloDoc.getString("numVuelo"));
                List<Document> listaAsientos = vueloDoc.getList("listaAsientos", Document.class);
                v1.setListaAsientos(convertirDocAAsientos(listaAsientos));
                v1.setOrigen(vueloDoc.getString("origen"));
                v1.setPrecio(vueloDoc.getDouble("precio").floatValue());
                return v1;
    }
    
    private List<Reservacion> convertirDocAReservacion(List<Document> docs){
        List<Reservacion> Reservacion = new ArrayList<>();
        for(Document doc : docs) {
                Reservacion r1 = new Reservacion();
                List<Document> listaAsientos = doc.getList("asientos", Document.class);
                r1.setAsientos(convertirDocAAsientos(listaAsientos));
                Date creado = doc.getDate("creadoEn");
                if(creado != null){
                     r1.setCreadoEn(creado.toInstant());
                }
                Date date = doc.getDate("fechaReservacion");
                LocalDateTime reserva = null;
                if (date != null) {
                    reserva = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
                }
                r1.setFechaReservacion(reserva);
                r1.setCorreoUsuario(doc.getString("correoUsuario"));
                Document vueloDoc = (Document) doc.get("vuelo");
                Vuelo v2 = convertirDocAVuelo(vueloDoc);
                r1.setVuelo(v2);
                Reservacion.add(r1);
            }
        return Reservacion;
    }
    
    private List<Asiento> convertirDocAAsientos(List<Document> listaAsientos){
        List<Asiento> asientos = new ArrayList<>();
        for(Document d : listaAsientos){
            Asiento a1 = new Asiento();
            a1.set_id(d.getObjectId("_id"));
            a1.setDisponibilidad(d.getBoolean("disponibilidad"));
            a1.setFila(d.getInteger("fila"));
            a1.setNumero(d.getInteger("numero"));
            a1.setColumna(d.getString("columna"));
        }
        return asientos;
    }

    @Override
    public Reservacion actualizarPorNumReservacion(Reservacion reservacion) throws PersistenciaException {
      try{     
            var filter = Filters.eq("numReservacion", reservacion.getNumReservacion());
            var updates = reservacion.toUpdateOperations();

            var result = collection.updateOne(filter,updates);
            reservacion = (Reservacion) collection.find(Filters.eq("numReservacion", reservacion.getNumReservacion()));
            
            return reservacion;
        }catch(MongoException ex){
            throw new PersistenciaException("Error al eliminar reservacion "+ ex.getMessage());
        }
    }
    
    @Override
    public Reservacion buscarPorNumReservacion(String numReservacion){
        Reservacion reservacionEncontrada = (Reservacion) collection.find(Filters.eq("numReservacion", numReservacion));
        return reservacionEncontrada;
    }
}
    

