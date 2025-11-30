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
public class ReservacionDAO extends CRUD<Reservacion> implements IReservacionDAO {
    
    private final MongoCollection<Document> colDoc;
    private final MongoCollection<Usuario> colUs;
    
    public ReservacionDAO() {
        super(MongoClientProvider.INSTANCE.database(),"Reservacion", Reservacion.class);
        this.colDoc = MongoClientProvider.INSTANCE.getCollection("Reservacion", Document.class);
        this.colUs = MongoClientProvider.INSTANCE.getCollection("Usuarios", Usuario.class);
    }
    
    @Override
    public List<Reservacion> obtenerReservacionesPorUsuario(Usuario us) throws PersistenciaException{
        try{
            Usuario usMongo = colUs.find(Filters.eq("correo", us.getCorreo())).first();
            System.out.println("Objeto encontrdo : " + usMongo);
            System.out.println("us q llega a reservacionDAo" + usMongo);
            List<Document> pipeLine = List.of(
                    new Document("$match", new Document("_id", usMongo.get_id())),
                    new Document("$lookup", 
                            new Document("from", "Usuarios")
                            .append("localField", "idUsuario")
                            .append("foreignField", "_id")
                            .append("as", "Mis reservaciones")
                    
                    )
            );
            List<Document> docs = colDoc.aggregate(pipeLine).into(new ArrayList<>());

            List<Reservacion> reservaciones = new ArrayList<>();
            
            for(Document doc : docs) {
                Reservacion r1 = new Reservacion();
                r1.setAsientos(doc.getList("listaAsientos", Asiento.class));
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
                r1.setIdUsuario(doc.getObjectId("idUsuario"));
                r1.setVuelo(doc.get("vuelo", Vuelo.class));
                reservaciones.add(r1);
            }
            return reservaciones;
        }catch(MongoException ex){
            throw new PersistenciaException("Error al consultar reservaciones");
        }
       
    }
}
