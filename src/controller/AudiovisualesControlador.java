package controller;


import java.util.Iterator;
import java.util.ArrayList;
import java.util.Calendar;

import model.*;
import model.DAO.*;


public class AudiovisualesControlador {

    public static Audiovisuales buscarAudiovisual(int codAudiovisual) throws Exception {

    	ArrayList<Audiovisuales> audiovisuales = ingresarModificarAudiovisual();
        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();

            if (audiovisual.getCodigo() == codAudiovisual){
                return audiovisual;
            }
        }
        return null;
    }

    /*public void mejorCalificacion(ArrayList<Audiovisuales> audiovisuales){

        int cantidadCalificaciones;
        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();


        }
    }*/
	
	public static ArrayList<Audiovisuales> ingresarModificarAudiovisual() throws Exception {

		ArrayList<Audiovisuales> audiovisuales = AudiovisualesActoresTXT.leoDevuelvoArchivoAudiovisuales();
		ArrayList<Audiovisuales> audiovisualesAux = AudiovisualesJSON.bajarAudiovisuales();

		Calendar fechaActual = Calendar.getInstance();
		
		for(Audiovisuales audiAux: audiovisualesAux) 
		{
			boolean encontrado = false;
			for(int i=0;i<audiovisuales.size();i++)
			{
				if(audiAux instanceof Series) 
				{
					if( audiAux.getNombre().replace(" ", "").toUpperCase().equals(audiovisuales.get(i).getNombre().replace(" ", "").toUpperCase()) &&
						((Series)audiAux).getTemporada() == ((Series)audiovisuales.get(i)).getTemporada() && 
						((Series)audiAux).getEpisodio() == ((Series)audiovisuales.get(i)).getEpisodio()) 
					{//MODIFICACION
						audiovisuales.get(i).setNombre(audiAux.getNombre());
						audiovisuales.get(i).setSinopsis(audiAux.getSinopsis());
						//audiovisuales.get(i).setFechaPubli(fechaActual); VER SI LO CAMBIAMOS O NO
						audiovisuales.get(i).setActores(audiAux.getActores());
						audiovisuales.get(i).setGenero(audiAux.getGenero());
						((Series)audiovisuales.get(i)).setTemporada(((Series)audiAux).getTemporada());
						((Series)audiovisuales.get(i)).setEpisodio(((Series)audiAux).getEpisodio());
						encontrado = true;
					}
					
				}else if(audiAux instanceof Peliculas) 
				{
					if( audiAux.getNombre().replace(" ", "").toUpperCase().equals(audiovisuales.get(i).getNombre().replace(" ", "").toUpperCase())) 
					{//MODIFICACION
						audiovisuales.get(i).setNombre(audiAux.getNombre());
						audiovisuales.get(i).setSinopsis(audiAux.getSinopsis());
						//audiovisuales.get(i).setFechaPubli(fechaActual); VER SI LO CAMBIAMOS O NO
						audiovisuales.get(i).setActores(audiAux.getActores());
						audiovisuales.get(i).setGenero(audiAux.getGenero());
						((Peliculas)audiovisuales.get(i)).setAnioFilm(((Peliculas)audiAux).getAnioFilm());
						((Peliculas)audiovisuales.get(i)).setDuracion(((Peliculas)audiAux).getDuracion());
						encontrado = true;
					}
				}
			}
			
			if(!encontrado) {
				
				//NUEVA PELICULA O SERIE
				audiovisuales.set(audiovisuales.size(), new Series());
				audiovisuales.get(audiovisuales.size()).setNombre(audiAux.getNombre());
				audiovisuales.get(audiovisuales.size()).setSinopsis(audiAux.getSinopsis());
				audiovisuales.get(audiovisuales.size()).setFechaPubli(fechaActual);
				audiovisuales.get(audiovisuales.size()).setActores(audiAux.getActores());
				audiovisuales.get(audiovisuales.size()).setGenero(audiAux.getGenero());
				
				if(audiAux instanceof Series) 
				{
					((Series)audiovisuales.get(audiovisuales.size())).setTemporada(((Series)audiAux).getTemporada());
					((Series)audiovisuales.get(audiovisuales.size())).setEpisodio(((Series)audiAux).getEpisodio());
					
				}else if(audiAux instanceof Peliculas) 
				{
					((Peliculas)audiovisuales.get(audiovisuales.size())).setAnioFilm(((Peliculas)audiAux).getAnioFilm());
					((Peliculas)audiovisuales.get(audiovisuales.size())).setDuracion(((Peliculas)audiAux).getDuracion());	
				}
			}
		}
		return audiovisuales;
	}
	
}
