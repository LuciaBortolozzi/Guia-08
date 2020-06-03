package controller;


import model.*;
import model.DAO.AudiovisualesJSON;
import model.DAO.GenerosTXT;
import model.DAO.SuscriptoresTXT;
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

    public static Audiovisuales recomendarMejorSerie(ArrayList<Audiovisuales> audiovisuales) throws Exception {

        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaAnterior = Validaciones.tresMesesAntes(fechaActual);

        int promedioTemporada;
        int mejorPromedio = -1;
        boolean menorA35;

        Audiovisuales mejorAudiovisual = null;
        
        for (Audiovisuales audi : audiovisuales) {
        	
        	int sumaEstrellas = 0;
            int cantidadCalificaciones = 0;
            
        	if(audi instanceof Series) {
        		for (Audiovisuales au : audiovisuales) {
        			if(audi instanceof Series) {
	            		if(((Series)au).getTemporada() == ((Series)audi).getTemporada() && ((Series)au).getNombre().equals(((Series)audi).getNombre())) {
	            			
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
	                     mejorAudiovisual = audi;
	                 }
        		}
        	}
        }
        return mejorAudiovisual;
    }
    
    public static ArrayList<Audiovisuales> recomendarMejorPelicula(ArrayList<Audiovisuales> audiovisuales, ArrayList<Generos> generos) throws Exception {

    	ArrayList<Audiovisuales> audiovisualesAux = new ArrayList<Audiovisuales>();
        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaAnterior = Validaciones.ultimoMes(fechaActual);

        
        int promedio;
        int mejorPromedio = -1;
        boolean mayorA55;

        Audiovisuales mejorAudiovisual = null;

        for (Generos genero : generos) {
        	
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
                }

                if (cantidadCalificaciones != 0) {
                    promedio = sumaEstrellas / cantidadCalificaciones;
                    if (promedio > mejorPromedio) {
                        mejorPromedio = promedio;
                        mejorAudiovisual = audiovisual;
                    }
                }
                sumaEstrellas = 0;
                cantidadCalificaciones = 0;
            }
            audiovisualesAux.add(mejorAudiovisual);
        }

        return audiovisualesAux;
    }
    
    public static void infoSeries(ArrayList<Audiovisuales> audiovisuales, ArrayList<Generos> generos) {
    	//Para cada uno de los distintos géneros, nombre de la serie, 
    	//cantidad total de temporadas y cantidad de actores, 
    	//ordenadas según la cantidad total de temporadas en forma descendente.
    	/*
    	ArrayList<Series> seriesAux = new ArrayList<Series>();
    	
    	for(Audiovisuales audi : audiovisuales) {
    		if(audi instanceof Series) {
    			
    			seriesAux.add((Series)audi);
    		}
    	}
    	
    	Collections.sort(seriesAux);
    	for(Generos gen : generos) {
    		
    		for(Series ser : seriesAux) {
    			
    			
    		}
    		
    	}
    	*/
    	
    }

    public static void mayoresSinCalificar(ArrayList<Audiovisuales> audiovisuales, TreeSet<Suscriptores> suscriptores) throws Exception {

        /*Nombre y apellido de los suscriptores mayores de 60 aÃ±os que nunca hayan calificado
        una pelÃ­cula.*/

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

        mostrarSuscriptores(suscriptoresMayores);
    }

    public static void mostrarSuscriptores(TreeSet<Suscriptores> suscriptores) {
        Suscriptores suscriptor;
        Iterator<Suscriptores> iteratorSuscriptores = suscriptores.iterator();
        while (iteratorSuscriptores.hasNext()) {
            suscriptor = iteratorSuscriptores.next();

            Mostrar.mostrar(suscriptor.getNombre() + suscriptor.getApellido());
        }
    }
}
