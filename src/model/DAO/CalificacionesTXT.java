package model.DAO;

import controller.AudiovisualesControlador;
import controller.SuscriptorControlador;
import model.Audiovisuales;
import model.Calificaciones;
import model.Suscriptores;
import view.Validaciones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class CalificacionesTXT {

    //private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public static ArrayList<Calificaciones> bajarCalificaciones() {

        ArrayList<Calificaciones> calificaciones = new ArrayList<Calificaciones>();

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

                    int codAudiovisual = Integer.parseInt(calificacionST[0]);                           // codAudiovisual
                    int estrellas = Integer.parseInt(calificacionST[1]);                                // estrellas
                    String motivo = calificacionST[2];                                                  // motivo
                    Calendar fechaRealizada = Validaciones.convertirAFechaCalendar(calificacionST[3]);  // fechaRealizada
                    int codSuscriptor = Integer.parseInt(calificacionST[4]);                            // codSuscriptor

                    Audiovisuales audiovisual = AudiovisualesControlador.buscarAudiovisual(codAudiovisual);
                    if ( audiovisual != null){
                        audiovisual.setCalificaciones(estrellas, motivo, fechaRealizada, SuscriptorControlador.buscarSuscriptor(codSuscriptor));
                    }
                }

                leerArchivoCalificaciones.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return calificaciones;
    }


}
