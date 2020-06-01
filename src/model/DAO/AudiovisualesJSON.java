package model.DAO;

import model.*;
import java.io.*;
import java.util.*;
import javax.json.*;

public class AudiovisualesJSON {

    //private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public static ArrayList <Audiovisuales> bajarAudiovisualesJSON() throws Exception {

        ArrayList<Audiovisuales> audiovisuales = AudiovisualesActoresTXT.leoDevuelvoArchivoAudiovisuales();
        Calendar fechaActual = Calendar.getInstance();
        File aJson;
        FileInputStream fsInJson;
        try {
            aJson = new File(directorio + "Audiovisuales.json");
            if (aJson.exists()){
                fsInJson = new FileInputStream(aJson);
                JsonReader rdrJson = Json.createReader(fsInJson);
                JsonObject objJson = (JsonObject) rdrJson.readObject();
                JsonArray peliculas = (JsonArray)objJson.get("Peliculas");

                int m = 0;
                Iterator iteratorPeliculas = peliculas.iterator();
                while (iteratorPeliculas.hasNext()) {
                    JsonObject pelicula = (JsonObject) iteratorPeliculas.next();
                    
        			boolean encontrado = false;
        			
        			for(int i=0;i<audiovisuales.size();i++)
        			{
	        			 if(audiovisuales.get(i) instanceof Peliculas) 
	        			 {
	        					if( (pelicula.get("nombre").toString()).replace(" ", "").toUpperCase().equals(audiovisuales.get(i).getNombre().replace(" ", "").toUpperCase())) 
	        					{//MODIFICACION
	        						audiovisuales.get(i).setSinopsis((pelicula.get("sinopsis").toString()));
	        						audiovisuales.get(i).setFechaPubli(fechaActual); 
	        						
	        						JsonArray actoresJSONArray = pelicula.getJsonArray("actores");
	        	                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
	        	                    setActorJSON(actoresArray, actoresJSONArray);
	        						audiovisuales.get(i).setActores(actoresArray);
	        						
	        						ArrayList<Generos> generos = GenerosTXT.bajarGeneros();
	    			            	Generos g;
	    			 				Iterator<Generos> gen = generos.iterator();
	    			 				while (gen.hasNext()) {
	    			 					g=gen.next();
	    			 					if(g.getDescripcion().equals(pelicula.get("genero").toString().trim())) {
	    			 						
	    			 						audiovisuales.get(i).setGenero(g);
	    			 					}
	    			 				}
	        						
	        						((Peliculas)audiovisuales.get(i)).setAnioFilm(Integer.parseInt((pelicula.get("anio")).toString()));
	        						((Peliculas)audiovisuales.get(i)).setDuracion(Integer.parseInt(pelicula.get("duracion").toString()));
	        						encontrado = true;
	        					}
	        			 }
        			}
        			
        			if(!encontrado) 
        			{
        				//NUEVA PELICULA
        				audiovisuales.set(audiovisuales.size(), new Series());
        				audiovisuales.get(audiovisuales.size()).setNombre((pelicula.get("nombre").toString()));
        				audiovisuales.get(audiovisuales.size()).setSinopsis((pelicula.get("sinopsis").toString()));
        				audiovisuales.get(audiovisuales.size()).setFechaPubli(fechaActual);
        				
        				JsonArray actoresJSONArray = pelicula.getJsonArray("actores");
	                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
	                    setActorJSON(actoresArray, actoresJSONArray);
						audiovisuales.get(audiovisuales.size()).setActores(actoresArray);
        				
						ArrayList<Generos> generos = GenerosTXT.bajarGeneros();
						Generos g;
		 				Iterator<Generos> gen = generos.iterator();
		 				while (gen.hasNext()) {
		 					g=gen.next();
		 					if(g.getDescripcion().equals(pelicula.get("genero").toString().trim())) {
		 						
		 						audiovisuales.get(audiovisuales.size()).setGenero(g);
		 					}
		 				}
        			
        				((Peliculas)audiovisuales.get(audiovisuales.size())).setAnioFilm(Integer.parseInt((pelicula.get("anio")).toString()));
    					((Peliculas)audiovisuales.get(audiovisuales.size())).setDuracion(Integer.parseInt(pelicula.get("duracion").toString()));
        			}
                }

                JsonArray series = (JsonArray)objJson.get("Series");
                Iterator iteratorSeries = series.iterator();
                while (iteratorSeries.hasNext()) {

                    JsonObject serie = (JsonObject) iteratorSeries.next();
                    
                    boolean encontrado = false;
        			
        			for(int i=0;i<audiovisuales.size();i++)
        			{
	        			 if(audiovisuales.get(i) instanceof Peliculas) 
	        			 {
	        					if( (serie.get("nombre").toString()).replace(" ", "").toUpperCase().equals(audiovisuales.get(i).getNombre().replace(" ", "").toUpperCase()) &&
	        							Integer.parseInt((serie.get("temporada")).toString()) == ((Series)audiovisuales.get(i)).getTemporada() && 
	        							Integer.parseInt((serie.get("episodio")).toString()) == ((Series)audiovisuales.get(i)).getEpisodio()) 
	        					{//MODIFICACION
	        						audiovisuales.get(i).setSinopsis((serie.get("sinopsis").toString()));
	        						audiovisuales.get(i).setFechaPubli(fechaActual); 
	        						
	        						JsonArray actoresJSONArray = serie.getJsonArray("actores");
	        	                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
	        	                    setActorJSON(actoresArray, actoresJSONArray);
	        						audiovisuales.get(i).setActores(actoresArray);
	        						
	        						ArrayList<Generos> generos = GenerosTXT.bajarGeneros();
	    			            	Generos g;
	    			 				Iterator<Generos> gen = generos.iterator();
	    			 				while (gen.hasNext()) {
	    			 					g=gen.next();
	    			 					if(g.getDescripcion().equals(serie.get("genero").toString().trim())) {
	    			 						
	    			 						audiovisuales.get(i).setGenero(g);
	    			 					}
	    			 				}
	        						
	        						((Series) audiovisuales.get(i)).setTemporada(Integer.parseInt((serie.get("temporada")).toString()));
	        	                    ((Series) audiovisuales.get(i)).setEpisodio(Integer.parseInt((serie.get("episodio")).toString()));
	        						encontrado = true;
	        					}
	        			 }
        			}
        			
        			if(!encontrado) 
        			{
        				//NUEVA SERIE
        				audiovisuales.set(audiovisuales.size(), new Series());
        				audiovisuales.get(audiovisuales.size()).setNombre((serie.get("nombre").toString()));
        				audiovisuales.get(audiovisuales.size()).setSinopsis((serie.get("sinopsis").toString()));
        				audiovisuales.get(audiovisuales.size()).setFechaPubli(fechaActual);
        				
        				JsonArray actoresJSONArray = serie.getJsonArray("actores");
	                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
	                    setActorJSON(actoresArray, actoresJSONArray);
						audiovisuales.get(audiovisuales.size()).setActores(actoresArray);
        				
						ArrayList<Generos> generos = GenerosTXT.bajarGeneros();
						Generos g;
		 				Iterator<Generos> gen = generos.iterator();
		 				while (gen.hasNext()) {
		 					g=gen.next();
		 					if(g.getDescripcion().equals(serie.get("genero").toString().trim())) {
		 						
		 						audiovisuales.get(audiovisuales.size()).setGenero(g);
		 					}
		 				}
        			
		 				((Series) audiovisuales.get(audiovisuales.size())).setTemporada(Integer.parseInt((serie.get("temporada")).toString()));
	                    ((Series) audiovisuales.get(audiovisuales.size())).setEpisodio(Integer.parseInt((serie.get("episodio")).toString()));
        			}
                }

                rdrJson.close();
                fsInJson.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return audiovisuales;
    }

    private static void setActorJSON(TreeSet<Actores> actoresArray, JsonArray actoresJSONArray) {

        TreeSet<Actores> actores = ActoresTXT.bajarActores();
        ArrayList<String> actoresST = new ArrayList<String>(actoresJSONArray.size());

        for(JsonValue jsonV : actoresJSONArray) {
            actoresST.add(jsonV.toString().replace("\"", "").trim());
        }

        for(String a: actoresST) {
            Actores actor;
            Iterator<Actores> actorIterator = actores.iterator();
            while (actorIterator.hasNext()) {
                actor = actorIterator.next();
                String concatenaNombreApellido = actor.getNombre() + actor.getApellido();
                if(concatenaNombreApellido.replace(" ","").equals(a.replace("\"", "").replace(" ",""))) {
                    actoresArray.add(actor);
                }
            }
        }
    }

    
    //FUNCIONA??? NO LO SE RICK..
    
    public static void grabarRecomendacionesSeriesJovenesJSON(Audiovisuales audiovisuales) throws Exception{
    	
    	File aJson = new File(directorio + "RecomendacionesSeriesJovenes.json");
        FileOutputStream fsOutJson = new FileOutputStream(aJson);
        JsonWriter wrtJson = Json.createWriter(fsOutJson);

        try {

            JsonObjectBuilder empresa = Json.createObjectBuilder();
     
            empresa.add("Empresa", "PeliSeri");
            
            JsonObjectBuilder detalleSerie = Json.createObjectBuilder();
            
            detalleSerie.add("Nombre", audiovisuales.getNombre());
            
            detalleSerie.add("Genero", audiovisuales.getGenero().getDescripcion());
            
            JsonArrayBuilder actores = Json.createArrayBuilder();
            Actores a;
				Iterator<Actores> act = audiovisuales.getActores().iterator();
				while (act.hasNext()) {
					a=act.next();
					actores.add((JsonValue) a); //NO SE SI ESTO VA A FUNCIONAR
				}
				
			JsonObject actoresJson = (javax.json.JsonObject) actores.build();
				
			detalleSerie.add("Actores", actoresJson);
			
			detalleSerie.add("Sinopsos", audiovisuales.getSinopsis());
			
			detalleSerie.add("Temporada", ((Series)audiovisuales).getTemporada());
			
			//LUUUU ACÁ ME VAS A TENER QUE PASAR LA CANTIDAD DE EPISODIOS TAMBIEN
			detalleSerie.add("Episodios", ((Series)audiovisuales).getEpisodio());
			
			int sumaEstrellas = 0;
			int cantidadCalificaciones = 0;
			for (Calificaciones calificacion : audiovisuales.getCalificaciones()) 
			{
               sumaEstrellas += calificacion.getEstrellas();
               cantidadCalificaciones++;
			}
		   
			int calificacion = sumaEstrellas/cantidadCalificaciones;
			detalleSerie.add("Calificacion",calificacion);
			
			JsonObject JSerie = detalleSerie.build();
			
			empresa.add("Serie", JSerie);
			
			JsonObject JsonSerie = empresa.build();
            
            wrtJson.writeObject(JsonSerie);

            wrtJson.close();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            fsOutJson.close();
        }
    	
    }
    
    public static void grabarRecomendacionesPeliculasMayoresJSON(ArrayList <Audiovisuales> audiovisuales) throws Exception{
    	
    	File aJson = new File(directorio + "RecomendacionesSeriesJovenes.json");
        FileOutputStream fsOutJson = new FileOutputStream(aJson);
        JsonWriter wrtJson = Json.createWriter(fsOutJson);

        try {

            JsonObjectBuilder empresa = Json.createObjectBuilder();
     
            empresa.add("Empresa", "PeliSeri");
            
            JsonArrayBuilder peliculas = Json.createArrayBuilder();
            
            for(Audiovisuales audi : audiovisuales) {
            	
            	JsonObjectBuilder detallePelicula = Json.createObjectBuilder();
                
            	detallePelicula.add("Nombre", audi.getNombre());
                
            	detallePelicula.add("Genero", audi.getGenero().getDescripcion());
                
                JsonArrayBuilder actores = Json.createArrayBuilder();
                Actores a;
				Iterator<Actores> act = audi.getActores().iterator();
				while (act.hasNext()) {
					a=act.next();
					actores.add((JsonValue) a); //NO SE SI ESTO VA A FUNCIONAR
				}
				
				JsonObject actoresJson = (javax.json.JsonObject) actores.build();
				
				detallePelicula.add("Actores", actoresJson);
			
				detallePelicula.add("Sinopsos", audi.getSinopsis());
    			
    			detallePelicula.add("Anio", ((Peliculas)audi).getAnioFilm());
    			
    			detallePelicula.add("Duracion", ((Peliculas)audi).getDuracion());
    			
    			int sumaEstrellas = 0;
    			int cantidadCalificaciones = 0;
    			for (Calificaciones calificacion : audi.getCalificaciones()) 
    			{
                   sumaEstrellas += calificacion.getEstrellas();
                   cantidadCalificaciones++;
    			}
    		   
    			int calificacion = sumaEstrellas/cantidadCalificaciones;
    			detallePelicula.add("Calificacion",calificacion);
    			
    			JsonObject JPelicula = detallePelicula.build();
    			
    			peliculas.add(JPelicula);
            }
            
            JsonObject JsonPeliculas = (javax.json.JsonObject) peliculas.build();
			
			empresa.add("Peliculas", JsonPeliculas);
			
			JsonObject JsonFinal = empresa.build();
            
            wrtJson.writeObject(JsonFinal);

            wrtJson.close();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            fsOutJson.close();
        }
    	
    	
    }
}
