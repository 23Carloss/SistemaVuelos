package styles;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//FALTA
//Añadir que el tamaño se pueda cambiar segun la necesidad

public class CustomButton extends JButton {

    private boolean hovered = false;
    private int CORNER_RADIUS_BUTTON;
    private Color color;
    private Color hoverColor;
    private boolean borde = false;
    private int width;
    private int height;
    Style style = new Style();

    //Constructo original medio modificado
    public CustomButton(String text, Color color, Color colorTexto, int width, int height, int fontSize, int cornerRadius) {
        super(text);
        this.width = width;
        this.height = height;
        this.CORNER_RADIUS_BUTTON = cornerRadius;
        //color = Color.WHITE;
        this.color = color;
        //hoverColor = Style.BUTTON_COLOR_HOVER;
        //setFont(FuenteUtil.cargarFuenteInter(fontSize, "Inter_Bold"));
        //setForeground(Color.black);
        setForeground(colorTexto);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
        setUI(new BasicButtonUI());

    }

    //Constructor boton verde base
    public CustomButton(String text) {
        super(text);
        this.width = 125;
        this.height = 60;
        this.CORNER_RADIUS_BUTTON = 25;
        color = style.cafeEG;
        hoverColor = style.cafeEGhover;

        setForeground(Color.white);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
        setUI(new BasicButtonUI());

    }

    //Constructor para boton según tipo
    //1 = color base, 2 = blanco
    public CustomButton(String text, int tipo, int width, int height) {
        super(text);
        this.width = width;
        this.height = height;
        CORNER_RADIUS_BUTTON = 25;

        //cambiar esto mayhaps
        if (tipo == 1) {
            color = style.cafeEG;
            hoverColor = style.cafeEGhover;
            setForeground(Color.white);
        }

        if (tipo == 2) {
            color = Color.white;
            hoverColor = style.hoverBlanco;
            setForeground(Color.black);
        }

        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
        setUI(new BasicButtonUI());

    }


    public void setNewColor(Color newColor, Color newhoverColor, Color txtColor){
        color = newColor;
        hoverColor = newhoverColor;
        setForeground(txtColor);
    }

    public void crearBorder(){
        borde = true;
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(hovered ? hoverColor : color);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS_BUTTON, CORNER_RADIUS_BUTTON);
        if (borde){
            g2d.setStroke(new BasicStroke(.5f));
            g2d.setColor(Color.white);
            g2d.drawRoundRect(0, 0, getWidth(), getHeight(), CORNER_RADIUS_BUTTON, CORNER_RADIUS_BUTTON);
        }
        g2d.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, CORNER_RADIUS_BUTTON, CORNER_RADIUS_BUTTON);
        super.paintComponent(g);
        g2d.dispose();

    }

    @Override
    public void updateUI() {
        setUI(new BasicButtonUI());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }


}
