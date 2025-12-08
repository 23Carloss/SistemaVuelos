package administrador;

import BOs.VueloBO;
import DTOs.VueloDTO;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PnlVuelosProgramados extends JPanel {

    Style style = new Style();

    PnlMenuAdmin pnlMenuAdmin;
    PnlCrearVuelo pnlCrearVuelo;
    PnlFiltrarVuelos pnlFiltrarVuelos;

    List<VueloDTO> listaVuelos;

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
    CustomButton btnFiltros = new CustomButton("Filtrar");
    CustomButton btnCrearVuelo = new CustomButton("Crear vuelo");

    JTable tabla;
    JScrollPane scroll;

    VueloBO bo;

    public PnlVuelosProgramados(PnlMenuAdmin pnlMenuAdmin) {
        
        bo = new VueloBO();
        this.pnlMenuAdmin = pnlMenuAdmin;
        setOpaque(false);

        // El panel maestro debe usar BoxLayout para que se respeten las alturas fijas
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
        encabezado.add(new CustomLabel("Vuelos Programados", 36));

        todo.add(encabezado);

        // ========== TABLA ============================================
        // Fijamos una altura permanente para la tabla (sin empujar botones)
        int ALTURA_TABLA_FIX = 500;

        vuelos = new ContainerPanel(style.frameX, ALTURA_TABLA_FIX, style.beigeBase, false);
        vuelos.setLayout(new BorderLayout());
        vuelos.setOpaque(false);

        vuelos.setPreferredSize(new Dimension(style.frameX, ALTURA_TABLA_FIX));
        vuelos.setMaximumSize(new Dimension(style.frameX, ALTURA_TABLA_FIX));

        cargarVuelosDemo();
        agregarTablaVuelosConAltura(ALTURA_TABLA_FIX);

        todo.add(vuelos);

        // ========== BOTONES ==========================================
        botones.setOpaque(false);
        botones.setLayout(new BorderLayout());

        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftBtns.setOpaque(false);
        leftBtns.add(btnVolver);

        JPanel rightBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        rightBtns.setOpaque(false);
        rightBtns.add(btnFiltros);
        rightBtns.add(btnCrearVuelo);

        botones.add(leftBtns, BorderLayout.WEST);
        botones.add(rightBtns, BorderLayout.EAST);

        botones.setPreferredSize(new Dimension(style.frameX, BOTONES_H));
        botones.setMaximumSize(new Dimension(style.frameX, BOTONES_H));

        todo.add(botones);

        // ========== EVENTOS ===========================================
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });
        btnFiltros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filtros();
            }
        });
        btnCrearVuelo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                crearVuelo();
            }
        });

        add(todo, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void cargarVuelosDemo() {
        try {
            listaVuelos =  bo.obtenerTodos();
            System.out.println(listaVuelos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar los vuelos");
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

        // Tamaño fijo para que NO empuje nada
        scroll.setPreferredSize(new Dimension(style.frameX, altura - 10));
        scroll.setMaximumSize(new Dimension(style.frameX, altura - 10));

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    DlgDetallesVueloAdmin dlgDetallesVuelo
                            = new DlgDetallesVueloAdmin(listaVuelos.get(fila));
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
        pnlMenuAdmin.remove(this);
        pnlMenuAdmin.mostrarComponentes();
    }

    public void filtros() {
        this.setVisible(false);
        pnlFiltrarVuelos = new PnlFiltrarVuelos(pnlMenuAdmin, this);
        pnlMenuAdmin.add(pnlFiltrarVuelos);
    }

    public void crearVuelo() {
        this.setVisible(false);
        pnlCrearVuelo = new PnlCrearVuelo(pnlMenuAdmin, this);
        pnlMenuAdmin.add(pnlCrearVuelo);
    }
    
    public void aplicarFiltros(List<VueloDTO> vuelos){
        if (vuelos ==null) {
            
        }
        listaVuelos=vuelos;
        refreshTabla();
    }
    
    public void resetearFiltros(){
        try {
        listaVuelos= bo.obtenerTodos();
        } catch (Exception e) {
        }
        refreshTabla();
    }
    
    
}



/*
//-70, 67, -742 coordenadas itzapalapa
package administrador;

import DTOs.VueloDTO;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PnlVuelosProgramados extends JPanel {

    Style style = new Style();

    PnlMenuAdmin pnlMenuAdmin;
    PnlCrearVuelo pnlCrearVuelo;
    PnlFiltrarVuelos pnlFiltrarVuelos;

    ArrayList<VueloDTO> listaVuelos = new ArrayList<>();

    // Tamaños/espaciado
    int logoX = 30;
    int logoY = 30;

    // Alturas previsibles (ajustables)
    final int ENCABEZADO_H = 80;
    final int BOTONES_H = 80;
    final int MARGIN_EXTRA = 20; // margen extra para evitar clipping

    // Rutas
    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";

    // Panels
    ContainerPanel encabezado = new ContainerPanel(style.frameX, ENCABEZADO_H, style.beigeBase, false);
    // Vuelos será creado dinámicamente más abajo con la altura calculada
    ContainerPanel vuelos;
    ContainerPanel botones = new ContainerPanel(style.frameX, BOTONES_H, style.beigeBase, false);
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, style.beigeBase, false);

    JLabel logo;

    CustomButton btnVolver = new CustomButton("Volver");
    CustomButton btnFiltros = new CustomButton("Filtrar");
    CustomButton btnCrearVuelo = new CustomButton("Crear vuelo");

    JTable tabla;
    JScrollPane scroll;

    public PnlVuelosProgramados(PnlMenuAdmin pnlMenuAdmin) {

        this.pnlMenuAdmin = pnlMenuAdmin;
        setOpaque(false);
        setLayout(new BorderLayout());
        setSize(style.frameX, style.frameY);

        todo.setLayout(new BorderLayout());

        // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        // ENCABEZADO
        // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        encabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        encabezado.setOpaque(false);

        ImageIcon icon = new ImageIcon(rutaLogo);
        Image img = icon.getImage().getScaledInstance(logoX, logoY, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        logo = new JLabel(icon);
        logo.setPreferredSize(new Dimension(logoX, logoY));

        encabezado.add(logo);
        encabezado.add(new CustomLabel("Vuelos Programados", 36));

        todo.add(encabezado, BorderLayout.NORTH);


        // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        // BOTONES (creo antes para calcular espacio)
        // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        botones.setOpaque(false);
        botones.setLayout(new BorderLayout());

        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftBtns.setOpaque(false);
        leftBtns.add(btnVolver);

        JPanel rightBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        rightBtns.setOpaque(false);
        rightBtns.add(btnFiltros);
        rightBtns.add(btnCrearVuelo);

        botones.add(leftBtns, BorderLayout.WEST);
        botones.add(rightBtns, BorderLayout.EAST);

        // Forzamos tamaño mínimo y preferido para que no se encoja y siempre sea visible
        botones.setPreferredSize(new Dimension(style.frameX, BOTONES_H));
        botones.setMinimumSize(new Dimension(style.frameX, BOTONES_H));
        botones.setMaximumSize(new Dimension(Integer.MAX_VALUE, BOTONES_H));

        todo.add(botones, BorderLayout.SOUTH);


        // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        // TABLA — calculo dinámico de altura y creación del panel 'vuelos'
        // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        listaVuelos.add(new VueloDTO(2500, "Vuelo MX101", "Ciudad Obregón", "CDMX",
                LocalDateTime.parse("2025-12-01 08:30", f), 120, "Volaris"));
        listaVuelos.add(new VueloDTO(3100, "Vuelo MX202", "Hermosillo", "Guadalajara",
                LocalDateTime.parse("2025-12-02 13:50", f), 90, "VivaAerobus"));
        listaVuelos.add(new VueloDTO(1800, "Vuelo MX303", "Tijuana", "Monterrey",
                LocalDateTime.parse("2025-12-05 17:20", f), 120, "Aeroméxico"));

        // calcular altura disponible para 'vuelos' en el layout inicial
        int alturaDisponible = style.frameY - ENCABEZADO_H - BOTONES_H - MARGIN_EXTRA;
        //int alturaDisponible = 300;
        if (alturaDisponible < 100) alturaDisponible = 100; // mínimo razonable

        vuelos = new ContainerPanel(style.frameX, alturaDisponible, style.beigeBase, false);
        vuelos.setLayout(new BorderLayout());
        vuelos.setOpaque(false);

        agregarTablaVuelosConAltura(alturaDisponible);

        todo.add(vuelos, BorderLayout.CENTER);


        // Eventos botones
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });
        btnFiltros.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                filtros();
            }
        });
        btnCrearVuelo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                crearVuelo();
            }
        });

        add(todo, BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // TABLA (NO EDITABLE) - creación con altura para evitar que coma espacio a los botones
    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    private void agregarTablaVuelosConAltura(int altura) {

        String[] columnas = {"Nombre", "Aerolínea", "Origen", "Destino",
                "Salida", "Duración", "Precio"};

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
            public boolean isCellEditable(int row, int column) {
                return false; // NO EDITABLE
            }
        });

        tabla.setOpaque(false);
        tabla.setShowGrid(false);
        tabla.setBorder(null);
        tabla.setFillsViewportHeight(true); // que use la altura del viewport

        scroll = new JScrollPane(tabla);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);

        // forzamos preferido del scroll para que ocupe la altura calculada (y no haga que botones queden fuera)
        scroll.setPreferredSize(new Dimension(style.frameX, altura));
        scroll.setMaximumSize(new Dimension(style.frameX, altura));
        scroll.setMinimumSize(new Dimension(style.frameX, 80));

        // Listener para clicks
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    DlgDetallesVueloAdmin dlgDetallesVuelo = new DlgDetallesVueloAdmin(listaVuelos.get(fila));
                    dlgDetallesVuelo.setVisible(true);
                }
            }
        });

        vuelos.removeAll();
        vuelos.add(scroll, BorderLayout.CENTER);
        vuelos.revalidate();
        vuelos.repaint();
    }


    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // REFRESH — recalcula tamaño y regenera la tabla (útil si cambia frameY o lista)
    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void refreshTabla() {
        // recalculamos altura actual disponible por si la ventana cambió
        int alturaDisponible = style.frameY - ENCABEZADO_H - BOTONES_H - MARGIN_EXTRA;
        if (alturaDisponible < 100) alturaDisponible = 100;
        vuelos.setPreferredSize(new Dimension(style.frameX, alturaDisponible));
        agregarTablaVuelosConAltura(alturaDisponible);
        revalidate();
        repaint();
    }


    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    // Métodos de botones
    // ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    public void volver() {
        pnlMenuAdmin.remove(this);
        pnlMenuAdmin.mostrarComponentes();
    }

    public void filtros() {
        this.setVisible(false);
        pnlFiltrarVuelos = new PnlFiltrarVuelos(pnlMenuAdmin, this);
        pnlMenuAdmin.add(pnlFiltrarVuelos);
    }

    public void crearVuelo() {
        this.setVisible(false);
        pnlCrearVuelo = new PnlCrearVuelo(pnlMenuAdmin, this);
        pnlMenuAdmin.add(pnlCrearVuelo);
    }
}
*/
