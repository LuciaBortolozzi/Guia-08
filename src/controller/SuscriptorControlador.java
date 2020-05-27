package controller;

import model.DAO.SuscriptoresTXT;
import model.Suscriptores;
import view.Validaciones;

import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;

public class SuscriptorControlador {

    SuscriptoresTXT suscriptoresTXT = new SuscriptoresTXT();
    AudiovisualesControlador audiovisualesCtrl = new AudiovisualesControlador();

    public Suscriptores buscarSuscriptor(int codSuscriptor){
        Suscriptores suscriptor = null;
        TreeSet<Suscriptores> suscriptores = suscriptoresTXT.bajarSuscriptores();

        Iterator iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            // codigo deberia estar en suscriptor tambien? o hacemos por nroDoc?
        }

        return suscriptor;
    }

    public void recomendarMejoresSeries(){

        /*A todos los suscriptores jóvenes (menores de 35 años), se les recomienda la temporada
        completa de la serie con mejor calificación promedio durante los últimos 3 meses, evaluada
        por quienes cumplan con el mismo rango de edad, mediante la generación de un archivo
        Json. Este archivo incluye el nombre de la empresa, nombre de la serie, género, nómina de
        actores, sinopsis, temporada, cantidad de episodios y su calificación.*/


        /*Calendar fechaActual = Calendar.getInstance();
        Validaciones.menor(fechaActual, suscriptor.getFechaDeNac(), 35);

        Validaciones.tresMesesAntes(fechaActual);

        audiovisualesCtrl.mejorCalificacion();*/


    }

    public void recomendarMejorPelicula(){

        /*Para cada uno de los géneros existentes, la película con mejor calificación obtenida en el
        último mes es recomendada a todos los suscriptores mayores de 55 años, mediante otro
        archivo JSon con la estructura similar a la de las series.*/


        /*Calendar fechaActual = Calendar.getInstance();
        Validaciones.mayor(fechaActual, suscriptor.getFechaDeNac(), 55);

        Validaciones.ultimoMes(fechaActual);

        audiovisualesCtrl.mejorCalificacion();*/


    }
}
