package administrador;

import DTOs.VueloDTO;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class PnlCrearVuelo extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;
        //Páneles
    PnlMenuAdmin pnlMenuAdmin;
    PnlVuelosProgramados pnlVuelosProgramados;
        //Variables
    long precio;
    String nombre;
    String origen;
    String destino;
    LocalDate fecha;
    LocalTime hora;
    LocalDateTime fechaHora;
    int duracion;
    String aerolinea;

    //-----LÓGICA AQUÍ-----
    //Placeholder ?
    ArrayList<VueloDTO> listaVuelos = new ArrayList<>();


    //Ajustes de tamaño
    int logoX = 30;
    int logoY = logoX;
        //Textfields
    int txtX = 200;
    int txtY = 50;
    int txtFS = 32;
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
    ContainerPanel acomodoInputs = new ContainerPanel(1000, 400, Color.RED, testeoColor);
    ContainerPanel inputs = new ContainerPanel(1000, 300, Color.MAGENTA, testeoColor);
        //Inputs
    CustomLabel lblPrecio = new CustomLabel("Precio: ");
    TxtFieldFormat txtPrecio = new TxtFieldFormat(1, "Precio", true, txtX, txtY, txtFS);
    CustomLabel lblCodigo = new CustomLabel("Código: ");
    TxtFieldPh txtNombre = new TxtFieldPh("Nombre", true, txtX, txtY, txtFS);
    CustomLabel lblOrigen = new CustomLabel("Origen: ");
    TxtFieldPh txtOrigen = new TxtFieldPh("Origen", true, txtX, txtY, txtFS);
    CustomLabel lblDestino = new CustomLabel("Destino: ");
    TxtFieldPh txtDestino = new TxtFieldPh("Destino", true, txtX, txtY, txtFS);
    CustomLabel lblFecha = new CustomLabel("Fecha: ");
    CustomDateChooser dateFecha = new CustomDateChooser();
    CustomLabel lblHora = new CustomLabel("Hora: ");
    TxtFieldFormat txtHora = new TxtFieldFormat(2, "Hora de abordaje", true, txtX, txtY, txtFS);
    CustomLabel lblDuracion = new CustomLabel("Duración: ");
    TxtFieldFormat txtDuracion = new TxtFieldFormat(1, "Duracion", true, txtX, txtY, txtFS);
    CustomLabel lblAerolinea = new CustomLabel("Aerolínea: ");
    TxtFieldPh txtAerolinea = new TxtFieldPh("Aerolínea", true, txtX, txtY, txtFS);

    //Botones
    ContainerPanel botones = new ContainerPanel(style.frameX, 100, Color.PINK, testeoColor);
    CustomButton btnVolver = new CustomButton("Cancelar");
    CustomButton btnCrearVuelo = new CustomButton("Crear");
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.GREEN, testeoColor);


    public PnlCrearVuelo(PnlMenuAdmin pnlMenuAdmin, PnlVuelosProgramados pnlVuelosProgramados) {

        //Setteo del panel
        this.pnlMenuAdmin = pnlMenuAdmin;
        this.pnlVuelosProgramados = pnlVuelosProgramados;
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
        //Titulo
        encabezado.add(new CustomLabel(" Programar nuevo vuelo", 36));
        todo.add(encabezado, BorderLayout.NORTH);

        //Contenido
        inputs.setLayout(new GridLayout(7, 5));
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblPrecio);
        inputs.add(txtPrecio);
        lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblCodigo);
        inputs.add(txtNombre);
        inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY));
        lblOrigen.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblOrigen);
        inputs.add(txtOrigen);
        lblDestino.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblDestino);
        inputs.add(txtDestino);
        inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY));
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblFecha);
        inputs.add(dateFecha);
        lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblHora);
        inputs.add(txtHora);
        inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY));
        lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblDuracion);
        inputs.add(txtDuracion);
        lblAerolinea.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblAerolinea);
        inputs.add(txtAerolinea);
            //acomodo
        acomodoInputs.setLayout(new GridBagLayout());
        acomodoInputs.add(inputs);
        todo.add(acomodoInputs, BorderLayout.CENTER);

        //Botones
        botones.setLayout(new BorderLayout());
        botones.add(btnVolver, BorderLayout.WEST);
        botones.add(btnCrearVuelo, BorderLayout.EAST);
        todo.add(botones, BorderLayout.SOUTH);
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });
        btnCrearVuelo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                crearVuelo();
            }
        });


        add(todo);
        repaint();
        revalidate();
        setVisible(true);
    }

    //Métodos de los botones
    public void volver() {
        pnlMenuAdmin.remove(this);
        pnlVuelosProgramados.setVisible(true);
    }

    public void crearVuelo() {
        //Prevalidaciones sencillas, asignación a variables locales
        try {
            precio = Long.parseLong(txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            System.out.println("Precio no válido");
            JOptionPane.showMessageDialog(null, "El precio no es válido.");
            return;
        }
        nombre = txtNombre.getText();
        origen = txtOrigen.getText();
        destino = txtDestino.getText();
        if (dateFecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Selecciona una fecha.");
            return;
        }
        fecha = dateFecha.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        try {
            hora = LocalTime.parse(txtHora.getText());
        } catch (Exception e) {
            System.out.println("La hora no es válida");
            JOptionPane.showMessageDialog(null, "La hora no es válida.");
            return;
        }
        try {
            duracion = Integer.parseInt(txtDuracion.getText());
        } catch (Exception e) {
            System.out.println("Duración no válida");
            JOptionPane.showMessageDialog(null, "Duración no válida.");
            return;
        }
        aerolinea = txtAerolinea.getText();
        if (txtPrecio.getText().isEmpty() || nombre.isEmpty() || origen.isEmpty() || destino.isEmpty() || txtDuracion.getText().isEmpty()|| aerolinea.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
            return;
        }
        fechaHora = LocalDateTime.of(fecha, hora);


        //-----LÓGICA AQUÍ-----
        //Registrar el vuelo adecuadamente
        System.out.println("Haz de cuenta que se registró el vuelo");
        JOptionPane.showMessageDialog(null, "Se registró exitosamente");
        volver();
    }


}
