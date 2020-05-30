package model.DAO;

import model.Suscriptores;
import view.Validaciones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TreeSet;

public class SuscriptoresTXT {

//    private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public static TreeSet<Suscriptores> bajarSuscriptores() {

        TreeSet<Suscriptores> suscriptores = new TreeSet<Suscriptores>();

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

                    int codigo              = Integer.parseInt(s.substring(0,10).trim());                           // codigo
                    String nombre           = s.substring(10,22).trim();                                            // nombre
                    String apellido         = s.substring(22,39).trim();                                            // apellido
                    long nroDoc             = Long.parseLong(s.substring(39,50).trim());                            // nroDoc
                    Calendar fechaDeNac     = Validaciones.convertirAFechaCalendar(s.substring(50,63).trim());      // fechaDeNac
                    char sexo               = s.substring(63,65).trim().charAt(0);                                  // sexo

                    suscriptores.add(new Suscriptores( codigo, nroDoc, nombre, apellido, fechaDeNac, sexo));
                }

                leerArchivoSuscriptores.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return suscriptores;
    }
}
