package controller;


import model.Audiovisuales;

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

		ArrayList<Audiovisuales> audiovisuales = AudiovisualesTXT.leoDevuelvoArchivoAudiovisuales();
		ArrayList<Audiovisuales> audiovisualesAux = AudiovisualesJSON.bajarAudiovisuales();

		Calendar fechaActual = Calendar.getInstance();
	
		for(Audiovisuales audiAux: audiovisualesAux) {
			
			for(Audiovisuales audi : audiovisuales) {
				
				if(audiAux.getNombre().replace(" ", "").toUpperCase().equals(audi.getNombre().replace(" ", "").toUpperCase())) {
					
					if(audiAux instanceof Series) {
						 
						if( ((Series)audiAux).getTemporada() == ((Series)audi).getTemporada() && ((Series)audiAux).getEpisodio() == ((Series)audi).getEpisodio()) {
							
							int codigoAudiovisual = audi.getCodigo();
							audi = audiAux;
							audi.setCodigo(codigoAudiovisual);
							audi.setFechaPubli(fechaActual);
						}
						
						
					}
					
				}
				
			}
			
		}
	
		
		
		
		
		int posicion = 0;
		
		//contador Audiovisuales
		int contadorAudiovisuales = 0;
		for(int i=0;i<audiovisuales.length;i++) {
			
			if(audiovisuales[i] == null) {
				
				break;
			}else {
				contadorAudiovisuales++;
			}
		}
		
		for(Audiovisuales audiNuevos:audiovisualesAux) {
			posicion++;
			if(audiNuevos == null) {
				
				break;
			}else {
				
				for(int i=0;i<audiovisuales.length;i++) {
					
					if(audiovisuales[i] == null) {
						
						break;
					}else {
						
						if(audiNuevos instanceof Series) {
							
							if((audiNuevos.getNombreAudiovisual().replace(" ","").toLowerCase()).equals(audiovisuales[i].getNombreAudiovisual().replace(" ","").toLowerCase())	
								&& ((Series)audiNuevos).getTemporadasSeries()==((Series)audiovisuales[i]).getTemporadasSeries()
								&& ((Series)audiNuevos).getEpisodiosSeries() ==((Series)audiovisuales[i]).getEpisodiosSeries()) 
							{
								
								int codigoAudiovisual = audiovisuales[i].getCodigoAudiovisual();
								audiovisuales[i] = audiNuevos;
								audiovisuales[i].setCodigoAudiovisual(codigoAudiovisual);
								audiovisuales[i].setFechaPublicacionAudiovisual(fechaActual);
							}else {
								
								audiovisuales[contadorAudiovisuales] = new Series();
								audiovisuales[contadorAudiovisuales] = audiNuevos;
								audiovisuales[contadorAudiovisuales].setCodigoAudiovisual(contadorAudiovisuales+1);
								audiovisuales[contadorAudiovisuales].setFechaPublicacionAudiovisual(fechaActual);
								contadorAudiovisuales++;
								i=contadorAudiovisuales;
							}
						}else if(audiNuevos instanceof Peliculas) {
							
							if((audiNuevos.getNombreAudiovisual().replace(" ","").toLowerCase()).equals(audiovisuales[i].getNombreAudiovisual().replace(" ","").toLowerCase())) 
							{
								
								int codigoAudiovisual = audiovisuales[i].getCodigoAudiovisual();
								audiovisuales[i] = audiNuevos;
								audiovisuales[i].setCodigoAudiovisual(codigoAudiovisual);
								audiovisuales[i].setFechaPublicacionAudiovisual(fechaActual);
							}else {
								
								audiovisuales[contadorAudiovisuales] = new Peliculas();
								audiovisuales[contadorAudiovisuales] = audiNuevos;
								audiovisuales[contadorAudiovisuales].setCodigoAudiovisual(contadorAudiovisuales+1);
								audiovisuales[contadorAudiovisuales].setFechaPublicacionAudiovisual(fechaActual);
								contadorAudiovisuales++;
								i=contadorAudiovisuales;
							}
						}
					}
				}
			}	
		}
	}
}
