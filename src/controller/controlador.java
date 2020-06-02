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

    static {
        try {
        	audiovisualesTXT = AudiovisualesActoresTXT.bajarAudiovisualesActoresTXT(audiovisualesTXT, actoresTXT);
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

    static {
        try {
        	audiovisualesTXT = CalificacionesTXT.bajarCalificaciones(audiovisualesTXT, suscriptores);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        try {
        	audiovisualesTXT = CronogramaPagosTXT.bajarCronogramas(audiovisualesTXT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public controlador() throws Exception {
    }


    public static void opcion0() throws Exception {
        CalificacionesControlador.calificar(audiovisualesTXT, suscriptores);
    }

    public static void opcion1() throws Exception {
        SuscriptorControlador.recomendarMejorSerie(audiovisualesTXT);
    }

    public static void opcion2() throws Exception {
        SuscriptorControlador.recomendarMejorPelicula(audiovisualesTXT, generos);
    }

    public static void opcion3(){}

    public static void opcion4() throws Exception {
        SuscriptorControlador.mayoresSinCalificar(audiovisualesTXT, suscriptores);
    }

    public static void opcion5() throws Exception {
        AudiovisualesControlador.peliculaAlAzar(audiovisualesTXT);
    }

    public static void opcion6() throws Exception {
        AudiovisualesControlador.serieNoCalificadaPorHombresAdultos(audiovisualesTXT);
    }

    public static void opcion7() throws Exception {
        ActoresControlador.actoresSiempreMismoGenero(audiovisualesTXT, actoresTXT);
    }

    public static void opcion8(){}

    public static void opcion9() throws Exception {
        ActoresControlador.actricesRecientes(audiovisualesTXT, actoresTXT);
    }
    
    public static void opcion10() throws Exception {
        AudiovisualesControlador.asignarPagos(audiovisualesTXT);
    }

}
