package controller;

import model.*;
import model.DAO.ActoresTXT;
import model.DAO.AudiovisualesJSON;
import model.DAO.GenerosTXT;
import model.DAO.SuscriptoresTXT;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TreeSet;

public class controlador {

    ArrayList<Audiovisuales> audiovisuales = new ArrayList<Audiovisuales>();
    TreeSet<Actores> actores = new TreeSet<Actores>();
    ArrayList<Generos> generos = new ArrayList<Generos>();
    TreeSet<Suscriptores> suscriptores = new TreeSet<Suscriptores>();

    CalificacionesControlador calificacionesControlador = new CalificacionesControlador();

    public void agregarActoresAMemoria () {
        
        ActoresTXT actoresTXT = new ActoresTXT();
        actoresTXT.bajarActores(actores);

    }

    public void agregarGenerosAMemoria () {

        GenerosTXT generosTXT = new GenerosTXT();
        generosTXT.bajarGeneros(generos);

    }

    public void agregarSuscriptoresAMemoria () {

        SuscriptoresTXT suscriptoresTXT = new SuscriptoresTXT();
        suscriptoresTXT.bajarSuscriptores(suscriptores);

    }

    public void agregarAudiovisualesAObjeto () throws Exception {

        AudiovisualesJSON audiovisualesJSON = new AudiovisualesJSON();
        audiovisualesJSON.bajarAudiovisuales(audiovisuales, actores);

    }

    public void opcion1(){
        calificacionesControlador.calificar(audiovisuales, suscriptores);
    }
}
