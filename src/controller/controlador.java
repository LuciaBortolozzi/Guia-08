package controller;

import model.*;
import model.DAO.*;

import java.util.ArrayList;
import java.util.TreeSet;

public class controlador {

    TreeSet<Actores> actores = ActoresTXT.bajarActores();
    ArrayList<Generos> generos = GenerosTXT.bajarGeneros();
    TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();
    ArrayList<Audiovisuales> audiovisuales = CalificacionesTXT.bajarCalificaciones();

    public controlador() throws Exception {
    }


    public void opcion1() throws Exception {
        CalificacionesControlador.calificar();
    }
}
