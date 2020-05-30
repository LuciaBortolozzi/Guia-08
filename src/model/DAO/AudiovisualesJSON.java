package model.DAO;

import model.*;

import java.io.*;
import java.util.*;


import javax.json.*;
import javax.json.stream.JsonParser;

public class AudiovisualesJSON {

    //private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public static void bajarAudiovisuales(ArrayList<Audiovisuales> audiovisualesAux, TreeSet<Actores> actores) throws Exception {
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
                    //((Peliculas) audiovisuales.get(i)).setGenero((pelicula.get("genero").toString()));

                    JsonArray actoresJSONArray = pelicula.getJsonArray("actores");
                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                    setActorJSON(actores, actoresArray, actoresJSONArray);
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
                    //((Series) audiovisuales.get(i)).setGenero((serie.get("genero").toString()));

                    JsonArray actoresJSONArray = serie.getJsonArray("actores");
                    TreeSet<Actores> actoresArray = new TreeSet<Actores>();
                    setActorJSON(actores, actoresArray, actoresJSONArray);

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

    public static ArrayList<Calificaciones> bajarCalificaciones() {

        ArrayList<Calificaciones> calificaciones = new ArrayList<Calificaciones>();

        try {
            File archivo = new File ( directorio + "Calificaciones.txt");
            if (archivo.exists()) {

                Scanner leerArchivoCalificaciones = new Scanner(archivo);
                ArrayList<String> calificacionesST = new ArrayList<String>();

                //Guardar contenido en String
                while (leerArchivoCalificaciones.hasNext()) {
                    String lineaActual = leerArchivoCalificaciones.nextLine();
                    calificacionesST.add(lineaActual);
                }

                // Guardar objetos
                for (String s : calificacionesST) {

                    String[] calificacionST = s.split(";");

                    int estrellas = Integer.parseInt(calificacionST[0]);                                //estrellas
                    String motivo = calificacionST[1];                                                  //motivo
                    Calendar fechaRealizada = convertirAFechaCalendar(calificacionST[2]);               //fechaRealizada
                    int codSuscriptor = Integer.parseInt(calificacionST[3]);                            //suscriptor

                    // calificaciones.add(new Calificaciones(estrellas, motivo, fechaRealizada, suscriptorCtrl.buscarSuscriptor(suscriptores, codSuscriptor)));
                    // audiovisuales.set(new Calificaciones) ?
                    // No hacer new Calificaciones aca porque es por composicion
                }

                leerArchivoCalificaciones.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return calificaciones;
    }

    public static Calendar convertirAFechaCalendar(String f) {
        Calendar fecha = Calendar.getInstance();

        //dd/mm/aaaa
        String[] aux = f.split("/");
        int day = Integer.parseInt(aux[0]);
        int month = Integer.parseInt(aux[1]);
        int year = Integer.parseInt(aux[2]);

        fecha.set(Calendar.DAY_OF_MONTH, day);
        fecha.set(Calendar.MONTH, (month - 1));
        fecha.set(Calendar.YEAR, year);

        return fecha;
    }
}
