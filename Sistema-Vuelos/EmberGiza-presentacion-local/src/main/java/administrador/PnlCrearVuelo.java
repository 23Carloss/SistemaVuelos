package administrador;

import BOs.VueloBO;
import DTOs.VueloDTO;
import Interfaces.IVueloBO;
import NegocioException.NegocioException;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class PnlCrearVuelo extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;

    // Paneles externos
    PnlMenuAdmin pnlMenuAdmin;
    PnlVuelosProgramados pnlVuelosProgramados;

    // Variables de vuelo
    long precio;
    String nombre;
    String origen;
    String destino;
    Instant fecha;
    LocalTime hora;
    Instant fechaHora;
    int duracion;
    String aerolinea;

    ArrayList<VueloDTO> listaVuelos = new ArrayList<>();

    int logoX = 30;
    int logoY = logoX;

    int txtX = 200;
    int txtY = 50;
    int txtFS = 32;
    int espX = 10;
    int espY = 10;

    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";

    // ===== Alturas estándar (idénticas al panel referencia) =====
    final int ENCABEZADO_H = 80;
    final int INPUTS_H = 500;
    final int BOTONES_H = 80;

    // ----- Paneles -----
    ContainerPanel encabezado = new ContainerPanel(style.frameX, ENCABEZADO_H, Color.RED, testeoColor);
    JLabel logo;

    ContainerPanel acomodoInputs = new ContainerPanel(style.frameX, INPUTS_H, Color.GREEN, testeoColor);
    ContainerPanel inputs = new ContainerPanel(style.frameX, INPUTS_H - 40, Color.BLUE, testeoColor);

    // ----- Inputs -----
    CustomLabel lblPrecio = new CustomLabel("Precio: ");
    TxtFieldFormat txtPrecio = new TxtFieldFormat(1, "Precio", true, txtX, txtY, txtFS);

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

    // ----- Botones -----
    ContainerPanel botones = new ContainerPanel(style.frameX, BOTONES_H, Color.ORANGE, testeoColor);
    CustomButton btnVolver = new CustomButton("Cancelar");
    CustomButton btnCrearVuelo = new CustomButton("Crear vuelo");

    ContainerPanel todo = new ContainerPanel(style.frameX, style.frameY, Color.PINK, testeoColor);

    IVueloBO bo;

    public PnlCrearVuelo(PnlMenuAdmin pnlMenuAdmin, PnlVuelosProgramados pnlVuelosProgramados) {
        bo = new VueloBO();
        this.pnlMenuAdmin = pnlMenuAdmin;
        this.pnlVuelosProgramados = pnlVuelosProgramados;

        setOpaque(false);
        setSize(style.frameX, style.frameY);

        // Layout vertical general
        todo.setLayout(new BoxLayout(todo, BoxLayout.Y_AXIS));
        todo.setOpaque(false);
        todo.setPreferredSize(new Dimension(style.frameX, style.frameY));

        // ================== ENCABEZADO ==================
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
        encabezado.add(new CustomLabel("Programar nuevo vuelo", 36));

        todo.add(encabezado);

        // ================== INPUTS ======================
        inputs.setLayout(new GridLayout(7, 5));

        lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblPrecio);
        inputs.add(txtPrecio);

        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));

        lblOrigen.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblOrigen);
        inputs.add(txtOrigen);

        lblDestino.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblDestino);
        inputs.add(txtDestino);

        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));

        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblFecha);
        inputs.add(dateFecha);

        lblHora.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblHora);
        inputs.add(txtHora);

        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));
        inputs.add(new Espaciador(espX, espY));

        lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblDuracion);
        inputs.add(txtDuracion);

        lblAerolinea.setHorizontalAlignment(SwingConstants.RIGHT);
        inputs.add(lblAerolinea);
        inputs.add(txtAerolinea);

        // Centrado vertical estilo referencia
        acomodoInputs.setLayout(new GridBagLayout());
        acomodoInputs.setOpaque(false);
        acomodoInputs.setPreferredSize(new Dimension(style.frameX, INPUTS_H));
        acomodoInputs.setMaximumSize(new Dimension(style.frameX, INPUTS_H));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        // espacio arriba
        gbc.gridy = 0;
        gbc.weighty = 0.3;
        acomodoInputs.add(Box.createVerticalStrut(1), gbc);

        // contenido centrado
        gbc.gridy = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        acomodoInputs.add(inputs, gbc);

        // espacio abajo
        gbc.gridy = 2;
        gbc.weighty = 0.7;
        gbc.fill = GridBagConstraints.BOTH;
        acomodoInputs.add(Box.createVerticalStrut(1), gbc);

        todo.add(acomodoInputs);

        // ================== BOTONES ======================
        botones.setOpaque(false);
        botones.setPreferredSize(new Dimension(style.frameX, BOTONES_H));
        botones.setMaximumSize(new Dimension(style.frameX, BOTONES_H));
        botones.setLayout(new BorderLayout());

        JPanel leftBtns = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        leftBtns.setOpaque(false);
        leftBtns.add(btnVolver);

        JPanel rightBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        rightBtns.setOpaque(false);
        rightBtns.add(btnCrearVuelo);

        botones.add(leftBtns, BorderLayout.WEST);
        botones.add(rightBtns, BorderLayout.EAST);

        todo.add(botones);

        // ================== EVENTOS ======================
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

    // ------------------- Métodos originales -------------------
    public void volver() {
        pnlMenuAdmin.remove(this);
        pnlVuelosProgramados.setVisible(true);
    }

    public void crearVuelo() {
        try {
            precio = Long.parseLong(txtPrecio.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El precio no es válido.");
            return;
        }

        origen = txtOrigen.getText();
        destino = txtDestino.getText();

        if (dateFecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Selecciona una fecha.");
            return;
        }

        fecha = dateFecha.getDate().toInstant();

        try {
            hora = LocalTime.parse(txtHora.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La hora no es válida.");
            return;
        }

        try {
            duracion = Integer.parseInt(txtDuracion.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Duración no válida.");
            return;
        }

        aerolinea = txtAerolinea.getText();

        if (txtPrecio.getText().isEmpty() || origen.isEmpty()
                || destino.isEmpty() || txtDuracion.getText().isEmpty() || aerolinea.isEmpty()) {

            JOptionPane.showMessageDialog(null, "Todos los campos deben estar llenos.");
            return;
        }

// Convertir el Instant a LocalDate
        LocalDate localDate = fecha.atZone(ZoneId.systemDefault()).toLocalDate();

// Combinar fecha y hora
        LocalDateTime fechaHora = LocalDateTime.of(localDate, hora);
        
        Instant salida = fechaHora.atZone(ZoneId.systemDefault()).toInstant();

        
        VueloDTO dto = new VueloDTO(precio, "ERROR", origen, destino, salida, duracion, aerolinea);
        try {
            bo.crearVuelo(dto);
        } catch (NegocioException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error al crear el vuelo");
            return;
        }

        JOptionPane.showMessageDialog(null, "Se registró exitosamente");

        volver();
    }
}


/*
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

 */
