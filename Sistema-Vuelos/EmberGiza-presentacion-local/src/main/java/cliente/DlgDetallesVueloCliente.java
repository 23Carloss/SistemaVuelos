package cliente;

import Aplicacion.Control;
import DTOs.ReservacionDTO;
import DTOs.VueloDTO;
import styles.CustomButton;
import styles.CustomLabel;
import styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesVueloCliente extends JDialog {
    private Control control;
    private ReservacionDTO reservacion;
    Style style = new Style();
    VueloDTO vuelo;
    CustomButton btnAccion;

    public DlgDetallesVueloCliente(VueloDTO vuelo, int tipo, Control control) {
        this.control = control;
        this.vuelo = vuelo;

        //Setup
        setLocationRelativeTo(null);
        Dimension dimension = new Dimension(500, 300);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.beigeBase);
        JPanel todo = new JPanel();
        todo.setLayout(new BorderLayout());
        todo.setOpaque(false);
        setContentPane(todo);

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);


        //Elementos
        System.out.println("vuelo: " + vuelo.toString());
        System.out.println(vuelo.getNumVuelo());
        todo.add(new CustomLabel("Detalles del vuelo " + vuelo.getNumVuelo(), style.letraSize + 10), BorderLayout.NORTH);
        contenido.add(new CustomLabel("Origen: " + vuelo.getOrigen()));
        contenido.add(new CustomLabel("Destino: " + vuelo.getDestino()));
        contenido.add(new CustomLabel("Fecha y hora: " + vuelo.getFechaSalida()));
        contenido.add(new CustomLabel("Duración: " + vuelo.getDuracion()));
        contenido.add(new CustomLabel("Aerolínea: " + vuelo.getAerolinea()));
        contenido.add(new CustomLabel("Precio: $" + vuelo.getPrecio()));
        todo.add(contenido, BorderLayout.CENTER);

        //Selección de lo que hace el botón
        switch (tipo) {
            case 1: //Seleccionar contenedorAsientos
                btnAccion = new CustomButton("Seleccionar asientos");
                btnAccion.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        seleccionarAsientos();
                    }
                });
                break;

            case 2: //Cancelar reservación
                btnAccion = new CustomButton("Cancelar reservación");
                btnAccion.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelarReservacion();
                    }
                });
                break;

            case 3: //Editar detalles de vuelo
                btnAccion = new CustomButton("Editar detalles de vuelo");
                btnAccion.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //editar detalles
                        System.out.println("Haz de cuenta que lo vamos a editar");
                    }
                });
                break;
            default:
                System.out.println("Nota: tipo de botón/dialog inválido, corregir");
        }
        todo.add(btnAccion, BorderLayout.SOUTH);

    }
    
    public DlgDetallesVueloCliente(VueloDTO vuelo, int tipo, ReservacionDTO reservacion, Control control) {
        this.control = control;
        this.reservacion = reservacion;
        this.vuelo = vuelo;

        //Setup
        setLocationRelativeTo(null);
        Dimension dimension = new Dimension(500, 300);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.beigeBase);
        JPanel todo = new JPanel();
        todo.setLayout(new BorderLayout());
        todo.setOpaque(false);
        setContentPane(todo);

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);


        //Elementos
        System.out.println("vuelo: " + vuelo.toString());
        System.out.println(vuelo.getNumVuelo());
        todo.add(new CustomLabel("Detalles del vuelo " + vuelo.getNumVuelo(), style.letraSize + 10), BorderLayout.NORTH);
        contenido.add(new CustomLabel("Origen: " + vuelo.getOrigen()));
        contenido.add(new CustomLabel("Destino: " + vuelo.getDestino()));
        contenido.add(new CustomLabel("Fecha y hora: " + vuelo.getFechaSalida()));
        contenido.add(new CustomLabel("Duración: " + vuelo.getDuracion()));
        contenido.add(new CustomLabel("Aerolínea: " + vuelo.getAerolinea()));
        contenido.add(new CustomLabel("Precio: $" + vuelo.getPrecio()));
        todo.add(contenido, BorderLayout.CENTER);

        //Selección de lo que hace el botón
        switch (tipo) {
            case 1: //Seleccionar contenedorAsientos
                btnAccion = new CustomButton("Seleccionar asientos");
                btnAccion.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        seleccionarAsientos();
                    }
                });
                break;

            case 2: //Cancelar reservación
                btnAccion = new CustomButton("Cancelar reservación");
                btnAccion.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelarReservacion();
                    }
                });
                break;

            case 3: //Editar detalles de vuelo
                btnAccion = new CustomButton("Editar detalles de vuelo");
                btnAccion.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //editar detalles
                        System.out.println("Haz de cuenta que lo vamos a editar");
                    }
                });
                break;
            default:
                System.out.println("Nota: tipo de botón/dialog inválido, corregir");
        }
        todo.add(btnAccion, BorderLayout.SOUTH);

    }

    public void seleccionarAsientos() {
        DlgSeleccionarAsientos dlgSeleccionarAsientos = new DlgSeleccionarAsientos(vuelo, control);
        dlgSeleccionarAsientos.setVisible(true);
        this.dispose();
    }

    public void cancelarReservacion() {
        System.out.println("Haz de cuenta que se canceló");
        //-----LÓGICA AQUÍ-----
        //aplicar cancelación del vuelo
        
        control.eliminarReservacion(reservacion);
        this.dispose();
    }
}
