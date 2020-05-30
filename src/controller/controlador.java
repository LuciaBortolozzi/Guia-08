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

    public void agregarActoresAMemoria() {

        actores = ActoresTXT.bajarActores();

    }

    public void agregarGenerosAMemoria() {

        generos = GenerosTXT.bajarGeneros();

    }

    public void agregarSuscriptoresAMemoria() {

        suscriptores = SuscriptoresTXT.bajarSuscriptores();

    }

    public void agregarAudiovisualesAObjeto() throws Exception {

        audiovisuales = AudiovisualesJSON.bajarAudiovisuales(actores);

    }

    public void opcion1(){
        CalificacionesControlador.calificar(audiovisuales, suscriptores);
    }
}
