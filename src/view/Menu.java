package view;

import controller.AudiovisualesControlador;
import controller.SuscriptorControlador;
import model.Audiovisuales;

public class Menu {

    public void menu(String arg) throws Exception {
        do {
            Mostrar.mostrar("MENU");

            Mostrar.mostrar("1. Ingresar .\n");

            Mostrar.mostrar("2. ");

            Mostrar.mostrar("3. Para cada uno de los distintos géneros, nombre de la serie, cantidad total de temporadas " +
                                    "y cantidad de actores, ordenadas según la cantidad total de temporadas en forma " +
                                    "descendente.\n");

            Mostrar.mostrar("4. Nombre y apellido de los suscriptores mayores de 60 años que nunca hayan calificado " +
                                    "una película.\n");

            Mostrar.mostrar("5. Apellido y nombre de los actores (ordenados por ambos), duración de una película, fecha " +
                                    "de publicación y evaluaciones (fecha, nombre del suscriptor y calificación) de una " +
                                    "película seleccionada al azar.\n");

            Mostrar.mostrar("6. Código y nombre de serie, temporada y episodio de aquellas que no hayan sido " +
                                    "calificadas por ningún suscriptor masculino adulto menor de 45 años.\n");

            Mostrar.mostrar("7. Cantidad de actores que solamente participan de series o películas de un mismo género.\n");

            Mostrar.mostrar("8. Nombre y género de las publicaciones cuyo monto abonado en concepto de renovación" +
                                    "de derechos de publicación supere en un 10% el valor ingresado como argumento de la" +
                                    "publicación y todas sus calificaciones del último año sean menores a 3 estrellas.\n");

            Mostrar.mostrar("9. Apellido y nombre de las actrices que hayan filmado una película en los últimos 6 meses.\n");

            Mostrar.mostrar("10. Salir del menu");

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
                    SuscriptorControlador.mayoresSinCalificar();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
