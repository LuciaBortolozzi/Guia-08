package model.DAO;

import controller.SuscriptorControlador;
import model.Calificaciones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class CalificacionesTXT {

    //private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    /*public void bajarCalificaciones(ArrayList<Calificaciones> calificaciones) {

        try {
            File archivo = new File ( directorio + "Calificaciones.txt");
            if (archivo.exists()) {

                Scanner leerArchivoCalificaciones = new Scanner(archivo);
                ArrayList<String> calificacionesST = new ArrayList<String>();

                //Guardar contenido en String
                while (leerArchivoCalificaciones.hasNext()) {
                    String lineaActual = leerArchivoCalificaciones.nextLine();
                    calificacionesST.add(lineaActual);
                }

                // Guardar objetos
                for (String s : calificacionesST) {

                    String[] calificacionST = s.split(";");

                    int estrellas = Integer.parseInt(calificacionST[0]);                                //estrellas
                    String motivo = calificacionST[1];                                                  //motivo
                    Calendar fechaRealizada = convertirAFechaCalendar(calificacionST[2]);               //fechaRealizada
                    int codSuscriptor = Integer.parseInt(calificacionST[3]);                            //suscriptor

                    // calificaciones.add(new Calificaciones(estrellas, motivo, fechaRealizada, suscriptorCtrl.buscarSuscriptor(suscriptores, codSuscriptor)));
                    // No hacer new Calificaciones aca porque es por composicion
                }

                leerArchivoCalificaciones.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Calendar convertirAFechaCalendar(String f) {
        Calendar fecha = Calendar.getInstance();

        //dd/mm/aaaa
        String[] aux = f.split("/");
        int day = Integer.parseInt(aux[0]);
        int month = Integer.parseInt(aux[1]);
        int year = Integer.parseInt(aux[2]);

        fecha.set(Calendar.DAY_OF_MONTH, day);
        fecha.set(Calendar.MONTH, (month - 1));
        fecha.set(Calendar.YEAR, year);

        return fecha;
    }*/
}
