package model.DAO;

import java.io.*;
import java.util.*;

import controller.ActoresControlador;
import controller.AudiovisualesControlador;
import controller.SuscriptorControlador;
import model.*;
import view.Validaciones;

public class AudiovisualesActoresTXT {

    private static final String directorio = "D:\\\\IdeaProjects\\\\Guia-08\\\\src\\\\resources\\\\";
//    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    //AudiovisualesActores.txt -> (codigoAudiovisual + "/t" + nombreApellido + "/t" + nombreApellido + ...)
    public static ArrayList<Audiovisuales> bajarAudiovisualesActoresTXT(ArrayList<Audiovisuales> audiovisualesTXT, TreeSet<Actores> actoresTXT) {

    	TreeSet<Actores> actores = new TreeSet<Actores>();
    	
        try {

            File archaudiovisualActores = new File(directorio + "AudiovisualesActores.txt");

            if (archaudiovisualActores.exists()) {

                Scanner leerArchivo = new Scanner(archaudiovisualActores);
                ArrayList<String> audiovisualActoresST = new ArrayList<String>();

                while (leerArchivo.hasNext()) {
                    String lineaActual = leerArchivo.nextLine();
                    audiovisualActoresST.add(lineaActual);
                }
                
                boolean primeraVez = true;
                int cod = 0;
                Audiovisuales audiovisual = null;
                Actores actor;
                // Guardar objetos
                for (String s : audiovisualActoresST) {

                    String[] audiovisualST = s.split("\t");

                    int codAudiovisual = Integer.parseInt(audiovisualST[0].trim());                     // codAudiovisual
                    String nombreActor = audiovisualST[1].trim().toUpperCase(); 						// nombreActor
                    String apellidoActor = audiovisualST[2].trim().toUpperCase(); 						// apellidoActor
                 
                    if(primeraVez) {
                    	audiovisual = AudiovisualesControlador.buscarAudiovisual(codAudiovisual, audiovisualesTXT);
                    	cod = codAudiovisual;
                    	primeraVez = false;
                    }
                    
                    if (audiovisual != null) {
	                    if(cod == codAudiovisual) {
	                    	
	                        actor = ActoresControlador.buscarActor(nombreActor, apellidoActor, actoresTXT);
	                  
                        	if (actor != null) {
                        		actores.add(actor);
                            }
	                         
	                        
	                    } else {
	                    	
	                    	audiovisual.setActores(actores);
	                    	 actores.clear(); 
	                    	audiovisual = AudiovisualesControlador.buscarAudiovisual(codAudiovisual, audiovisualesTXT);
	                    	
	                    	if (audiovisual != null) {
	                    	
		                    	cod = codAudiovisual;
		                    	
		                    	if(cod == codAudiovisual) {
			                    	
			                        actor = ActoresControlador.buscarActor(nombreActor, apellidoActor, actoresTXT);
			                       
		                        	if (actor != null) {
		                        		
		                        		actores.add(actor);
		                            }
			                    }
	                    	}
	                    }
                    }
                }
                if (audiovisual != null) {
                audiovisual.setActores(actores);
                }
                leerArchivo.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo AudiovisualesActores.txt ");
        }
        return audiovisualesTXT;
    }


	public static void grabarAudiovisualesActoresTXT(ArrayList<Audiovisuales> audiovisuales) {

        try {
            File fichero = new File(directorio + "AudiovisualesActores.txt");

            if (fichero.exists()) {
                PrintWriter archivoSalida = new PrintWriter(fichero);

                for (Audiovisuales audi : audiovisuales) {
                    String actores = "";
                    Actores a;
                    Iterator<Actores> actoresIterator = (audi.getActores()).iterator();
                    while (actoresIterator.hasNext()) {
                        a = actoresIterator.next();
                       
                        //AudiovisualesActores.txt -> (codigoAudiovisual + "/t" + nombreApellido + "/t" + nombreApellido)
                        archivoSalida.println(String.format("%4s", audi.getCodigo())
                                + "\t"
                                + a.getApellido().toUpperCase()
                                + "\t"
                                + a.getNombre().toUpperCase()
                        );
                    }
                }
                archivoSalida.close();
            }

        } catch (Exception e3) {
            System.out.println("No se puede grabar el archivo de AudiovisualesActores.txt");
        }
    }
}
