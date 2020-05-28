package model.DAO;

import java.io.*;
import java.util.*;

import model.*;
import model.DAO.*;

public class AudiovisualesTXT {
	
	//private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";
	
	//Audiovisuales.txt -> (identificador + codigo + nombre + genero + sinopsis + diaPublicacion + "/" + mesPublicacion + "/" + anioPublicacion + duracion + anio)
	//Audiovisuales.txt -> (identificador + codigo + nombre + genero + sinopsis + diaPublicacion + "/" + mesPublicacion + "/" + anioPublicacion + temporada + episodio)
	public ArrayList<Audiovisuales> leoDevuelvoArchivoAudiovisuales (Set<Actores> actores, Set<Generos> generos) throws Exception
	{

		ArrayList<Audiovisuales> audiovisuales = new ArrayList<Audiovisuales>();
		
		try{
      
			File archaudiovisual = new File ( directorio + "Audiovisuales.txt");

			if (archaudiovisual.exists())
			{
				
				Scanner leerArchivo = new Scanner(archaudiovisual);
				ArrayList<String> audiovisualST = new ArrayList<String>();
	
				while (leerArchivo.hasNext())
				{
					String lineaActual = leerArchivo.nextLine();
					audiovisualST.add(lineaActual);
				}
				
				int j = 0;
				for (String audi : audiovisualST)
				{
					
					if(audi.substring(0,2).equals("06")) {
						audiovisuales.set(j, new Peliculas());
						((Peliculas)audiovisuales.get(j)).setDuracion(Integer.parseInt(audi.substring(119,123)));
						((Peliculas)audiovisuales.get(j)).setAnioFilm(Integer.parseInt(audi.substring(123,127).replace(" ", "")));
						
					}else if(audi.substring(0,2).equals("07")) {
						audiovisuales.set(j, new Series());	
						((Series)audiovisuales.get(j)).setTemporada(Integer.parseInt(audi.substring(119,122)));
						((Series)audiovisuales.get(j)).setEpisodio(Integer.parseInt(audi.substring(122,125)));
						
					}
					
					audiovisuales.get(j).setCodigo(Integer.parseInt(audi.substring(2,6).replace(" ", "")));
					audiovisuales.get(j).setNombre(audi.substring(6,56));
					
	
	            	Generos g;
	 				Iterator<Generos> gen = generos.iterator();
	 				while (gen.hasNext()) {
	 					g=gen.next();
	 					if(g.getDescripcion().equals(audi.substring(56,59).trim())) {
	 						
	 						audiovisuales.get(j).setGenero(g);
	 					}
	 				}
			        
					audiovisuales.get(j).setSinopsis(audi.substring(59,109));
					Calendar fechaPublicacion = Calendar.getInstance();
					fechaPublicacion.set(Calendar.YEAR, Integer.parseInt(audi.substring(115,119)));
					fechaPublicacion.set(Calendar.MONTH, (Integer.parseInt(audi.substring(112,114).replace(" ", "")))-1);
					fechaPublicacion.set(Calendar.DAY_OF_MONTH, Integer.parseInt(audi.substring(109,111)));
					audiovisuales.get(j).setFechaPubli(fechaPublicacion);
					
				}
				
				leerArchivo.close();
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se pudo leer el archivo Audiovisuales.txt ");
		}
		return audiovisuales;
	}
	
	public void grabarAudiovisualesTXT(ArrayList<Audiovisuales> audiovisuales) {
		
		try{
			File fichero = new File(directorio + "Audiovisuales.txt");
			
			if (fichero.exists())
			{
				PrintWriter archivoSalida = new PrintWriter(fichero);
				
				for(Audiovisuales audi: audiovisuales) 
				{
		
					if(audi instanceof Peliculas) 
					{
						
						
						//Audiovisuales.txt -> (identificador + codigo + nombre + genero + sinopsis + diaPublicacion + "/" + mesPublicacion + "/" + anioPublicacion + duracion + anio)
						archivoSalida.println(       String.format("%2s", "06") 
												   + String.format("%4s", audi.getCodigo())   
												   + String.format("%50s", audi.getNombre())  
												   + String.format("%3s", audi.getGenero().getCodigo())  
												   + String.format("%50s", audi.getSinopsis())
												   + String.format("%2s", audi.getFechaPubli().get(Calendar.DAY_OF_MONTH))
												   + "/"
												   + String.format("%2s", audi.getFechaPubli().get(Calendar.MONTH))
												   + "/"
												   + String.format("%2s", audi.getFechaPubli().get(Calendar.YEAR))
												   + String.format("%4s", ((Peliculas) audi).getDuracion())
												   + String.format("%4s", ((Peliculas) audi).getAnioFilm())
												   );
						
					}else if(audi instanceof Series) 
					{
						
						
						//Audiovisuales.txt -> (identificador + codigo + nombre + genero + sinopsis + diaPublicacion + "/" + mesPublicacion + "/" + anioPublicacion + temporada + episodio)
						archivoSalida.println(       String.format("%2s", "07") 
												   + String.format("%4s", audi.getCodigo()   
												   + String.format("%50s", audi.getNombre())  
												   + String.format("%3s", audi.getGenero().getCodigo())  
												   + String.format("%50s", audi.getSinopsis()) 
												   + String.format("%2s", audi.getFechaPubli().get(Calendar.DAY_OF_MONTH))
												   + "/"
												   + String.format("%2s", audi.getFechaPubli().get(Calendar.MONTH))
												   + "/"
												   + String.format("%2s", audi.getFechaPubli().get(Calendar.YEAR))
												   + String.format("%3s", ((Series) audi).getTemporada())
												   + String.format("%3s", ((Series) audi).getEpisodio()))
												   );
												
					}
				}
	
				archivoSalida.close();
			}
			
		} catch (Exception e3) {
			System.out.println("No se puede grabar el archivo de Audiovisuales.txt");
		}
	}
		
		
	
	
}
