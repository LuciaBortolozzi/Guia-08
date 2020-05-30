package controller;

import model.Audiovisuales;

import java.util.Iterator;

public class AudiovisualesControlador {

    public static Audiovisuales buscarAudiovisual(int codAudiovisual) {

        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();

            if (audiovisual.getCodigo() == codAudiovisual){
                return audiovisual;
            }
        }
        return null;
    }

    /*public void mejorCalificacion(ArrayList<Audiovisuales> audiovisuales){

        int cantidadCalificaciones;
        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();


        }
    }*/
}
