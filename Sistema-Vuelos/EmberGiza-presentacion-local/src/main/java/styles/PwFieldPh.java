package styles;

import javax.swing.*;
import java.awt.*;

public class PwFieldPh extends JPasswordField {

    private String txtPlaceholder;
    private boolean fondo;
    private int fontSize;
    private int width;
    private int height;

    public PwFieldPh(String txtPlaceholder, boolean fondo, int width, int height, int fontSize) {
        this.txtPlaceholder = txtPlaceholder;
        this.fondo = fondo;
        this.width = width;
        this.height = height;
        //setFont(FuenteUtil.cargarFuenteInter(fontSize, "Inter_Regular"));
        setPreferredSize(new Dimension(width, height));
        setBorder(new RoundBorder(Style.CORNER_RADIUS_TXT));
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        //if (getText().isEmpty()) {
        Graphics2D g2d = (Graphics2D) g.create();
        if (getText().isEmpty()) {
            g2d.setColor(Color.white);
            if (fondo) {
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), Style.CORNER_RADIUS_TXT, Style.CORNER_RADIUS_TXT);
                g2d.setColor(Style.COLOR_TXT_PH);

            }
            g2d.drawString(txtPlaceholder, 15, getHeight() / 2 + 6);
        } else {
            if (fondo) {
                //Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.white);
                if (fondo) {
                    g2d.fillRoundRect(0, 0, getWidth(), getHeight(), Style.CORNER_RADIUS_TXT, Style.CORNER_RADIUS_TXT);
                    g2d.setColor(Style.COLOR_TXT_PH);
                }
                //g2d.setFont(FuenteUtil.cargarFuenteInter(16, "Inter_Regular"));
                //g2d.drawString(txtPlaceholder, 15, getHeight() / 2 + 6);
                g2d.dispose();
            }
            super.paintComponent(g);
        }
    }
}