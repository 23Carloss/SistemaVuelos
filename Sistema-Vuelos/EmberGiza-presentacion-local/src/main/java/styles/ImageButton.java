package styles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ImageButton extends JPanel {

    Style style = new Style();
    String texto;
    String rutaImagen = null;
    JLabel imagen;
    int radius = 60;

    public ImageButton(String texto, String rutaImagen) {

        this.texto= texto;
        this.rutaImagen = rutaImagen;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLayout(new BorderLayout());
        setOpaque(false);
        Dimension dimension = new Dimension(style.imageButtonX, style.imageButtonY);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);

        //Imagen
            //Esquinas redondas
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

                g2.dispose();
                super.paintComponent(g);
            }
        };
        panelImagen.setOpaque(false);
        panelImagen.setBackground(style.cafeAlt);
        panelImagen.setLayout(new BorderLayout());
            //Tamaño
        int tolerancia = 20;
        //panelImagen.setSize(style.imageButtonX- tolerancia, style.imageButtonY - tolerancia);
        Dimension dimensionPanelImagen = new Dimension(style.imageButtonX- tolerancia, style.imageButtonY - tolerancia);
        panelImagen.setMaximumSize(dimensionPanelImagen);
        panelImagen.setMinimumSize(dimensionPanelImagen);
        panelImagen.setPreferredSize(dimensionPanelImagen);
            //Hover
        panelImagen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                panelImagen.setBackground(style.cafeAltHover);
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                panelImagen.setBackground(style.cafeAlt);
                repaint();
            }
            public void mouseClicked(MouseEvent e) {
                // Reenviar el evento al padre (ImageButton)
                for (MouseListener ml : ImageButton.this.getMouseListeners()) {
                    ml.mouseClicked(SwingUtilities.convertMouseEvent(panelImagen, e, ImageButton.this));
                }
            }
        });

        //setteo de la imagen
        if (rutaImagen != null) {
            int imageSize = style.imageButtonX-(tolerancia + 10);
            ImageIcon icon = new ImageIcon(rutaImagen);
            Image img = icon.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            imagen = new JLabel(icon);
            imagen.setIcon(new ImageIcon(img));
            imagen.setPreferredSize(new Dimension(imageSize, imageSize));
        } else {
            imagen = new JLabel("Placeholder");
        }


        panelImagen.add(imagen,BorderLayout.CENTER);
        add(panelImagen);
        add(new CustomLabel(texto, 24));

    }
}
