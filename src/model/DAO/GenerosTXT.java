package model.DAO;

import model.Generos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GenerosTXT {
    
//    private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public void bajarGeneros(ArrayList<Generos> generos) {

        try {
            File archivo = new File ( directorio + "Generos.txt");
            if (archivo.exists()){
                Scanner leerArchivoGeneros = new Scanner(archivo);
                ArrayList<String> generosST = new ArrayList<String>();

                //Guardar contenido en String
                while (leerArchivoGeneros.hasNext()) {
                    String lineaActual = leerArchivoGeneros.nextLine();
                    generosST.add(lineaActual);
                }

                // Guardar objetos
                for (String s : generosST) {

                    int codigo              = Integer.parseInt(s.substring(0,8).trim());              // codigo
                    String descripcion      = s.substring(8,24).trim();                               // descripcion

                    generos.add(new Generos(codigo, descripcion));
                }

                leerArchivoGeneros.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
