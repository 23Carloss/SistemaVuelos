package styles;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomCbox<E> extends JComboBox<E> {

    private boolean hovered = false;
    private int cornerRadius = 25;
    private Color color = Color.white;
    private Color hoverColor = new Color(230,230,230);
    private Color textColor = Color.black;
    private int width = 200;
    private int height = 50;

    public CustomCbox() {
        super();
        initUI();
    }

    public CustomCbox(Color color, Color hoverColor, Color textColor, int width, int height, int cornerRadius) {
        super();
        this.color = color;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
        this.width = width;
        this.height = height;
        this.cornerRadius = cornerRadius;
        initUI();
    }

    private void initUI() {
        setOpaque(false);
        setFocusable(false);
        setForeground(textColor);
        setBorder(null);
        setBackground(new Color(0,0,0,0)); // evita fondo gris interno
        setPreferredSize(new Dimension(width, height));

        // Renderer para lista y elemento seleccionado
        setRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {

                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                // Mantener colores personalizados
                if (isSelected) {
                    c.setBackground(new Color(230,230,230));
                    c.setForeground(Color.black);
                } else {
                    c.setBackground(Color.white);
                    c.setForeground(Color.black);
                }

                return c;
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { hovered = true; repaint(); }
            @Override public void mouseExited(MouseEvent e) { hovered = false; repaint(); }
        });

        setUI(new CustomUI());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(hovered ? hoverColor : color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Deja que Swing pinte el texto y demás encima
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    // ---------------- CUSTOM UI ----------------
    private class CustomUI extends BasicComboBoxUI {

        @Override
        protected JButton createArrowButton() {
            return new ArrowButton();
        }

        @Override
        public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
            // NO pintar fondo default
        }

        class ArrowButton extends JButton {
            ArrowButton() {
                setBorder(null);
                setContentAreaFilled(false);
                setFocusPainted(false);
                setOpaque(false);
                setPreferredSize(new Dimension(30, 30));
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                g2.setColor(Color.black); // flecha negra siempre

                int[] x = { w/2 - 5, w/2 + 5, w/2 };
                int[] y = { h/2 - 3, h/2 - 3, h/2 + 4 };

                g2.fillPolygon(x, y, 3);
                g2.dispose();
            }
        }
    }
}