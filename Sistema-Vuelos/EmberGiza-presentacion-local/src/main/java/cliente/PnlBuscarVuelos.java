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
