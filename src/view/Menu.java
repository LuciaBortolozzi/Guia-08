package view;

import controller.ActoresControlador;
import controller.AudiovisualesControlador;
import controller.SuscriptorControlador;
import model.Audiovisuales;

public class Menu {

    public void menu(String arg) throws Exception {
        do {
            Mostrar.mostrar("MENU");

            Mostrar.mostrar("0. Ingresar .\n");

            Mostrar.mostrar("1. A todos los suscriptores jóvenes (menores de 35 años), se les recomienda la temporada " +
                                    "completa de la serie con mejor calificación promedio durante los últimos 3 meses, evaluada " +
                                    "por quienes cumplan con el mismo rango de edad, mediante la generación de un archivo " +
                                    "Json. Este archivo incluye el nombre de la empresa, nombre de la serie, género, nómina de " +
                                    "actores, sinopsis, temporada, cantidad de episodios y su calificación.\n");

            Mostrar.mostrar("2. Para cada uno de los géneros existentes, la película con mejor calificación obtenida en el " +
                                    "último mes es recomendada a todos los suscriptores mayores de 55 años, mediante otro " +
                                    "archivo JSon con la estructura similar a la de las series.\n");

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
                    SuscriptorControlador.recomendarMejorSerie();
                    break;
                case 2:
                    SuscriptorControlador.recomendarMejorPelicula();
                    break;
                case 3:
                    break;
                case 4:
                    SuscriptorControlador.mayoresSinCalificar();
                    break;
                case 5:
                    AudiovisualesControlador.peliculaAlAzar();
                    break;
                case 6:
                    AudiovisualesControlador.serieNoCalificadaPorHombresAdultos();
                    break;
                case 7:
                    ActoresControlador.actoresSiempreMismoGenero();
                    break;
                case 8:
                    break;
                case 9:
                    ActoresControlador.actricesRecientes();
                    break;
                case 10:
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
