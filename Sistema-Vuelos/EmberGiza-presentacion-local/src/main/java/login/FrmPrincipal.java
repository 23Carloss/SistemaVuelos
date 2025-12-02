package login;

import cliente.PnlMenuCliente;
import styles.Style;

import javax.swing.*;

public class FrmPrincipal extends JFrame {

    //agregar menú principal como panel interno

    Style style = new Style();
    private PnlMenuCliente pnlMenu;
    //private JPanel panelSur;



    public FrmPrincipal( ) {

        //Establecimiento del frame
        setSize(style.frameX, style.frameY);
        getContentPane().setBackground(Style.beigeBase);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        PnlLogin pnlLogin = new PnlLogin();
        add(pnlLogin);

        repaint();
        setVisible(true);
    }
}