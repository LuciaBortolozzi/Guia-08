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


    public void agregarActoresAMemoria() {
        TreeSet<Actores> actores = new TreeSet<Actores>();
        actores = ActoresTXT.bajarActores();

    }

    public void agregarGenerosAMemoria() {
        ArrayList<Generos> generos = new ArrayList<Generos>();
        generos = GenerosTXT.bajarGeneros();

    }

    public void agregarSuscriptoresAMemoria() {
        TreeSet<Suscriptores> suscriptores = new TreeSet<Suscriptores>();
        suscriptores = SuscriptoresTXT.bajarSuscriptores();

    }

    public void agregarAudiovisualesAObjeto() throws Exception {
        ArrayList<Audiovisuales> audiovisuales = new ArrayList<Audiovisuales>();
        audiovisuales = AudiovisualesJSON.bajarAudiovisuales();

    }


    public void opcion1(){
        CalificacionesControlador.calificar(audiovisuales, suscriptores);
    }
}
