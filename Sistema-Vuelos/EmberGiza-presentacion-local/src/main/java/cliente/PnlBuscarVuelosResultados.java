package cliente;

import Aplicacion.Control;
import BOs.VueloBO;
import DTOs.VueloDTO;
 import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PnlBuscarVuelosResultados extends JPanel {
    private Control control;
    Style style = new Style();
    boolean testeoColor = false;

    PnlMenuCliente pnlMenuCliente;
    PnlBuscarVuelos pnlBuscarVuelos;

    List<VueloDTO> listaVuelos = new ArrayList<>();


    int logoX = 30;
    int logoY = 30;

    final int ENCABEZADO_H = 80;
    final int BOTONES_H = 80;

    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";

    ContainerPanel encabezado = new ContainerPanel(style.frameX, ENCABEZADO_H, style.beigeBase, false);
    ContainerPanel vuelos;
    ContainerPanel botones = new ContainerPanel(style.frameX, BOTONES_H, style.beigeBase, false);
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, style.beigeBase, false);

    JLabel logo;

    CustomButton btnVolver = new CustomButton("Volver");

    JTable tabla;
    JScrollPane scroll;
    
    VueloBO bo;
    
    String origen, destino;
    Date salida;
    
    public PnlBuscarVuelosResultados(PnlMenuCliente pnlMenuCliente, PnlBuscarVuelos pnlBuscarVuelos, String origen,String destino, Date salida, Control control) {
        this.control = control;
        this.origen= origen;
        this.destino= destino;
        this.salida= salida;
        
        bo= new VueloBO();
                
        this.pnlMenuCliente = pnlMenuCliente;
        this.pnlBuscarVuelos = pnlBuscarVuelos;

        setOpaque(false);
        setLayout(new BorderLayout());

        todo.setOpaque(false);
        todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));

        // ========== ENCABEZADO ======================================
        encabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        encabezado.setOpaque(false);
        encabezado.setPreferredSize(new Dimension(style.frameX, ENCABEZADO_H));
        encabezado.setMaximumSize(new Dimension(style.frameX, ENCABEZADO_H));

        ImageIcon icon = new ImageIcon(rutaLogo);
        Image img = icon.getImage().getScaledInstance(logoX, logoY, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        logo = new JLabel(icon);
        logo.setPreferredSize(new Dimension(logoX, logoY));

        encabezado.add(logo);
        encabezado.add(new CustomLabel("Buscar vuelos - Resultados", 36));
        todo.add(encabezado);

        // ========== TABLA ============================================
        int ALTURA_TABLA_FIX = 500;
        vuelos = new ContainerPanel(style.frameX, ALTURA_TABLA_FIX, style.beigeBase, false);
        vuelos.setLayout(new BorderLayout());
        vuelos.setOpaque(false);
        vuelos.setPreferredSize(new Dimension(style.frameX, ALTURA_TABLA_FIX));
        vuelos.setMaximumSize(new Dimension(style.frameX, ALTURA_TABLA_FIX));

        cargarVuelosFiltro();
        agregarTablaVuelosConAltura(ALTURA_TABLA_FIX);

        todo.add(vuelos);

        // ========== BOTONES ==========================================
        botones.setOpaque(false);
        botones.setLayout(new BorderLayout());

        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftBtns.setOpaque(false);
        leftBtns.add(btnVolver);

        botones.add(leftBtns, BorderLayout.WEST);

        botones.setPreferredSize(new Dimension(style.frameX, BOTONES_H));
        botones.setMaximumSize(new Dimension(style.frameX, BOTONES_H));
        todo.add(botones);

        // ========== EVENTOS ==========================================
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

    private void cargarVuelos(){
        listaVuelos = control.cargarVuelos();
    }
    private void cargarVuelosFiltro() {
        try {
            cargarVuelos();
            if(origen!= null || destino != null || salida != null){
                listaVuelos= bo.getBuscarVuelos(origen, destino, salida);
                System.out.println(listaVuelos);
            }
             
            
        } catch (Exception e) {
            System.out.println(e);
//            JOptionPane.showMessageDialog(null, "No se pudo cargar los vuelos con origen, destino y salida: "+origen+", "+destino+", "+salida);
            cargarVuelos();//Si tira la exception significa que no hau vuelos con esos atributos entonces cargamos todos o podiras buscar por destino
//            listaVuelos = bo.filtrarVuelos(destino);
        }
    }

    private void agregarTablaVuelosConAltura(int altura) {

        String[] columnas = {"Nombre", "Aerolínea", "Origen", "Destino",
                "Salida", "Duración", "Precio"};

        Object[][] datos = new Object[listaVuelos.size()][columnas.length];

        for (int i = 0; i < listaVuelos.size(); i++) {
            VueloDTO v = listaVuelos.get(i);
            datos[i][0] = v.getNumVuelo();
            datos[i][1] = v.getAerolinea();
            datos[i][2] = v.getOrigen();
            datos[i][3] = v.getDestino();
            datos[i][4] = v.getFechaSalida();
            datos[i][5] = v.getDuracion();
            datos[i][6] = "$" + v.getPrecio();
        }

        tabla = new JTable(new javax.swing.table.DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
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
        scroll.setPreferredSize(new Dimension(style.frameX, altura - 10));
        scroll.setMaximumSize(new Dimension(style.frameX, altura - 10));

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    DlgDetallesVueloCliente dlgDetallesVuelo =
                            new DlgDetallesVueloCliente(listaVuelos.get(fila), 1, control);
                    dlgDetallesVuelo.setVisible(true);
                }
            }
        });

        vuelos.removeAll();
        vuelos.add(scroll, BorderLayout.CENTER);
        vuelos.revalidate();
        vuelos.repaint();
    }

    public void refreshTabla() {
        int ALTURA_TABLA_FIX = 460;
        vuelos.setPreferredSize(new Dimension(style.frameX, ALTURA_TABLA_FIX));
        vuelos.setMaximumSize(new Dimension(style.frameX, ALTURA_TABLA_FIX));
        agregarTablaVuelosConAltura(ALTURA_TABLA_FIX);
        revalidate();
        repaint();
    }

    public void volver() {
        pnlMenuCliente.remove(this);
        pnlBuscarVuelos.setVisible(true);
    }
}


/*
package cliente;

import DTOs.VueloDTO;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PnlBuscarVuelosResultados extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;
    PnlMenuCliente pnlMenuCliente;
    PnlBuscarVuelos pnlBuscarVuelos;

    //-----LÓGICA AQUÍ-----
    //Placeholder ?
    ArrayList<VueloDTO> listaVuelos = new ArrayList<>();


    //Ajustes de tamaño
    int logoX = 30;
    int logoY = logoX;


    //-----LÓGICA AQUÍ-----
    //Poner la ruta donde está guardado el logo de EG
    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";


    //::::::::::::::::::::::::::::::ESTÉTICA::::::::::::::::::::::::::::::
    //Encabezado
    ContainerPanel encabezado = new ContainerPanel(style.frameX, 80, Color.CYAN, testeoColor);
    JLabel logo;
    //Contenido
    ContainerPanel vuelos = new ContainerPanel(800, 400, Color.MAGENTA, testeoColor);

    //Botones
    ContainerPanel botones = new ContainerPanel(style.frameX, 100, Color.PINK, testeoColor);
    CustomButton btnVolver = new CustomButton("Volver");
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.GREEN, testeoColor);


    public PnlBuscarVuelosResultados(PnlMenuCliente pnlMenuCliente, PnlBuscarVuelos pnlBuscarVuelos ) {

        //Setteo del panel
        this.pnlMenuCliente = pnlMenuCliente;
        this.pnlBuscarVuelos = pnlBuscarVuelos;
        setOpaque(false);
        setSize(style.frameX, style.frameY);
        todo.setLayout(new BorderLayout());

        //Encabezado
        encabezado.setLayout(new GridLayout(1,4));
            //Logo
        ImageIcon icon = new ImageIcon(rutaLogo);
        Image img = icon.getImage().getScaledInstance(logoX, logoY, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        logo = new JLabel(icon);
        logo.setIcon(new ImageIcon(img));
        logo.setPreferredSize(new Dimension(logoX, logoY));
        encabezado.add(logo);
            //Empresa
        encabezado.add(new CustomLabel(" Buscar vuelos - resultados", 36));
        todo.add(encabezado, BorderLayout.NORTH);

        //Contenido
            //-----Placeholders de ejemplo -----
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        listaVuelos.add(new VueloDTO(2500, "Vuelo MX101", "Ciudad Obregón", "CDMX", LocalDateTime.parse("2025-12-01 08:30", f), 120, "Volaris"));
        listaVuelos.add(new VueloDTO(3100, "Vuelo MX202", "Hermosillo", "Guadalajara", LocalDateTime.parse("2025-12-02 13:50", f), 90, "VivaAerobus"));
        listaVuelos.add(new VueloDTO(1800, "Vuelo MX303", "Tijuana", "Monterrey", LocalDateTime.parse("2025-12-05 17:20", f) , 120, "Aeroméxico"));
            //Tabla
        mostrarTablaVuelos();
        todo.add(vuelos, BorderLayout.CENTER);

        //agregar tabla con lo vuelos aqui
        todo.add(vuelos, BorderLayout.CENTER);

        //Botones
        botones.setLayout(new BorderLayout());
        botones.add(btnVolver, BorderLayout.WEST);
        todo.add(botones, BorderLayout.SOUTH);
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });


        add(todo);
        repaint();
        revalidate();
        setVisible(true);
    }

    //Métodos de los botones
    public void volver() {
        pnlMenuCliente.remove(this);
        pnlBuscarVuelos.setVisible(true);
    }


    private void mostrarTablaVuelos() {

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

        JTable tabla = new JTable(datos, columnas);
        JScrollPane scroll = new JScrollPane(tabla);

        // Listener para clicks
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    DlgDetallesVueloCliente dlgDetallesVuelo = new DlgDetallesVueloCliente(listaVuelos.get(fila), 1);
                    dlgDetallesVuelo.setVisible(true);
                }
            }
        });

        vuelos.setLayout(new BorderLayout());
        vuelos.add(scroll, BorderLayout.CENTER);
        vuelos.setOpaque(false);
        vuelos.revalidate();
        vuelos.repaint();
    }

}
*/