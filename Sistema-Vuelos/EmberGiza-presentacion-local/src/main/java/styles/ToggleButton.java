package styles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToggleButton extends JPanel {

    Style style = new Style();
    private boolean disponible;
    private boolean seleccionado = false;
    //private String nombre;
    String columna;
    int fila;
    private JPanel innerPanel;

    //Ajustes de tamaño
    int sizeEx = 80;
    int sizeIn = 50;
    static int sizeBorde = 5;

    public ToggleButton(boolean disponible, String columna, int fila) {
        this.disponible = disponible;
        this.columna = columna;
        this.fila = fila;

        setOpaque(false);
        setPreferredSize(new Dimension(sizeEx, sizeEx));   // Cuadrado externo
        setLayout(new GridBagLayout());            // Para centrar el panel interior

        innerPanel = new RoundedPanel(20);         // 20px de radio para esquinas redondas
        innerPanel.setPreferredSize(new Dimension(sizeIn, sizeIn));
        innerPanel.setOpaque(false);

        add(innerPanel);

        actualizarEstadoVisual();

        // Click solo si está disponible
        if (disponible) {
            innerPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    seleccionado = !seleccionado;
                    actualizarEstadoVisual();
                }
            });
        }
    }

    private void actualizarEstadoVisual() {
        if (!disponible) {
            innerPanel.setBackground(style.cafeEG);
            innerPanel.repaint();
        } else {
            if (seleccionado) {
                // Disponible y seleccionado → color sólido + borde visible
                innerPanel.setBackground(style.seleccion);
            } else {
                innerPanel.setBackground(new Color(0, 0, 0, 0)); // transparente
            }
            innerPanel.repaint();
        }
    }
    /*
    public String getNombre() {
        return nombre;
    }*/
    public String getColumna() {
        return columna;
    }
    public int getFila() {
        return fila;
    }
    public boolean isSeleccionado() {
        return seleccionado;
    }

    //Esquinas redondas
    private static class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo si tiene color
            if (getBackground().getAlpha() > 0) {
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            }

            // Borde siempre visible
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(sizeBorde));
            g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, radius, radius);

            g2.dispose();
        }
    }
}
