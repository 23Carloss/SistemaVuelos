package styles;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.text.ParseException;

public class TxtFieldFormat extends JFormattedTextField {

    private String txtPlaceholder;
    private boolean fondo;
    private int fontSize;
    private int width;
    private int height;
    private int tipo; // 1 = números, 2 = formato hora

    public TxtFieldFormat(int tipo, String txtPlaceholder, boolean fondo, int width, int height, int fontSize) {
        super(tipo == 2 ? createMask() : null);
        this.tipo = tipo;
        this.txtPlaceholder = txtPlaceholder;
        this.fondo = fondo;
        this.width = width;
        this.height = height;

        setPreferredSize(new Dimension(width, height));
        setBorder(new RoundBorder(Style.CORNER_RADIUS_TXT));
        setOpaque(false);
        setForeground(Color.black);
        setCaretColor(Color.black);

        // Si es tipo 1 (solo números), agregamos un filtro
        if (tipo == 1) {
            setDocument(new NumericDocument());
        }

        //setFont(FuenteUtil.cargarFuenteInter(fontSize, "Inter_Regular"));
    }

    /** Máscara ##:## para horas */
    private static MaskFormatter createMask() {
        try {
            MaskFormatter mf = new MaskFormatter("##:##");
            mf.setPlaceholderCharacter('_');
            return mf;
        } catch (ParseException e) {
            throw new RuntimeException("Error creando máscara de hora", e);
        }
    }

    /** Document que solo permite números (para tipo 1) */
    private static class NumericDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) return;
            if (str.matches("\\d+")) { // solo números
                super.insertString(offs, str, a);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        String text = getText().replace("_", "").trim();
        boolean empty = text.isEmpty() || text.equals(":");

        if (empty) {
            g2d.setColor(Color.white);

            if (fondo) {
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(),
                        Style.CORNER_RADIUS_TXT, Style.CORNER_RADIUS_TXT);
                g2d.setColor(Style.COLOR_TXT_PH);
            }

            // placeholder
            g2d.drawString(txtPlaceholder, 15, getHeight() / 2 + 6);
        } else {
            if (fondo) {
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.white);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(),
                        Style.CORNER_RADIUS_TXT, Style.CORNER_RADIUS_TXT);
            }

            super.paintComponent(g);
        }

        g2d.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
