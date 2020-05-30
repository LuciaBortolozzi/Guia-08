package model.DAO;

import model.*;

import java.io.*;
import java.util.*;


import javax.json.*;
import javax.json.stream.JsonParser;

public class AudiovisualesJSON {

    //private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public static void bajarAudiovisuales(ArrayList<Audiovisuales> audiovisuales, TreeSet<Actores> actores) throws Exception {
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
                    audiovisuales.set(i, new Peliculas());
                    ((Peliculas) audiovisuales.get(i)).setNombre((pelicula.get("nombre").toString()));
                    ((Peliculas) audiovisuales.get(i)).setAnioFilm(Integer.parseInt((pelicula.get("anio")).toString()));
                    ((Peliculas) audiovisuales.get(i)).setSinopsis ((pelicula.get("sinopsis").toString()));
                    //((Peliculas) audiovisuales.get(i)).setGenero((pelicula.get("genero").toString()));

                    JsonArray actoresJSONArray = pelicula.getJsonArray("actores");
                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                    setActorJSON(actores, actoresArray, actoresJSONArray);
                    ((Peliculas) audiovisuales.get(i)).setActores(actoresArray);
                    ((Peliculas) audiovisuales.get(i)).setDuracion(Integer.parseInt(pelicula.get("duracion").toString()));
                    i++;
                }

                JsonArray series = (JsonArray)objJson.get("Series");
                Iterator iteratorSeries = series.iterator();
                while (iteratorSeries.hasNext()) {

                    JsonObject serie = (JsonObject) iteratorSeries.next();
                    audiovisuales.set(i, new Series());
                    ((Series) audiovisuales.get(i)).setNombre((serie.get("nombre").toString()));
                    //((Series) audiovisuales.get(i)).setGenero((serie.get("genero").toString()));

                    JsonArray actoresJSONArray = serie.getJsonArray("actores");
                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                    setActorJSON(actores, actoresArray, actoresJSONArray);

                    ((Series) audiovisuales.get(i)).setActores(actoresArray);
                    ((Series) audiovisuales.get(i)).setTemporada(Integer.parseInt((serie.get("temporada")).toString()));
                    ((Series) audiovisuales.get(i)).setEpisodio(Integer.parseInt((serie.get("episodio")).toString()));
                    ((Series) audiovisuales.get(i)).setSinopsis ((serie.get("sinopsis").toString()));
                    i++;
                }

                rdrJson.close();
                fsInJson.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setActorJSON(TreeSet<Actores> actores, TreeSet<Actores> actoresArray, JsonArray actoresJSONArray) {

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
