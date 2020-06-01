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

    public static void actoresSiempreMismoGenero() throws Exception {
        /*Cantidad de actores que solamente participan de series o películas de un mismo género.*/

        ArrayList<Audiovisuales> audiovisuales = CalificacionesTXT.bajarCalificaciones();                               // Modificar para sea despues de pagos!!!!
        ArrayList<Generos> generos = GenerosTXT.bajarGeneros();
        TreeSet<Actores> actores = ActoresTXT.bajarActores();
        TreeSet<Actores> actoresMonotonos = new TreeSet<>();

        Actores actor = null;
        boolean cambioGenero;
        int cantidad = 0;

        for (Generos genero: generos
             ) {
            cambioGenero = false;
            for (Audiovisuales audiovisual : audiovisuales) {
                if (audiovisual.getGenero().getCodigo() == genero.getCodigo()){
                    Iterator<Actores> iteratorActores = actores.iterator();
                    while (iteratorActores.hasNext()) {
                        actor = iteratorActores.next();
                        actoresMonotonos.add(actor);
                    }
                } else {
                    cambioGenero = true;
                    actoresMonotonos.remove(actor);
                }
            }
        }
    }

    public static void actricesRecientes() throws Exception {
        /*Apellido y nombre de las actrices que hayan filmado una película en los últimos 2 anios.*/

        ArrayList<Audiovisuales> audiovisuales = CalificacionesTXT.bajarCalificaciones();                               // Modificar para sea despues de pagos!!!!
        TreeSet<Actores> actores = ActoresTXT.bajarActores();
        Actores actor;

        Calendar fechaActual = Calendar.getInstance();
        Calendar seisAniosAntes = Validaciones.dosAniosAntes(fechaActual);


        for (Audiovisuales audiovisual : audiovisuales) {
            if (audiovisual instanceof Peliculas && ((Peliculas) audiovisual).getAnioFilm() > seisAniosAntes.get(Calendar.YEAR)){

                Iterator<Actores> iteratorActores = actores.iterator();
                while (iteratorActores.hasNext()) {
                    actor = iteratorActores.next();

                    if (actor.getSexo() == 'F'){
                        Mostrar.mostrar(actor.getApellido() + actor.getNombre());
                    }
                }
            }
        }
    }
}
