package controller;

import model.*;
import model.DAO.*;

import java.util.ArrayList;
import java.util.TreeSet;

public class controlador {

    static ArrayList<Generos> generos = GenerosTXT.bajarGeneros();
    static TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();
    // Arreglar lo de los audiovisuales

    static ArrayList<Audiovisuales> audiovisualesTXT;

    static {
        try {
            audiovisualesTXT = AudiovisualesTXT.bajarAudiovisualesTXT(generos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static TreeSet<Actores> actoresTXT = ActoresTXT.bajarActoresTXT();

    static TreeSet<Actores> actoresJSON;

    static {
        try {
            actoresJSON = AudiovisualesActoresTXT.bajarAudiovisualesActoresTXT(audiovisualesTXT, actoresTXT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Audiovisuales> audiovisualesJSON;

    static {
        try {
            audiovisualesJSON = AudiovisualesJSON.bajarAudiovisualesJSON(audiovisualesTXT, actoresTXT, generos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static ArrayList<Audiovisuales> audiovisuales;

    static {
        try {
            audiovisuales = CalificacionesTXT.bajarCalificaciones(audiovisualesTXT, audiovisualesJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Falta el de asignar pagos

    public controlador() throws Exception {
    }


    public static void opcion0() throws Exception {
        CalificacionesControlador.calificar(audiovisuales, suscriptores);
    }

    public static void opcion1() throws Exception {
        SuscriptorControlador.recomendarMejorSerie(audiovisuales);
    }

    public static void opcion2() throws Exception {
        SuscriptorControlador.recomendarMejorPelicula(audiovisuales, generos);
    }

    public static void opcion3(){}

    public static void opcion4() throws Exception {
        SuscriptorControlador.mayoresSinCalificar(audiovisuales, suscriptores);
    }

    public static void opcion5() throws Exception {
        AudiovisualesControlador.peliculaAlAzar(audiovisuales);
    }

    public static void opcion6() throws Exception {
        AudiovisualesControlador.serieNoCalificadaPorHombresAdultos(audiovisuales);
    }

    public static void opcion7() throws Exception {
        ActoresControlador.actoresSiempreMismoGenero(audiovisuales, actores);
    }

    public static void opcion8(){}

    public static void opcion9() throws Exception {
        ActoresControlador.actricesRecientes(audiovisuales, actores);
    }

}
