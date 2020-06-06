package controller;

import model.*;
import view.Mostrar;
import view.Validaciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;

public class ActoresControlador {

    public static void actoresSiempreMismoGenero(ArrayList<Audiovisuales> audiovisuales, TreeSet<Actores> actores) {
        /*Cantidad de actores que solamente participan de series o películas de un mismo género.*/

        TreeSet<Actores> actoresMonotonos = new TreeSet<>();

        Actores actor;
        Generos genero;
        boolean primerGenero;
        boolean distintoGenero;
        int cantidad = 0;

        Iterator<Actores> iteratorActores = actores.iterator();
        while (iteratorActores.hasNext()) {
            actor = iteratorActores.next();

            primerGenero = true;
            distintoGenero = false;
            genero = null;

            for (Audiovisuales audiovisual : audiovisuales
            ) {
                if (audiovisual.getActores().contains(actor)) {
                    if (primerGenero) {
                        genero = audiovisual.getGenero();
                        primerGenero = false;
                    }
                    if (audiovisual.getGenero() != genero) {
                        distintoGenero = true;
                        break;
                    }
                }
            }
            if (!distintoGenero){
                actoresMonotonos.add(actor);
            }
        }

        if (!actoresMonotonos.isEmpty()) {
            iteratorActores = actoresMonotonos.iterator();
            while (iteratorActores.hasNext()) {
                actor = iteratorActores.next();
                cantidad++;
            }
        }

        Mostrar.mostrar("Cantidad de actores que solamente participan de series o peliculas de un mismo genero: " + cantidad);
    }

    public static void actricesRecientes(ArrayList<Audiovisuales> audiovisuales, TreeSet<Actores> actores) {
        /*Apellido y nombre de las actrices que hayan filmado una pelicula en los ultimos 2 anios.*/

        Actores actor;

        Calendar dosAniosAntes = Calendar.getInstance();
        Validaciones.dosAniosAntes(dosAniosAntes);
        boolean cumple = false;

        for (Audiovisuales audiovisual : audiovisuales) {
            if (audiovisual instanceof Peliculas && ((Peliculas) audiovisual).getAnioFilm() > dosAniosAntes.get(Calendar.YEAR)) {

                Iterator<Actores> iteratorActores = audiovisual.getActores().iterator();
                while (iteratorActores.hasNext()) {
                    actor = iteratorActores.next();

                    if (actor.getSexo() == 'F') {
                        Mostrar.mostrar("Apellido y nombre: " + actor.getApellido() + " " + actor.getNombre());
                        cumple = true;
                    }
                }
            }
        }
        if (!cumple){
            Mostrar.mostrar("No hay actrices que hayan filmado una pelicula en los iltimos 2 anios");
        }
    }

    public static Actores buscarActor(String nombreActor, String apellidoActor, TreeSet<Actores> actoresTXT) {

    	Actores actor;
        Iterator<Actores> iteratorActores = actoresTXT.iterator();
        while (iteratorActores.hasNext()) {
        	actor = iteratorActores.next();

            if (actor.getApellido().equals(apellidoActor) && actor.getNombre().equals(nombreActor)) {
                return actor;
            }
        }
        return null;
    }
}
