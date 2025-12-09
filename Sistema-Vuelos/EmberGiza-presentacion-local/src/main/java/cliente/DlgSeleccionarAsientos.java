package cliente;

import Aplicacion.Control;
import DTOs.AsientoDTO;
import DTOs.ReservacionDTO;
import DTOs.UsuarioDTO;
import DTOs.VueloDTO;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DlgSeleccionarAsientos extends JDialog {
    private Control control;
    Style style = new Style();
    boolean testeoColor = false;
    VueloDTO vuelo;
    private UsuarioDTO usuario;
    ReservacionDTO reservacion;

    ToggleButton asientos[] = new ToggleButton[style.cantidadAsientos];
    ArrayList<AsientoDTO> seleccion = new ArrayList<>();

    //Estética
    ContainerPanel contenedorAsientos = new ContainerPanel(style.frameX, 600, Color.PINK, testeoColor);
    ContainerPanel botones = new ContainerPanel(style.frameX, 60, Color.PINK, testeoColor);
    CustomButton btnVolver = new CustomButton("Volver");
    CustomButton btnComprar = new CustomButton("Proceder al pago", 1, 220, 60);

    public DlgSeleccionarAsientos(VueloDTO vuelo,Control control) {
        this.control =control;
        this.usuario = control.getUsuario();        this.reservacion = new ReservacionDTO();
        this.vuelo = vuelo;

        //Setup
        setLocationRelativeTo(null);
        Dimension dimension = new Dimension(style.dialogY, style.dialogX);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.beigeBase);

        JPanel contenido = new JPanel();
        contenido.setLayout(new BorderLayout());
        contenido.setOpaque(false);
        setContentPane(contenido);

        //Encabezado
        add(new CustomLabel("Seleccionar asientos - Vuelo " + vuelo.getNumVuelo()), BorderLayout.NORTH);

        // MATRIZ 11×8 (fila 0 = encabezado)
        contenedorAsientos.setLayout(new GridLayout(11, 8));

        // Encabezados
        contenedorAsientos.add(new CustomLabel(""));
        contenedorAsientos.add(new CustomLabel("A"));
        contenedorAsientos.add(new CustomLabel("B"));
        contenedorAsientos.add(new CustomLabel("C"));
        contenedorAsientos.add(new CustomLabel(""));
        contenedorAsientos.add(new CustomLabel("D"));
        contenedorAsientos.add(new CustomLabel("E"));
        contenedorAsientos.add(new CustomLabel("F"));

        // --- GENERAR LA MATRIZ DE ASIENTOS ---
        // Suponiendo 10 filas (1–10)
        // Columnas A–F

        String columnas[] = {"A", "B", "C", "D", "E", "F"};

        int indexAsiento = 0;

        for (int fila = 1; fila <= 10; fila++) {

            // Primera celda de cada fila → número de fila
            contenedorAsientos.add(new CustomLabel("" + fila));

            for (String col : columnas) {

                // Espacio entre C y D
                if (col.equals("D")) {
                    contenedorAsientos.add(new CustomLabel(""));
                }

                AsientoDTO dto = vuelo.getAsiento(indexAsiento);

                ToggleButton btn =
                        new ToggleButton(dto.isDisponibilidad(), dto.getColumna(), dto.getFila());

                asientos[indexAsiento] = btn;
                contenedorAsientos.add(btn);

                indexAsiento++;
            }
        }

        add(contenedorAsientos, BorderLayout.CENTER);

        //Botones
        botones.setLayout(new BorderLayout());
        botones.add(btnVolver, BorderLayout.WEST);
        botones.add(btnComprar, BorderLayout.EAST);
        add(botones, BorderLayout.SOUTH);

        btnVolver.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { volver(); }
        });
        btnComprar.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { comprar(); }
        });
    }

    public void volver() {
        this.dispose();
    }
    public void crearReservacionDTO(List<AsientoDTO> asientos){
        for (AsientoDTO asientoDTO : asientos) {
            reservacion.setAsiento(asientoDTO);
            reservacion.setCoreoUsuario(usuario.getCorreo());
            reservacion.setFechaReservacion(vuelo.getFechaSalida());
            reservacion.setVuelo(vuelo);
            control.crearrReservacion(reservacion);
            System.out.println("Reservacion creada: " + reservacion);
        }
        
    }

    
    public void comprar() {
        if(asientos == null)JOptionPane.showMessageDialog(null, "Seleccione asientos.");
        
        for (int i = 0; i < asientos.length; i++) {
            if (asientos[i].isSeleccionado()) {
                AsientoDTO asiento = new AsientoDTO(
                        asientos[i].getColumna(),
                        asientos[i].getFila(),
                        true
                );   
                seleccion.add(asiento);
            }
        }
        crearReservacionDTO(seleccion);
        JOptionPane.showMessageDialog(null, "Reservación realizada.");

        //PRINT TEST
        System.out.println("Asientos seleccionados: ");
        for (AsientoDTO a : seleccion) {
            System.out.println(a);
        }

        this.dispose();
    }
}
