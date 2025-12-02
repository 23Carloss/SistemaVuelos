package cliente;

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

public class PnlMisVuelos extends JPanel {

    Style style = new Style();
    boolean testeoColor = true;
    PnlMenuCliente pnlMenuCliente;

    //-----LÓGICA AQUÍ-----
    //Placeholder ?
    ArrayList<VueloDTO> listaVuelos = new ArrayList<>();


    //Ajustes de tamaño
    int logoX = 30;
    int logoY = logoX;
    /*
    int espX = 10;
    int espY = 10;*/


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


    public PnlMisVuelos(PnlMenuCliente pnlMenuCliente) {

        //Setteo del panel
        this.pnlMenuCliente = pnlMenuCliente;
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
        encabezado.add(new CustomLabel(" Mis vuelos", 36));
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
            //Add
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
        pnlMenuCliente.mostrarComponentes();
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
                    DlgDetallesVueloCliente dlgDetallesVuelo = new DlgDetallesVueloCliente(listaVuelos.get(fila), 2);
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
