package cliente;

import DTOs.UsuarioDTO;
import login.PnlLogin;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlMenuCliente extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;

    //Páneles
    PnlLogin login;
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.PINK, testeoColor);
    PnlBuscarVuelos pnlBuscarVuelo;
    PnlMisReservaciones pnlMisVuelos;
        //DTO
    UsuarioDTO usuario;

    //Ajustes de tamaño
    int logoX = 50;
    int logoY = logoX;
        //espacios
    int espacioSuperior = 60;
    int espacioEntreTextoYBotones = 40;
    int espacioAntesCerrar = 100;
    int espacioEntreBotones = 100;

    //Rutas
    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";
    String rutaBuscarVuelos = null;
    String rutaMisReservaciones = null;

    //------------------ ESTÉTICA ------------------
    ContainerPanel encabezado = new ContainerPanel(style.frameX, 100, Color.ORANGE, testeoColor);

    JLabel logo;
    JLabel lblEmpresa = new CustomLabel("EMBER GIZA ®", 36); // más pequeño
    JLabel lblBienvenida = new CustomLabel("¡Bienvenid@!", 52); // más pequeño

    ContainerPanel botones = new ContainerPanel(style.frameX, 200, Color.RED, testeoColor);
    ImageButton btnBuscarVuelos = new ImageButton("Buscar vuelos", rutaBuscarVuelos);
    ImageButton btnMisReservaciones = new ImageButton("Mis reservaciones", rutaMisReservaciones);

    // Cerrar sesión
    ContainerPanel pnlCerrar = new ContainerPanel(style.frameX, 80, Color.PINK, testeoColor);
    CustomButton btnCerrarSesion = new CustomButton("Cerrar sesión");


    public PnlMenuCliente(PnlLogin login, UsuarioDTO usuario) {

        this.usuario = usuario;
        this.login = login;

        setOpaque(false);
        setSize(style.frameX, style.frameY);

        todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));
        todo.setAlignmentX(CENTER_ALIGNMENT);

        //--------------- ENCABEZADO (100px) ----------------
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.X_AXIS));

        Image img = new ImageIcon(rutaLogo).getImage().getScaledInstance(logoX, logoY, Image.SCALE_SMOOTH);
        logo = new JLabel(new ImageIcon(img));

        encabezado.add(Box.createHorizontalGlue());
        encabezado.add(logo);
        encabezado.add(Box.createRigidArea(new Dimension(20, 0)));
        encabezado.add(lblEmpresa);
        encabezado.add(Box.createHorizontalGlue());

        //--------------- BIENVENIDA ----------------
        lblBienvenida.setAlignmentX(CENTER_ALIGNMENT);

        //--------------- BOTONES (200px) ----------------
        botones.setLayout(new BoxLayout(botones, BoxLayout.X_AXIS));

        botones.add(Box.createHorizontalGlue());
        botones.add(btnBuscarVuelos);
        botones.add(Box.createRigidArea(new Dimension(espacioEntreBotones, 0)));
        botones.add(btnMisReservaciones);
        botones.add(Box.createHorizontalGlue());

        //--------------- CERRAR SESIÓN (80px) ----------------
        pnlCerrar.setLayout(new BoxLayout(pnlCerrar, BoxLayout.X_AXIS));

        btnCerrarSesion.setPreferredSize(new Dimension(220, 45));
        btnCerrarSesion.setMaximumSize(new Dimension(220, 45));

        pnlCerrar.add(Box.createHorizontalGlue());
        pnlCerrar.add(btnCerrarSesion);
        pnlCerrar.add(Box.createHorizontalGlue());

        //----------------- ENSAMBLE CON ALTURAS CONTROLADAS -----------------
        // Total utilizado: 100 (header) + 52 (texto) + 200 (botones) + 80 (cerrar)
        // = 432 px, nos sobran ~ 288 px para centrar verticalmente

        todo.add(Box.createVerticalStrut(espacioSuperior));     // empuja hacia el centro
        todo.add(encabezado);
        todo.add(Box.createVerticalStrut(10));
        todo.add(lblBienvenida);
        todo.add(Box.createVerticalStrut(espacioEntreTextoYBotones));
        todo.add(botones);
        todo.add(Box.createVerticalStrut(espacioAntesCerrar));
        todo.add(pnlCerrar);
        todo.add(Box.createVerticalGlue()); // ajusta automático sin salirte


        //---------------- LISTENERS ----------------
        btnBuscarVuelos.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { tabBuscarVuelos(); }
        });

        btnMisReservaciones.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { tabMisReservaciones(); }
        });

        btnCerrarSesion.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { cerrarSesion(); }
        });

        add(todo);
        repaint();
        revalidate();
    }


    // ----------- MÉTODOS EXISTENTES -----------

    public void esconderComponentes() {
        todo.setVisible(false);
        repaint(); revalidate();
    }

    public void mostrarComponentes() {
        todo.setVisible(true);
        repaint(); revalidate();
    }

    public void tabBuscarVuelos() {
        esconderComponentes();
        pnlBuscarVuelo = new PnlBuscarVuelos(this);
        add(pnlBuscarVuelo);
    }

    public void tabMisReservaciones() {
        esconderComponentes();
        pnlMisVuelos = new PnlMisReservaciones(this, usuario);
        add(pnlMisVuelos);
    }

    public void cerrarSesion() {
        login.volverLogin();
        login.remove(this);
        login.repaint();
    }
}



/*
import DTOs.UsuarioDTO;
import login.PnlLogin;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PnlMenuCliente extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;
        //Páneles
    PnlLogin login;
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.PINK, testeoColor);
    PnlBuscarVuelos pnlBuscarVuelo;
    PnlMisReservaciones pnlMisVuelos;
        //DTO
    UsuarioDTO usuario;

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
    ImageButton btnBuscarVuelos = new ImageButton("Buscar vuelos", rutaBuscarVuelos);
    ImageButton btnMisReservaciones = new ImageButton("Mis reservaciones", rutaMisReservaciones);
    CustomButton btnCerrarSesion = new CustomButton("Cerrar sesión");


    public PnlMenuCliente(PnlLogin login, UsuarioDTO usuario) {

        this.usuario = usuario;
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
        todo.add(new CustomLabel("¡Bienvenid@!", 64));
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
                tabBuscarVuelos();
            }
        });
        btnMisReservaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabMisReservaciones();
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
    public void tabBuscarVuelos() {
        esconderComponentes();
        pnlBuscarVuelo = new PnlBuscarVuelos(this);
        add(pnlBuscarVuelo);
    }

    public void tabMisReservaciones() {
        esconderComponentes();
        pnlMisVuelos = new PnlMisReservaciones(this);
        add(pnlMisVuelos);
    }

    public void cerrarSesion() {
        login.volverLogin();
        login.remove(this);
        login.repaint();
    }

}
*/