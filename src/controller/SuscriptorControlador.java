package controller;


import model.Audiovisuales;
import model.Calificaciones;
import model.DAO.SuscriptoresTXT;
import model.Suscriptores;
import view.Mostrar;
import view.Validaciones;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;

public class SuscriptorControlador {

    public static Suscriptores buscarSuscriptor(int codSuscriptor){

        TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();
        Suscriptores suscriptor;

        Iterator<Suscriptores> iteratorAudiovisuales = suscriptores.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            suscriptor = iteratorAudiovisuales.next();

            if (suscriptor.getCodigo() == codSuscriptor){
                return suscriptor;
            }
        }
        return null;
    }

    public static void recomendarMejoresSeries() throws Exception {

        /*A todos los suscriptores jóvenes (menores de 35 años), se les recomienda la temporada
        completa de la serie con mejor calificación promedio durante los últimos 3 meses, evaluada
        por quienes cumplan con el mismo rango de edad, mediante la generación de un archivo
        Json. Este archivo incluye el nombre de la empresa, nombre de la serie, género, nómina de
        actores, sinopsis, temporada, cantidad de episodios y su calificación.*/

        TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();
        TreeSet<Suscriptores> suscriptoresARecomendar = new TreeSet<Suscriptores>();

        Calendar fechaActual = Calendar.getInstance();

        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();
            if (Validaciones.menor(suscriptor.getFechaDeNac(), fechaActual, 35)){
                suscriptoresARecomendar.add(suscriptor);
            }
        }

        Calendar fechaAnterior = Validaciones.tresMesesAntes(fechaActual);

        ArrayList<Audiovisuales> audiovisuales = AudiovisualesControlador.ingresarModificarAudiovisual();               // Modificar para sea despues de pagos!!!!
        ArrayList<Audiovisuales> mejoresAudiovisuales = new ArrayList<Audiovisuales>();
        int cantidadCalificacionesDeEpisodiosPorTemporada;
        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();
            if (audiovisual.getFechaPubli().after(fechaAnterior)){

            }

        }


    }

    public static void recomendarMejorPelicula() throws Exception {

        /*Para cada uno de los géneros existentes, la película con mejor calificación obtenida en el
        último mes es recomendada a todos los suscriptores mayores de 55 años, mediante otro
        archivo JSon con la estructura similar a la de las series.*/

        TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();
        TreeSet<Suscriptores> suscriptoresARecomendar = new TreeSet<Suscriptores>();

        Calendar fechaActual = Calendar.getInstance();

        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();
            if (Validaciones.mayor(suscriptor.getFechaDeNac(), fechaActual, 55)){
                suscriptoresARecomendar.add(suscriptor);
            }
        }

        Calendar fechaAnterior = Validaciones.ultimoMes(fechaActual);

        ArrayList<Audiovisuales> audiovisuales = AudiovisualesControlador.ingresarModificarAudiovisual();               // Modificar para sea despues de pagos!!!!
        ArrayList<Audiovisuales> mejoresAudiovisuales = new ArrayList<Audiovisuales>();
        int cantidadCalificaciones;
        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();
            if (audiovisual.getFechaPubli().after(fechaAnterior)){

            }

        }


    }

    public static void mayoresSinCalificar() throws Exception {

        /*Nombre y apellido de los suscriptores mayores de 60 años que nunca hayan calificado
        una película.*/

        TreeSet<Suscriptores> suscriptores = SuscriptoresTXT.bajarSuscriptores();
        TreeSet<Suscriptores> suscriptoresMayores = new TreeSet<Suscriptores>();

        Calendar fechaActual = Calendar.getInstance();

        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();
            if (Validaciones.mayor(suscriptor.getFechaDeNac(), fechaActual,60)){
                suscriptoresMayores.add(suscriptor);                                                                    // Colecto los mayores a 60
            }
        }

        ArrayList<Audiovisuales> audiovisuales = AudiovisualesControlador.ingresarModificarAudiovisual();               // Modificar para sea despues de pagos!!!!
        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();

            if (audiovisual.getCalificaciones() != null){                                                               // Si fue calificado, recorro las calificaciones
                Calificaciones calificacion;
                Iterator<Calificaciones> iteratorCalificaciones = audiovisual.getCalificaciones().iterator();
                while (iteratorCalificaciones.hasNext()) {
                    calificacion = iteratorCalificaciones.next();
                    suscriptoresMayores.remove(calificacion.getSuscriptor());                                           // Quito los mayores que hayan calificado
                }
            }
        }

        mostrarSuscriptores(suscriptoresMayores);
    }

    public static void mostrarSuscriptores(TreeSet<Suscriptores> suscriptores){
        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();

            Mostrar.mostrar(suscriptor.getNombre() + suscriptor.getApellido());
        }
    }
}
