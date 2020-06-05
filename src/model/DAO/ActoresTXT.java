package model.DAO;

import model.Actores;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ActoresTXT {
  //  private static final String directorio = "D:\\\\IdeaProjects\\\\Guia-08\\\\src\\\\resources\\\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    //Actores.txt -> ("\n" + nombre + "/t"+ apellido + "/t" + sexo)
    public static TreeSet<Actores> bajarActoresTXT() {

        TreeSet<Actores> actores = new TreeSet<Actores>();
        try {
            File archivo = new File ( directorio + "Actores.txt");
            if (archivo.exists()){
                Scanner leerArchivoActores = new Scanner(archivo);
                ArrayList<String> actoresST = new ArrayList<String>();

                //Guardar contenido en String
                while (leerArchivoActores.hasNext()) {
                    String lineaActual = leerArchivoActores.nextLine();
                    actoresST.add(lineaActual);
                }

                // Guardar objetos
                for (String s : actoresST) {

                    String[] actorST = s.split("\t");

                    String nombre = actorST[0];
                    String apellido = actorST[1];
                    char sexo = actorST[2].charAt(0);

                    actores.add(new Actores(nombre, apellido, sexo));
                }

                leerArchivoActores.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return actores;
    }
}
