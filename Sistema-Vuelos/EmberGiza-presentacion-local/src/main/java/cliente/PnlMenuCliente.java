package cliente;


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
    PnlMisVuelos pnlMisVuelos;

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


    public PnlMenuCliente(PnlLogin login) {

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
        pnlMisVuelos = new PnlMisVuelos(this);
        add(pnlMisVuelos);
    }

    public void cerrarSesion() {
        login.volverLogin();
        login.remove(this);
        login.repaint();
    }

}
