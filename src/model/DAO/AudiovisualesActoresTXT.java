package model.DAO;

import java.io.*;
import java.util.*;

import model.*;

public class AudiovisualesActoresTXT {

	//private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";
	
	//AudiovisualesActores.txt -> (codigoAudiovisual + "/t" + nombreApellido + "/t" + nombreApellido + ...)
	public static ArrayList<Audiovisuales> leoDevuelvoArchivoAudiovisuales (Set<Actores> actores) throws Exception {

		ArrayList<Audiovisuales> audiovisuales = new ArrayList<Audiovisuales>();

		try{
      
			File archaudiovisualActores = new File ( directorio + "AudiovisualesActores.txt");

			if (archaudiovisualActores.exists()) {
				
				Scanner leerArchivo = new Scanner(archaudiovisualActores);
				ArrayList<String> audiovisualActoresST = new ArrayList<String>();
	
				while (leerArchivo.hasNext()) {
					String lineaActual = leerArchivo.nextLine();
					audiovisualActoresST.add(lineaActual);
				}

				for (String audiAct : audiovisualActoresST) {
					TreeSet<Actores> actoresArray = new TreeSet<Actores>();
					String[] audiActST = audiAct.split("\t");					// Revisarr
					for(Audiovisuales audi : audiovisuales) {
						if(Integer.parseInt(audiActST[0]) == audi.getCodigo()) {
							for(int i = 1; i < 10; i++) {
								if(audiActST[i]==null) {
									
									break;
								}
								else {
									Actores a;
					 				Iterator<Actores> act = actores.iterator();
					 				while (act.hasNext()) {
					 					a = act.next();
					 					String concatenoNombreApellido = a.getNombre() + a.getApellido();
					 					if(concatenoNombreApellido.replace(" ","").equals(audiActST[i].replace(" ",""))) {
					 						actoresArray.add(a);
					 					}
					 				}	
								}	
							}	
							
							audi.setActores(actoresArray);
						}		
					}	
		        }
						
				leerArchivo.close();
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se pudo leer el archivo AudiovisualesActores.txt ");
		}

		return audiovisuales;
	}
	
	public static void grabarAudiovisualesTXT(ArrayList<Audiovisuales> audiovisuales) {
		
		try{
			File fichero = new File(directorio + "AudiovisualesActores.txt");
			
			if (fichero.exists()) {
				PrintWriter archivoSalida = new PrintWriter(fichero);
				
				for(Audiovisuales audi: audiovisuales) 
				{
		            String actores = "";
					Actores a;
					Iterator<Actores> actoresIterator = (audi.getActores()).iterator();
					while (actoresIterator.hasNext()) {
						a=actoresIterator.next();
						actores = actores + a.getNombre() + " " + a.getApellido() + "/t";
						
					}
					
					//AudiovisualesActores.txt -> (codigoAudiovisual + "/t" + nombreApellido + "/t" + nombreApellido + ...)
					archivoSalida.println( String.format("%4s", audi.getCodigo())  
							               + "/t"
										   + actores
										 );
	    
				}
	
				archivoSalida.close();
			}
			
		} catch (Exception e3) {
			System.out.println("No se puede grabar el archivo de AudiovisualesActores.txt");
		}
	}
}
