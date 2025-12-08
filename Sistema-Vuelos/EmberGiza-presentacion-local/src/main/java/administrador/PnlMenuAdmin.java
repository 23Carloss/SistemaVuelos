
package administrador;

import login.PnlLogin;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlMenuAdmin extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;

    // Login
    PnlLogin login;

    // Panel contenedor principal
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.PINK, testeoColor);

    // Subpantallas
    PnlVuelosProgramados pnlVuelosProgramados;
    PnlReportes pnlReportes;

    // Tamaños
    int logoX = 50;
    int logoY = logoX;

    // Espacios (coinciden con el PnlMenuCliente)
    int espacioSuperior = 60;
    int espacioEntreTextoYBotones = 40;
    int espacioAntesCerrar = 100;
    int espacioEntreBotones = 100;

    // Rutas
    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";
    String rutaAdministrarVuelos = null;
    String rutaReportes = null;

    //------------------ ESTÉTICA ------------------
    ContainerPanel encabezado = new ContainerPanel(style.frameX, 100, Color.ORANGE, testeoColor);

    JLabel logo;
    JLabel lblEmpresa = new CustomLabel("EMBER GIZA ®", 36);
    JLabel lblTitulo = new CustomLabel("Administración", 52);

    ContainerPanel botones = new ContainerPanel(style.frameX, 200, Color.RED, testeoColor);
    ImageButton btnAdministrarVuelos = new ImageButton("Administrar vuelos", rutaAdministrarVuelos);
    ImageButton btnReportes = new ImageButton("Reportes", rutaReportes);

    // Cerrar sesión
    ContainerPanel pnlCerrar = new ContainerPanel(style.frameX, 80, Color.PINK, testeoColor);
    CustomButton btnCerrarSesion = new CustomButton("Cerrar sesión");


    public PnlMenuAdmin(PnlLogin login) {

        this.login = login;

        setOpaque(false);
        setSize(style.frameX, style.frameY);

        todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));
        todo.setAlignmentX(CENTER_ALIGNMENT);

        //---------------- ENCABEZADO ----------------
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.X_AXIS));

        Image img = new ImageIcon(rutaLogo).getImage().getScaledInstance(logoX, logoY, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(img));

        encabezado.add(Box.createHorizontalGlue());
        encabezado.add(logo);
        encabezado.add(Box.createRigidArea(new Dimension(20, 0)));
        encabezado.add(lblEmpresa);
        encabezado.add(Box.createHorizontalGlue());

        //---------------- TÍTULO ----------------
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        //---------------- BOTONES ----------------
        botones.setLayout(new BoxLayout(botones, BoxLayout.X_AXIS));

        // *** Evitar aplastamiento de botones ***
        Dimension btnSize = btnAdministrarVuelos.getPreferredSize();

        btnAdministrarVuelos.setMaximumSize(btnSize);
        btnAdministrarVuelos.setMinimumSize(btnSize);
        btnAdministrarVuelos.setPreferredSize(btnSize);

        btnReportes.setMaximumSize(btnSize);
        btnReportes.setMinimumSize(btnSize);
        btnReportes.setPreferredSize(btnSize);

        botones.add(Box.createHorizontalGlue());
        botones.add(btnAdministrarVuelos);
        botones.add(Box.createRigidArea(new Dimension(espacioEntreBotones, 0)));
        botones.add(btnReportes);
        botones.add(Box.createHorizontalGlue());

        //---------------- CERRAR SESIÓN ----------------
        pnlCerrar.setLayout(new BoxLayout(pnlCerrar, BoxLayout.X_AXIS));

        btnCerrarSesion.setPreferredSize(new Dimension(220, 45));
        btnCerrarSesion.setMaximumSize(new Dimension(220, 45));

        pnlCerrar.add(Box.createHorizontalGlue());
        pnlCerrar.add(btnCerrarSesion);
        pnlCerrar.add(Box.createHorizontalGlue());

        //---------------- ENSAMBLE ----------------
        todo.add(Box.createVerticalStrut(espacioSuperior));
        todo.add(encabezado);
        todo.add(Box.createVerticalStrut(10));
        todo.add(lblTitulo);
        todo.add(Box.createVerticalStrut(espacioEntreTextoYBotones));
        todo.add(botones);
        todo.add(Box.createVerticalStrut(espacioAntesCerrar));
        todo.add(pnlCerrar);
        todo.add(Box.createVerticalGlue());

        //---------------- LISTENERS ----------------
        btnAdministrarVuelos.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { tabAdministrarVuelos(); }
        });

        btnReportes.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { tabReportes(); }
        });

        btnCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { cerrarSesion(); }
        });

        add(todo);
        repaint();
        revalidate();
    }


    // -------- Métodos de navegación --------

    public void esconderComponentes() {
        todo.setVisible(false);
        repaint(); revalidate();
    }

    public void mostrarComponentes() {
        todo.setVisible(true);
        repaint(); revalidate();
    }

    public void tabAdministrarVuelos() {
        esconderComponentes();
        pnlVuelosProgramados = new PnlVuelosProgramados(this);
        add(pnlVuelosProgramados);
    }

    public void tabReportes() {
        esconderComponentes();
        pnlReportes = new PnlReportes(this);
        add(pnlReportes);
    }

    public void cerrarSesion() {
        login.volverLogin(); // igual que en cliente
        login.remove(this);
        login.repaint();
    }
}

/*
package administrador;

import cliente.PnlBuscarVuelos;
import cliente.PnlMisReservaciones;
import login.PnlLogin;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlMenuAdmin extends JPanel {
    Style style = new Style();
    boolean testeoColor = false;
    PnlLogin login;
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.PINK, testeoColor);
    PnlVuelosProgramados pnlVuelosProgramados;
    PnlReportes pnlReportes;

    //Ajustes de tamaño
    int logoX = 50;
    int logoY = logoX;
    int espX = 10;
    int espY = 10;


    //-----LÓGICA AQUÍ-----
    //Poner la ruta donde está guardado el logo de EG
    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";
    //Poner los PNGs correspongientes
    String rutaBuscarVuelos = null;
    String rutaMisReservaciones = null;


    //::::::::::::::::::::::::::::::ESTÉTICA::::::::::::::::::::::::::::::
    ContainerPanel encabezado = new ContainerPanel(style.frameX, 100, Color.ORANGE, testeoColor);
    JLabel logo;
    ContainerPanel botones = new ContainerPanel(style.frameX, 400, Color.RED, testeoColor);
    ImageButton btnBuscarVuelos = new ImageButton("Administrar vuelos", rutaBuscarVuelos);
    ImageButton btnMisReservaciones = new ImageButton("Reportes", rutaMisReservaciones);
    CustomButton btnCerrarSesion = new CustomButton("Cerrar sesión");


    public PnlMenuAdmin(PnlLogin login) {

        //Setteo del panel
        this.login = login;
        setOpaque(false);
        setSize(style.frameX, style.frameY);
        todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));

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
        encabezado.add(new CustomLabel("EMBER GIZA ®", 42));
        todo.add(encabezado);
        //Texto de bienvenida
        todo.add(new CustomLabel("Administración", 64));
        //Botones
        botones.setLayout(new GridLayout(1, 5));
        botones.add(new Espaciador(espX, espY));
        botones.add(btnBuscarVuelos);
        botones.add(new Espaciador(espX, espY));
        botones.add(btnMisReservaciones);
        botones.add(new Espaciador(espX, espY));
        todo.add(botones);

        btnBuscarVuelos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabAdministrarVuelos();
            }
        });
        btnMisReservaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabReportes();
            }
        });

        todo.add(btnCerrarSesion);
        btnCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cerrarSesion();
            }
        });

        add(todo);
        repaint();
        revalidate();
        setVisible(true);
    }




    public void esconderComponentes() {
        todo.setVisible(false);
        repaint();
        revalidate();
    }
    public void mostrarComponentes() {
        todo.setVisible(true);
        repaint();
        revalidate();
    }

    //Métodos de los botones
    public void tabAdministrarVuelos() {
        esconderComponentes();
        pnlVuelosProgramados = new PnlVuelosProgramados(this);
        add(pnlVuelosProgramados);
    }

    public void tabReportes() {
        esconderComponentes();
        pnlReportes = new PnlReportes(this);
        add(pnlReportes);
    }

    public void cerrarSesion() {
        login.remove(this);
        login.repaint();
    }

}
*/