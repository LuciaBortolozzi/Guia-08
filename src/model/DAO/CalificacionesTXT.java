package model.DAO;

import controller.AudiovisualesControlador;
import controller.SuscriptorControlador;
import model.*;
import view.Validaciones;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TreeSet;

public class CalificacionesTXT {

   // private static final String directorio = "D:\\\\IdeaProjects\\\\Guia-08\\\\src\\\\resources\\\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    //Calificaciones.txt -> ("\n" + codAudiovisual + ";"+ estrellas + ";" + motivo + ";" + dd/mm/aaaa + ";" + codSuscriptor)
    public static ArrayList<Audiovisuales> bajarCalificaciones(ArrayList<Audiovisuales> audiovisualesTXT, TreeSet<Suscriptores> suscriptores) {


        try {
            File archivo = new File(directorio + "Calificaciones.txt");
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

                    int codAudiovisual = Integer.parseInt(calificacionST[0]);                           // codAudiovisual
                    int estrellas = Integer.parseInt(calificacionST[1]);                                // estrellas
                    String motivo = calificacionST[2];                                                  // motivo
                    Calendar fechaRealizada = Validaciones.convertirAFechaCalendar(calificacionST[3]);  // fechaRealizada
                    int codSuscriptor = Integer.parseInt(calificacionST[4]);                            // codSuscriptor


                    Audiovisuales audiovisual = AudiovisualesControlador.buscarAudiovisual(codAudiovisual, audiovisualesTXT);
                    if (audiovisual != null) {
                        audiovisual.setCalificaciones(estrellas, motivo, fechaRealizada, SuscriptorControlador.buscarSuscriptor(codSuscriptor, suscriptores));
                    }
                }

                leerArchivoCalificaciones.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return audiovisualesTXT;
    }

    public static void grabarCalificacionesTXT(ArrayList<Audiovisuales> audiovisuales) {

        try {
            File fichero = new File(directorio + "Calificaciones.txt");

            if (fichero.exists()) {
                PrintWriter archivoSalida = new PrintWriter(fichero);

                for (Audiovisuales audiovisual : audiovisuales) {

                    for (Calificaciones calificacion : audiovisual.getCalificaciones()) {

                        //Calificaciones.txt -> ("\n" + codAudiovisual + ";"+ estrellas + ";" + motivo + ";" + dd/mm/aaaa + ";" + codSuscriptor)
                        archivoSalida.println(audiovisual.getCodigo() + ";"
                                + calificacion.getEstrellas() + ";"
                                + calificacion.getMotivo() + ";"
                                + calificacion.getFechaRealizada().get(Calendar.DAY_OF_MONTH)
                                + "/"
                                + (calificacion.getFechaRealizada().get(Calendar.MONTH) + 1)
                                + "/"
                                + calificacion.getFechaRealizada().get(Calendar.YEAR) + ";"
                                + calificacion.getSuscriptor().getCodigo()

                        );
                    }
                }

                archivoSalida.close();
            }

        } catch (Exception e) {
            System.out.println("No se puede grabar el archivo de Calificaciones.txt");
        }
    }
}
