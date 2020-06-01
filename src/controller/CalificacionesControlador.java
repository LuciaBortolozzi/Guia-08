package controller;

import model.Audiovisuales;
import model.DAO.AudiovisualesJSON;
import model.DAO.CalificacionesTXT;
import model.DAO.SuscriptoresTXT;
import model.Suscriptores;

import view.Mostrar;
import view.Validaciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;

public class CalificacionesControlador {

    public static void calificar() throws Exception {

        Mostrar.mostrar("Calificar");

        String nombreAudiovisual = Validaciones.ingresar("nombre del audiovisual: ", true);
        String nombreSuscriptor = Validaciones.ingresar("nombre del suscriptor: ", true);

        ArrayList<Audiovisuales> audiovisuales = AudiovisualesJSON.bajarAudiovisualesJSON();
        TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();

        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();

        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();

            if (audiovisual.getNombre().equals(nombreAudiovisual)){             // Encontro audiovisual

                while (iteratorSuscriptores.hasNext()) {
                    suscriptor = iteratorSuscriptores.next();

                    if (suscriptor.getNombre().equals(nombreSuscriptor)){       // Encontro suscriptor

                        Mostrar.mostrar("Ingresar estrellas: ");
                        int estrellas = Validaciones.limite(1,5);

                        String motivo = Validaciones.ingresar("motivo de la calificacion propuesta: ", true);
                        Calendar fechaRealizada = Calendar.getInstance();

                        audiovisual.setCalificaciones(estrellas, motivo, fechaRealizada, suscriptor);
                    }

                }
            }
        }

        CalificacionesTXT.grabarCalificacionesTXT(audiovisuales);
    }

}
