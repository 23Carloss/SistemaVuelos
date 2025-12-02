package styles;

import java.awt.*;

public class Style {

    //Constantes
    public static final int cantidadAsientos = 60;

    //Dimensiones
    public static final int frameX = 1080;
    public static final int frameY = 720;

    public static final int dialogX = 720;
    public static final int dialogY = dialogX;

    public static final int imageButtonX = 200;
    public static final int imageButtonY = imageButtonX;

    //public static final int contenidoY = frameY-400;

    public static final Dimension dimensionBase = new Dimension(frameX, frameY);
    public static final Dimension dimensionPanel = new Dimension(frameX, frameY-200);

    //Colores
    public static final Color beigeBase = new Color(230, 210, 190);
    public static final Color cafeEG = new Color(96, 83, 71);
    public static final Color cafeEGhover = new Color(119, 107, 92);
    public static final Color cafeAlt = new Color(198, 162, 125);
    public static final Color cafeAltHover = new Color(205, 167, 131);
    public static final Color seleccion = new Color(253, 165, 0);

    //Fuentes
    public static final int letraSize = 18;
    public static final int letraTituloSize = letraSize + 16;



    //Colores que ya estaban y quizá luego tenga que cambiar
    public static final Color grisBase = new Color(50, 50, 50);
    public static final Color grisHover = new Color(225, 225, 225);
    public static final Color lineaGris = new Color(69, 69, 69);

    public static final Color hoverBlanco = new Color(236, 236, 236);

    public static final Color grisDialog = new Color(213, 213, 213);


    //Otras cosas I guess
    public static final Color BUTTON_COLOR_HOVER  = new Color(218, 218, 218);
    public static final Color COLOR_TXT_PH = new Color(97, 97, 97, 125);
    public static final int CORNER_RADIUS_TXT = 25;

    public Style() {

    }
}