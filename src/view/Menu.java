package view;

import controller.AudiovisualesControlador;
import model.Audiovisuales;

public class Menu {

    public void menu(String arg){
        do {
            Mostrar.mostrar("MENU");

            Mostrar.mostrar("1. Ingresar .\n");

            Mostrar.mostrar("4. Salir del menu");

            int opcion = Validaciones.limite(0, 9);

            switch (opcion){
                case 0:
                    break;
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
