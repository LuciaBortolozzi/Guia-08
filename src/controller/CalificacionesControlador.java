package controller;

import model.Audiovisuales;
import model.DAO.CalificacionesTXT;
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

        Mostrar.mostrar("Calificar");

        // Corregir
        long dni = Validaciones.validarLong("documento: ");
        String nombreAudiovisual = Validaciones.ingresar("nombre del audiovisual: ", true).toUpperCase();

        Audiovisuales audiovisual;
        Suscriptores suscriptor;

        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();

            if (audiovisual.getNombre().equals(nombreAudiovisual)) {             // Encontro audiovisual

                if (audiovisual instanceof Series) {
                    int temporada = Validaciones.validarInt("temporada: ");
                    int episodio = Validaciones.validarInt("episodio: ");

                    if (((Series) audiovisual).getTemporada() == temporada && ((Series) audiovisual).getEpisodio() == episodio) {

                        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
                        while (iteratorSuscriptores.hasNext()) {
                            suscriptor = iteratorSuscriptores.next();

                            if (suscriptor.getNroDoc() == dni) {                                                        // Encontro suscriptor

                                Calendar fechaRealizada = Calendar.getInstance();
                                Mostrar.mostrar("Ingresar estrellas: ");
                                int estrellas = Validaciones.limite(1, 5);
                                String motivo = Validaciones.ingresar("motivo de la calificacion propuesta: ", true);

                                audiovisual.setCalificaciones(estrellas, motivo, fechaRealizada, suscriptor);
                                break;
                            }
                        }
                    }
                } else {

                    Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
                    while (iteratorSuscriptores.hasNext()) {
                        suscriptor = iteratorSuscriptores.next();

                        if (suscriptor.getNroDoc() == dni) {                                                            // Encontro suscriptor

                            Calendar fechaRealizada = Calendar.getInstance();
                            Mostrar.mostrar("Ingresar estrellas: ");
                            int estrellas = Validaciones.limite(1, 5);
                            String motivo = Validaciones.ingresar("motivo de la calificacion propuesta: ", true);

                            audiovisual.setCalificaciones(estrellas, motivo, fechaRealizada, suscriptor);
                            break;
                        }
                    }
                }
            }
        }

        CalificacionesTXT.grabarCalificacionesTXT(audiovisuales);
    }

}
