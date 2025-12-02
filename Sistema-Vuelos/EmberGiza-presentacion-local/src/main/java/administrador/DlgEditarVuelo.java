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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DlgEditarVuelo extends JDialog {

    Style style = new Style();
    boolean testeoColor = false;

    //Variables
    VueloDTO vuelo;
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
    CustomButton btnEliminarVuelo = new CustomButton("Eliminar vuelo");
    CustomButton btnCrearVuelo = new CustomButton("Crear");
    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.GREEN, testeoColor);


    public DlgEditarVuelo(VueloDTO vuelo) {

        //Setteo del panel
        //this.pnlMenuAdmin = pnlMenuAdmin;
        //this.pnlVuelosProgramados = pnlVuelosProgramados;
        this.vuelo = vuelo;
        setBackground(style.beigeBase);
        //setOpaque(false);
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
        encabezado.add(new CustomLabel(" Editar detalles del vuelo " + vuelo.getNombre(), 36));
        todo.add(encabezado, BorderLayout.NORTH);

        //Contenido
        inputs.setLayout(new GridLayout(7, 5));
        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblPrecio);
        txtPrecio.setText("" + vuelo.getPrecio());
        inputs.add(txtPrecio);
        lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblCodigo);
        txtNombre.setText(vuelo.getNombre());
        inputs.add(txtNombre);
        inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY));
        lblOrigen.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblOrigen);
        txtOrigen.setText(vuelo.getOrigen());
        inputs.add(txtOrigen);
        lblDestino.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblDestino);
        txtDestino.setText(vuelo.getDestino());
        inputs.add(txtDestino);
        inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY));
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblFecha);
        dateFecha.setDate(Date.from(vuelo.getFechaSalida().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        inputs.add(dateFecha);
        lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblHora);
        txtHora.setText(vuelo.getFechaSalida().format(DateTimeFormatter.ofPattern("HH:mm")));
        inputs.add(txtHora);
        inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY)); inputs.add(new Espaciador(espX, espY));
        lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblDuracion);
        txtDuracion.setText("" + vuelo.getDuracion());
        inputs.add(txtDuracion);
        lblAerolinea.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblAerolinea);
        txtAerolinea.setText(vuelo.getAerolinea());
        inputs.add(txtAerolinea);
        //acomodo
        acomodoInputs.setLayout(new GridBagLayout());
        acomodoInputs.add(inputs);
        todo.add(acomodoInputs, BorderLayout.CENTER);

        //Botones
        botones.setLayout(new BorderLayout());
        botones.add(btnVolver, BorderLayout.WEST);
        botones.add(btnEliminarVuelo, BorderLayout.CENTER);
        botones.add(btnCrearVuelo, BorderLayout.EAST);
        todo.add(botones, BorderLayout.SOUTH);
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volver();
            }
        });
        btnEliminarVuelo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarVuelo();
            }
        });
        btnCrearVuelo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarVuelo();
            }
        });

        add(todo);
        repaint();
        revalidate();
        setVisible(true);
    }

    //Métodos de los botones
    public void volver() {
        this.dispose();
    }

    public void eliminarVuelo() {

        //-----LÓGICA AQUÍ-----
        //Registrar el vuelo adecuadamente
        System.out.println("Haz de cuenta que se eliminó el vuelo");
        JOptionPane.showMessageDialog(null, "Se eliminó exitosamente");
        volver();
    }

    public void guardarVuelo() {
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
        System.out.println("Haz de cuenta que se guradó el vuelo");
        JOptionPane.showMessageDialog(null, "Se guardó exitosamente");
        volver();
    }

}