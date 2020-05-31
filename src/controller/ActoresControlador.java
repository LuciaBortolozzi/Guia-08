package controller;

import model.Actores;
import model.Audiovisuales;
import model.DAO.GenerosTXT;
import model.Generos;
import model.Peliculas;
import view.Mostrar;
import view.Validaciones;

import java.util.ArrayList;
import java.util.Calendar;

public class ActoresControlador {

    public void actoresSiempreMismoGenero(){
        /*Cantidad de actores que solamente participan de series o películas de un mismo género.*/                      // Modificar para sea despues de pagos!!!!

        ArrayList<Generos> generos = GenerosTXT.bajarGeneros();
        for (Generos genero: generos
             ) {

        }
    }

    public void actricesRecientes() throws Exception {
        /*Apellido y nombre de las actrices que hayan filmado una película en los últimos 6 anios.*/

        ArrayList<Audiovisuales> audiovisuales = AudiovisualesControlador.ingresarModificarAudiovisual();               // Modificar para sea despues de pagos!!!!

        Calendar fechaActual = Calendar.getInstance();
        Calendar seisAniosAntes = Validaciones.seisAniosAntes(fechaActual);

        for (Audiovisuales audiovisual : audiovisuales) {
            if (audiovisual instanceof Peliculas && ((Peliculas) audiovisual).getAnioFilm() > seisAniosAntes.get(Calendar.YEAR)){
                for (Actores actor: audiovisual.getActores()
                     ) {
                    Mostrar.mostrar(actor.getApellido() + actor.getNombre());
                }
            }
        }
    }
}
