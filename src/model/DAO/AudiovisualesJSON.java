package model.DAO;

import model.*;

import java.io.*;
import java.util.*;


import javax.json.*;
import javax.json.stream.JsonParser;

public class AudiovisualesJSON {

    //private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public static ArrayList <Audiovisuales> bajarAudiovisuales() throws Exception {

        ArrayList <Audiovisuales> audiovisualesAux = new ArrayList<Audiovisuales>();

        File aJson;
        FileInputStream fsInJson;
        try {
            aJson = new File(directorio + "Audiovisuales.json");
            if (aJson.exists()){
                fsInJson = new FileInputStream(aJson);
                JsonReader rdrJson = Json.createReader(fsInJson);
                JsonObject objJson = (JsonObject) rdrJson.readObject();
                JsonArray peliculas = (JsonArray)objJson.get("Peliculas");

                int i = 0;
                Iterator iteratorPeliculas = peliculas.iterator();
                while (iteratorPeliculas.hasNext()) {
                    JsonObject pelicula = (JsonObject) iteratorPeliculas.next();
                    audiovisualesAux.set(i, new Peliculas());
                    ((Peliculas) audiovisualesAux.get(i)).setNombre((pelicula.get("nombre").toString()));
                    ((Peliculas) audiovisualesAux.get(i)).setAnioFilm(Integer.parseInt((pelicula.get("anio")).toString()));
                    ((Peliculas) audiovisualesAux.get(i)).setSinopsis ((pelicula.get("sinopsis").toString()));
                    
                    ArrayList<Generos> generos = GenerosTXT.bajarGeneros();

	            	Generos g;
	 				Iterator<Generos> gen = generos.iterator();
	 				while (gen.hasNext()) {
	 					g=gen.next();
	 					if(g.getDescripcion().equals(pelicula.get("genero").toString().trim())) {
	 						
	 						((Peliculas) audiovisualesAux.get(i)).setGenero(g);
	 					}
	 				}

                    JsonArray actoresJSONArray = pelicula.getJsonArray("actores");
                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                    setActorJSON(actoresArray, actoresJSONArray);
                    ((Peliculas) audiovisualesAux.get(i)).setActores(actoresArray);
                    ((Peliculas) audiovisualesAux.get(i)).setDuracion(Integer.parseInt(pelicula.get("duracion").toString()));
                    i++;
                }

                JsonArray series = (JsonArray)objJson.get("Series");
                Iterator iteratorSeries = series.iterator();
                while (iteratorSeries.hasNext()) {

                    JsonObject serie = (JsonObject) iteratorSeries.next();
                    audiovisualesAux.set(i, new Series());
                    ((Series) audiovisualesAux.get(i)).setNombre((serie.get("nombre").toString()));
                    
                    ArrayList<Generos> generos = GenerosTXT.bajarGeneros();

	            	Generos g;
	 				Iterator<Generos> gen = generos.iterator();
	 				while (gen.hasNext()) {
	 					g=gen.next();
	 					if(g.getDescripcion().equals(serie.get("genero").toString().trim())) {
	 						
	 						((Series) audiovisualesAux.get(i)).setGenero(g);
	 					}
	 				}

                    JsonArray actoresJSONArray = serie.getJsonArray("actores");
                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                    setActorJSON(actoresArray, actoresJSONArray);

                    ((Series) audiovisualesAux.get(i)).setActores(actoresArray);
                    ((Series) audiovisualesAux.get(i)).setTemporada(Integer.parseInt((serie.get("temporada")).toString()));
                    ((Series) audiovisualesAux.get(i)).setEpisodio(Integer.parseInt((serie.get("episodio")).toString()));
                    ((Series) audiovisualesAux.get(i)).setSinopsis ((serie.get("sinopsis").toString()));
                    i++;
                }

                rdrJson.close();
                fsInJson.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return audiovisualesAux;
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
}
