package controller;


import model.*;
import model.DAO.GenerosTXT;
import model.DAO.SuscriptoresTXT;
import view.Mostrar;
import view.Validaciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;

public class SuscriptorControlador {

    public static Suscriptores buscarSuscriptor(int codSuscriptor){

        TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();
        Suscriptores suscriptor;

        Iterator<Suscriptores> iteratorAudiovisuales = suscriptores.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            suscriptor = iteratorAudiovisuales.next();

            if (suscriptor.getCodigo() == codSuscriptor){
                return suscriptor;
            }
        }
        return null;
    }

    public static Audiovisuales recomendarMejorSerie() throws Exception {

        /*A todos los suscriptores jóvenes (menores de 35 años), se les recomienda la temporada
        completa de la serie con mejor calificación promedio durante los últimos 3 meses, evaluada
        por quienes cumplan con el mismo rango de edad, mediante la generación de un archivo
        Json. Este archivo incluye el nombre de la empresa, nombre de la serie, género, nómina de
        actores, sinopsis, temporada, cantidad de episodios y su calificación.*/

        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaAnterior = Validaciones.tresMesesAntes(fechaActual);

        ArrayList<Audiovisuales> audiovisuales = AudiovisualesControlador.ingresarModificarAudiovisual();               // Modificar para sea despues de pagos!!!!

        int sumaEstrellas = 0;
        int cantidadCalificaciones = 0;
        int promedioTemporada;
        int mejorPromedio = -1;
        boolean menorA35;

        Audiovisuales mejorAudiovisual = audiovisuales.get(0);

        // Primero recorrer audiovisuales para saber cantidad de episodios por temporada

        for (Audiovisuales audiovisual : audiovisuales) {

            if (audiovisual instanceof Series && audiovisual.getFechaPubli().after(fechaAnterior)) {                    // Es serie y fue publicada en los ultimos 3 meses

                for (Calificaciones calificacion : audiovisual.getCalificaciones()) {

                    menorA35 = Validaciones.menor(calificacion.getSuscriptor().getFechaDeNac(), fechaActual, 35);
                    if (menorA35) {                                                                                     // Suscriptor es menor de 35?
                        sumaEstrellas += calificacion.getEstrellas();
                        cantidadCalificaciones++;
                    }

                }
            }
            if (cantidadCalificaciones != 0) {
                promedioTemporada = sumaEstrellas / cantidadCalificaciones;

                if (promedioTemporada > mejorPromedio) {
                    mejorPromedio = promedioTemporada;
                    mejorAudiovisual = audiovisual;
                }
            }
            sumaEstrellas = 0;
            cantidadCalificaciones = 0;
        }

        return mejorAudiovisual;
    }

    public static Audiovisuales recomendarMejorPelicula() throws Exception {

        /*Para cada uno de los géneros existentes, la película con mejor calificación obtenida en el
        último mes es recomendada a todos los suscriptores mayores de 55 años, mediante otro
        archivo JSon con la estructura similar a la de las series.*/

        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaAnterior = Validaciones.ultimoMes(fechaActual);

        ArrayList<Audiovisuales> audiovisuales = AudiovisualesControlador.ingresarModificarAudiovisual();               // Modificar para sea despues de pagos!!!!
        ArrayList<Generos> generos = GenerosTXT.bajarGeneros();

        int sumaEstrellas = 0;
        int cantidadCalificaciones = 0;
        int promedio;
        int mejorPromedio = -1;
        boolean mayorA55;

        Audiovisuales mejorAudiovisual = audiovisuales.get(0);

        for (Generos genero: generos
        ) {
            for (Audiovisuales audiovisual : audiovisuales) {

                if (audiovisual instanceof Peliculas                                                                    // Es pelicula y fue publicada en el ultimo mes
                        && audiovisual.getFechaPubli().after(fechaAnterior)
                        && genero.getCodigo() == audiovisual.getGenero().getCodigo()) {

                    for (Calificaciones calificacion : audiovisual.getCalificaciones()) {

                        mayorA55 = Validaciones.mayor(calificacion.getSuscriptor().getFechaDeNac(), fechaActual, 55);
                        if (mayorA55) {                                                                                     // Suscriptor es mayor de 55?
                            sumaEstrellas += calificacion.getEstrellas();
                            cantidadCalificaciones++;
                        }

                    }

                }

                if (cantidadCalificaciones != 0) {
                    promedio = sumaEstrellas / cantidadCalificaciones;
                    if (promedio > mejorPromedio) {
                        mejorPromedio = promedio;
                        mejorAudiovisual = audiovisual;
                    }
                }
                sumaEstrellas = 0;
                cantidadCalificaciones = 0;
            }
        }

        return mejorAudiovisual;
    }

    public static void mayoresSinCalificar() throws Exception {

        /*Nombre y apellido de los suscriptores mayores de 60 años que nunca hayan calificado
        una película.*/

        TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();
        TreeSet<Suscriptores> suscriptoresMayores = new TreeSet<Suscriptores>();

        Calendar fechaActual = Calendar.getInstance();

        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();
            if (Validaciones.mayor(suscriptor.getFechaDeNac(), fechaActual,60)){
                suscriptoresMayores.add(suscriptor);                                                                    // Colecto los mayores a 60 = no colecto los menores de 60
            }
        }

        ArrayList<Audiovisuales> audiovisuales = AudiovisualesControlador.ingresarModificarAudiovisual();               // Modificar para sea despues de pagos!!!!

        for (Audiovisuales audiovisual : audiovisuales) {

            if (audiovisual instanceof Peliculas && audiovisual.getCalificaciones() != null) {                           // Si fue calificado, recorro las calificaciones

                for (Calificaciones calificacion : audiovisual.getCalificaciones()) {
                    suscriptoresMayores.remove(calificacion.getSuscriptor());                                           // Quito los mayores que hayan calificado
                }
            }
        }

        mostrarSuscriptores(suscriptoresMayores);
    }

    public static void mostrarSuscriptores(TreeSet<Suscriptores> suscriptores){
        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();

            Mostrar.mostrar(suscriptor.getNombre() + suscriptor.getApellido());
        }
    }
}
