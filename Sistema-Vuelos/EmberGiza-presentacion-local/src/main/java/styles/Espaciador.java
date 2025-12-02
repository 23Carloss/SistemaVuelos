package styles;

import javax.swing.*;
import java.awt.*;

public class Espaciador extends JPanel {

    public Espaciador(int x, int y) {
        //setMaximumSize(new Dimension(1366, 25));
        setMaximumSize(new Dimension(x, y));
        setPreferredSize(new Dimension(x, y));
        setOpaque(false);
    }
}