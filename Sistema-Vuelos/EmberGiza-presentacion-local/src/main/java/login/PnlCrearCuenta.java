package login;

import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class PnlCrearCuenta extends JPanel {

    boolean testeoColor = false;
    Style style = new Style();
    PnlLogin pnlLogin;

    int height = 100;

    //Espacios
    Espaciador espaciov1 = new Espaciador(style.frameX, 80);
    Espaciador espaciov2 = new Espaciador(style.frameX, 40);
    Espaciador espaciov3 = new Espaciador(style.frameX, 30);
    Espaciador espaciov4 = new Espaciador(style.frameX, 40);
    Espaciador espaciov5 = new Espaciador(style.frameX, 50);

    //Botón volver
    ContainerPanel contenedorVolver = new ContainerPanel(style.frameX, 80, Color.RED, testeoColor);
    TitleButton btnVolver = new TitleButton(" Volver", 100, 80);
    //Título
    ContainerPanel contenedorTitulo = new ContainerPanel(style.frameX, height, Color.ORANGE, testeoColor);
    CustomLabel lblTitulo = new CustomLabel("Crear cuenta", 48);

    int txtFieldSizeX = 350, fontSize = 18;

    //Entradas
    //Columna 1
    CustomLabel lblCorreo = new CustomLabel("Correo electrónico", fontSize);
    ContainerPanel contCorreo = new ContainerPanel(txtFieldSizeX, height, Color.CYAN, testeoColor);
    TxtFieldPh txtCorreo = new TxtFieldPh("Correo electrónico", true, 300, 50, 32);

    CustomLabel lblContra = new CustomLabel("Contraseña", fontSize);
    ContainerPanel contContra = new ContainerPanel(txtFieldSizeX, height, Color.ORANGE, testeoColor);
    PwFieldPh txtContra = new PwFieldPh("Contraseña", true, 300, 50, 32);

    CustomLabel lblContra2 = new CustomLabel("Confirmar contraseña", fontSize);
    ContainerPanel contContra2 = new ContainerPanel(txtFieldSizeX, height, Color.YELLOW, testeoColor);
    PwFieldPh txtContra2 = new PwFieldPh("Confirmar contraseña", true, 300, 50, 32);

    //Columna 2
    CustomLabel lblNombre = new CustomLabel("Nombre(s)", fontSize);
    ContainerPanel contNombre = new ContainerPanel(txtFieldSizeX, height, Color.PINK, testeoColor);
    TxtFieldPh txtNombre = new TxtFieldPh("Nombre(s)", true, 300, 50, 32);

    CustomLabel lblAP = new CustomLabel("Apellido paterno", fontSize);
    ContainerPanel contAP = new ContainerPanel(txtFieldSizeX, height, Color.GREEN, testeoColor);
    TxtFieldPh txtAP = new TxtFieldPh("Apellido paterno", true, 300, 50, 32);

    CustomLabel lblAM = new CustomLabel("Apellido materno", fontSize);
    ContainerPanel contAM = new ContainerPanel(txtFieldSizeX, height, Color.BLUE, testeoColor);
    TxtFieldPh txtAM = new TxtFieldPh("Apellido materno", true, 300, 50, 32);

    //Carrera
    CustomLabel lblCarrera = new CustomLabel("Carrera", fontSize);
    ContainerPanel contCarrera = new ContainerPanel(txtFieldSizeX, height, Color.MAGENTA, testeoColor);
    //CustomCbox<Carrera> cboxCarrera = new CustomCbox<>();

    //Botón crear cuenta
    int btnX = 150, btnY = 50;
    ContainerPanel contBotones = new ContainerPanel(btnX, 120, Color.PINK, testeoColor);
    CustomButton btnCrear = new CustomButton("Crear cuenta", 1, btnX, btnY);

    //Inputs
    String sCorreo, sContra, sConfContra;
    String sNombre, sApellidoP, sApellidoM;
    String sDescripcion, sCarrera;
    String rutaImagen = null, rutaFinalImagen = null;


    public PnlCrearCuenta(PnlLogin pnlLogin) {
        this.pnlLogin = pnlLogin;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setSize(style.frameX, style.frameY);

        contenedorVolver.setLayout(new BorderLayout());
        btnVolver.addMouseListener(new MouseAdapter(){ @Override public void mouseClicked(MouseEvent e){ volverLogin(); }});
        contenedorVolver.add(btnVolver, BorderLayout.WEST);
        add(contenedorVolver);

        add(espaciov1);

        contenedorTitulo.add(lblTitulo);
        add(contenedorTitulo);

        add(espaciov2);

        //Columnas
        JPanel contColumnas = new JPanel();
        contColumnas.setLayout(new GridLayout(1,2,40,0));
        contColumnas.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        contColumnas.setOpaque(false);

        JPanel col1 = new JPanel(); col1.setLayout(new BoxLayout(col1, BoxLayout.Y_AXIS)); col1.setOpaque(false);
        JPanel col2 = new JPanel(); col2.setLayout(new BoxLayout(col2, BoxLayout.Y_AXIS)); col2.setOpaque(false);

        //Conteneores
        contCorreo.setLayout(new GridLayout(2,1)); contCorreo.add(lblCorreo); contCorreo.add(txtCorreo);
        contContra.setLayout(new GridLayout(2,1)); contContra.add(lblContra); contContra.add(txtContra);
        contContra2.setLayout(new GridLayout(2,1)); contContra2.add(lblContra2); contContra2.add(txtContra2);

        contNombre.setLayout(new GridLayout(2,1)); contNombre.add(lblNombre); contNombre.add(txtNombre);
        contAP.setLayout(new GridLayout(2,1)); contAP.add(lblAP); contAP.add(txtAP);
        contAM.setLayout(new GridLayout(2,1)); contAM.add(lblAM); contAM.add(txtAM);

        //Acomodo
        col1.add(contCorreo);
        col1.add(contContra);
        col1.add(contContra2);
        col2.add(contNombre);
        col2.add(contAP);
        col2.add(contAM);
        contColumnas.add(col1);
        contColumnas.add(col2);
        add(contColumnas);
        add(espaciov3);
        add(new Espaciador(style.frameX, 30));

        //Botón crear cuenta
        btnCrear.addMouseListener(new MouseAdapter(){ @Override public void mouseClicked(MouseEvent e){ crearCuenta(); }});
        contBotones.setLayout(new BorderLayout());
        contBotones.add(btnCrear, BorderLayout.NORTH);
        add(contBotones);
    }


    //-----LÓGICA AQUÍ-----
    public void crearCuenta() {

        //Asignación de valores
        sCorreo = txtCorreo.getText();
        sContra = txtContra.getText();
        sConfContra = txtContra2.getText();
        sNombre = txtNombre.getText();
        sApellidoP = txtAP.getText();
        sApellidoM = txtAM.getText();

        //Prevalidaciones sencillas
        if (sNombre.length() > 100) {
            JOptionPane.showMessageDialog(null, "El nombre no puede exceder 100 caracteres");
            return;
        }
        if (sApellidoP.length() > 70) {
            JOptionPane.showMessageDialog(null, "El apellido paterno no puede exceder 70 caracteres");
            return;
        }
        if (sApellidoM.length() > 70) {
            JOptionPane.showMessageDialog(null, "El apellido materno no puede exceder 70 caracteres");
            return;
        }
        if (sDescripcion.length() > 300) {
            JOptionPane.showMessageDialog(null, "La descripción no puede exceder 300 caracteres");
            return;
        }
        if (!sContra.equals(sConfContra)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            return;
        }
        if (sCorreo.isEmpty() || sContra.isEmpty() || sConfContra.isEmpty() || sNombre.isEmpty() || sApellidoP.isEmpty() || sApellidoM.isEmpty() || sDescripcion.isEmpty() || sCarrera.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor rellena todos los campos");
            return;
        }


    }

    public void elegirImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Imágenes (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif"
        ));

        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            rutaImagen = archivo.getAbsolutePath();
            JOptionPane.showMessageDialog(null, "Imagen seleccionada:\n" + rutaImagen);
        }
    }

    public void volverLogin() {
        pnlLogin.volverLogin();
    }
}