package controller;

import model.*;
import model.DAO.AudiovisualesJSON;
import view.Mostrar;
import view.Validaciones;

import java.util.*;

public class SuscriptorControlador {

    public static Suscriptores buscarSuscriptor(int codSuscriptor, TreeSet<Suscriptores> suscriptores) {

        Suscriptores suscriptor;

        Iterator<Suscriptores> iteratorAudiovisuales = suscriptores.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            suscriptor = iteratorAudiovisuales.next();

            if (suscriptor.getCodigo() == codSuscriptor) {
                return suscriptor;
            }
        }
        return null;
    }

    public static void recomendarMejorSerie(ArrayList<Audiovisuales> audiovisuales) {

        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaAnterior = Calendar.getInstance();
        Validaciones.tresMesesAntes(fechaAnterior);

        int promedioTemporada;
        int mejorPromedio = -1;
        boolean menorA35;

        Audiovisuales mejorAudiovisual = null;
        
        for (Audiovisuales audiovisual : audiovisuales) {
        	
        	int sumaEstrellas = 0;
            int cantidadCalificaciones = 0;
            
        	if(audiovisual instanceof Series) {
        		for (Audiovisuales au : audiovisuales) {
        			if(au instanceof Series) {
	            		if(((Series) au).getTemporada() == ((Series) audiovisual).getTemporada()
                                && au.getNombre().equals(audiovisual.getNombre())) {
	            			
	    	                for (Calificaciones calificacion : au.getCalificaciones()) {
	
	    	                    menorA35 = Validaciones.menor(calificacion.getSuscriptor().getFechaDeNac(), fechaActual, 35);
	    	                    
	    	                    if (menorA35 && calificacion.getFechaRealizada().after(fechaAnterior)) {                                                                                     // Suscriptor es menor de 35?
	    	                        sumaEstrellas += calificacion.getEstrellas();
	    	                        cantidadCalificaciones++;
	    	                    }	
	    	                } 
	            		}
        			}
            	}
        		if (cantidadCalificaciones != 0) {
	        		promedioTemporada = sumaEstrellas/ cantidadCalificaciones;
	        		 if (promedioTemporada > mejorPromedio) {
	                     mejorPromedio = promedioTemporada;
	                     mejorAudiovisual = audiovisual;
	                 }
        		}
        	}
        }

        int cant = 0;
        for (Audiovisuales audiovisual : audiovisuales) {
            if (audiovisual instanceof Series
                && audiovisual.getNombre().equals(mejorAudiovisual.getNombre())
                && ((Series) audiovisual).getTemporada() == ((Series) mejorAudiovisual).getTemporada()){
                cant++;
            }
        }

        if (mejorAudiovisual != null){
            AudiovisualesJSON.grabarRecomendacionesSeriesJovenesJSON(mejorAudiovisual, cant);
        } else {
            Mostrar.mostrar("No hay serie a recomendar");
        }

    }
    
    public static void recomendarMejorPeliculaDeGenero(ArrayList<Audiovisuales> audiovisuales, ArrayList<Generos> generos) {

    	ArrayList<Audiovisuales> audiovisualesAux = new ArrayList<Audiovisuales>();
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaAnterior = Calendar.getInstance();
        Validaciones.ultimoMes(fechaAnterior);
        
        int promedio;
        int mejorPromedio;
        boolean mayorA55;

        Audiovisuales mejorAudiovisual;

        for (Generos genero : generos) {
            mejorAudiovisual = null;
            mejorPromedio = -1;
            for (Audiovisuales audiovisual : audiovisuales) {
            	int sumaEstrellas = 0;
                int cantidadCalificaciones = 0;
                if (audiovisual instanceof Peliculas && genero.getCodigo() == audiovisual.getGenero().getCodigo()) {

                    for (Calificaciones calificacion : audiovisual.getCalificaciones()) {

                        mayorA55 = Validaciones.mayor(calificacion.getSuscriptor().getFechaDeNac(), fechaActual, 55);
                        
                        if (mayorA55 && calificacion.getFechaRealizada().after(fechaAnterior) ) {                                                                                     // Suscriptor es mayor de 55?
                            sumaEstrellas += calificacion.getEstrellas();
                            cantidadCalificaciones++;
                        }
                    }
                    if (cantidadCalificaciones != 0) {
                        promedio = sumaEstrellas / cantidadCalificaciones;
                        if (promedio > mejorPromedio) {
                            mejorPromedio = promedio;
                            mejorAudiovisual = audiovisual;

                        }
                    }
                }
            }
            if (mejorAudiovisual != null) {
                audiovisualesAux.add(mejorAudiovisual);
            }
        }

        if (!audiovisualesAux.isEmpty()){
            AudiovisualesJSON.grabarRecomendacionesPeliculasMayoresJSON(audiovisualesAux);
        } else {
            Mostrar.mostrar("No hay peliculas a recomendar");
        }

    }
    
    public static void infoSeries(ArrayList<Audiovisuales> audiovisuales, ArrayList<Generos> generos) {
    	//Para cada uno de los distintos géneros, nombre de la serie, 
    	//cantidad total de temporadas y cantidad de actores, 
    	//ordenadas según la cantidad total de temporadas en forma descendente.
    	
    	ArrayList<Series> seriesAux = new ArrayList<Series>();
    	ArrayList<Series> seriesNoRepetidas = new ArrayList<Series>();
    	TreeSet<Actores> actor = new TreeSet<Actores>();
    	
    	for(Generos gen : generos) {
    		Mostrar.mostrar("GENERO: " + gen.getDescripcion());
    		seriesAux.clear();
    		for(Audiovisuales audi : audiovisuales) {
        		if(audi instanceof Series && audi.getGenero().getCodigo() == gen.getCodigo()) {
        			
        			seriesAux.add((Series) audi);
        		}
        	}

    		if (!seriesAux.isEmpty()){
                Collections.sort(seriesAux);

                seriesNoRepetidas.clear();

                String nombreSerie = "";
                for (Series ser : seriesAux) {

                    if (!nombreSerie.equals(ser.getNombre())) {

                        seriesNoRepetidas.add(ser);
                        nombreSerie = ser.getNombre();
                    }
                }
                Collections.sort(seriesNoRepetidas);

                for (Series ser : seriesNoRepetidas) {
                    Mostrar.mostrar("Serie: " + ser.getNombre());
                    Mostrar.mostrar("Cantidad de temporadas : " + ser.getTemporada());
                    //Al estar ordenado, nos trae la temporada más alta, por ende, es la cantidad de temporadas que posee la serie
                    actor.clear();
                    for (Audiovisuales audi : audiovisuales) {

                        if (audi instanceof Series && ser.getCodigo() == audi.getCodigo()) {

                            actor.addAll(audi.getActores());
                        }
                    }
                    Mostrar.mostrar("Cantidad de actores : " + actor.size());
                }
            } else {
                Mostrar.mostrar("No hay series para el genero " + gen.getDescripcion());

            }
    	}	
    }

    public static void mayoresSinCalificar(ArrayList<Audiovisuales> audiovisuales, TreeSet<Suscriptores> suscriptores) {

        /*Nombre y apellido de los suscriptores mayores de 60 anios que nunca hayan calificado
        una pelicula.*/

        TreeSet<Suscriptores> suscriptoresMayores = new TreeSet<Suscriptores>();

        Calendar fechaActual = Calendar.getInstance();

        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();
            if (Validaciones.mayor(suscriptor.getFechaDeNac(), fechaActual, 60)) {
                suscriptoresMayores.add(suscriptor);                                                                    // Colecto los mayores a 60 = no colecto los menores de 60
            }
        }

        for (Audiovisuales audiovisual : audiovisuales) {

            if (audiovisual instanceof Peliculas && audiovisual.getCalificaciones() != null) {                           // Si fue calificado, recorro las calificaciones

                for (Calificaciones calificacion : audiovisual.getCalificaciones()) {
                    suscriptoresMayores.remove(calificacion.getSuscriptor());                                           // Quito los mayores que hayan calificado
                }
            }
        }

        if (suscriptoresMayores.isEmpty()){
            Mostrar.mostrar("No hay suscriptores mayores de 60 anios que nunca hayan calificado una pelicula");
        } else {
            mostrarSuscriptores(suscriptoresMayores);
        }
    }

    public static void mostrarSuscriptores(TreeSet<Suscriptores> suscriptores) {
        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();

            Mostrar.mostrar("Nombre: " + suscriptor.getNombre() + "Apellido: " + suscriptor.getApellido());
        }
    }
}
