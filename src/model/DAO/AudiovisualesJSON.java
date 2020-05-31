package model.DAO;

import model.*;
import pruebas.File;
import pruebas.FileOutputStream;
import pruebas.JsonArray;
import pruebas.JsonArrayBuilder;
import pruebas.JsonObject;
import pruebas.JsonObjectBuilder;
import pruebas.JsonWriter;

import java.io.*;
import java.util.*;


import javax.json.*;
import javax.json.stream.JsonParser;

public class AudiovisualesJSON {

    //private static final String directorio = "D:\\\\IdeaProjects\\\\Java\\\\Guia-08\\\\src\\\\resources\\";
    private static final String directorio = "C:\\\\Users\\\\Flor\\\\git\\\\Guia-08\\\\src\\\\resources\\";

    public static ArrayList <Audiovisuales> bajarAudiovisualesJSON() throws Exception {

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

    public static void grabarRecomendacionesSeriesJovenesJSON(Audiovisuales audiovisuales) throws Exception{
    	
    	File aJson = new File(directorio + "RecomendacionesSeriesJovenes.json");
        FileOutputStream fsOutJson = new FileOutputStream(aJson);
        JsonWriter wrtJson = Json.createWriter(fsOutJson);

        try {

            JsonObjectBuilder empresa = Json.createObjectBuilder();
     
            empresa.add("Empresa", "PeliSeri");
            
            JsonObjectBuilder serie = Json.createObjectBuilder();
            
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
				
			JsonArray jsonArray = actores.build();
			
			detalleSerie.add("Actores", audiovisuales.getGenero().getDescripcion());
			
			detalleSerie.add("Sinopsos", audiovisuales.getSinopsis());
			
			detalleSerie.add("Temporada", ((Series)audiovisuales).getTemporada());
			
			//LUUUU ACÁ ME VAS A TENER QUE PASAR LA CANTIDAD DE EPISODIOS TAMBIEN
			detalleSerie.add("Episodios", ((Series)audiovisuales).getEpisodio());
			
			
			JsonObject JsonSerie = detalleSerie.build();
			
			empresa.add("Serie", detalleSerie);
            
            for(Audiovisuales audi : audiovisuales) {

                JsonObjectBuilder pasajesJson = Json.createObjectBuilder();
                pasajesJson.add("Numero de Pasaje",reserva.getPasajes().get(i).getNroPasaje());
                pasajesJson.add("Equipaje Extra", reserva.getPasajes().get(i).isEquipajeExtra());
                pasajesJson.add("Tratamiento Preferencial", reserva.getPasajes().get(i).isTratamientoPref());

                JsonObjectBuilder vuelosJson = Json.createObjectBuilder();

                vuelosJson.add("Numero de Vuelo", reserva.getPasajes().get(i).getVuelo().getNumVuelo());
                vuelosJson.add("Cantidad de Horas", reserva.getPasajes().get(i).getVuelo().getCantHoras());
                vuelosJson.add("Partida", reserva.getPasajes().get(i).getVuelo().getPartida());
                vuelosJson.add("Escalas", reserva.getPasajes().get(i).getVuelo().isEscalas());
                vuelosJson.add("Asientos Disponibles", reserva.getPasajes().get(i).getVuelo().getCantAsientosDisp());
                vuelosJson.add("Aerolinea", reserva.getPasajes().get(i).getVuelo().getPrecio().getAerolinea());
                vuelosJson.add("Destino", reserva.getPasajes().get(i).getVuelo().getPrecio().getDestino());
                vuelosJson.add("Franja Horaria", reserva.getPasajes().get(i).getVuelo().getPrecio().getFranjaHoraria());
                vuelosJson.add("Costo", reserva.getPasajes().get(i).getVuelo().getPrecio().getCosto());
                vuelosJson.add("Costo Adicional", reserva.getPasajes().get(i).getVuelo().getPrecio().getCostoAdicional());

                JsonObject vuelosJsonCreado = vuelosJson.build();

                pasajesJson.add("Vuelo", vuelosJsonCreado);

                JsonObjectBuilder pasajerosJson = Json.createObjectBuilder();

                pasajerosJson.add("Numero de Documento", reserva.getPasajes().get(i).getPasajero().getNroDoc());
                pasajerosJson.add("Nombre", reserva.getPasajes().get(i).getPasajero().getNombre());
                pasajerosJson.add("Fecha de Nacimiento", (JsonArrayBuilder) reserva.getPasajes().get(i).getPasajero().getFechaNac());
                pasajerosJson.add("Sexo", reserva.getPasajes().get(i).getPasajero().getSexo());
                pasajerosJson.add("Puntos", reserva.getPasajes().get(i).getPasajero().getPuntos());

                JsonObject pasajerosJsonCreado = pasajerosJson.build();
                pasajesJson.add("Pasajero", pasajerosJsonCreado);

                JsonObject pasajesJsonCreado = pasajesJson.build();
                pasajesArrayJson.add(pasajesJsonCreado);
            }

            JsonArray pasajesArrayJsonCreado = pasajesArrayJson.build();

            reservaJson.add("Numero de Reserva", reserva.getNumReserva());

            reservaJson.add("Operador", reserva.getOperador());
            reservaJson.add("Cantidad de Pasajes Requeridos", reserva.getCantPasajesReq());
            reservaJson.add("Precio Total", reserva.calculoReserva());
            reservaJson.add("Pasajes", pasajesArrayJsonCreado);

            JsonObjectBuilder pasajeroResponsableJson = Json.createObjectBuilder();
            pasajeroResponsableJson.add("Numero de Documento", 2);
            pasajeroResponsableJson.add("Nombre", reserva.getPasajeroResp().getNombre());
            pasajeroResponsableJson.add("Fecha de Nacimiento", (JsonArrayBuilder) reserva.getPasajeroResp().getFechaNac());
            pasajeroResponsableJson.add("Sexo", reserva.getPasajeroResp().getSexo());
            pasajeroResponsableJson.add("Puntos", reserva.getPasajeroResp().getPuntos());

            JsonObject pasajerosResponsableJsonCreado = pasajeroResponsableJson.build();

            reservaJson.add("Pasajero Responsable", pasajerosResponsableJsonCreado);

            JsonObject reservaJsonCreado = reservaJson.build();

            wrtJson.writeObject(reservaJsonCreado);

            wrtJson.close();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            fsOutJson.close();
        }
    	
    }
    
    public static void grabarRecomendacionesPeliculasMayoresJSON() throws Exception{
    	
    	
    }
}
