package controller;


import java.util.*;

import model.*;
import model.DAO.*;
import view.*;

public class AudiovisualesControlador {

    public static Audiovisuales buscarAudiovisual(int codAudiovisual, ArrayList<Audiovisuales> audiovisuales) throws Exception {

    	
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
	
	public static ArrayList<Audiovisuales> ingresarModificarAudiovisual() throws Exception {

		ArrayList<Audiovisuales> audiovisuales = AudiovisualesActoresTXT.leoDevuelvoArchivoAudiovisuales();
		ArrayList<Audiovisuales> audiovisualesAux = AudiovisualesJSON.bajarAudiovisualesJSON();

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
						audiovisuales.get(i).setSinopsis(audiAux.getSinopsis());
						//audiovisuales.get(i).setFechaPubli(fechaActual); VER SI LO CAMBIAMOS O NO
						audiovisuales.get(i).setActores(audiAux.getActores());
						audiovisuales.get(i).setGenero(audiAux.getGenero());
						encontrado = true;
					}
					
				}else if(audiAux instanceof Peliculas) 
				{
					if( audiAux.getNombre().replace(" ", "").toUpperCase().equals(audiovisuales.get(i).getNombre().replace(" ", "").toUpperCase())) 
					{//MODIFICACION
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
	
	//AC� LE VOY A TENER QUE PASAR AUDIOVISUALES POR PARAMETRO PORQUE ELLA QUIERE HACERLO COMO OPCION DE MENU
	public static ArrayList<Audiovisuales> asignarPagos() throws Exception{
		
		ArrayList<Audiovisuales> audiovisuales = AudiovisualesJSON.bajarAudiovisualesJSON();
		
		Calendar fechaPublicacionActual = Calendar.getInstance();
		Calendar fechaActual = Calendar.getInstance();
		Validaciones.OnceMesesAntes(fechaActual); 
		Calendar fechaProximoPago = Calendar.getInstance();
		ArrayList<Audiovisuales> audiovisualesAuxiliar = new ArrayList<Audiovisuales>();
		
		double montoDerPelicula = Validaciones.validarDouble("monto de los derechos de pel�culas");
		
		double montoDerSeries = Validaciones.validarDouble("monto de los derechos de series");

		boolean primeroDelMes = true;
		int cantidadPublicaciones = 0;
		double totalAbonar = 0;
		for(Audiovisuales audi : audiovisuales) 
		{
			if(audi.getFechaPubli().get(Calendar.MONTH)==fechaPublicacionActual.get(Calendar.MONTH)
				&& audi.getFechaPubli().get(Calendar.YEAR)==fechaPublicacionActual.get(Calendar.YEAR)) 
			{
				if(primeroDelMes)
				{
					fechaProximoPago = Validaciones.UnMesDespues(fechaProximoPago);
					primeroDelMes = false;
					
				}else
				{
					fechaProximoPago = Validaciones.UnaSemanaDespues(fechaProximoPago);
				}
					
				if(audi instanceof Peliculas)
				{
					audi.setCronogramaPagos(fechaProximoPago, montoDerPelicula);
					
				}else if(audi instanceof Series)
				{
					audi.setCronogramaPagos(fechaProximoPago, montoDerSeries);
				}
				
				cantidadPublicaciones++;
				totalAbonar = totalAbonar + audi.getCronogramaPagos().getMonto();
				
			}else if(audi.getFechaPubli().compareTo(fechaActual) >= 0)
			{
				if(primeroDelMes) 
				{
					fechaProximoPago = Validaciones.UnMesDespues(fechaProximoPago);
					primeroDelMes = false;
				}else 
				{
					fechaProximoPago = Validaciones.UnaSemanaDespues(fechaProximoPago);
				}
					
				if(audi instanceof Peliculas) //MODIFICAR LOS MONTOS
				{
					montoDerPelicula = ((Peliculas)audi).calculoMontoTotalPeliculas();
					audi.setCronogramaPagos(fechaProximoPago, montoDerPelicula);
					
				}else if(audi instanceof Series)
				{
					int cantidadEpisodios = ContarEpisodiosTemporada(audiovisuales,audi);
					
					if(cantidadEpisodios>12) 
					{
						montoDerSeries = ((Series)audi).calculoMontoTotalSeriesMasDoce();
					}else
					{
						montoDerSeries = ((Series)audi).calculoMontoTotalSeriesMenosDoce();
					}	
					
					audi.setCronogramaPagos(fechaProximoPago, montoDerSeries);
				}
				
				cantidadPublicaciones++;
				totalAbonar = totalAbonar + audi.getCronogramaPagos().getMonto();
			}
			
			audiovisualesAuxiliar.add(audi);
		}
		
		CronogramaPagosTXT.grabarCronogramaPagosTXT(audiovisualesAuxiliar, cantidadPublicaciones, totalAbonar);
		
		return audiovisuales;
	}
	
	public static int ContarEpisodiosTemporada(ArrayList<Audiovisuales>audiovisuales,Audiovisuales audi) {
		
		int cantidadEpisodios = 0;
		
		for(Audiovisuales audiovisual : audiovisuales) 
		{
			if(audiovisual instanceof Series) 
			{
				if(  audiovisual.getNombre().replace(" ", "").toUpperCase().equals(audi.getNombre().replace(" ", "").toUpperCase())
						&& ((Series)audiovisual).getTemporada() == ((Series)audi).getTemporada())
				{
					cantidadEpisodios++;
				}
			}
		}
		return cantidadEpisodios;
	}
	
	public static Audiovisuales buscarAudiovisualPorNombreyFecha(String nombreAudiovisual, ArrayList<Audiovisuales> audiovisuales) throws Exception {

        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();

            if (audiovisual.getNombre().replace(" ", "").toUpperCase() == nombreAudiovisual.replace(" ", "").toUpperCase()){
                return audiovisual;
            }
        }
        return null;
    }

    public static void serieNoCalificadaPorHombresAdultos() throws Exception {
		/*Código  y  nombre  de  serie,  temporada  y  episodio  de  aquellas  que  no  hayan  sido
		calificadas por ningún suscriptor masculino adulto menor de 45 años.*/

		ArrayList<Audiovisuales> audiovisuales = CalificacionesTXT.bajarCalificaciones();

		Calendar fechaActual = Calendar.getInstance();
		boolean menorA45;
		boolean calificadaPorHombreAdulto;

		for (Audiovisuales audiovisual : audiovisuales) {
			calificadaPorHombreAdulto = false;

			if (audiovisual instanceof Series){
				for (Calificaciones calificacion : audiovisual.getCalificaciones()) {

					menorA45 = Validaciones.menor(calificacion.getSuscriptor().getFechaDeNac(), fechaActual, 45);

					if (menorA45 && calificacion.getSuscriptor().getSexo() == 'M') {
						calificadaPorHombreAdulto = true;
					}
				}

				if (!calificadaPorHombreAdulto){
					Mostrar.mostrar(audiovisual.getNombre() + ((Series) audiovisual).getTemporada() + ((Series) audiovisual).getEpisodio());
				}
			}
		}
	}

	public static void peliculaAlAzar() throws Exception {
		/*Apellido y nombre de los actores (ordenados por ambos), duración de una película, fecha
		de  publicación  y  evaluaciones  (fecha,  nombre  del  suscriptor  y  calificación)  de  una
		película seleccionada al azar.*/

		ArrayList<Audiovisuales> audiovisuales = CalificacionesTXT.bajarCalificaciones();
		Actores actor;

		Random rand = new Random();
		Audiovisuales azar;
		do {
			azar = audiovisuales.get(rand.nextInt(audiovisuales.size()));
		} while (azar instanceof Series);
		TreeSet<Actores> actores = azar.getActores();

		Iterator<Actores> iteratorActores = actores.iterator();
		while (iteratorActores.hasNext()) {
			actor = iteratorActores.next();

			Mostrar.mostrar("Apellido: " + actor.getApellido());
			Mostrar.mostrar("Nombre: " + actor.getNombre());
		}

		Mostrar.mostrar("Duracion de pelicula: " + ((Peliculas) azar).getDuracion());
		Mostrar.mostrar("Fecha de publicacion: " + azar.getFechaPubli());

		Mostrar.mostrar("Calificaciones");
		for (Calificaciones calificacion: azar.getCalificaciones()
			 ) {
			Mostrar.mostrar("Fecha realizada: " + calificacion.getFechaRealizada());
			Mostrar.mostrar("Nombre del suscriptor: " + calificacion.getSuscriptor().getNombre());
			Mostrar.mostrar("Estrellas: " + calificacion.getEstrellas());
		}
	}

	
}
