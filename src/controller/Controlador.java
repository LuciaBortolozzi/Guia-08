package controller;

import model.*;
import model.DAO.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Controlador {

    static ArrayList<Generos> generos = GenerosTXT.bajarGeneros();
    static TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();
    static ArrayList<Audiovisuales> audiovisualesTXT = AudiovisualesTXT.bajarAudiovisualesTXT(generos);
    static TreeSet<Actores> actoresTXT = ActoresTXT.bajarActoresTXT();

    static {
        	audiovisualesTXT = AudiovisualesActoresTXT.bajarAudiovisualesActoresTXT(audiovisualesTXT, actoresTXT);
    }

    static {
        audiovisualesTXT = AudiovisualesJSON.bajarAudiovisualesJSON(audiovisualesTXT, actoresTXT, generos);
    }


    static {
        	audiovisualesTXT = CalificacionesTXT.bajarCalificaciones(audiovisualesTXT, suscriptores);
    }

    /*
    static {
        audiovisualesTXT = CronogramaPagosTXT.bajarCronogramas(audiovisualesTXT);
    }*/

    public Controlador() {

    }

    public static void grabarArchivosAudiovisuales(){

        AudiovisualesTXT.grabarAudiovisualesTXT(audiovisualesTXT);
        AudiovisualesActoresTXT.grabarAudiovisualesActoresTXT(audiovisualesTXT);
    }


    public static void opcion0() {
        CalificacionesControlador.calificar(audiovisualesTXT, suscriptores);
    }

    public static void opcion1() {
        AudiovisualesControlador.asignarPagos(audiovisualesTXT);
    }

    public static void opcion2() {
        SuscriptorControlador.recomendarMejorSerie(audiovisualesTXT);
    }

    public static void opcion3() {
        SuscriptorControlador.recomendarMejorPelicula(audiovisualesTXT, generos);
    }

    public static void opcion4(){

    }

    public static void opcion5() {
        SuscriptorControlador.mayoresSinCalificar(audiovisualesTXT, suscriptores);
    }

    public static void opcion6() {
        AudiovisualesControlador.peliculaAlAzar(audiovisualesTXT);
    }

    public static void opcion7() {
        AudiovisualesControlador.serieNoCalificadaPorHombresAdultos(audiovisualesTXT);
    }

    public static void opcion8() {
        ActoresControlador.actoresSiempreMismoGenero(audiovisualesTXT, actoresTXT);
    }

    public static void opcion9(String arg){
    	AudiovisualesControlador.superanAgumento(audiovisualesTXT, arg);
    }

    public static void opcion10() {
        ActoresControlador.actricesRecientes(audiovisualesTXT, actoresTXT);
    }

}
