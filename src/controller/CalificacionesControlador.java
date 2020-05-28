package controller;

import model.Audiovisuales;
import model.DAO.CalificacionesTXT;
import model.Peliculas;
import model.Series;
import model.Suscriptores;
import view.Mostrar;
import view.Validaciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class CalificacionesControlador {

    CalificacionesTXT calificacionesTXT = new CalificacionesTXT();

    public void calificar(ArrayList<Audiovisuales> audiovisuales, TreeSet<Suscriptores> suscriptores){

        // IMPORTANTE: usuario carga suscriptor + calificacion
        // para calificar: usuario ingresa por teclado nombre de peli y suscriptor

        /*Luego de reproducir una película o una serie, el suscriptor tiene la posibilidad de recomendar
        la misma, basado en la calificación de una a cinco estrellas (siendo esta última, la máxima
        puntuación)  indicando  con  un  breve  comentario  del  motivo  de  la  calificación  propuesta.
        Estas  calificaciones  son ingresadas  manualmente  por  el  usuario  de  la  aplicación  a
        desarrollar, quedando almacenadas en el archivo Calificaciones.txt con sus datos separadas
        por “;” (punto y coma) incluyendo la fecha en formato dd/mm/aaaa de la calificación.*/

        Mostrar.mostrar("Calificar");

        String nombreAudiovisual = Validaciones.ingresar("nombre del audiovisual: ", true);
        String nombreSuscriptor = Validaciones.ingresar("nombre del suscriptor: ", true);

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

                        int codAudiovisual = audiovisual.getCodigo();
                        long nroDocSuscriptor = suscriptor.getNroDoc();
                        Mostrar.mostrar("Ingresar estrellas: ");
                        int estrellas = Validaciones.limite(1,5);

                        String motivo = Validaciones.ingresar("motivo de la calificacion propuesta: ", true);
//                        Calendar fechaRealizada

//                        meterEnAudiovisuales();
//                        calificacionesTXT.grabarCalificaciones(codAudiovisual, nroDocSuscriptor, estrellas, motivo, fecha);

                    }

                }
            }
        }

        /*// Audiovisuales o pelicula/serie?
        // asumi que audiovisuales

        int tipoAudiovisual = Validaciones.tipo("(1) si es pelicula o (2) si es serie: ");

        if (tipoAudiovisual == 1){
            Peliculas pelicula;
            Iterator<Peliculas> itAudiovisuales = audiovisuales.iterator();

            while (itAudiovisuales.hasNext()) {
                pelicula = itAudiovisuales.next();

                System.out.println(pelicula.getNombre());
            }
        } else {
            Series serie;
            Iterator<Series> itAudiovisuales = audiovisuales.iterator();

            while (itAudiovisuales.hasNext()) {
                serie = itAudiovisuales.next();

                System.out.println(serie.getNombre());
            }
        }*/





    }
}
