package cliente;

import Aplicacion.Control;
import BOs.ReservacionBO;
import DTOs.ReservacionDTO;
import DTOs.UsuarioDTO;
import styles.ContainerPanel;
import styles.CustomButton;
import styles.CustomLabel;
import styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PnlMisReservaciones extends JPanel {
    private Control control;
    Style style = new Style();
    boolean testeoColor = false;

    PnlMenuCliente pnlMenuCliente;
    UsuarioDTO usuario;

    ArrayList<ReservacionDTO> listaReservaciones = new ArrayList<>();

    int logoX = 30;
    int logoY = 30;

    final int ENCABEZADO_H = 80;
    final int TABLA_H = 500;
    final int BOTONES_H = 80;

    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";

    ContainerPanel encabezado = new ContainerPanel(style.frameX, ENCABEZADO_H, style.beigeBase, false);
    ContainerPanel reservacionesPanel;
    ContainerPanel botones = new ContainerPanel(style.frameX, BOTONES_H, style.beigeBase, false);
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, style.beigeBase, false);

    JLabel logo;
    JTable tabla;
    JScrollPane scroll;

    CustomButton btnVolver = new CustomButton("Volver");

    public PnlMisReservaciones(PnlMenuCliente pnlMenuCliente, UsuarioDTO usuario, Control control) {
        this.control = control;
        this.usuario = usuario;
        this.pnlMenuCliente = pnlMenuCliente;

        setOpaque(false);
        setLayout(new BorderLayout());

        todo.setOpaque(false);
        todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));

        // ===================== ENCABEZADO ================================
        encabezado.setOpaque(false);
        encabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        encabezado.setPreferredSize(new Dimension(style.frameX, ENCABEZADO_H));
        encabezado.setMaximumSize(new Dimension(style.frameX, ENCABEZADO_H));

        ImageIcon icon = new ImageIcon(rutaLogo);
        Image img = icon.getImage().getScaledInstance(logoX, logoY, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        logo = new JLabel(icon);
        logo.setPreferredSize(new Dimension(logoX, logoY));

        encabezado.add(logo);
        encabezado.add(new CustomLabel("Mis Reservaciones", 36));

        todo.add(encabezado);

        // ===================== CONTENEDOR TABLA ==========================
        reservacionesPanel = new ContainerPanel(style.frameX, TABLA_H, style.beigeBase, false);
        reservacionesPanel.setOpaque(false);
        reservacionesPanel.setLayout(new BorderLayout());
        reservacionesPanel.setPreferredSize(new Dimension(style.frameX, TABLA_H));
        reservacionesPanel.setMaximumSize(new Dimension(style.frameX, TABLA_H));

        cargarReservacionesUsuario();
        agregarTabla();

        todo.add(reservacionesPanel);

        // ===================== BOTONES ===================================
        botones.setOpaque(false);
        botones.setLayout(new BorderLayout());

        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftBtns.setOpaque(false);
        leftBtns.add(btnVolver);

        botones.add(leftBtns, BorderLayout.WEST);
        botones.setPreferredSize(new Dimension(style.frameX, BOTONES_H));
        botones.setMaximumSize(new Dimension(style.frameX, BOTONES_H));

        todo.add(botones);

        // Eventos
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });

        add(todo, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // ---------------------------------------------------------------------
    private void cargarReservacionesUsuario() {
        listaReservaciones.clear();
        
//        ReservacionBO reservacionBO= new ReservacionBO();
        try {
            List<ReservacionDTO> reservacionesUsuario = control.cargarReservacionesPorUsuario(usuario);
            listaReservaciones.addAll(reservacionesUsuario);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener las reservaciones del usuario");
        }
        
    }

    // ---------------------------------------------------------------------
    private void agregarTabla() {

        String[] columnas = {"Asiento", "Numero del Vuelo", "Aerolínea", "Origen", "Destino", "Salida", "Duración", "Precio"};

        Object[][] datos = new Object[listaReservaciones.size()][columnas.length];
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (int i = 0; i < listaReservaciones.size(); i++) {
            ReservacionDTO r = listaReservaciones.get(i);
            datos[i][0] = (r.getAsiento().getColumna() + r.getAsiento().getFila());
            datos[i][1] = r.getVuelo().getNumVuelo();
            datos[i][2] = r.getVuelo().getAerolinea();
            datos[i][3] = r.getVuelo().getOrigen();
            datos[i][4] = r.getVuelo().getDestino();
            datos[i][5] = r.getVuelo().getFechaSalida().format(f);
            datos[i][6] = r.getVuelo().getDuracion();
            datos[i][7] = "$" + r.getVuelo().getPrecio();
        }

        tabla = new JTable(new javax.swing.table.DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });

        tabla.setOpaque(false);
        tabla.setShowGrid(false);
        tabla.setBorder(null);
        tabla.setFillsViewportHeight(true);

        scroll = new JScrollPane(tabla);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);

        scroll.setPreferredSize(new Dimension(style.frameX, TABLA_H - 10));
        scroll.setMaximumSize(new Dimension(style.frameX, TABLA_H - 10));

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    ReservacionDTO seleccion = listaReservaciones.get(fila);
                    new DlgDetallesVueloCliente(seleccion.getVuelo(), 2, seleccion, control).setVisible(true);
                }
            }
        });

        reservacionesPanel.removeAll();
        reservacionesPanel.add(scroll, BorderLayout.CENTER);

        reservacionesPanel.revalidate();
        reservacionesPanel.repaint();
    }

    // ---------------------------------------------------------------------
    public void volver() {
        pnlMenuCliente.remove(this);
        pnlMenuCliente.mostrarComponentes();
    }

    // ---------------------------------------------------------------------
    public void refreshTabla() {
        cargarReservacionesUsuario();
        agregarTabla();
        revalidate();
        repaint();
    }
}


/*
package cliente;

import DTOs.UsuarioDTO;
import DTOs.VueloDTO;
import styles.ContainerPanel;
import styles.CustomButton;
import styles.CustomLabel;
import styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PnlMisReservaciones extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;

    PnlMenuCliente pnlMenuCliente;
    UsuarioDTO usuario;

    ArrayList<VueloDTO> listaVuelos = new ArrayList<>();

    int logoX = 30;
    int logoY = 30;

    final int ENCABEZADO_H = 80;
    final int TABLA_H = 500;     // Altura fija como en PnlVuelosProgramados
    final int BOTONES_H = 80;

    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";

    ContainerPanel encabezado = new ContainerPanel(style.frameX, ENCABEZADO_H, style.beigeBase, false);
    ContainerPanel vuelos;
    ContainerPanel botones = new ContainerPanel(style.frameX, BOTONES_H, style.beigeBase, false);
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, style.beigeBase, false);

    JLabel logo;
    JTable tabla;
    JScrollPane scroll;

    CustomButton btnVolver = new CustomButton("Volver");

    public PnlMisReservaciones(PnlMenuCliente pnlMenuCliente, UsuarioDTO usuario) {

        this.usuario = usuario;
        this.pnlMenuCliente = pnlMenuCliente;

        setOpaque(false);
        setLayout(new BorderLayout());

        todo.setOpaque(false);
        todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));

        // ===================== ENCABEZADO ================================
        encabezado.setOpaque(false);
        encabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));

        encabezado.setPreferredSize(new Dimension(style.frameX, ENCABEZADO_H));
        encabezado.setMaximumSize(new Dimension(style.frameX, ENCABEZADO_H));

        ImageIcon icon = new ImageIcon(rutaLogo);
        Image img = icon.getImage().getScaledInstance(logoX, logoY, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        logo = new JLabel(icon);
        logo.setPreferredSize(new Dimension(logoX, logoY));

        encabezado.add(logo);
        encabezado.add(new CustomLabel("Mis Reservaciones", 36));

        todo.add(encabezado);

        // ===================== CONTENEDOR TABLA ==========================
        vuelos = new ContainerPanel(style.frameX, TABLA_H, style.beigeBase, false);
        vuelos.setOpaque(false);
        vuelos.setLayout(new BorderLayout());

        vuelos.setPreferredSize(new Dimension(style.frameX, TABLA_H));
        vuelos.setMaximumSize(new Dimension(style.frameX, TABLA_H));

        cargarVuelosDemo();
        agregarTabla();

        todo.add(vuelos);

        // ===================== BOTONES ==================================
        botones.setOpaque(false);
        botones.setLayout(new BorderLayout());

        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftBtns.setOpaque(false);
        leftBtns.add(btnVolver);

        botones.add(leftBtns, BorderLayout.WEST);

        botones.setPreferredSize(new Dimension(style.frameX, BOTONES_H));
        botones.setMaximumSize(new Dimension(style.frameX, BOTONES_H));

        todo.add(botones);

        // Eventos
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });

        add(todo, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // ---------------------------------------------------------------------
    private void cargarVuelosDemo() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        listaVuelos.add(new VueloDTO(2500, "Vuelo MX101", "Ciudad Obregón", "CDMX",
                LocalDateTime.parse("2025-12-01 08:30", f), 120, "Volaris"));

        listaVuelos.add(new VueloDTO(3100, "Vuelo MX202", "Hermosillo", "Guadalajara",
                LocalDateTime.parse("2025-12-02 13:50", f), 90, "VivaAerobus"));

        listaVuelos.add(new VueloDTO(1800, "Vuelo MX303", "Tijuana", "Monterrey",
                LocalDateTime.parse("2025-12-05 17:20", f), 120, "Aeroméxico"));
    }

    // ---------------------------------------------------------------------
    private void agregarTabla() {

        String[] columnas = {"Nombre", "Aerolínea", "Origen", "Destino", "Salida", "Duración", "Precio"};

        Object[][] datos = new Object[listaVuelos.size()][columnas.length];

        for (int i = 0; i < listaVuelos.size(); i++) {
            VueloDTO v = listaVuelos.get(i);
            datos[i][0] = v.getNombre();
            datos[i][1] = v.getAerolinea();
            datos[i][2] = v.getOrigen();
            datos[i][3] = v.getDestino();
            datos[i][4] = v.getFechaSalida();
            datos[i][5] = v.getDuracion();
            datos[i][6] = "$" + v.getPrecio();
        }

        tabla = new JTable(new javax.swing.table.DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        });

        tabla.setOpaque(false);
        tabla.setShowGrid(false);
        tabla.setBorder(null);
        tabla.setFillsViewportHeight(true);

        scroll = new JScrollPane(tabla);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);

        scroll.setPreferredSize(new Dimension(style.frameX, TABLA_H - 10));
        scroll.setMaximumSize(new Dimension(style.frameX, TABLA_H - 10));

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    new DlgDetallesVueloCliente(listaVuelos.get(fila), 2).setVisible(true);
                }
            }
        });

        vuelos.removeAll();
        vuelos.add(scroll, BorderLayout.CENTER);

        vuelos.revalidate();
        vuelos.repaint();
    }

    // ---------------------------------------------------------------------
    public void volver() {
        pnlMenuCliente.remove(this);
        pnlMenuCliente.mostrarComponentes();
    }

    // ---------------------------------------------------------------------
    public void refreshTabla() {
        agregarTabla();
        revalidate();
        repaint();
    }
}
*/