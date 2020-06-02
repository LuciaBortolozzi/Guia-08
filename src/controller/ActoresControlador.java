package controller;

import model.*;
import model.DAO.ActoresTXT;
import model.DAO.CalificacionesTXT;
import model.DAO.GenerosTXT;
import view.Mostrar;
import view.Validaciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;

public class ActoresControlador {

    public static void actoresSiempreMismoGenero(ArrayList<Audiovisuales> audiovisuales, TreeSet<Actores> actores) throws Exception {
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

        Mostrar.mostrar("Cantidad de actores que solamente participan de series o películas de un mismo género: " + cantidad);
    }

    public static void actricesRecientes(ArrayList<Audiovisuales> audiovisuales, TreeSet<Actores> actores) throws Exception {
        /*Apellido y nombre de las actrices que hayan filmado una película en los últimos 2 anios.*/

        Actores actor;

        Calendar fechaActual = Calendar.getInstance();
        Calendar seisAniosAntes = Validaciones.dosAniosAntes(fechaActual);


        for (Audiovisuales audiovisual : audiovisuales) {
            if (audiovisual instanceof Peliculas && ((Peliculas) audiovisual).getAnioFilm() > seisAniosAntes.get(Calendar.YEAR)) {

                Iterator<Actores> iteratorActores = actores.iterator();
                while (iteratorActores.hasNext()) {
                    actor = iteratorActores.next();

                    if (actor.getSexo() == 'F') {
                        Mostrar.mostrar(actor.getApellido() + actor.getNombre());
                    }
                }
            }
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
