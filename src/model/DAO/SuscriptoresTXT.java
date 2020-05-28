package model.DAO;

import model.Suscriptores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TreeSet;

public class SuscriptoresTXT {

//    private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public void bajarSuscriptores(TreeSet<Suscriptores> suscriptores) {

        try {
            File archivo = new File ( directorio + "Suscriptores.txt");
            if (archivo.exists()){
                Scanner leerArchivoSuscriptores = new Scanner(archivo);
                ArrayList<String> suscriptoresST = new ArrayList<String>();

                //Guardar contenido en String
                while (leerArchivoSuscriptores.hasNext()) {
                    String lineaActual = leerArchivoSuscriptores.nextLine();
                    suscriptoresST.add(lineaActual);
                }

                // Guardar objetos
                for (String s : suscriptoresST) {

                    int codigo              = Integer.parseInt(s.substring(0,10).trim());            // codigo
                    String nombre           = s.substring(10,22).trim();                             // nombre
                    String apellido         = s.substring(22,39).trim();                             // apellido
                    long nroDoc             = Long.parseLong(s.substring(39,50).trim());             // nroDoc
                    Calendar fechaDeNac     = convertirAFechaCalendar(s.substring(50,63).trim());    // fechaDeNac
                    char sexo               = s.substring(63,65).trim().charAt(0);                   // sexo

                    suscriptores.add(new Suscriptores( nroDoc, nombre, apellido, fechaDeNac, sexo));
                }

                leerArchivoSuscriptores.close();
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
    }
}
