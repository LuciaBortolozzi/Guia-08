package model.DAO;

import model.Actores;
import model.Audiovisuales;
import model.Peliculas;
import model.Series;

import java.io.*;
import java.util.*;


import javax.json.*;
import javax.json.stream.JsonParser;

public class AudiovisualesJSON {

    private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";

    public void bajarAudiovisuales(ArrayList<Audiovisuales> audiovisuales, TreeSet<Actores> actores) throws Exception {
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
                    ((Peliculas) audiovisuales.get(i)).setGenero((pelicula.get("genero").toString()));

                    JsonArray actoresJSONArray = pelicula.getJsonArray("actores");
                    String[] actoresST = new String[actoresJSONArray.size()];
                    int j = 0;
                    for(JsonValue jsonV : actoresJSONArray) {
                        actoresST[j++] = (jsonV.toString()).replace("\"", "").trim();
                    }
                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                    for(String a: actoresST) {
                        Actores actor;
                        Iterator<Actores> actorIterator = actores.iterator();
                        while (actorIterator.hasNext()) {
                            actor = actorIterator.next();
                            if(actor.getNombre().replace("\"", "").trim().equals(a.replace("\"", "").trim())) {
                                actoresArray.add(actor);
                            }
                        }
                    }
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
                    ((Series) audiovisuales.get(i)).setGenero((serie.get("genero").toString()));

                    JsonArray actoresJSONArray = serie.getJsonArray("actores");
                    String[] actoresST = new String[actoresJSONArray.size()];
                    int j = 0;
                    for(JsonValue jsonV : actoresJSONArray) {
                        actoresST[j++] = (jsonV.toString()).replace("\"", "").trim();
                    }
                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                    for(String a: actoresST) {
                        Actores actor;
                        Iterator<Actores> actorIterator = actores.iterator();
                        while (actorIterator.hasNext()) {
                            actor=actorIterator.next();
                            if(actor.getNombre().replace("\"", "").trim().equals(a.replace("\"", "").trim())) {
                                actoresArray.add(actor);
                            }
                        }
                    }
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
}
