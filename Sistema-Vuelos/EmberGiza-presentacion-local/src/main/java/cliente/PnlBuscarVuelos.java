package cliente;

import Aplicacion.Control;
import styles.*;

import javax.swing.*;
import java.awt.*;
import static java.awt.SystemColor.control;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;

public class PnlBuscarVuelos extends JPanel {
    private Control control;
    Style style = new Style();
    boolean testeoColor = false;

    // Variables
    String origen;
    String destino;
    
    Date fecha;

    // Páneles
    PnlMenuCliente pnlMenuCliente;

    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.GREEN, testeoColor);

    int logoX = 30;
    int logoY = logoX;

    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";

    // =================== PANELES =======================
    final int ENCABEZADO_H = 80;
    final int INPUTS_H = 500;   // panel alto
    final int BOTONES_H = 100;

    ContainerPanel encabezado = new ContainerPanel(style.frameX, ENCABEZADO_H, Color.CYAN, testeoColor);
    JLabel logo;

    ContainerPanel acomodoInputs = new ContainerPanel(style.frameX, INPUTS_H, Color.RED, testeoColor);
    ContainerPanel inputs = new ContainerPanel(style.frameX, INPUTS_H, Color.RED, testeoColor); // panel interno para campos

    // Inputs
    CustomLabel lblOrigen = new CustomLabel("Origen:");
    TxtFieldPh txtOrigen = new TxtFieldPh("Origen (opcional)", true, 250, 50, style.letraSize);

    CustomLabel lblDestino = new CustomLabel("Destino:");
    TxtFieldPh txtDestino = new TxtFieldPh("Destino", true, 250, 50, style.letraSize);

    CustomLabel lblFecha = new CustomLabel("Fecha:");
    CustomDateChooser dateFecha = new CustomDateChooser();

    // Botones
    ContainerPanel botones = new ContainerPanel(style.frameX, BOTONES_H, Color.PINK, testeoColor);
    CustomButton btnVolver = new CustomButton("Volver");
    CustomButton btnBuscarVuelos = new CustomButton("Buscar vuelos");


    public PnlBuscarVuelos(PnlMenuCliente pnlMenuCliente, Control control) {
        this.control = control;
        this.pnlMenuCliente = pnlMenuCliente;

        setOpaque(false);
        setSize(style.frameX, style.frameY);

        // ==== ESTILO PRINCIPAL ====
        todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));
        todo.setOpaque(false);
        todo.setPreferredSize(new Dimension(style.frameX, style.frameY));

        // =================== ENCABEZADO ===================
        encabezado.setOpaque(false);
        encabezado.setPreferredSize(new Dimension(style.frameX, ENCABEZADO_H));
        encabezado.setMaximumSize(new Dimension(style.frameX, ENCABEZADO_H));
        encabezado.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));

        ImageIcon icon = new ImageIcon(rutaLogo);
        Image img = icon.getImage().getScaledInstance(logoX, logoY, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        logo = new JLabel(icon);
        logo.setPreferredSize(new Dimension(logoX, logoY));

        encabezado.add(logo);
        encabezado.add(new CustomLabel("Buscar vuelos", 36));

        todo.add(encabezado);

        // =================== INPUTS ===================
        inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));
        inputs.setOpaque(false);

        // Origen
        JPanel rowOrigen = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        rowOrigen.setOpaque(false);
        lblOrigen.setPreferredSize(new Dimension(120, 50));
        txtOrigen.setPreferredSize(new Dimension(250, 50));
        rowOrigen.add(lblOrigen);
        rowOrigen.add(txtOrigen);
        inputs.add(rowOrigen);

        // Destino
        JPanel rowDestino = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        rowDestino.setOpaque(false);
        lblDestino.setPreferredSize(new Dimension(120, 50));
        txtDestino.setPreferredSize(new Dimension(250, 50));
        rowDestino.add(lblDestino);
        rowDestino.add(txtDestino);
        inputs.add(rowDestino);

        // Fecha
        JPanel rowFecha = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        rowFecha.setOpaque(false);
        lblFecha.setPreferredSize(new Dimension(120, 50));
        dateFecha.setPreferredSize(new Dimension(250, 50));
        rowFecha.add(lblFecha);
        rowFecha.add(dateFecha);
        inputs.add(rowFecha);

        // Acomodo Inputs en centro del panel
        acomodoInputs.setLayout(new GridBagLayout());
        acomodoInputs.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.3;             // espacio arriba
        gbc.fill = GridBagConstraints.BOTH;
        acomodoInputs.add(Box.createVerticalStrut(1), gbc);

        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        acomodoInputs.add(inputs, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.7;             // espacio abajo
        gbc.fill = GridBagConstraints.BOTH;
        acomodoInputs.add(Box.createVerticalStrut(1), gbc);

        acomodoInputs.setPreferredSize(new Dimension(style.frameX, INPUTS_H));
        acomodoInputs.setMaximumSize(new Dimension(style.frameX, INPUTS_H));

        todo.add(acomodoInputs);

        // =================== BOTONES ===================
        botones.setOpaque(false);
        botones.setPreferredSize(new Dimension(style.frameX, BOTONES_H));
        botones.setMaximumSize(new Dimension(style.frameX, BOTONES_H));
        botones.setLayout(new BorderLayout());

        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 20));
        leftBtns.setOpaque(false);
        leftBtns.add(btnVolver);

        JPanel rightBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 20));
        rightBtns.setOpaque(false);
        rightBtns.add(btnBuscarVuelos);

        botones.add(leftBtns, BorderLayout.WEST);
        botones.add(rightBtns, BorderLayout.EAST);

        todo.add(botones);

        // =================== EVENTOS ===================
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });

        btnBuscarVuelos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscarVuelos();
            }
        });

        add(todo);
        repaint();
        revalidate();
        setVisible(true);
    }

    // ------------------ MÉTODOS ------------------
    public void volver() {
        pnlMenuCliente.remove(this);
        pnlMenuCliente.mostrarComponentes();
    }

    public void buscarVuelos() {

        origen = txtOrigen.getText();
        destino = txtDestino.getText();
        
       
        
        if (origen.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor de especificar origen.");
            return;
        }
        if (destino.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor de especificar destino.");
            return;
        }

        if (dateFecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Selecciona una fecha.");
            return;
        }

        fecha = dateFecha.getDate();

        setVisible(false);
        PnlBuscarVuelosResultados pnlBuscarVuelosResultados = new PnlBuscarVuelosResultados(pnlMenuCliente, this, origen, destino, fecha,control);
        pnlMenuCliente.add(pnlBuscarVuelosResultados);
    }
}


/*
Esto de aqui es pedo del burgos :p


package cliente;

import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;

public class PnlBuscarVuelos extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;

    //Variables
    String origen;
    String destino;
    LocalDate fecha;
        //Páneles
    PnlMenuCliente pnlMenuCliente;

    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.GREEN, testeoColor);

    //Ajustes de tamaño
    int logoX = 30;
    int logoY = logoX;
    int espX = 10;
    int espY = 10;


    //-----LÓGICA AQUÍ-----
    //Poner la ruta donde está guardado el logo de EG
    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";


    //::::::::::::::::::::::::::::::ESTÉTICA::::::::::::::::::::::::::::::
    //Encabezado
    ContainerPanel encabezado = new ContainerPanel(style.frameX, 80, Color.CYAN, testeoColor);
    JLabel logo;
    //Contenido
    ContainerPanel inputs = new ContainerPanel(800, 400, Color.MAGENTA, testeoColor);
    TxtFieldPh txtOrigen = new TxtFieldPh("Origen", true, 140, 50, style.letraSize);
    TxtFieldPh txtDestino = new TxtFieldPh("Destino", true, 140, 50, style.letraSize);
    CustomDateChooser dateFecha = new CustomDateChooser();
    //TxtFieldPh txtFecha = new TxtFieldPh("Fecha", true, 140, 50, style.letraSize);
    //Botones
    ContainerPanel botones = new ContainerPanel(style.frameX, 100, Color.PINK, testeoColor);
    CustomButton btnVolver = new CustomButton("Volver");
    CustomButton btnBuscarVuelos = new CustomButton("Buscar vuelos");


    public PnlBuscarVuelos(PnlMenuCliente pnlMenuCliente) {

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
        encabezado.add(new CustomLabel(" Buscar vuelos", 36));
        todo.add(encabezado, BorderLayout.NORTH);

        //Contenido
        inputs.setLayout(new GridLayout(5, 3));
            //row 1
        inputs.add(new CustomLabel("Origen (opcional)"));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new CustomLabel("Destino"));
            //row 2
        inputs.add(txtOrigen);
        inputs.add(new Espaciador(espX, espY));
        inputs.add(txtDestino);
            //row 3 (espacios)
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
            //row 4
        inputs.add(new CustomLabel("Fecha"));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
            //row 5
        inputs.add(dateFecha);
            //add
        todo.add(inputs, BorderLayout.CENTER);

        //Botones
        botones.setLayout(new BorderLayout());
        botones.add(btnVolver, BorderLayout.WEST);
        botones.add(btnBuscarVuelos, BorderLayout.EAST);
        todo.add(botones, BorderLayout.SOUTH);
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });
        btnBuscarVuelos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscarVuelos();
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
    public void buscarVuelos() {

        origen = txtOrigen.getText();
        destino = txtDestino.getText();
        if (destino.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Favor de especificar destino.");
            return;
        }
        if (dateFecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Selecciona una fecha.");
            return;
        }
        fecha = dateFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        setVisible(false);
        PnlBuscarVuelosResultados pnlBuscarVuelosResultados = new PnlBuscarVuelosResultados(pnlMenuCliente, this);
        pnlMenuCliente.add(pnlBuscarVuelosResultados);
    }
}
*/