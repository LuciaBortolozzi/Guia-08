package view;

import controller.Controlador;

public class Menu {

    public void menu(String arg) {
        Controlador controlador = new Controlador();
        Controlador.grabarArchivosAudiovisuales();

        do {
            Mostrar.mostrar("MENU");

            Mostrar.mostrar("0. Calificar un audiovisual.\n");

            Mostrar.mostrar("1. Crear cronograma de pagos\n");

            Mostrar.mostrar("2. A todos los suscriptores jovenes (menores de 35 anios), se les recomienda la temporada " +
                                    "completa de la serie con mejor calificacion promedio durante los ultimos 3 meses, evaluada " +
                                    "por quienes cumplan con el mismo rango de edad, mediante la generacion de un archivo " +
                                    "Json. Este archivo incluye el nombre de la empresa, nombre de la serie, genero, nomina de " +
                                    "actores, sinopsis, temporada, cantidad de episodios y su calificacion.\n");

            Mostrar.mostrar("3. Para cada uno de los generos existentes, la pelicula con mejor calificacion obtenida en el " +
                                    "ultimo mes es recomendada a todos los suscriptores mayores de 55 anios, mediante otro " +
                                    "archivo JSon con la estructura similar a la de las series.\n");

            Mostrar.mostrar("4. Para cada uno de los distintos generos, nombre de la serie, cantidad total de temporadas " +
                                    "y cantidad de actores, ordenadas segun la cantidad total de temporadas en forma " +
                                    "descendente.\n");

            Mostrar.mostrar("5. Nombre y apellido de los suscriptores mayores de 60 anios que nunca hayan calificado " +
                                    "una pelicula.\n");

            Mostrar.mostrar("6. Apellido y nombre de los actores (ordenados por ambos), duracion de una pelicula, fecha " +
                                    "de publicacion y evaluaciones (fecha, nombre del suscriptor y calificacion) de una " +
                                    "pelicula seleccionada al azar.\n");

            Mostrar.mostrar("7. Codigo y nombre de serie, temporada y episodio de aquellas que no hayan sido " +
                                    "calificadas por ningun suscriptor masculino adulto menor de 45 anios.\n");

            Mostrar.mostrar("8. Cantidad de actores que solamente participan de series o peliculas de un mismo genero.\n");

            Mostrar.mostrar("9. Nombre y genero de las publicaciones cuyo monto abonado en concepto de renovacion" +
                                    "de derechos de publicacion supere en un 10% el valor ingresado como argumento de la" +
                                    "aplicacion y todas sus calificaciones del ultimo anio sean menores a 3 estrellas.\n");

            Mostrar.mostrar("10. Apellido y nombre de las actrices que hayan filmado una pelicula en los ultimos 2 anios.\n");

            Mostrar.mostrar("11. Salir del menu");

            int opcion = Validaciones.limite(0, 11);

            switch (opcion){
                case 0:
                    Controlador.opcion0();
                    break;
                case 1:
                    Controlador.opcion1();
                    break;
                case 2:
                    Controlador.opcion2();
                    break;
                case 3:
                    Controlador.opcion3();
                    break;
                case 4:
                    Controlador.opcion4();
                    break;
                case 5:
                    Controlador.opcion5();
                    break;
                case 6:
                    Controlador.opcion6();
                    break;
                case 7:
                    Controlador.opcion7();
                    break;
                case 8:
                	Controlador.opcion8();
                    break;
                case 9:
                    Controlador.opcion9(arg);
                    break;
                case 10:
                    Controlador.opcion10();
                    break;
                case 11:
                    System.exit(0);
                    break;
            }
        } while (true);
    }
}
