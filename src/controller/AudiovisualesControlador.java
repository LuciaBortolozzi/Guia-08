package controller;


import java.util.*;

import model.*;
import model.DAO.*;
import view.*;

public class AudiovisualesControlador {

    public static Audiovisuales buscarAudiovisual(int codAudiovisual, ArrayList<Audiovisuales> audiovisuales) {

        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();

            if (audiovisual.getCodigo() == codAudiovisual) {
                return audiovisual;
            }
        }
        return null;
    }

    public static ArrayList<Audiovisuales> asignarPagos(ArrayList<Audiovisuales> audiovisualesTXT) {

        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaHace1mes = Calendar.getInstance();
        Validaciones.ultimoMes(fechaHace1mes);
        Calendar fechaProximoPago = Calendar.getInstance();
        Calendar fechaActualHace1anio = Calendar.getInstance();
        Validaciones.unAnioAntes(fechaActualHace1anio);
        Calendar fechaHace1mesHace1anio = Calendar.getInstance();
        Validaciones.ultimoMes(fechaHace1mesHace1anio);
        Validaciones.unAnioAntes(fechaHace1mesHace1anio);
        ArrayList<Audiovisuales> audiovisualesAuxiliar = new ArrayList<Audiovisuales>();

        double montoDerPelicula = Validaciones.validarDouble("monto de los derechos de peliculas");

        double montoDerSeries = Validaciones.validarDouble("monto de los derechos de series");

        boolean primeroDelMes = true;
        int cantidadPublicaciones = 0;
        double totalAbonar = 0;
        
        for (Audiovisuales audi : audiovisualesTXT) {
            if (audi.getFechaPubli().after(fechaHace1mes) && audi.getFechaPubli().before(fechaActual)) {
                if (primeroDelMes) {
                    fechaProximoPago = Validaciones.UnMesDespues(fechaProximoPago);
                    primeroDelMes = false;

                } else {
                    fechaProximoPago = Validaciones.UnaSemanaDespues(fechaProximoPago);
                }

                if (audi instanceof Peliculas) {
                    audi.setCronogramaPagos(fechaProximoPago, montoDerPelicula);

                } else if (audi instanceof Series) {
                    audi.setCronogramaPagos(fechaProximoPago, montoDerSeries);
                }

                cantidadPublicaciones++;
                totalAbonar = totalAbonar + audi.getCronogramaPagos().getMonto();

            } else if (audi.getFechaPubli().after(fechaHace1mesHace1anio) && audi.getFechaPubli().before(fechaActualHace1anio)) {
                if (primeroDelMes) {
                    fechaProximoPago = Validaciones.UnMesDespues(fechaProximoPago);
                    primeroDelMes = false;
                } else {
                    fechaProximoPago = Validaciones.UnaSemanaDespues(fechaProximoPago);
                }

                if (audi instanceof Peliculas) //MODIFICAR LOS MONTOS
                {
                    montoDerPelicula = audi.calculoMontoTotalPeliculas();
                    audi.setCronogramaPagos(fechaProximoPago, montoDerPelicula);

                } else if (audi instanceof Series) {
                    int cantidadEpisodios = ContarEpisodiosTemporada(audiovisualesTXT, audi);

                    if (cantidadEpisodios > 12) {
                        montoDerSeries = audi.calculoMontoTotalSeriesMasDoce();
                    } else {
                        montoDerSeries = audi.calculoMontoTotalSeriesMenosDoce();
                    }

                    audi.setCronogramaPagos(fechaProximoPago, montoDerSeries);
                }

                cantidadPublicaciones++;
                totalAbonar = totalAbonar + audi.getCronogramaPagos().getMonto();
            }

            audiovisualesAuxiliar.add(audi);
        }

        CronogramaPagosTXT.grabarCronogramaPagosTXT(audiovisualesAuxiliar, cantidadPublicaciones, totalAbonar);

        return audiovisualesTXT;
    }

    public static int ContarEpisodiosTemporada(ArrayList<Audiovisuales> audiovisuales, Audiovisuales audi) {

        int cantidadEpisodios = 0;

        for (Audiovisuales audiovisual : audiovisuales) {
            if (audiovisual instanceof Series) {
                if (audiovisual.getNombre().replace(" ", "").toUpperCase().equals(audi.getNombre().replace(" ", "").toUpperCase())
                        && ((Series) audiovisual).getTemporada() == ((Series) audi).getTemporada()) {
                    cantidadEpisodios++;
                }
            }
        }
        return cantidadEpisodios;
    }

    public static void serieNoCalificadaPorHombresAdultos(ArrayList<Audiovisuales> audiovisuales) {
		/*Codigo  y  nombre  de  serie,  temporada  y  episodio  de  aquellas  que  no  hayan  sido
		calificadas por ningun suscriptor masculino adulto menor de 45 anios.*/

        Calendar fechaActual = Calendar.getInstance();
        boolean menorA45;
        boolean calificadaPorHombreAdulto;

        for (Audiovisuales audiovisual : audiovisuales) {
            calificadaPorHombreAdulto = false;

            if (audiovisual instanceof Series) {
                for (Calificaciones calificacion : audiovisual.getCalificaciones()) {
                    calificadaPorHombreAdulto = false;
                    menorA45 = Validaciones.menor(calificacion.getSuscriptor().getFechaDeNac(), fechaActual, 45);

                    if (menorA45 && calificacion.getSuscriptor().getSexo() == 'M') {
                        calificadaPorHombreAdulto = true;
                    }
                }

                if (!calificadaPorHombreAdulto) {
                    Mostrar.mostrar("Codigo: "+ audiovisual.getCodigo()
                            + " Nombre: " + audiovisual.getNombre()
                            + " Temporada: " + ((Series) audiovisual).getTemporada()
                            + " Episodio: " + ((Series) audiovisual).getEpisodio());
                }
            }
        }
    }

    public static void peliculaAlAzar(ArrayList<Audiovisuales> audiovisuales) {
		/*Apellido y nombre de los actores (ordenados por ambos), duracion de una pelicula, fecha
		de  publicacion  y  evaluaciones  (fecha,  nombre  del  suscriptor  y  calificacion)  de  una
		pelicula seleccionada al azar.*/

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
        for (Calificaciones calificacion : azar.getCalificaciones()
        ) {
            Mostrar.mostrar("Fecha realizada: " + calificacion.getFechaRealizada());
            Mostrar.mostrar("Nombre del suscriptor: " + calificacion.getSuscriptor().getNombre());
            Mostrar.mostrar("Estrellas: " + calificacion.getEstrellas());
        }
    }

    public static void superanAgumento(ArrayList<Audiovisuales> audiovisuales, String arg) {
    	
    	Calendar fechaActual = Calendar.getInstance();
    	double argumento = Double.parseDouble(arg);
    	for(Audiovisuales audi : audiovisuales) {
    		boolean cumple = true;
    		double difPorcentaje = calculoPorcentaje(audi.getCronogramaPagos().getMonto(),argumento);
    		
    		if( difPorcentaje > 10) {
    			
    			for(Calificaciones cal : audi.getCalificaciones()) {
    				
    				if(cal.getEstrellas()>=3 || cal.getFechaRealizada().get(Calendar.YEAR) != fechaActual.get(Calendar.YEAR)) {
    					
    					cumple = false;
    					break;
    				}
    			}
    			
    			if(cumple) {
    				
    				Mostrar.mostrar("Nombre de la publicacion:" + audi.getNombre());
    				Mostrar.mostrar("Genero:" + audi.getGenero().getDescripcion());
    			}
    		}	
    	}	
    }
    
    public static double calculoPorcentaje(double monto, double argumento) {
    	
    	double porcentajeMonto = (monto * 100)/argumento;
    	
    	double difPorcentaje = porcentajeMonto - 100;
    	
    	return difPorcentaje;
    }

    /*public static Audiovisuales buscarAudiovisualPorNombreyFecha(String nombreAudiovisual, ArrayList<Audiovisuales> audiovisuales) {

        Audiovisuales audiovisual;
        Iterator<Audiovisuales> iteratorAudiovisuales = audiovisuales.iterator();
        while (iteratorAudiovisuales.hasNext()) {
            audiovisual = iteratorAudiovisuales.next();

            if (audiovisual.getNombre().replace(" ", "").toUpperCase() == nombreAudiovisual.replace(" ", "").toUpperCase()) {
                return audiovisual;
            }
        }
        return null;
    }*/

    /*	public static ArrayList<Audiovisuales> agregarActoresEnAudiovisualesTXT (ArrayList<Audiovisuales> audiovisualesTXT, TreeSet<Actores> audiovisualesActoresTXT, TreeSet<Actores> actoresTXT){

	    for (String audiAct : audiovisualActoresST) {
            TreeSet<Actores> actoresArray = new TreeSet<Actores>();
            String[] audiActST = audiAct.split("\t");                    // Revisarr
            for (Audiovisuales audi : audiovisuales) {
                if (Integer.parseInt(audiActST[0]) == audi.getCodigo()) {
                    for (int i = 1; i < 10; i++) {
                        if (audiActST[i] == null) {

                            break;
                        } else {
                            Actores a;
                            Iterator<Actores> act = actores.iterator();
                            while (act.hasNext()) {
                                a = act.next();
                                String concatenoNombreApellido = a.getNombre() + a.getApellido();
                                if (concatenoNombreApellido.replace(" ", "").equals(audiActST[i].replace(" ", ""))) {
                                    actoresArray.add(a);
                                }
                            }
                        }
                    }

                    audi.setActores(actoresArray);
                }
            }
        }


		return audiovisualesTXT;
	}
	*/
}
