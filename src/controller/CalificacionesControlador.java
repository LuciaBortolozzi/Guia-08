package controller;

import model.Audiovisuales;
import model.DAO.CalificacionesTXT;
import model.Peliculas;
import model.Series;
import model.Suscriptores;

import view.Mostrar;
import view.Validaciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;

public class CalificacionesControlador {

    public static void calificar(ArrayList<Audiovisuales> audiovisuales, TreeSet<Suscriptores> suscriptores) {

        Audiovisuales audiovisual;
        Suscriptores suscriptor;
        long dni = Validaciones.validarLong("documento: ");
        int tipo = Validaciones.tipo("1 si es pelicula o 2 si es serie: ");
        String nombreAudiovisual = Validaciones.ingresar("nombre del audiovisual: ", true).toUpperCase();
        int estrellas;
        String motivo;
        boolean califico = false;
        Calendar fechaRealizada = Calendar.getInstance();

        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();

            if (suscriptor.getNroDoc() == dni) {                                                                        // Encontro suscriptor

                Mostrar.mostrar("Ingresar estrellas: ");
                estrellas = Validaciones.limite(1, 5);
                motivo = Validaciones.ingresar("motivo de la calificacion propuesta: ", true);

                if (tipo == 1 ) {

                    Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
                    while (iteratorAudiovisuales.hasNext()) {
                        audiovisual = iteratorAudiovisuales.next();

                        if (audiovisual instanceof Peliculas
                                && audiovisual.getNombre().equals(nombreAudiovisual)) {                                 // Encontro pelicula
                            audiovisual.setCalificaciones(estrellas, motivo, fechaRealizada, suscriptor);
                            califico = true;
                            break;
                        }
                    }

                } else if (tipo == 2) {

                    int temporada = Validaciones.validarInt("temporada: ");
                    int episodio = Validaciones.validarInt("episodio: ");

                    Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
                    while (iteratorAudiovisuales.hasNext()) {
                        audiovisual = iteratorAudiovisuales.next();

                        if (audiovisual instanceof Series
                                && audiovisual.getNombre().equals(nombreAudiovisual)
                                && ((Series) audiovisual).getTemporada() == temporada
                                && ((Series) audiovisual).getEpisodio() == episodio) {                                  // Encontro serie

                            audiovisual.setCalificaciones(estrellas, motivo, fechaRealizada, suscriptor);
                            califico = true;
                            break;

                        }
                    }
                }
            }
        }
        if (califico){
            CalificacionesTXT.grabarCalificacionesTXT(audiovisuales);
        } else {
            Mostrar.mostrar("No se pudo calificar");
        }
    }
}
