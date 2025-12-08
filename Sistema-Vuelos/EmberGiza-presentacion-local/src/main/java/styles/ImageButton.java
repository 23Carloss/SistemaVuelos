
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

    // Tamaño real y fijo del botón
    Dimension fixedSize;

    public ImageButton(String texto, String rutaImagen) {

        this.texto = texto;
        this.rutaImagen = rutaImagen;

        fixedSize = new Dimension(style.imageButtonX, style.imageButtonY);

        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Bloquear tamaño SIEMPRE
        setPreferredSize(fixedSize);
        setMinimumSize(fixedSize);
        setMaximumSize(fixedSize);

        // ---------- PANEL INTERNO QUE CONTIENE TODO ----------
        JPanel contenido = new JPanel();
        contenido.setOpaque(false);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setAlignmentX(CENTER_ALIGNMENT);

        // ---------- PANEL DE IMAGEN CON ESQUINAS REDONDAS ----------
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

        int tolerancia = 20;
        Dimension imgPanelSize = new Dimension(style.imageButtonX - tolerancia, style.imageButtonY - tolerancia - 40);

        panelImagen.setPreferredSize(imgPanelSize);
        panelImagen.setMaximumSize(imgPanelSize);
        panelImagen.setMinimumSize(imgPanelSize);

        // Hover
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

            @Override
            public void mouseClicked(MouseEvent e) {
                for (MouseListener ml : ImageButton.this.getMouseListeners()) {
                    ml.mouseClicked(SwingUtilities.convertMouseEvent(panelImagen, e, ImageButton.this));
                }
            }
        });

        // Imagen interna
        if (rutaImagen != null) {
            int imageSize = imgPanelSize.width - 20;
            ImageIcon icon = new ImageIcon(rutaImagen);
            Image img = icon.getImage().getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
            imagen = new JLabel(new ImageIcon(img));
        } else {
            imagen = new JLabel("Placeholder");
        }

        imagen.setHorizontalAlignment(SwingConstants.CENTER);
        panelImagen.add(imagen, BorderLayout.CENTER);

        contenido.add(panelImagen);

        // Texto
        JLabel lblTexto = new CustomLabel(texto, 24);
        lblTexto.setAlignmentX(CENTER_ALIGNMENT);
        contenido.add(Box.createVerticalStrut(10));
        contenido.add(lblTexto);

        // Añadir contenido fijo
        add(Box.createVerticalGlue());
        add(contenido);
        add(Box.createVerticalGlue());
    }

    @Override
    public Dimension getMaximumSize() {
        return fixedSize;
    }
}