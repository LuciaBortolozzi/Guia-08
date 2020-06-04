package model.DAO;

import model.*;

import java.io.*;
import java.util.*;
import javax.json.*;

public class AudiovisualesJSON {

    private static final String directorio = "D:\\\\IdeaProjects\\\\Guia-08\\\\src\\\\resources\\\\";
//    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public static ArrayList<Audiovisuales> bajarAudiovisualesJSON(ArrayList<Audiovisuales> audiovisualesTXT, TreeSet<Actores> actoresTXT, ArrayList<Generos> generos) {

        Calendar fechaActual = Calendar.getInstance();
        File aJson;
        FileInputStream fsInJson;
        try {
            aJson = new File(directorio + "Audiovisuales.json");
            if (aJson.exists()) {
                fsInJson = new FileInputStream(aJson);
                JsonReader rdrJson = Json.createReader(fsInJson);
                JsonObject objJson = (JsonObject) rdrJson.readObject();
                JsonArray peliculas = (JsonArray) objJson.get("Peliculas");

                Iterator iteratorPeliculas = peliculas.iterator();
                while (iteratorPeliculas.hasNext()) {
                    JsonObject pelicula = (JsonObject) iteratorPeliculas.next();

                    boolean encontrado = false;

                    for (int i = 0; i < audiovisualesTXT.size(); i++) {
                        if (audiovisualesTXT.get(i) instanceof Peliculas) {
                            if ((pelicula.get("nombre").toString()).replace(" ", "").toUpperCase().equals(audiovisualesTXT.get(i).getNombre().replace(" ", "").toUpperCase())) {//MODIFICACION
                                audiovisualesTXT.get(i).setSinopsis((pelicula.get("sinopsis").toString()));
                                audiovisualesTXT.get(i).setFechaPubli(fechaActual);

                                JsonArray actoresJSONArray = pelicula.getJsonArray("actores");
                                TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                                agregarActorJSON(actoresTXT, actoresArray, actoresJSONArray);
                                audiovisualesTXT.get(i).setActores(actoresArray);

                                agregarGenero(audiovisualesTXT, generos, pelicula, i);

                                ((Peliculas) audiovisualesTXT.get(i)).setAnioFilm(Integer.parseInt((pelicula.get("anio")).toString()));
                                ((Peliculas) audiovisualesTXT.get(i)).setDuracion(Integer.parseInt(pelicula.get("duracion").toString()));
                                encontrado = true;
                            }
                        }
                    }

                    if (!encontrado) {
                        //NUEVA PELICULA
                        // CORREGIR
                        audiovisualesTXT.add(new Peliculas());
                        audiovisualesTXT.get(audiovisualesTXT.size()).setNombre((pelicula.get("nombre").toString()));
                        audiovisualesTXT.get(audiovisualesTXT.size()).setSinopsis((pelicula.get("sinopsis").toString()));
                        audiovisualesTXT.get(audiovisualesTXT.size()).setFechaPubli(fechaActual);

                        JsonArray actoresJSONArray = pelicula.getJsonArray("actores");
                        TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                        agregarActorJSON(actoresTXT, actoresArray, actoresJSONArray);
                        audiovisualesTXT.get(audiovisualesTXT.size()).setActores(actoresArray);

                        agregarGenero(audiovisualesTXT, generos, pelicula, audiovisualesTXT.size());

                        ((Peliculas) audiovisualesTXT.get(audiovisualesTXT.size())).setAnioFilm(Integer.parseInt((pelicula.get("anio")).toString()));
                        ((Peliculas) audiovisualesTXT.get(audiovisualesTXT.size())).setDuracion(Integer.parseInt(pelicula.get("duracion").toString()));
                    }
                }

                JsonArray series = (JsonArray) objJson.get("Series");
                Iterator iteratorSeries = series.iterator();
                while (iteratorSeries.hasNext()) {

                    JsonObject serie = (JsonObject) iteratorSeries.next();

                    boolean encontrado = false;

                    for (int i = 0; i < audiovisualesTXT.size(); i++) {
                        if (audiovisualesTXT.get(i) instanceof Peliculas) {
                            if ((serie.get("nombre").toString()).replace(" ", "").toUpperCase().equals(audiovisualesTXT.get(i).getNombre().replace(" ", "").toUpperCase()) &&
                                    Integer.parseInt((serie.get("temporada")).toString()) == ((Series) audiovisualesTXT.get(i)).getTemporada() &&
                                    Integer.parseInt((serie.get("episodio")).toString()) == ((Series) audiovisualesTXT.get(i)).getEpisodio()) {//MODIFICACION
                                audiovisualesTXT.get(i).setSinopsis((serie.get("sinopsis").toString()));
                                audiovisualesTXT.get(i).setFechaPubli(fechaActual);

                                JsonArray actoresJSONArray = serie.getJsonArray("actoresTXT");
                                TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                                agregarActorJSON(actoresTXT, actoresArray, actoresJSONArray);
                                audiovisualesTXT.get(i).setActores(actoresArray);

                                agregarGenero(audiovisualesTXT, generos, serie, i);

                                ((Series) audiovisualesTXT.get(i)).setTemporada(Integer.parseInt((serie.get("temporada")).toString()));
                                ((Series) audiovisualesTXT.get(i)).setEpisodio(Integer.parseInt((serie.get("episodio")).toString()));
                                encontrado = true;
                            }
                        }
                    }

                    if (!encontrado) {
                        //NUEVA SERIE
                        // CORREGIR
                        audiovisualesTXT.add(new Series());
                        audiovisualesTXT.get(audiovisualesTXT.size()).setNombre((serie.get("nombre").toString()));
                        audiovisualesTXT.get(audiovisualesTXT.size()).setSinopsis((serie.get("sinopsis").toString()));
                        audiovisualesTXT.get(audiovisualesTXT.size()).setFechaPubli(fechaActual);

                        JsonArray actoresJSONArray = serie.getJsonArray("actoresTXT");
                        TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                        agregarActorJSON(actoresTXT, actoresArray, actoresJSONArray);
                        audiovisualesTXT.get(audiovisualesTXT.size()).setActores(actoresArray);

                        agregarGenero(audiovisualesTXT, generos, serie, audiovisualesTXT.size());

                        ((Series) audiovisualesTXT.get(audiovisualesTXT.size())).setTemporada(Integer.parseInt((serie.get("temporada")).toString()));
                        ((Series) audiovisualesTXT.get(audiovisualesTXT.size())).setEpisodio(Integer.parseInt((serie.get("episodio")).toString()));
                    }
                }

                rdrJson.close();
                fsInJson.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return audiovisualesTXT;
    }

    private static void agregarGenero(ArrayList<Audiovisuales> audiovisualesTXT, ArrayList<Generos> generos, JsonObject pelicula, int i) {
        Generos genero;
        Iterator<Generos> gen = generos.iterator();
        while (gen.hasNext()) {
            genero = gen.next();
            if (genero.getDescripcion().equals(pelicula.get("genero").toString().toUpperCase().trim())) {

                audiovisualesTXT.get(i).setGenero(genero);
            }
        }
    }

    private static void agregarActorJSON(TreeSet<Actores> actoresTXT, TreeSet<Actores> actoresArray, JsonArray actoresJSONArray) {

        ArrayList<String> actoresST = new ArrayList<String>(actoresJSONArray.size());

        for (JsonValue jsonV : actoresJSONArray) {
            actoresST.add(jsonV.toString().replace("\"", "").trim());
        }

        for (String a : actoresST) {
            Actores actor;
            Iterator<Actores> actorIterator = actoresTXT.iterator();
            while (actorIterator.hasNext()) {
                actor = actorIterator.next();
                String concatenaNombreApellido = actor.getNombre() + actor.getApellido();
                if (concatenaNombreApellido.replace(" ", "").equals(a.replace("\"", "").replace(" ", ""))) {
                    actoresArray.add(actor);
                }
            }
        }
    }

    public static void grabarRecomendacionesSeriesJovenesJSON(Audiovisuales audiovisuales) {

        try {

            File aJson = new File(directorio + "RecomendacionesSeriesJovenes.json");
            FileOutputStream fsOutJson = new FileOutputStream(aJson);
            JsonWriter wrtJson = Json.createWriter(fsOutJson);

            JsonObjectBuilder empresa = Json.createObjectBuilder();

            empresa.add("Empresa", "PeliSeri");

            JsonObjectBuilder detalleSerie = Json.createObjectBuilder();

            detalleSerie.add("Nombre", audiovisuales.getNombre());

            detalleSerie.add("Genero", audiovisuales.getGenero().getDescripcion());

            JsonArrayBuilder actores = Json.createArrayBuilder();
            Actores a;
            Iterator<Actores> act = audiovisuales.getActores().iterator();
            while (act.hasNext()) {
                a = act.next();
                actores.add((JsonValue) a);
            }

            JsonObject actoresJson = (javax.json.JsonObject) actores.build();

            detalleSerie.add("Actores", actoresJson);

            detalleSerie.add("Sinopsis", audiovisuales.getSinopsis());

            detalleSerie.add("Temporada", ((Series) audiovisuales).getTemporada());

            //LUUUU ACÁ ME VAS A TENER QUE PASAR LA CANTIDAD DE EPISODIOS TAMBIEN
            detalleSerie.add("Episodios", ((Series) audiovisuales).getEpisodio());

            int sumaEstrellas = 0;
            int cantidadCalificaciones = 0;
            for (Calificaciones calificacion : audiovisuales.getCalificaciones()) {
                sumaEstrellas += calificacion.getEstrellas();
                cantidadCalificaciones++;
            }

            int calificacion = sumaEstrellas / cantidadCalificaciones;
            detalleSerie.add("Calificacion", calificacion);

            JsonObject JSerie = detalleSerie.build();

            empresa.add("Serie", JSerie);

            JsonObject JsonSerie = empresa.build();

            wrtJson.writeObject(JsonSerie);

            wrtJson.close();

            fsOutJson.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void grabarRecomendacionesPeliculasMayoresJSON(ArrayList<Audiovisuales> audiovisuales) {

        try {

            File aJson = new File(directorio + "RecomendacionesSeriesJovenes.json");
            FileOutputStream fsOutJson = new FileOutputStream(aJson);
            JsonWriter wrtJson = Json.createWriter(fsOutJson);

            JsonObjectBuilder empresa = Json.createObjectBuilder();

            empresa.add("Empresa", "PeliSeri");

            JsonArrayBuilder peliculas = Json.createArrayBuilder();

            for (Audiovisuales audi : audiovisuales) {

                JsonObjectBuilder detallePelicula = Json.createObjectBuilder();

                detallePelicula.add("Nombre", audi.getNombre());

                detallePelicula.add("Genero", audi.getGenero().getDescripcion());

                JsonArrayBuilder actores = Json.createArrayBuilder();
                Actores a;
                Iterator<Actores> act = audi.getActores().iterator();
                while (act.hasNext()) {
                    a = act.next();
                    actores.add((JsonValue) a);
                }

                JsonObject actoresJson = (javax.json.JsonObject) actores.build();

                detallePelicula.add("Actores", actoresJson);

                detallePelicula.add("Sinopsis", audi.getSinopsis());

                detallePelicula.add("Anio", ((Peliculas) audi).getAnioFilm());

                detallePelicula.add("Duracion", ((Peliculas) audi).getDuracion());

                int sumaEstrellas = 0;
                int cantidadCalificaciones = 0;
                for (Calificaciones calificacion : audi.getCalificaciones()) {
                    sumaEstrellas += calificacion.getEstrellas();
                    cantidadCalificaciones++;
                }

                int calificacion = sumaEstrellas / cantidadCalificaciones;
                detallePelicula.add("Calificacion", calificacion);

                JsonObject JPelicula = detallePelicula.build();

                peliculas.add(JPelicula);
            }

            JsonObject JsonPeliculas = (javax.json.JsonObject) peliculas.build();

            empresa.add("Peliculas", JsonPeliculas);

            JsonObject JsonFinal = empresa.build();

            wrtJson.writeObject(JsonFinal);

            wrtJson.close();

            fsOutJson.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
