package styles;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    //int size;
    //Font baseArial = new Font("Arial", Font.BOLD, 32);
    Style style = new Style();

    public CustomLabel(String texto, int size) {

        setFont(new Font("Arial", Font.BOLD, size));
        setForeground(Color.BLACK);
        setText(texto);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    //Label para texto genérico con tamaño base
    public CustomLabel(String texto) {
        setFont(new Font("Arial", Font.BOLD, style.letraSize));
        setForeground(Color.BLACK);
        setText(texto);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

    public CustomLabel(String texto, int size, int blanco) {

        setFont(new Font("Arial", Font.BOLD, size));
        setForeground(Color.WHITE);
        setText(texto);
        setHorizontalAlignment(SwingConstants.CENTER);
    }

}
