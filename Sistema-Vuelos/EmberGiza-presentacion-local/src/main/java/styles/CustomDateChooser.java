package styles;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomDateChooser extends JDateChooser {

    private boolean hovered = false;
    private int cornerRadius = 25;
    private Color color = Color.white;
    private Color hoverColor = new Color(230,230,230);
    private Color textColor = Color.black;
    private int width = 200;
    private int height = 50;

    private JButton btnCalendar;
    private JTextField txtField;

    public CustomDateChooser() {
        super();
        initUI();
    }

    public CustomDateChooser(Color color, Color hoverColor, Color textColor, int width, int height, int cornerRadius) {
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
        setPreferredSize(new Dimension(width, height));
        setBorder(null);

        // TextField interno
        txtField = (JTextField) getDateEditor().getUiComponent();
        txtField.setBorder(null);
        txtField.setOpaque(false);
        txtField.setForeground(textColor);
        txtField.setBackground(new Color(0,0,0,0));
        txtField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        txtField.setCaretColor(textColor);
        txtField.setPreferredSize(new Dimension(width - 50, height));

        // Botón de calendario
        btnCalendar = getCalendarButton();
        styleCalendarButton();

        // Hover del componente completo
        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { hovered = true; repaint(); }
            @Override public void mouseExited(MouseEvent e) { hovered = false; repaint(); }
        });

        revalidate();
        repaint();
    }

    private void styleCalendarButton() {
        btnCalendar.setBorder(null);
        btnCalendar.setFocusPainted(false);
        btnCalendar.setContentAreaFilled(false);
        btnCalendar.setOpaque(false);
        btnCalendar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCalendar.setPreferredSize(new Dimension(35, 35));

        // Pintamos un ícono tipo "flecha" como en CustomCbox
        btnCalendar.setIcon(new Icon() {
            @Override
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = c.getWidth();
                int h = c.getHeight();

                g2.setColor(Color.black);

                int[] px = { w/2 - 5, w/2 + 5, w/2 };
                int[] py = { h/2 - 3, h/2 - 3, h/2 + 4 };

                g2.fillPolygon(px, py, 3);
                g2.dispose();
            }

            @Override public int getIconWidth() { return 20; }
            @Override public int getIconHeight() { return 20; }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(hovered ? hoverColor : color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
