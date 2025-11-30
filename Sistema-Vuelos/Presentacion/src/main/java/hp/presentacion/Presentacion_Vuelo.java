/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hp.presentacion;

import Apliacion.Control;
import Config.MongoClientProvider;

/**
 *
 * @author HP
 */
public class Presentacion_Vuelo {

    public static void main(String[] args) {
        MongoClientProvider.INSTANCE.init();
        Control control = new Control();
        control.iniciar();
    }
}
