package administrador;

import DTOs.VueloDTO;
import cliente.DlgSeleccionarAsientos;
import styles.CustomButton;
import styles.CustomLabel;
import styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesVueloAdmin extends JDialog{

    Style style = new Style();
    VueloDTO vuelo;
    CustomButton btnAccion;

    public DlgDetallesVueloAdmin(VueloDTO vuelo) {

        this.vuelo = vuelo;

        //Setup
        setLocationRelativeTo(null);
        Dimension dimension = new Dimension(500, 300);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.beigeBase);
        JPanel todo = new JPanel();
        todo.setLayout(new BorderLayout());
        todo.setOpaque(false);
        setContentPane(todo);

        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setOpaque(false);


        //Elementos
        System.out.println("vuelo: " + vuelo.toString());
        System.out.println(vuelo.getNumVuelo());
        todo.add(new CustomLabel("Detalles del vuelo " + vuelo.getNumVuelo(), style.letraSize + 10), BorderLayout.NORTH);
        contenido.add(new CustomLabel("Origen: " + vuelo.getOrigen()));
        contenido.add(new CustomLabel("Destino: " + vuelo.getDestino()));
        contenido.add(new CustomLabel("Fecha y hora: " + vuelo.getFechaSalida()));
        contenido.add(new CustomLabel("Duración: " + vuelo.getDuracion()));
        contenido.add(new CustomLabel("Aerolínea: " + vuelo.getAerolinea()));
        contenido.add(new CustomLabel("Precio: $" + vuelo.getPrecio()));
        todo.add(contenido, BorderLayout.CENTER);

        btnAccion = new CustomButton("Editar detalles de vuelo");
        btnAccion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editarVuelo();
            }
        });
        todo.add(btnAccion, BorderLayout.SOUTH);
    }

    public void editarVuelo() {
        DlgEditarVuelo dlgEditarVuelo = new DlgEditarVuelo(vuelo);
        dlgEditarVuelo.setVisible(true);
        this.dispose();
    }
}
