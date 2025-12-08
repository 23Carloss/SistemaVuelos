package login;

import Aplicacion.Control;
import BOs.UsuarioBO;
import DTOs.UsuarioDTO;
import POJOs.TipoUsuario;
import administrador.PnlMenuAdmin;
import cliente.PnlMenuCliente;
import styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlLogin extends JPanel {

    private Control control;
    //Declaraciones
    Style style = new Style();
    boolean testeoColor = false;
        //Paneles
    PnlCrearCuenta pnlCrearCuenta;
    PnlMenuCliente pnlMenuCliente = null;
    PnlMenuAdmin pnlMenuAdmin = null;
        //Variables
    String correoInput;
    String contraInput;
        //DTO
    UsuarioDTO usuario;

    //-----LÓGICA AQUÍ-----
    //Poner la ruta donde está guardado el logo de EG
    String rutaProyecto = "";
    String rutaLogo = rutaProyecto + "logo.png";
    //-----LOGICA BO-------
    UsuarioBO bo;
    

    //::::::::::::::::::::::::::::::ESTÉTICA::::::::::::::::::::::::::::::
    //Elementos estéticos, dudo que se ocupe mover
        //Título
    ContainerPanel contenedorVyb = new ContainerPanel(style.frameX, 120, Color.ORANGE, testeoColor);
    JLabel logo;

    ContainerPanel contenedorNombre = new ContainerPanel(style.frameX/2, 120, Color.ORANGE, testeoColor);
    CustomLabel lblNombre = new CustomLabel(" EMBER GIZA ®", 64);
    ContainerPanel contenedorIniciarSesion = new ContainerPanel(style.frameX, 80, Color.YELLOW, testeoColor);
    CustomLabel lblIniciarSesion = new CustomLabel("Iniciar sesión", 48);

    //Textfields
    ContainerPanel contenedorCorreo = new ContainerPanel(style.frameX, 80, Color.CYAN, testeoColor);
    TxtFieldPh txtFieldCorreo = new TxtFieldPh("Correo electrónico", true, 300, 50, 32);
    ContainerPanel contenedorContra = new ContainerPanel(style.frameX, 80, Color.ORANGE, testeoColor);
    PwFieldPh pwFieldContra = new PwFieldPh("Contraseña", true, 300, 50, 32);

    //Botones
    int btnX = 150, btnY = 50;
    ContainerPanel contenedorBotones = new ContainerPanel(btnX, 120, Color.PINK, testeoColor);
    CustomButton btnIniciarSesion = new CustomButton("Iniciar sesión", 1, btnX, btnY);
    CustomButton btnCrearCuenta = new CustomButton("Crear cuenta", 2, btnX, btnY);
        //Espacios
    Espaciador espaciov1 = new Espaciador(style.frameX, 200);
    Espaciador espaciov2 = new Espaciador(style.frameX, 80);
    Espaciador espaciov3 = new Espaciador(style.frameX, 50);
    Espaciador espaciov4 = new Espaciador(style.frameX, 20);
    //::::::::::::::::::::::::::::::FIN DE ESTÉTICA::::::::::::::::::::::::::::::


    public PnlLogin(Control control) {
        this.control = control;
        bo= new UsuarioBO();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setSize(style.frameX, style.frameY);

        add(espaciov1);

        //Nombre y logo
            //Logo
        ImageIcon icon = new ImageIcon(rutaLogo);
        Image img = icon.getImage().getScaledInstance(130, 180, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        logo = new JLabel(icon);
        logo.setIcon(new ImageIcon(img));
        logo.setPreferredSize(new Dimension(130, 180));
        contenedorVyb.add(logo);

        contenedorNombre.setLayout(new BoxLayout(contenedorNombre, BoxLayout.Y_AXIS));
        contenedorNombre.add(lblNombre);
        contenedorVyb.add(contenedorNombre);
        add(contenedorVyb);

        //Título
        contenedorIniciarSesion.add(lblIniciarSesion);
        add(contenedorIniciarSesion);
        add(espaciov2);
        //Textfields
        contenedorCorreo.add(txtFieldCorreo);
        contenedorContra.add(pwFieldContra);
        add(contenedorCorreo);
        add(contenedorContra);
        add(espaciov3);
        //Botones
        btnIniciarSesion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                iniciarSesion();
            }
        });
        btnCrearCuenta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                crearCuenta();
            }
        });
        contenedorBotones.setLayout(new BorderLayout());
        contenedorBotones.add(btnIniciarSesion, BorderLayout.NORTH);
        contenedorBotones.add(espaciov4, BorderLayout.CENTER);
        contenedorBotones.add(btnCrearCuenta, BorderLayout.SOUTH);
        add(contenedorBotones);

    }

    public void iniciarSesion() {
        correoInput = txtFieldCorreo.getText();
        contraInput = pwFieldContra.getText();
        
        
        try {
            usuario = bo.iniciarSesion(correoInput, contraInput);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión");
        }
        
        
        
        
        System.out.println("correo " +  correoInput);

        //-----BYPASS Y PLACEHOLDER-----
        //Cambiar por validación real
        
        
        System.out.println(usuario.toString());
        

        if (usuario!=null) {
            esconderComponentes();
            //Determinación de tipo de menú
            if (usuario.getTipoUsuario()==TipoUsuario.administrador) {
                pnlMenuAdmin = new PnlMenuAdmin(this);
                if (pnlCrearCuenta != null) {
                    remove(pnlCrearCuenta);
                }
                add(pnlMenuAdmin);
            } else {
                pnlMenuCliente = new PnlMenuCliente(this, usuario, control);
                pnlMenuAdmin = new PnlMenuAdmin(this);
                if (pnlCrearCuenta != null) {
                    remove(pnlCrearCuenta);
                }
                add(pnlMenuCliente);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Correo o contraseña inválidos.");
        }

    }

    public void crearCuenta() {
        esconderComponentes();
        pnlCrearCuenta = new PnlCrearCuenta(this);
        add(pnlCrearCuenta);
        revalidate();
        repaint();
    }

    public void esconderComponentes(){
        espaciov1.setVisible(false);
        espaciov2.setVisible(false);
        espaciov3.setVisible(false);
        contenedorVyb.setVisible(false);
        contenedorNombre.setVisible(false);
        contenedorIniciarSesion.setVisible(false);
        contenedorCorreo.setVisible(false);
        contenedorContra.setVisible(false);
        contenedorBotones.setVisible(false);
        revalidate();
        repaint();
    }

    public void volverLogin() {
        if (pnlCrearCuenta != null) {
            pnlCrearCuenta.setVisible(false);
        }
        if (pnlMenuCliente != null) {
            remove(pnlMenuCliente);
        }
        espaciov1.setVisible(true);
        espaciov2.setVisible(true);
        espaciov3.setVisible(true);
        contenedorVyb.setVisible(true);
        contenedorNombre.setVisible(true);
        contenedorIniciarSesion.setVisible(true);
        contenedorCorreo.setVisible(true);
        contenedorContra.setVisible(true);
        contenedorBotones.setVisible(true);
        usuario = null;
        revalidate();
        repaint();
    }
}

